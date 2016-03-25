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
    <title>角色管理</title>
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
  <html:form action="role">
  	<html:hidden property="o" value="toList" />
  	<span style="font-weight:bold;">查询条件</span>
		<table class="input_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<td class="input_title">角色名</td>
				<td class="input_content">
					<html:text property="item.roleName" />
				</td>
				<td class="input_title">状态</td>
				<td class="input_content">
					<html:select property="item.roleFlag">
						<html:option value="-1">全部</html:option>
						<html:option value="1">正常</html:option>
						<html:option value="0">已删除</html:option>
					</html:select>				
				</td>
				<td class="input_content" colspan="2" style="text-align:right;">
					<button onclick="javascript:doSubmit('toList');">查    询</button>
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<span style="font-weight:bold;">单据列表</span>
		<table class="data_table" border="0" cellPadding="3" cellSpacing="0">
		<tr>
			<td class="data_title" style="width:40px;" onclick="order_by('roleId');">编号</td>
			<td class="data_title" style="width:20%;" onclick="order_by('roleName');">角色名</td>
			<td class="data_title" style="width:30%;" onclick="order_by('roleName');">角色描述</td>
			<td class="data_title" style="width:15%;" onclick="order_by('roleFlag');">状态</td>
			<td class="data_title">操作</td>
		</tr>
		<logic:iterate id="item" name="roleForm" property="pageResult.list"
			type="org.jb.y272.team0.entity.SysRole"> 
			<tr>
				<td class="data_cell" style="text-align:right;padding:0 10px;">${item.roleId }</td>
				<td class="data_cell" style="text-align:center;">${item.roleName }</td>
				<td class="data_cell" style="text-align:left;">${item.roleDesc }&nbsp;</td>
				<td class="data_cell" style="text-align:center;">${item.roleFlagString }&nbsp;</td>
				<td class="data_cell">
					<input type="button" class="op_button" onclick="window.location.href='?o=toAssignRights&id=${item.roleId }'" 
						style="width:60px;" value="分配权限" />
				</td>
			</tr>
		</logic:iterate>
		<logic:empty name="roleForm" property="pageResult.list">
			<tr><td class="data_cell" colspan="20" style="text-align:center;height:40px;">没有记录</td></tr>
		</logic:empty>
		</table>
		<jb:pager form="roleForm" />
	</html:form>	
  </body>
</html:html>
