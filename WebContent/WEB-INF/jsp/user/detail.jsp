<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/jb-common.tld" prefix="jb" %>
<html:errors/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <title>察看用户</title>
	<link rel="stylesheet" type="text/css" href="styles.css" >

  </head>

  <body class="main">
  <html:form action="user" method="post">
  	<html:hidden property="o" value="doAssignRole" />
  	<html:hidden property="item.usrId" />
  	<html:hidden property="item.usrFlag" />
  		<span style="font-weight:bold;">用户信息</span>
		<table class="input_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<td class="input_title">用户名</td>
				<td class="input_content">
					${userForm.item.usrName }&nbsp;
				</td>
				<td class="input_title">密码</td>
				<td class="input_content">
					******			
				</td>
			</tr>
			<tr>
				<td class="input_title">角色</td>
				<td class="input_content">
					${userForm.item.usrRole.roleName==null?"未指定":userForm.item.usrRole.roleName }&nbsp;			
				</td>
				<td class="input_title">状态</td>
				<td class="input_content">
					${userForm.item.usrFlagString }&nbsp;
				</td>
			</tr>
		</table>
		<div class="button_bar">
			<button onclick="javascript:history.go(-1);">返    回</button>
		</div>
	</html:form>	
  </body>
</html:html>
