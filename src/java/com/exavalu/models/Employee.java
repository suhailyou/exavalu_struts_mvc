package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String gender;
    private String age;
    private String roleId;
    private String departmentId;
    private String roleName;
    private String departmentName;
    private String basicSalary;
    private String carAllowance;
    private String isDeleted;

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(String basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getCarAllowance() {
        return carAllowance;
    }

    public void setCarAllowance(String carAllowance) {
        this.carAllowance = carAllowance;
    }

    private int status;

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public ApplicationMap getMap() {
        return map;
    }

    public void setMap(ApplicationMap map) {
        this.map = map;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String doSearch() throws Exception {

        String result = "FAILURE";
        ArrayList empList = EmployeeService.getInstance().doSearchEmployee(this);
        ArrayList depList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();
        //System.out.println("depList size::"+depList.size());
        //System.out.println("roleList size::"+roleList.size());
        if (true) {

            sessionMap.put("EmpListSearchPage", empList);
            sessionMap.put("DepListSearchPage", depList);
            sessionMap.put("RoleListSearchPage", roleList);
            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

        }
        return result;
    }

    public String dogetEmpDetail() throws Exception {

        String result = "FAILURE";
        Employee emp = EmployeeService.getEmployee(this);
        sessionMap.put("EmployeeToEdit", emp);

        ArrayList depList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();
        sessionMap.put("DepListEditPage", depList);
        sessionMap.put("RoleListEditPage", roleList);

        if (true) {

            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

        }
        return result;
    }

    public String doSaveUpdate() throws Exception {

        String result = "FAILURE";
        boolean success = EmployeeService.updateEmployee(this);

        if (success) {
            String updateMsg = "employeeId :: " + this.getEmployeeId() + " :: updated";
            sessionMap.put("UpdateMsg", updateMsg);
            
            ArrayList empList = new ArrayList();
            empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpListHome", empList);
            
            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

        }
        return result;
    }
    
    public String doDelete() throws Exception {

        String result = "FAILURE";
        boolean success = EmployeeService.deleteEmployee(this);

        if (success) {
            String updateMsg = "employeeId :: " + this.getEmployeeId() + " :: deleted successfully!";
            sessionMap.put("UpdateMsg", updateMsg);
            
            ArrayList empList = new ArrayList();
            empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpListHome", empList);
            
            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

        }
        return result;
    }

    public String doCreate() throws Exception {

        String result = "FAILURE";

        boolean success = EmployeeService.getInstance().doCreateEmployee(this);
        ArrayList depList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();
        sessionMap.put("DepListCreatePage", depList);
        sessionMap.put("RoleListCreatePage", roleList);
        //System.out.println("depList size::"+depList.size());
        //System.out.println("roleList size::"+roleList.size());
        if (success) {
//             sessionMap.put("DepList", depList);
//             sessionMap.put("RoleList", roleList);
            String createdMsg = "Employee created successfully!!";
            sessionMap.put("CreatedMsgForCreateEmployee", createdMsg);
            ArrayList empList = new ArrayList();
            empList = EmployeeService.getInstance().getAllEmployees();
            sessionMap.put("EmpListHome", empList);

            System.out.println("returning Success from doSearch method");
            result = "SUCCESS";

        } else {
            System.out.println("returning Failure from doSearch method");
        }
        return result;
    }

}
