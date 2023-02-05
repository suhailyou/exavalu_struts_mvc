
package com.exavalu.models;

import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author LenovoRaja
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {
    
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
    
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();
    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();
    
     @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
        sessionMap.put("Loggedin", this);
    }
     
     public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = LoginService.getInstance().doLogin(this);

        if (success) {
             ArrayList empList = new ArrayList();
             empList = EmployeeService.getInstance().getAllEmployees();
             sessionMap.put("EmpListHome",empList);
             sessionMap.put("Loggedin", this);
            
             
            System.out.println("returning Success from doLogin method");
            result = "SUCCESS";
            
        } else {
            System.out.println("returning Failure from doLogin method");
        }

        return result;
    }
     
    public String doLogout(){
         String result = "SUCCESS";
         sessionMap.clear();
         return result;
     }
    
    public String doSignUp(){
         String result = "FAILURE";
         boolean success = LoginService.getInstance().doSignUp(this);
         if (success) {
             String createmsg = "User created !";
             sessionMap.put("Createmsg", createmsg);
           
            System.out.println("returning Success from doLogin method");
            result = "SUCCESS";
            
        } else {
            System.out.println("returning Failure from doLogin method");
        }
         return result; 
    }
     
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
