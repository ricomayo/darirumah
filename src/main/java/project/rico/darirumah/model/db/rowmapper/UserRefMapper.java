package project.rico.darirumah.model.db.rowmapper;


import org.springframework.jdbc.core.RowMapper;
import project.rico.darirumah.model.db.UserRef;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRefMapper implements RowMapper<UserRef> {
    @Override
    public UserRef mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserRef data = new UserRef();
        data.setIduser(rs.getString("id_user"));
        data.setUsername(rs.getString("username"));
        data.setName(rs.getString("name"));
        data.setPassword(rs.getString("password"));
        data.setAddress(rs.getString("address"));
        data.setHandphone(rs.getString("handphone"));
        data.setAccess(rs.getString("access"));

        return data;
    }
}
