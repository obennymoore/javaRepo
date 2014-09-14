<%-- 
    Document   : instructor
    Created on : Aug 12, 2014, 2:49:30 AM
    Author     : OBENNY
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detech D.L.I Instructor</title>
        <link rel="stylesheet" type="text/css" href="dli.css" />
<link rel="stylesheet" type="text/css" href="jquery/css/ui-lightness/jquery-ui-1.10.4.custom.css">

  <script type="text/javascript" src="jquery/js/jquery-1.10.2.js"></script>
  <script type="text/javascript" src="jquery/js/jquery-ui-1.10.4.custom.js"></script>
	

<style type="text/css">
body {
	background-color: #D7D7D7;
}
</style>

<script>

function enable()
  {
  document.getElementById("instrfname").disabled=false;
  instrfname.style.border = "solid";
  document.getElementById('instrfname').style.borderColor='#291F53';
  
  document.getElementById("instrlname").disabled=false;
  instrlname.style.border = "solid";
  document.getElementById('instrlname').style.borderColor='#291F53';
  
//  document.getElementById("instrdob").disabled=false;
//  instrdob.style.border = "solid";
//  document.getElementById('instrdob').style.borderColor='#291F53';

//  document.getElementById("instrdept").disabled=false;
//  instrdept.style.border = "solid";
//  document.getElementById('instrdept').style.borderColor='#291F53';
  
  document.getElementById("instradd").disabled=false;
  instradd.style.border = "solid";
  document.getElementById('instradd').style.borderColor='#291F53';
  
  document.getElementById("instrphone").disabled=false;
 instrphone.style.border = "solid";
  document.getElementById('instrphone').style.borderColor='#291F53';
  
  document.getElementById("instremail").disabled=false;
  instremail.style.border = "solid";
  document.getElementById('instremail').style.borderColor='#291F53';
  
  document.getElementById("instrupdate").disabled=false;
  
  document.getElementById("instredit").disabled=true;
 
  
  
  }
  
</script>





<script>
function addClassFunc(){
  document.getElementById("courseInformation").style.display = "";
  document.getElementById("addClass").disabled=true;
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
$( "#accordion" ).accordion({
icons: icons,
collapsible: true
});
$( "#toggle" ).button().click(function() {
if ( $( "#accordion" ).accordion( "option", "icons" ) ) {
$( "#accordion" ).accordion( "option", "icons", null );
} else {
$( "#accordion" ).accordion( "option", "icons", icons );
}
});
});


//<-- end!--> 
		 
  </script>                     
</head>

<body>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="805" bgcolor="#F0F0F0"><table width="800" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td><img src="images/banner.jpg" width="800" height="120"  alt=""/></td>
      </tr>
    </table>
      <table width="795" border="0" align="center">
        <tr>
          <td><strong>Welcome: ${instr.firstName}</strong></td>
          <td>&nbsp;</td>
          <td align="right"> <strong><a href="logout">Logout</a></strong></td>
        </tr>
      </table>
      <br>
      <table width="400" border="0" align="center">
        <tr>
          <td align="center" class="goldmain"><strong>INSTRUCTOR PORTAL</strong></td>
        </tr>
      </table>
      <p align="center" style="color:red"><i>${message}</i></p>
      <table width="610" border="0" align="center">
        <tr>
          <td><!-- Accordion -->
<div id="accordion">
	<h3>Instructor Management Information</h3>
	<div><table width="600" border="0" align="center">
        <tr>
        <td>
        
        <form id="form1" name="form1" action="update" method="post">
        <fieldset>
        <legend><strong>Instructor Management  Information </strong></legend>
          <p></p>
          <table width="370" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#F0F0F0">
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td><strong>First Name:</strong></td>
              <td>
                <input type="text" name="firstName" value="${instr.firstName}" id="instrfname" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Last Name:</strong></td>
              <td>
                <input type="text" name="lastName" value="${instr.lastName}" id="instrlname" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Date Of Birth:</strong></td>
              <td>
                <input type="text" name="dob" value="${instr.dob}" id="instrdob" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Address:</strong></td>
              <td>
                <input type="text" name="address" value="${instr.address}" id="instradd" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Department:</strong></td>
              <td>
                <input type="text" name="dept" value="${instr.dept}" id="instrdept" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Phone:</strong></td>
              <td>
                <input type="text" name="phone" value="${instr.phone}" id="instrphone" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Email:</strong></td>
              <td>
                <input type="text" name="email" value="${instr.email}" id="instremail" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td align="right"><input type="button" name="edit" id="instredit" value="Edit" onClick="enable();"></td>
              <td><input type="button" name="update" id="instrupdate" onclick="submit()" value="Update" disabled> 
                &nbsp;</td>
            </tr>
          </table>
          <p><input type="hidden" name="category" value="${instr.category}"></p>
          </fieldset>
        </form></td>
      </tr>
  </table></div>
    <h3>Course Management Information</h3>
	<div>
    
    <table width="600" border="0" align="center">
      <tr>
        <td><div style="width:600px;">
        <form id="form2" name="form2" method="post">
        <fieldset>
        <legend><strong>Class Records</strong></legend>
        <br>
        <table width="500" border="0" align="center">
          <tr>
            <td align="center">${instrClasses}</td>
          </tr>
        </table>
        <br>
        </fieldset>
        </form></div></td>
        
        </tr>
    </table>
    <input type="button" name="addClass" id="addClass" value="Add Course" onClick="addClassFunc();">
    <table width="600" border="0" align="center">
      <tr>
        <td><div style="width:600px; display:none" id="courseInformation">
        <form id="form2" name="form2" action="add" method="post">
        <fieldset>
        <legend><strong>Add New Class</strong></legend>
        <br>
        <table width="500" border="0" align="center">
          <tr>
            <td align="center"><table width="370" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#F0F0F0">
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td><strong>Course name: </strong></td>
              <td>
                  <select name="courseId" style="background-color:#F0F0F0; border-color:#291F53">
                    <c:forEach var="course" items="${courseList}">
                        <option value="${course.courseId}">${course.courseId}</option>
                    </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td><strong>Student:</strong></td>
              <td>
                  <select name="studentId" style="background-color:#F0F0F0; border-color:#291F53">
                    <c:forEach var="student" items="${studentList}">
                        <option value="${student}">${student}</option>
                    </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td align="right"></td>
              <td><input type="button" name="addClass" onclick="submit()" id="addClassRecord" value="Add Course" > 
                &nbsp;</td>
            </tr>
          </table></td>
          </tr>
        </table>
        <p><input type="hidden" name="category" value="${instr.category}"></p>
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
