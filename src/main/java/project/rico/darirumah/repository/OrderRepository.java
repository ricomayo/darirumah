package project.rico.darirumah.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import project.rico.darirumah.config.AppConstant;
import project.rico.darirumah.config.AppProperties;
import project.rico.darirumah.util.QueryTools;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class OrderRepository {

    @Autowired
    @Qualifier(AppConstant.BEAN_JDBC_MASTERDATA_POSTGRES)
    private JdbcTemplate dbpostgre;

    @Autowired
    @Qualifier(AppConstant.BEAN_APP_CONFIG)
    private AppProperties appProperties;

    String TABLE_ORDER = "order";

    public String placeOrder(int idUser, String productCode, int qty, String destName, String destAddress) {
        List<Object> parameter = new ArrayList<>();
        String sql = "SELECT * FROM " + AppProperties.SCHEMA + ".f_placeorder(?,?,?,?,? )";

        parameter.add(String.valueOf(idUser));
        parameter.add(productCode);
        parameter.add(String.valueOf(qty));
        parameter.add(destName);
        parameter.add(destAddress);

        Object[] myObj = parameter.toArray();
        return dbpostgre.queryForObject(sql, myObj, String.class);
    }

    public String finishOrder(int idOrder,int idUser,int idProduct){
        List<Object> parameter = new ArrayList<>();
        String sql = "SELECT * FROM " + AppProperties.SCHEMA + ".f_finishorder(?,?,? )";

        parameter.add(idOrder);
        parameter.add(idUser);
        parameter.add(idProduct);

        Object[] myObj = parameter.toArray();
        return dbpostgre.queryForObject(sql, myObj, String.class);
    }

    public String cancelOrder(int idOrder,int idUser,int idProduct){
        List<Object> parameter = new ArrayList<>();
        String sql = "SELECT * FROM " + AppProperties.SCHEMA + ".f_cancelorder(?,?,? )";

        parameter.add(idOrder);
        parameter.add(idUser);
        parameter.add(idProduct);

        Object[] myObj = parameter.toArray();
        return dbpostgre.queryForObject(sql, myObj, String.class);
    }
}
