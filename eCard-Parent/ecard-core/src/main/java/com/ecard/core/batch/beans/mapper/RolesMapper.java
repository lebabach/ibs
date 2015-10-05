package com.ecard.core.batch.beans.mapper;

import com.ecard.core.batch.beans.Roles;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author vinhla
 */
public class RolesMapper implements RowMapper<Roles>{
    public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
        Roles role = new Roles();
        role.setRoleName(rs.getString("role_name"));
        role.setEmail(rs.getString("email"));
        
        return role;
    }
}
