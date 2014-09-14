<%-- 
    Document   : newstudent
    Created on : Aug 20, 2014, 4:52:33 PM
    Author     : OBENNY
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>{{operation}} Student</title>
    </head>
    <body>
        <h1>Hello World! {{operation}}  {{loc}} ID: {{sid}} NAME: {{studentName}}</h1>
        <div style="width:600px" id="studentForm">
                                                        <form id="{{operation}}Form" name="addEditForm" action="{{act}}" method="post">
                                                            <fieldset>
                                                                <legend><strong>{{operation}} Student</strong></legend>
                                                                <br>
                                                                <table width="500" border="0" align="center">
                                                                    <tr>
                                                                        <td align="center"><table width="370" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#F0F0F0">
                                                                                <tr>
                                                                                    <td><p><input type="hidden" name="category" value="${admin.category}"></p></td>
                                                                                    <td><p><input type="hidden" name="addcategory" value="3"></p></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Username:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="uname" id="uname" style="background-color:#F0F0F0; border-color:#291F53" value="{{student.uname}}" required ng-minlength="6"></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Student Id:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="studentId" id="stdid" style="background-color:#F0F0F0; border-color:#291F53" value="{{sid}}" required ng-maxlength="5"></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>First Name:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="firstName" id="fname" style="background-color:#F0F0F0; border-color:#291F53" value="{{fName}}" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Last Name:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="lastName" id="lname"  style="background-color:#F0F0F0; border-color:#291F53" value="{{lName}}" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Department:</strong></td>
                                                                                    
                                                                                    <td>
                                                                                        <select name="department" style="background-color:#F0F0F0; border-color:#291F53">
                                                                                            <option ng-repeat="dept in allDepts.departmentDAO" value="{{dept.deptId}}">{{dept.deptName}}</option>
                                                                                        </select>
                                                                                    </td>
                                                                                </tr>
                                                                                
                                                                                <tr>
                                                                                    <td><strong>Advisor:</strong></td>
                                                                                    <td>
                                                                                        <select name="advisor" style="background-color:#F0F0F0; border-color:#291F53">
                                                                                            <c:forEach var="adv" items="${advisors}">
                                                                                                <option value="${adv}">${adv}</option>
                                                                                            </c:forEach>
                                                                                        </select>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Date Of Birth(YYYY-MM-DD):</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="dob" id="dob"  style="background-color:#F0F0F0; border-color:#291F53" value="{{dob}}" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Address:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="address" id="add"  style="background-color:#F0F0F0; border-color:#291F53" value="{{address}}" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Phone:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="phone" id="phone"  style="background-color:#F0F0F0; border-color:#291F53" value="{{phone}}" required ng-minlength="10" ng-maxlength="10"></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Email:</strong></td>
                                                                                    <td>
                                                                                        <input type="email" name="email" id="email" style="background-color:#F0F0F0; border-color:#291F53" value="{{email}}" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td align="right"></td>
                                                                                    <td><input type="submit" name="addEditButton" id="addEditStudent" onclick="submit()" value="{{operation}} Student" > 
                                                                                        &nbsp;</td>
                                                                                </tr>
                                                                            </table></td>
                                                                    </tr>
                                                                </table>
                                                                <br>
                                                            </fieldset>
                                                        </form></div>
    </body>
</html>
