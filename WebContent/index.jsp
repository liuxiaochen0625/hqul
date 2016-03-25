<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
if ("1".equals(request.getParameter("error"))){
%>
<script>
	alert("用户名或密码不正确。");
</script>
<%
}else if ("2".equals(request.getParameter("error"))){
%>
<script>
	alert("请先登录系统。");
</script>
<%
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>index.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">    
  </head>  
  <body>
  	<html:form action="user" method="post">
  	<html:hidden property="o" value="doLogin" />
	<table>
		<tr>
			<td>用户名</td>
			<td><html:text property="item.usrName" /> </td>
		</tr>
		<tr>
			<td>密码</td>			
			<td><html:password property="item.usrPassword" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><html:submit>  登   录  </html:submit></td>
		</tr>
	</table>
	</html:form>
  </body>
</html:html>
