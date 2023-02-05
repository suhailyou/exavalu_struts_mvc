/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;


import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * My random commit
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {
    
    public static EmployeeService employeeService = null;
    
    public static EmployeeService getInstance()
    {
        if(employeeService==null)
        {
            return new EmployeeService();
        }
        else
        {
            return employeeService;
        }
    }
    
    public ArrayList getAllEmployees()
    {
        ArrayList empList = new ArrayList();
        String sql = "SELECT * FROM employeedb.employees JOIN departments ON employees.departmentId =departments.departmentId join role on employees.roleId = role.roleId WHERE isDeleted=0;";
            
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next())
            {
                Employee emp = new Employee();
                
               
                
                emp.setEmployeeId(rs.getString("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setAddress(rs.getString("address"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age")); 
                emp.setPhone(rs.getString("phone"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));
                
                
                
                empList.add(emp);
            }
            
            
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.err.println("Total rows:"+empList.size());
        return empList;
    }
    
        public static Employee getEmployee(Employee emp) {

        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, departments d, role r "
                    + "where e.departmentId=d.departmentId and e.roleId=r.roleId and  e.employeeId =? AND isDeleted=0";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, emp.getEmployeeId());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getString("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhone(rs.getString("phone"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentId(rs.getString("departmentId"));
                emp.setRoleId(rs.getString("roleId"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return emp;

    }
         public static boolean updateEmployee(Employee emp) {

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employeedb.employees\n"
                    + "SET\n"
                    + "firstName = ? ,\n"
                    + "lastName = ? ,\n"
                    + "phone = ? ,\n"
                    + "address = ? ,\n"
                    + "gender = ? ,\n"
                    + "age = ? ,\n"
                    + "basicSalary = ? ,\n"
                    + "carAllowance = ? , \n"
                    + "departmentId = ? ,\n"
                    + "roleId = ? \n"
                    + "WHERE employeeId = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setDouble(7, Double.parseDouble(emp.getBasicSalary()));
            preparedStatement.setDouble(8, Double.parseDouble(emp.getCarAllowance()));
            preparedStatement.setInt(9, Integer.parseInt(emp.getDepartmentId()));
            preparedStatement.setInt(10, Integer.parseInt(emp.getRoleId()));

            preparedStatement.setInt(11, Integer.parseInt(emp.getEmployeeId()));

            //System.out.println("sql=" + preparedStatement);

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    } 
      public static boolean deleteEmployee(Employee emp) {

        boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "UPDATE employeedb.employees SET isDeleted = 1 WHERE employeeId = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getEmployeeId());
           

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
        
     public ArrayList doSearchEmployee(Employee emp) {
            
        ArrayList empList = new ArrayList();
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "select * from employees e, departments d, role r where e.departmentId=d.departmentId and e.roleId=r.roleId having firstName like ? and lastName like ? and gender like ? and departmentName like ? and roleName like ? AND isDeleted=0;";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            
            preparedStatement.setString(1, emp.getFirstName() + "%");
            preparedStatement.setString(2, emp.getLastName() + "%");
            preparedStatement.setString(3, emp.getGender() + "%");
            preparedStatement.setString(4, emp.getDepartmentName() + "%");
            preparedStatement.setString(5, emp.getRoleName() + "%");
//            System.out.println("sql"+preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
//            System.out.println("size of rs="+ rs.getFetchSize());
             
            while (rs.next()) {
                emp = new Employee();
                emp.setEmployeeId(rs.getString("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setGender(rs.getString("gender"));
                emp.setAddress(rs.getString("address"));
                emp.setAge(rs.getString("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setPhone(rs.getString("phone"));
                emp.setBasicSalary(rs.getString("basicSalary"));
                emp.setCarAllowance(rs.getString("carAllowance"));
                
                empList.add(emp);
            }


        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return empList;
    }
     
     public boolean doCreateEmployee(Employee emp) {
         
         boolean result = false;
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "INSERT INTO employees (firstName,lastName,phone,address,gender,age,departmentId,roleId,basicSalary,carAllowance) VALUES (? ,? ,? ,?,?,?,?,?,?,? )";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhone());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setString(6, emp.getAge());
            preparedStatement.setString(7, emp.getDepartmentId());
            preparedStatement.setString(8, emp.getRoleId());
            preparedStatement.setString(9, emp.getBasicSalary());
            preparedStatement.setString(10, emp.getCarAllowance());
            System.out.println("sql:"+preparedStatement);

            int row = preparedStatement.executeUpdate();
            if(row==1) {
                result=true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
}
