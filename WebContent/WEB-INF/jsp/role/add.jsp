<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jb-common.tld" prefix="jb" %>
<script src="validate.js" ></script>
<script src="icommon.js" ></script>
<html:errors/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <title>增加用户</title>
	<link rel="stylesheet" type="text/css" href="styles.css" >
	<script>
		function doDel(id){
			if ( window.confirm("确定删除？") ){
				window.location.href = "?o=doDel&id=" + id;
			}
		}
	</script>
  </head>

  <body class="main">
  <html:form action="role" method="post">
  	<html:hidden property="o" value="doAdd" />
  		<span style="font-weight:bold;">添加角色</span>
		<table class="input_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<td class="input_title">角色名</td>
				<td class="input_content">
					<html:text property="item.roleName" />
				</td>
				<td class="input_title">描述</td>
				<td class="input_content">
					<html:text property="item.roleDesc" />			
				</td>
			</tr>
			<tr>
				<td class="input_title">角色</td>
				<td class="input_content">
					未指定
				</td>
				<td class="input_title">状态</td>
				<td class="input_content">
					正常<html:hidden property="item.roleFlag" value="1" />
				</td>
			</tr>
		</table>
		<div class="button_bar">
			<button onclick="javascript:doSubmit('doAdd');">保    存</button>
		</div>
		<script>
		build_validate("item.roleName","角色名不能为空","Limit","1","50");
		</script>
	</html:form>	
  </body>
</html:html>
