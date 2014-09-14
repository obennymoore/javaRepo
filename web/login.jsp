<%-- 
    Document   : login
    Created on : Aug 12, 2014, 2:46:47 AM
    Author     : OBENNY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detech DLI Login</title>
<link rel="stylesheet" type="text/css" href="dli.css" />
<style type="text/css">
body {
	background-color: #D7D7D7;
}
</style>
</head>

<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="450" align="center" cellpadding="0" cellspacing="0" style="border-collapse:collapse" bordercolor="#999999">
  <tr>
    <td bgcolor="#FFFFFF"><table width="450" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td><img src="images/login_top.jpg" width="450" height="100" /></td>
      </tr>
    </table>
      <br />
      <table width="430" border="0" align="center">
        <tr>
          <td align="center" class="reg"><strong class="blue">Login Below to DLI  Portal </strong></td>
        </tr>
      </table>
      <form id="loginForm" name="form1" action="login" method="POST">
        <table width="300" border="0" align="center" cellpadding="4" cellspacing="3">
          <tr>
            <td colspan="2">&nbsp;</td>
          </tr>
          <tr>
            <td><strong>Username:</strong></td>
            <td><label for="textfield">
              <input type="text" name="userid" id="txtUsername" />
            </label></td>
          </tr>
          <tr>
            <td><strong>Password:</strong></td>
            <td><input type="password" name="password" id="pwdPassword" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input type="image" name="imageField" id="imageField" src="images/login_button.jpg" />
            &nbsp;</td>
          </tr>
          <tr>
            
              <td colspan="2" align="center" style="color:red"><p><i>${message}</i></p></td>
          </tr>
        </table>
        <br />
      </form>
      <hr size="1" noshade="noshade" color="#D7D7D7" />
      <p>&nbsp;</p></td>
    <td bgcolor="#FFFFFF"><img src="images/login_icon.jpg" width="332" height="332"  alt=""/></td>
  </tr>
</table>
<map name="Map">
  <area shape="rect" coords="228,314,372,349" href="login.jsp">
</map>
</body>
</html>
