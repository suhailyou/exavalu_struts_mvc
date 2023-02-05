
package com.exavalu.services;

import com.exavalu.models.Role;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author LenovoRaja
 */
public class RoleService {

    public static ArrayList getAllRole() {
        ArrayList roleList = new ArrayList();

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "SELECT * FROM employeedb.role;";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                
                Role role = new Role();
                role.setRoleId(rs.getString("roleId"));
                role.setRoleName(rs.getString("roleName"));
                roleList.add(role);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return roleList;
        
        }
        
}

