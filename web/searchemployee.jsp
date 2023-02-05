
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@page import="com.exavalu.empweb.entities.Role"%>--%>
<%--<%@page import="com.exavalu.empweb.entities.Department"%>--%>
<%--<%@page import="com.exavalu.empweb.entities.Employee"%>--%>
<%--<%@page import="java.util.Iterator"%>--%>
<%--<%@page import="java.util.ArrayList"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${Loggedin==null}">
    <c:redirect url="landingPage.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/product.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <div class="d-flex align-items-center justify-content-center mt-3">
                <form action="SearchEmployee" method="get">
                    <table class="table table-borderless">
                        <tr>
                            <td>
                                <div class="form-outline">
                                    <input type="text" id="form1" class="form-control" placeholder="first name" name="firstName"/>
                                </div>
                            </td>
                            <td>
                                <div class="form-outline">
                                    <input type="text" id="form1" class="form-control" placeholder="last name" name="lastName"/>
                                </div>
                            </td>
                            <td>
                                <div class="form-outline">
                                    <select name="gender"class="form-select">
                                        <option value="">select gender</option>
                                        <option value="male">male</option>
                                        <option value="female">female</option>
                                    </select>
                                </div>
                            </td>
                            <td>

                                <div class="form-outline">

                                    <select name="departmentName" class="form-select" id="departmetnId">

                                        <option value="">select a department</option>

                                    <c:forEach items="${DepListSearchPage}" var="dep">
                                        <option value="${dep.departmentName}">
                                            ${dep.departmentName}
                                        </option>
                                    </c:forEach>

                                </select>
                            </div>
                        </td>
                        <td>
                            <div class="form-outline">
                                <select name="roleName" class="form-select" id="roleId">
                                    <option value="">select a Role</option>


                                    <c:forEach items="${RoleListSearchPage}" var="role">

                                        <option value="${role.roleName}">
                                            ${role.roleName}
                                        </option>

                                    </c:forEach>

                                </select>
                            </div>
                        </td>
                        <td>
                            <button type="submit" class="btn btn-outline-success">
                                search
                            </button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>


        <div class='table-responsive'>

            <table class="table table-bordered table-hover">
                <thead>
                    <tr class="table-striped table-primary">
                        <th scope="col">Employee Id</th>
                        <th scope="col">First Name</th>
                        <th scope="col">Last Name</th>
                        <th scope="col">Address</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Age</th>
                        <th scope="col">Department</th>
                        <th scope="col">Role</th>
                        <th scope="col">Basic Salary</th>
                        <th scope="col">Car Allowance</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${EmpListSearchPage}" var="emp">
                        <tr>
                            <th scope="row">${emp.employeeId}</th>

                            <td>
                                ${emp.firstName}
                            </td>
                            <td>
                                ${emp.lastName}
                            </td>
                            <td>
                                ${emp.address}
                            </td>
                            <td>
                                ${emp.phone}
                            </td>
                            <td>
                                ${emp.gender}
                            </td>
                            <td>
                                ${emp.age}
                            </td>
                            <td>
                                ${emp.departmentName}
                            </td>
                            <td>
                                ${emp.roleName}
                            </td>
                            <td>
                                ${emp.basicSalary}
                            </td>
                            <td>
                                ${emp.carAllowance}
                            </td>
                            <td> 
                                <a href='EditEmployee?employeeId=${emp.employeeId}'>
                                    <button class="btn-primary">Edit</button>
                                </a>
                                <a href='DeleteEmployee?employeeId=${emp.employeeId}'>
                                    <button class="btn-dark">Delete</button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
