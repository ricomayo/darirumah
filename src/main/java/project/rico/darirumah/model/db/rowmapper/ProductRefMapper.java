package project.rico.darirumah.model.db.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import project.rico.darirumah.model.db.ProductRef;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductRefMapper implements RowMapper<ProductRef> {
    @Override
    public ProductRef mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductRef data = new ProductRef();
        data.setProductCode(rs.getString("productcode"));
        data.setProductName(rs.getString("productname"));
        data.setSupplier(rs.getString("supplier"));
        data.setType(rs.getString("type"));
        data.setUom(rs.getString("uom"));
        return data;
    }
}
