package project.rico.darirumah.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.db.UserRef;
import project.rico.darirumah.model.db.rowmapper.UserRefMapper;
import project.rico.darirumah.util.QueryTools;
import project.rico.darirumah.util.StringTools;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    @Qualifier(AppConstant.BEAN_JDBC_MASTERDATA_POSTGRES)
    private JdbcTemplate dbpostgre;

    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    String table_user = "mst_user";

    private final String QUERY_SELECT = "SELECT id_user,username,name, password, address,handphone,access FROM ";

    public List<UserRef> getLogin(String username) {
        List<Object> parameter = new ArrayList<>();
        StringBuilder sql = QueryTools.buildQuery(QUERY_SELECT, table_user);

        sql.append(" WHERE 1=1");
        sql.append(" AND username = ? ");

        parameter.add(username);

        Object[] myObj = parameter.toArray();
        log.debug("[{}][{}]", sql, myObj);
        return dbpostgre.query(sql.toString(), myObj, new UserRefMapper());
    }

    public String insertUser(String username, String name, String password, String address, String handphone) {
        List<Object> parameter = new ArrayList<>();
        String sql = "select * from " + AppProperties.SCHEMA + ".f_insertuser(?,?,?,?,? )";

        parameter.add(username);
        parameter.add(name);
        parameter.add(password);
        parameter.add(address);
        parameter.add(handphone);

        Object[] myObj = parameter.toArray();
        return dbpostgre.queryForObject(sql, myObj, String.class);
    }

    public int updateData(int idUser, String password, String name, String address, String handphone) {
        StringBuilder sql = QueryTools.buildQuery("UPDATE ", table_user);

        sql.append(" set ");
        if (!StringTools.isEmptyOrNull(name)) {
            sql.append(" name = '" + name + "' ");
            if (!StringTools.isEmptyOrNull(address) || !StringTools.isEmptyOrNull(handphone)) {
                sql.append(",");
            }
        }

        if (!StringTools.isEmptyOrNull(address)) {
            sql.append(" address = '" + address + "' ");
            if (!StringTools.isEmptyOrNull(handphone)) {
                sql.append(",");
            }
        }

        if (!StringTools.isEmptyOrNull(handphone)) {
            sql.append(" handphone = '" + handphone + "' ");
        }

        sql.append(" where id_user = ? ");
        sql.append(" and password = ? ");
        System.out.println("QUERY DATA =" + sql);
        return dbpostgre.update(sql.toString(), idUser, password);
    }

    public String updatePassword(int idUser, String oldPassword, String newPassword) {
        List<Object> parameter = new ArrayList<>();
        String sql = "select * from " + AppProperties.SCHEMA + ".f_updatepassword(?,?,? )";

        parameter.add(idUser);
        parameter.add(oldPassword);
        parameter.add(newPassword);

        Object[] myObj = parameter.toArray();
        System.out.println("QUERY PASS = " + sql);
        return dbpostgre.queryForObject(sql, myObj, String.class);
    }

    public int getAccessLevel (int idUser){
        List<Object> parameter = new ArrayList<>();
        StringBuilder sql = QueryTools.buildQuery(" SELECT access FROM ", table_user);
        sql.append(" WHERE id_user = ? ");
        parameter.add(idUser);

        Object[] myObj = parameter.toArray();
        String response = dbpostgre.queryForObject(sql.toString(), myObj, String.class);
        return Integer.valueOf(response);
    }

}
