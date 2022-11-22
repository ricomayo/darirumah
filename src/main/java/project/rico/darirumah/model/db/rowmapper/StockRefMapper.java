package project.rico.darirumah.model.db.rowmapper;


import org.springframework.jdbc.core.RowMapper;
import project.rico.darirumah.model.db.StockRef;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockRefMapper implements RowMapper<StockRef> {
    @Override
    public StockRef mapRow(ResultSet rs, int rowNum) throws SQLException {
        StockRef data = new StockRef();
        data.setIdStock(rs.getString("id_stock"));
        data.setIdProduct(rs.getString("id_product"));
        data.setProductCode(rs.getString("productcode"));
        data.setQtyFree(rs.getString("qty_free"));
        data.setQtyBook(rs.getString("qty_booked"));
        data.setUom(rs.getString("uom"));
        return data;
    }
}
