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
import project.rico.darirumah.model.login.LoginRs;
import project.rico.darirumah.util.QueryTools;

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

String table_name = "mst_user";

    private final String QUERY_SELECT = "SELECT id_user,username,name, password, address,handphone,access ";

    public List<UserRef> getLogin(String username){
        List<Object> parameter = new ArrayList<>();
        StringBuilder sql = QueryTools.buildQuery(QUERY_SELECT,table_name);

        sql.append(" WHERE 1=1");
        sql.append(" AND username = ? ");

        parameter.add(username);

        Object[] myObj = parameter.toArray();
        log.debug("[{}][{}]", sql, myObj);
        return dbpostgre.query(sql.toString(), myObj, new UserRefMapper());
    }


}
