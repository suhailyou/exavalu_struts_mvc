

<%--<%@page import="com.exavalu.empweb.entities.Role"%>
<%@page import="com.exavalu.empweb.entities.Department"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${Loggedin==null}">
    <c:redirect url="landingPage.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Employee</title>
        <!-- Custom styles for this template -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <link href="css/product.css" rel="stylesheet">
        <link href="css/createemployee.css" rel="stylesheet">

    </head>
    <style>
        body {
            background-image: url(https://www.exavalu.com/wp-content/uploads/2019/05/bnrLgo1.jpg);
            background-repeat: repeat;
            background-size: auto;
        }
    </style>
    <body>
        <jsp:include page="menu.jsp"></jsp:include>
            <section>

                <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                    <div class="container h-100">
                        <div class="row d-flex justify-content-center align-items-center h-100">
                            <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                                <div class="card" style="border-radius: 15px;">
                                    <div class="card-body p-5">
                                        <h2 class="text-uppercase text-center mb-5">Create a new Employee</h2>

                                        <!--      to display message that employee is created-->


                                        <form action="CreateEmployee" method="post">

                                            <div class="form-outline mb-4">
                                                <input type="text" id="first" class="form-control form-control-lg" name="firstName"
                                                       minlength="3" />
                                                <label class="form-label" for="first">First Name</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="text" id="last" class="form-control form-control-lg" name="lastName"
                                                       minlength="3"/>
                                                <label class="form-label" for="last">last name</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="text" id="phoneNo" class="form-control form-control-lg" placeholder="10 digit phone no" name="phone"  pattern="[0-9]{10}"required/>
                                                <label class="form-label" for="phoneNo">phone</label>
                                            </div>

                                            <div class="form-outline mb-4">
                                                <input type="text" id="addr" class="form-control form-control-lg" name="address" maxlength="16"/>
                                                <label class="form-label" for="addr">Address</label>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <select class="form-control form-select" name="gender">
                                                    <option value="0" disabled>select gender</option>
                                                    <option value="male">male</option>
                                                    <option value="female">female</option>
                                                </select>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <input type="point" id="ageid" class="form-control form-control-lg" name="age" min="18" max="88" />
                                                <label class="form-label" for="ageid">Age</label>
                                            </div>
                                            <div class="form-outline mb-4">
                                                <select name="departmentId" class="form-control form-select" id="departmetnId">
                                                    <option value="0" disabled>select a department</option>

                                                <c:forEach items="${DepListCreatePage}" var="dep">
                                                    <option value="${dep.departmentId}">
                                                        ${dep.departmentName}
                                                    </option>
                                                </c:forEach>

                                            </select>
                                            <label class="form-label" >Department Select</label>
                                        </div>

                                        <div class="form-outline mb-4">

                                            <select name="roleId" class="form-control form-select" id="roleId" onchange="changeCarAllowanceInput()">
                                                <option value="0" disabled>select a Role</option>

                                                <c:forEach items="${RoleListCreatePage}" var="role">
                                                    <option value="${role.roleId}">
                                                        ${role.roleName}
                                                    </option>
                                                </c:forEach>

                                            </select>
                                            <label class="form-label" >Role Select</label>
                                        </div>
                                        <div class="form-outline mb-4">
                                            <input type="text" id="basicSalaryid" class="form-control form-control-lg" name="basicSalary"/>
                                            <label class="form-label" for="basicSalaryid">Basic Salary</label>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input type="text" id="carAllowanceid" class="form-control form-control-lg" name="carAllowance"                                                         value="0"/>
                                            <label class="form-label" for="carAllowanceid">Car Allowance</label>
                                        </div>

                                        <div class="d-flex justify-content-evenly">
                                            <button type="submit"
                                                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Register</button>
                                            <button type="reset"
                                                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Reset</button>

                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>
            function changeCarAllowanceInput() {
                var selectBox = document.getElementById("roleId");
                var selectedValue = parseInt(selectBox.options[selectBox.selectedIndex].value);
                document.getElementById('carAllowanceid').readOnly = selectedValue === 3 ? true : false;
            }
        </script>
    </body>

</html>
