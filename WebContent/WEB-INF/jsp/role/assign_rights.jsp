<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jb-common.tld" prefix="jb" %>
<script src="validate.js" ></script>
<script src="icommon.js" ></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <title>角色管理 - 分配权限</title>
	<link rel="stylesheet" type="text/css" href="styles.css" >
	<script>
		function save(){

			doSubmit('doAssignRights');
		}
	</script>
  </head>
  
  <body class="main">
  <html:form action="role">
  	<html:hidden property="o" value="doAssignRights" />
  	<html:hidden property="item.roleId" />
		<table class="input_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<td class="input_title" width="10%">角色名</td>
				<td class="input_content" width="20%">
					${roleForm.item.roleName }&nbsp;
				</td>
				<td class="input_title" width="10%">角色描述</td>
				<td class="input_content" width="20%">
					${roleForm.item.roleDesc }&nbsp;
				</td>
				<td class="input_title" width="10%">状态</td>
				<td class="input_content" width="20%">
					${roleForm.item.roleFlagString }&nbsp;
				</td>				
			</tr>
			<tr>
				<td class="input_title" >权限</td>
				<td class="input_content" colspan="5" style="text-align:right;">
					<button onclick="javascript:history.go(-1);">取    消</button>
					<button onclick="javascript:save();">保    存</button>
				</td>				
			</tr>
			<tr>
				<td class="input_content" colspan="10" valign="top"> 
		<%-- 显示所有权限 --%>
		<logic:iterate id="right" name="ALL_RIGHTS_LIST" type="org.jb.y272.team0.entity.SysRight">
			<div>
				<% 
					for(int i=0; i< right.getRightCode().length();++i){
						out.print("&nbsp;&nbsp;&nbsp;");
					}
				%>
				${right.rightText } 
				<input name="selectedRights" type="checkbox" ${right.isSelected } value="${right.rightCode }" />
			</div>
		</logic:iterate>
				</td>
			</tr>
		</table>
	</html:form>	
  </body>
</html:html>
