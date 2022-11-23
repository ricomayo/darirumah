package project.rico.darirumah.repository;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.model.db.StockRef;
import project.rico.darirumah.model.db.rowmapper.StockRefMapper;
import project.rico.darirumah.util.QueryTools;
import project.rico.darirumah.util.StringTools;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class StockRepository {
    @Autowired
    @Qualifier(AppConstant.BEAN_JDBC_MASTERDATA_POSTGRES)
    private JdbcTemplate dbpostgre;

    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    String TABLE_STOCK = "stock";
    String TABLE_USER = "mst_user";

    private final String QUERY_SELECT = "SELECT id_stock,id_product,productcode, qty, uom  FROM ";

    public List<StockRef> getStock(String productCode, String productName, String type) {

        StringBuilder sql = QueryTools.buildQuery(QUERY_SELECT, TABLE_STOCK);

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

        return dbpostgre.query(sql.toString(), new StockRefMapper());
    }

    public String insertStock(int idUser, String productCode, int qty) {
        List<Object> parameter = new ArrayList<>();
        String sql = "SELECT * FROM " + AppProperties.SCHEMA + ".f_insertstock(?,?,?)";

        parameter.add(idUser);
        parameter.add(productCode);
        parameter.add(String.valueOf(qty));

        Object[] myObj = parameter.toArray();
        return dbpostgre.queryForObject(sql, myObj, String.class);
    }

    public int updateStock(int idUser, int idStock, int idProduct, int qty) {
        StringBuilder sql = QueryTools.buildQuery("UPDATE ", TABLE_STOCK);

        sql.append(" set qty_free = ? ");
        StringBuilder add = QueryTools.buildQuery(" , updated_by = (SELECT username FROM ", TABLE_USER);
        sql.append(add);
        sql.append(" WHERE id_user =? ) ");
        sql.append(" , updated_date = now() ");

        sql.append(" WHERE id_stock = ? ");
        sql.append(" AND id_product = ? ");

        return dbpostgre.update(sql.toString(), qty, idUser, idStock, idProduct);
    }

    public int deleteStock() {
        StringBuilder sql = QueryTools.buildQuery("DELETE FROM ", TABLE_STOCK);

        sql.append(" WHERE qty_free = 0 AND qty_booked = 0 ;");

        return dbpostgre.update(sql.toString());
    }

}
