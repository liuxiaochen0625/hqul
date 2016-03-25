<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/jb-common.tld" prefix="jb" %>
<script src="validate.js" ></script>
<script src="icommon.js" ></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <title>用户管理</title>
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
  <html:form action="user">
  	<html:hidden property="o" value="toList" />
  	<span style="font-weight:bold;">查询条件</span>
		<table class="input_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<td class="input_title">用户名</td>
				<td class="input_content">
					<html:text property="item.usrName" />
				</td>
				<td class="input_title">状态</td>
				<td class="input_content">
					<html:select property="item.usrFlag">
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
			<td class="data_title" style="width:40px;" onclick="order_by('usrId');">编号</td>
			<td class="data_title" style="width:20%;" onclick="order_by('usrName');">用户名</td>
			<td class="data_title" style="width:20%;" onclick="order_by('usrFlag');">状态</td>
			<td class="data_title">操作</td>
		</tr>
		<logic:iterate id="item" name="userForm" property="pageResult.list"
			type="org.jb.y272.team0.entity.SysUser"> 
			<tr>
				<td class="data_cell" style="text-align:right;padding:0 10px;">${item.usrId }</td>
				<td class="data_cell" style="text-align:center;">${item.usrName }</td>
				<td class="data_cell">${item.usrFlagString }&nbsp;</td>
				<td class="data_cell">
					<input type="button" class="op_button" onclick="window.location.href='?o=toDetail&id=${item.usrId }'" style="width:50px;" value="查看" />
					<logic:notEqual name="item" property="usrFlag" value="0">
						<input type="button" class="op_button" onclick="doDel(${item.usrId })" style="width:50px;" value="删除" />
						<input type="button" class="op_button" onclick="window.location.href='?o=toEdit&id=${item.usrId }'" style="width:50px;" value="编辑" />
						<input type="button" class="op_button" onclick="window.location.href='?o=toAssignRole&id=${item.usrId }'" style="width:75px;" value="分配角色" />
					</logic:notEqual>
					<logic:equal name="item" property="usrFlag" value="0">
						<input type="button" class="op_button" onclick="doDel(${item.usrId })" disabled="true" style="width:50px;" value="删除" />
						<input type="button" class="op_button" onclick="window.location.href='?o=toEdit&id=${item.usrId }'" disabled="true" style="width:50px;"value="编辑" />
						<input type="button" class="op_button" onclick="window.location.href='?o=toAssignRole&id=${item.usrId }'" disabled="true" style="width:75px;" value="分配角色" />
					</logic:equal>
				</td>
			</tr>
		</logic:iterate>
		<logic:empty name="userForm" property="pageResult.list">
			<tr><td class="data_cell" colspan="20" style="text-align:center;height:40px;">没有记录</td></tr>
		</logic:empty>
		</table>
		<jb:pager form="userForm" />
	</html:form>	
  </body>
</html:html>
