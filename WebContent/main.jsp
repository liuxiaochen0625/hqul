<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<logic:empty name="USER" scope="session">
<script>
	window.location.href = "index.jsp?error=2";
</script>
</logic:empty>

<title>信息管理系统</title>

<frameset rows="70,*,50" frameborder="no">
	<frame src="home.do?o=header" scrolling="no">
	<frameset cols="200,*" frameborder="1">
		<frame src="home.do?o=menu" name="menu"  scrolling="auto">
		<frame src="home.do?o=welcome" name="text">
	</frameset>
	<frame src="home.do?o=footer" scrolling="no" >
</frameset>