<%-- 
    Document   : admin
    Created on : Aug 12, 2014, 2:48:31 AM
    Author     : OBENNY
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DLI Admin Portal</title>

        <link rel="stylesheet" type="text/css" href="dli.css" />
        <link rel="stylesheet" type="text/css" href="jquery/css/ui-lightness/jquery-ui-1.10.4.custom.css">

        <script type="text/javascript" src="jquery/js/jquery-1.10.2.js"></script>



        <script type="text/javascript" src="jquery/js/angular.min.js"></script>
        <script type="text/javascript" src="jquery/js/angular-route.min.js"></script>
        <script type="text/javascript" src="jquery/js/angular-resource.min.js"></script>
        <script type="text/javascript" src="jquery/js/jquery-ui-1.10.4.custom.js"></script>

        <style type="text/css">
            body {

                background-color: #D7D7D7;

            }
        </style>

        <script>

            var myApp = angular.module("myapp", ['ngRoute', 'ngResource']);

            myApp.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {

                    $locationProvider.html5Mode(true);

                    $routeProvider.
                            when('/addStudent', {
                                templateUrl: 'newstudent.jsp',
                                controller: 'NewStudentController'
                            }).
                            when('/edit', {
                                templateUrl: 'newstudent.jsp',
                                controller: 'EditStudentController'
                            }).
                            otherwise({
                                redirectTo: '/'
                            });

                }]);

myApp.controller("AdminController", function($scope) {
    
    $scope.delete = function(event){
        //alert("in the event handler!!! ");
        if(confirm("Are you sure?"))
        {
            event.stopPropagation();
        }
        
    };
    
    alert("in my admin controller!");
});

            myApp.controller("NewStudentController", function($scope, $location, $resource) {

                
                $scope.operation = "New";
                $scope.act = "add";
                $scope.loc = $location.absUrl();
                var departmentRestService = $resource('/DeTechDLI/rest/dept/all');
                departmentRestService.get(function(depts) {
                    $scope.allDepts = depts;
                });


                //alert("in my new student controller");

            });

            myApp.controller("EditStudentController", function($scope, $resource, $location) {

                $scope.student = {
                    fName: '',
                    lName: '',
                    address: '',
                    dob: '',
                    uname: '',
                    phone: '',
                    email: '',
                    sid: '',
                    advisor: '',
                    dept: '',
                };
                $scope.operation = "Edit";
                $scope.act = "update";
                $scope.loc = $location.absUrl();
                $scope.sid = ($location.search()).id;
                var studentRestService = $resource('/DeTechDLI/rest/student/' + $scope.sid);
                studentRestService.get(function(st) {
                    $scope.student.fName = st.firstName;
                    $scope.student.lName = st.lastName;
                    $scope.student.address = st.address;
                    $scope.student.email = st.email;
                    $scope.student.phone = st.phone;
                    $scope.student.uname = st.uName;
                    $scope.student.sid = st.studentId;
                    $scope.student.dob = st.dob;
                    $scope.student.advisor = st.advisor;
                    $scope.student.dept = st.dept;
                });
                //$scope.message = Student.get();
                //alert("in my edit student controller");
            });
// 

        </script>

        <script>

            function enable()
            {
                document.getElementById("afname").disabled = false;
                afname.style.border = "solid";
                document.getElementById('afname').style.borderColor = '#291F53';
                document.getElementById("alname").disabled = false;
                alname.style.border = "solid";
                document.getElementById('alname').style.borderColor = '#291F53';
                document.getElementById("adob").disabled = false;
                adob.style.border = "solid";
                document.getElementById('adob').style.borderColor = '#291F53';
                document.getElementById("aadd").disabled = false;
                aadd.style.border = "solid";
                document.getElementById('aadd').style.borderColor = '#291F53';
                document.getElementById("aphone").disabled = false;
                aphone.style.border = "solid";
                document.getElementById('aphone').style.borderColor = '#291F53';
                document.getElementById("aemail").disabled = false;
                aemail.style.border = "solid";
                document.getElementById('aemail').style.borderColor = '#291F53';
                document.getElementById("aupdate").disabled = false;
                document.getElementById("aedit").disabled = true;
            }

        </script>

        <script>
            $(document).ready(
                    //        function(){
                            //          $( "#accordion" ).accordion();
                                    //          
                                            //          }
                                                    //          );  



                                                            function() {
                                                                var icons = {
                                                                    header: "ui-icon-circle-arrow-e",
                                                                    activeHeader: "ui-icon-circle-arrow-s"
                                                                };
                                                                $("#accordion").accordion({
                                                                    icons: icons,
                                                                    collapsible: true,
                                                                    heightStyle: "content"
                                                                });
                                                                $("#toggle").button().click(function() {
                                                                    if ($("#accordion").accordion("option", "icons")) {
                                                                        $("#accordion").accordion("option", "icons", null);
                                                                    } else {
                                                                        $("#accordion").accordion("option", "icons", icons);
                                                                    }
                                                                });
                                                                
                                                            });
                                                    //<-- end!-->


        </script> 
        <base href="/DeTechDLI/" />
    </head>
    <body ng-app="myapp" >

        <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td width="805" bgcolor="#F0F0F0"><table width="800" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="images/banner.jpg" width="800" height="120"  alt=""/></td>
                        </tr>
                    </table>
                    <table width="795" border="0" align="center">
                        <tr>
                            <td><strong>Welcome: ${admin.firstName}</strong></td>
                            <td>&nbsp;</td>
                            <td align="right"> <strong><a href="logout">Logout</a></strong></td>
                        </tr>
                    </table>
                    <br>
                    <table width="400" border="0" align="center">
                        <tr>
                            <td align="center" class="goldmain"><strong>ADMIN PORTAL</strong></td>
                        </tr>
                    </table>
                    <p align="center" style="color:red"><i>${message}</i></p>
                    <table width="610" border="0" align="center">
                        <tr>
                            <td><!-- Accordion -->
                                <div id="accordion">
                                    <h3>Admin Management Information</h3>
                                    <div><table width="600" border="0" align="center">
                                            <tr>
                                                <td>

                                                    <form id="form1" name="form1" action="update" method="post">
                                                        <fieldset>
                                                            <legend><strong>Admin Management  Information </strong></legend>
                                                            <p></p>
                                                            <table width="370" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#F0F0F0">
                                                                <tr>
                                                                    <td>&nbsp;</td>
                                                                    <td>&nbsp;</td>
                                                                </tr>
                                                                <tr>
                                                                    <td><strong>First Name:</strong></td>
                                                                    <td>
                                                                        <input type="text" name="firstName" id="afname" value="${admin.firstName}" disabled style="background-color:#F0F0F0; border:none"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td><strong>Last Name:</strong></td>
                                                                    <td>
                                                                        <input type="text" name="lastName" id="alname" value="${admin.lastName}" disabled style="background-color:#F0F0F0; border:none"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td><strong>Date Of Birth:</strong></td>
                                                                    <td>
                                                                        <input type="text" name="dob" id="adob" value="${admin.dob}" disabled style="background-color:#F0F0F0; border:none"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td><strong>Address:</strong></td>
                                                                    <td>
                                                                        <input type="text" name="address" id="aadd" value="${admin.address}" disabled style="background-color:#F0F0F0; border:none"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td><strong>Phone:</strong></td>
                                                                    <td>
                                                                        <input type="text" name="phone" id="aphone" value="${admin.phone}" disabled style="background-color:#F0F0F0; border:none"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td><strong>Email:</strong></td>
                                                                    <td>
                                                                        <input type="text" name="email" id="aemail" value="${admin.email}" disabled style="background-color:#F0F0F0; border:none"></td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right"><input type="button" name="edit" id="aedit" value="Edit" onClick="enable();"></td>
                                                                    <td><input type="button" name="update" id="aupdate" value="Update" onclick="submit()" disabled> 
                                                                        &nbsp;</td>
                                                                </tr>
                                                            </table>
                                                            <p><input type="hidden" name="category" value="${admin.category}"></p>
                                                        </fieldset>
                                                    </form></td>
                                            </tr>
                                        </table></div>
                                    <h3>Student Management Information</h3>
                                    <div ng-controller="AdminController">



                                        <table width="600" border="0" align="center">
                                            <tr>
                                                <td><div style="width:600px;">
                                                        
                                                            <fieldset>
                                                                <legend><strong>Student Record</strong></legend>
                                                                <br>
                                                                <table width="500" border="0" align="center">
                                                                    <tr>
                                                                        <td align="center">${students}</td>
                                                                    </tr>
                                                                </table>
                                                                <br>
                                                            </fieldset>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                        <table width="600" border="0" align="center">
                                            <tr>
                                                <td><div ng-view></div></td>
                                            </tr>
                                        </table>
                                    </div>
                                    <h3>Instructor Management Information</h3>
                                    <div>

                                        <table width="600" border="0" align="center">
                                            <tr>
                                                <td><div style="width:600px;">
                                                        <form id="form2" name="form2" method="post">
                                                            <fieldset>
                                                                <legend><strong>Instructor Record</strong></legend>
                                                                <br>
                                                                <table width="500" border="0" align="center">
                                                                    <tr>
                                                                        <td align="center">${instructors}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><p><input type="hidden" name="remcategory" value="2"></p></td>
                                                                    </tr>
                                                                </table>
                                                                <br>
                                                            </fieldset>
                                                        </form></div></td>

                                            </tr>
                                        </table>
                                        <input type="button" name="addInstructor" id="addInstructor" value="Add Instructor" ng-click="showInstructorForm = !showInstructorForm">
                                        <table width="600" border="0" align="center">
                                            <tr>
                                                <td><div style="width:600px;" ng-show="showInstructorForm">
                                                        <form id="addInstructorForm" name="addInstructorForm" action="add" method="post">
                                                            <fieldset>
                                                                <legend><strong>Add New Instructor</strong></legend>
                                                                <br>
                                                                <table width="500" border="0" align="center">
                                                                    <tr>
                                                                        <td align="center"><table width="370" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#F0F0F0">
                                                                                <tr>
                                                                                    <td><p><input type="hidden" name="category" value="${admin.category}"></p></td>
                                                                                    <td><p><input type="hidden" name="addcategory" value="2"></p></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Username:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="uname" id="iuname"  style="background-color:#F0F0F0; border-color:#291F53" required ng-minlength="6"></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Instructor Id:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="instructorId" id="instid"  style="background-color:#F0F0F0; border-color:#291F53" required ng-maxlength="5"></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>First Name:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="firstName" id="fname"  style="background-color:#F0F0F0; border-color:#291F53" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Last Name:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="lastName" id="lname"  style="background-color:#F0F0F0; border-color:#291F53" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Department:</strong></td>
                                                                                    <td>
                                                                                        <select name="department" style="background-color:#F0F0F0; border-color:#291F53">
                                                                                            <c:forEach var="dept" items="${departments}">
                                                                                                <option value="${dept.deptId}">${dept.deptName}</option>
                                                                                            </c:forEach>
                                                                                        </select>
                                                                                    </td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Salary:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="salary" id="sal"  style="background-color:#F0F0F0; border-color:#291F53" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Date Of Birth(YYYY-MM-DD):</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="dob" id="dob"  style="background-color:#F0F0F0; border-color:#291F53" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Address:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="address" id="add"  style="background-color:#F0F0F0; border-color:#291F53" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Phone:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="phone" id="phone"  style="background-color:#F0F0F0; border-color:#291F53" required ng-minlength="10" ng-maxlength="10"></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Email:</strong></td>
                                                                                    <td>
                                                                                        <input type="email" name="email" id="email" style="background-color:#F0F0F0;" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td align="right"></td>
                                                                                    <td><input type="submit" name="addInstructor" id="addInstructorRecord" ng-disabled="!addInstructorForm.$valid" ng-click="add(addInstructorForm)" value="Add Instructor" > 
                                                                                        &nbsp;</td>
                                                                                </tr>
                                                                            </table></td>
                                                                    </tr>
                                                                </table>
                                                                <br>
                                                            </fieldset>
                                                        </form></div></td>

                                            </tr>
                                        </table>

                                    </div>
                                    <h3>Course Management Information</h3>
                                    <div>

                                        <table width="600" border="0" align="center">
                                            <tr>
                                                <td><div style="width:600px;">
                                                        <form id="form2" name="form2" action="add" method="post">
                                                            <fieldset>
                                                                <legend><strong>Course Record</strong></legend>
                                                                <br>
                                                                <table width="500" border="0" align="center">
                                                                    <tr>
                                                                        <td align="center">${courses}</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td><p><input type="hidden" name="remcategory" value="0"></p></td>
                                                                    </tr>
                                                                </table>
                                                                <br>
                                                            </fieldset>
                                                        </form></div></td>

                                            </tr>
                                        </table>
                                        <input type="button" name="addCourse" id="addCourse" value="Add Course" ng-click="showCourseForm = !showCourseForm">
                                        <table width="600" border="0" align="center">
                                            <tr>
                                                <td><div style="width:600px;" ng-show="showCourseForm">
                                                        <form id="addCourseForm" name="addCourseForm" action="add" method="post">
                                                            <fieldset>
                                                                <legend><strong>Add New Course</strong></legend>
                                                                <br>
                                                                <table width="500" border="0" align="center">
                                                                    <tr>
                                                                        <td align="center"><table width="370" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#F0F0F0">
                                                                                <tr>
                                                                                    <td><p><input type="hidden" name="category" value="${admin.category}"></p></td>
                                                                                    <td><p><input type="hidden" name="addcategory" value="0"></p></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Course Id:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="courseId" id="courseId"  style="background-color:#F0F0F0; border-color:#291F53"required ng-minlength="3" ></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Course name: </strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="courseName" id="courseName"  style="background-color:#F0F0F0; border-color:#291F53" required></td>
                                                                                </tr>
                                                                                <tr>
                                                                                    <td><strong>Course Description:</strong></td>
                                                                                    <td>
                                                                                        <input type="text" name="courseDesc" id="desc"  style="background-color:#F0F0F0; border-color:#291F53" required></td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td><strong>Credits:</strong></td>
                                                                                    <td>
                                                                                        <select name="credits" style="background-color:#F0F0F0; border-color:#291F53">
                                                                                            <option value="0" selected>0</option>
                                                                                            <option value="1">1</option>
                                                                                            <option value="2">2</option>
                                                                                            <option value="3">3</option>
                                                                                            <option value="4">4</option>
                                                                                        </select>
                                                                                    </td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td><strong>Department:</strong></td>
                                                                                    <td>
                                                                                        <select name="department" style="background-color:#F0F0F0; border-color:#291F53">
                                                                                            <c:forEach var="dept" items="${departments}">
                                                                                                <option value="${dept.deptId}">${dept.deptName}</option>
                                                                                            </c:forEach>
                                                                                        </select>
                                                                                    </td>
                                                                                </tr>

                                                                                <tr>
                                                                                    <td align="right"></td>
                                                                                    <td><input type="submit" name="addCourse" id="addCourseRecord" ng-disabled="!addCourseForm.$valid" ng-click="add(addCourseForm)" value="Add Course" > 
                                                                                        &nbsp;</td>
                                                                                </tr>
                                                                            </table></td>
                                                                    </tr>
                                                                </table>
                                                                <br>
                                                            </fieldset>
                                                        </form></div></td>

                                            </tr>
                                        </table>

                                    </div>
                                </div>&nbsp;</td>
                        </tr>
                    </table>
                    <p><br>
                    </p>
                    <table width="790" height="28" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#291F53">
                        <tr>
                            <td width="364" height="22" bgcolor="#291F53" class="white"><strong> &nbsp;&nbsp;Copyright © 2014 DLI All Rights Reserved.</strong></td>
                            <td width="213">&nbsp;</td>
                            <td width="177"><strong><span class="white">Powered By Team Group</span></strong></td>
                        </tr>
                    </table></td>
            </tr>
        </table>

    </body>
</html>
