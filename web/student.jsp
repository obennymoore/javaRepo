<%-- 
    Document   : student
    Created on : Aug 12, 2014, 2:48:49 AM
    Author     : OBENNY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detech DLI Student</title>
<link rel="stylesheet" type="text/css" href="dli.css" />

<style type="text/css">
body {
	background-color: #D7D7D7;
}
</style>

<script>

function enable()
  {
  document.getElementById("fname").disabled=false;
  fname.style.border = "solid";
  document.getElementById('fname').style.borderColor='#291F53';
  
  document.getElementById("lname").disabled=false;
  lname.style.border = "solid";
  document.getElementById('lname').style.borderColor='#291F53';
  
  document.getElementById("add").disabled=false;
  add.style.border = "solid";
  document.getElementById('add').style.borderColor='#291F53';
  
  document.getElementById("phone").disabled=false;
  phone.style.border = "solid";
  document.getElementById('phone').style.borderColor='#291F53';
  
  document.getElementById("email").disabled=false;
  email.style.border = "solid";
  document.getElementById('email').style.borderColor='#291F53';
  
  document.getElementById("update").disabled=false;
  
  document.getElementById("edit").disabled=true;
 
  
  
  }
  
</script>

<script>
function courseFunc(){
  document.getElementById("courseinfo").style.display = "";
  
}


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
          <td><strong>Welcome: ${student.firstName} </strong></td>
          <td>&nbsp;</td>
          <td align="right"> <strong><a href="logout">Logout</a></strong></td>
        </tr>
      </table>
      <br>
      <table width="400" border="0" align="center">
        <tr>
          <td align="center" class="goldmain"><strong>STUDENT PORTAL</strong></td>
        </tr>
      </table>
      <br>
      <table width="600" border="0" align="center">
        <tr>
        <td>
        
        <form id="form1" name="form1" action="update" method="post">
        <fieldset>
        <legend><strong>Student Management  Information </strong></legend>
          <p></p>
          <table width="370" border="0" align="center" cellpadding="4" cellspacing="3" bgcolor="#F0F0F0">
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td><strong>Student Id:</strong></td>
              <td>
                <input type="text" name="studentId" id="stdid" value="${student.studentId}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>First Name:</strong></td>
              <td>
                <input type="text" name="firstName" id="fname" value="${student.firstName}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Last Name:</strong></td>
              <td>
                <input type="text" name="lastName" id="lname" value="${student.lastName}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Department:</strong></td>
              <td>
                  <input type="text" name="dept" id="dep" value="${student.dept}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Advisor:</strong></td>
              <td>
                  <input type="text" name="advisor" id="dep" value="${student.advisor}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Date Of Birth:</strong></td>
              <td>
                <input type="text" name="dob" id="dob" value="${student.dob}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Address:</strong></td>
              <td>
                <input type="text" name="address" id="add" value="${student.address}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Phone:</strong></td>
              <td>
                <input type="text" name="phone" id="phone" value="${student.phone}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td><strong>Email:</strong></td>
              <td>
                <input type="text" name="email" id="email" value="${student.email}" size="35" disabled style="background-color:#F0F0F0; border:none"></td>
            </tr>
            <tr>
              <td align="right"><input type="button" name="edit" id="edit" value="Edit" onClick="enable();"></td>
              <td><input type="button" name="update" id="update" value="Update" onClick="submit()" disabled> 
                &nbsp;<input type="button" name="viewCourse" id="viewCourse" value="View Courses" onClick="courseFunc();"></td>
            </tr>
            <tr>
                <td colspan="2" align="center" style="color:red"><p><i>${message}</i></p></td>
            </tr>
          </table>
            <p><input type="hidden" name="category" value="${student.category}"></p>
          </fieldset>
        </form></td>
      </tr>
  </table>
    <p>&nbsp;</p>
    <table width="600" border="0" align="center">
      <tr>
        <td><div style="width:600px; display:none" id="courseinfo">
        <form id="form2" name="form2" method="post">
        <fieldset>
        <legend><strong>Student Course Information</strong></legend>
        <br>
        <table width="500" border="0" align="center">
          <tr>
            <td align="center"> ${courses}</td>
          </tr>
        </table>
        <br>
        </fieldset>
        </form></div></td>
        
        </tr>
    </table>
    
  
    <p>&nbsp;</p>
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
