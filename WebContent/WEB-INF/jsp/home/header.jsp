<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jb-common.tld" prefix="jb" %>
<script src="validate.js" ></script>
<script src="icommon.js" ></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <title>欢迎使用！</title>
	<link rel="stylesheet" type="text/css" href="styles.css" >
  </head>
  <body style="background-image:url(../images/header-bg.jpg);margin:0px;text-align:left;scroll:no;border-bottom:solid 6px #6FBAF1;scroll:no;">
 	<table width="100%">
 		<tr>
 			<td>
 	<img src="../images/logo.gif" />
 	<span style="font-size:33px;font-weight:bold;">
 		&nbsp;信息管理系统
 	</span>
 			</td>
 			<td style="text-align:right;" valign="bottom">
 	<b>当前登录用户:</b> ${sessionScope.USER.usrName }(${sessionScope.USER.usrRole.roleName }) [<a href="user.do?o=doLogout">注销</a>]&nbsp;&nbsp;<br />
&nbsp;
 			</td>
 		</tr>
 	</table>	
  </body>
</html:html>
