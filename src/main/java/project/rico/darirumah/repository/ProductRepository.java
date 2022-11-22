package project.rico.darirumah.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.db.ProductRef;
import project.rico.darirumah.model.db.UserRef;
import project.rico.darirumah.model.db.rowmapper.ProductRefMapper;
import project.rico.darirumah.model.db.rowmapper.UserRefMapper;
import project.rico.darirumah.util.QueryTools;
import project.rico.darirumah.util.StringTools;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class ProductRepository {
    @Autowired
    @Qualifier(AppConstant.BEAN_JDBC_MASTERDATA_POSTGRES)
    private JdbcTemplate dbpostgre;

    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    String table_product = "mst_product";
    String table_user = "mst_user";

    private final String QUERY_SELECT = "SELECT id_product,productcode, productname, supplier, uom, type  FROM ";

    public List<ProductRef> getProduct(String productCode, String productName, String type, boolean supplierFlag, int idUser) {

        StringBuilder sql = QueryTools.buildQuery(QUERY_SELECT, table_product);

        sql.append(" WHERE 1=1");
        if (!StringTools.isEmptyOrNull(productCode)) {
            sql.append(" AND productcode = '" + productCode + "' ");
        }
        if (!StringTools.isEmptyOrNull(productName)) {
            sql.append(" AND productname = '" + productName + "' ");
        }
        if (!StringTools.isEmptyOrNull(type)) {
            sql.append(" AND type = '" + type + "' ");
        }

        if(supplierFlag){
            StringBuilder add = QueryTools.buildQuery(" AND supplier = (SELECT name FROM ", table_user);
            sql.append(add);
            sql.append(" WHERE id_user = "+idUser+" ) ");
        }

        return dbpostgre.query(sql.toString(), new ProductRefMapper());
    }

    public String insertProduct(int idUser, String productCode, String productName, String uom, String type) {
        List<Object> parameter = new ArrayList<>();
        String sql = "select * from " + AppProperties.SCHEMA + ".f_insertproduct(?,?,?,?,? )";

        parameter.add(idUser);
        parameter.add(productCode);
        parameter.add(productName);
        parameter.add(uom);
        parameter.add(type);

        Object[] myObj = parameter.toArray();
        return dbpostgre.queryForObject(sql, myObj, String.class);
    }

    public int updateProduct(int idUser, String productCode, String productName, String uom, String type) {
        StringBuilder sql = QueryTools.buildQuery("UPDATE ", table_product);

        sql.append(" set ");
        if (!StringTools.isEmptyOrNull(productName)) {
            sql.append(" productname = '" + productName + "' ");
            if (!StringTools.isEmptyOrNull(uom) || !StringTools.isEmptyOrNull(type)) {
                sql.append(",");
            }
        }

        if (!StringTools.isEmptyOrNull(uom)) {
            sql.append(" uom = '" + uom + "' ");
            if (!StringTools.isEmptyOrNull(type)) {
                sql.append(",");
            }
        }

        if (!StringTools.isEmptyOrNull(type)) {
            sql.append(" type = '" + type + "' ");
        }

        if (!StringTools.isEmptyOrNull(productName) || !StringTools.isEmptyOrNull(uom) || !StringTools.isEmptyOrNull(type)) {
            StringBuilder add = QueryTools.buildQuery(" updated_by = (SELECT username FROM ", table_user);
            sql.append(add);
            sql.append(" WHERE id_user =? ) ");
        }

        sql.append(" where productcode = ? ");
        StringBuilder add = QueryTools.buildQuery(" AND supplier = (SELECT name FROM ", table_user);
        sql.append(add);
        sql.append(" WHERE id_user =? ) ");

        System.out.println("QUERY DATA =" + sql);
        return dbpostgre.update(sql.toString(), idUser, productCode, idUser);
    }

    public int deleteProduct(int idUser, String productCode) {
        StringBuilder sql = QueryTools.buildQuery("DELETE FROM ", table_product);

        sql.append(" WHERE productcode = ? ");
        StringBuilder add = QueryTools.buildQuery(" AND supplier = (SELECT name FROM ", table_user);
        sql.append(add);
        sql.append(" WHERE id_user =? ) ");

        return dbpostgre.update(sql.toString(), productCode, idUser);
    }
}
