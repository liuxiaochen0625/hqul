<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<title>欢迎使用！</title>
	<link rel="stylesheet" href="jquery.treeview.css" />
	<link rel="stylesheet" href="screen.css">
	<style type="text/css">
	a, span {color:black;text-decoration:none;}
	</style>
	<script src="scripts/lib/jquery.js" type="text/javascript"></script>
	<script src="scripts/lib/jquery.cookie.js" type="text/javascript"></script>
	<script src="scripts/jquery.treeview.js" type="text/javascript"></script>
	<script type="text/javascript" src="demo.js"></script>
	<script type="text/javascript">
	if (self.name != 'menu') { 
		self.location.href = 'main.jsp'; 
	}
	</script>
</head>
<BODY marginwidth="1" marginheight="1" onResize="frameResized();" topmargin="0" leftmargin="0" bgcolor="#6FBAF1" text="#fff">

<c:if test="${sessionScope.MENU_LIST != null}">
<ul id="browser" class="filetree">
	<li><span>信息管理系统</span>
		<ul>
		<logic:iterate id="menu" name="MENU_LIST">
			<c:if test="${menu.rightParentCode != 'ROOT_MENU'}">
			<c:if test="${menu.parent==true && menu.rightCode!='L01'}">
			
			<li>
			<span>${menu.rightText}</span>
				<ul>
				<logic:iterate id="subMenu" name="menu" property="childRights">
					<li>
					<a title="${subMenu.rightText}" href="${pageContext.request.contextPath}/${subMenu.rightUrl}"  target="text">
						${subMenu.rightText}
						</a>
					</li>
				</logic:iterate>
				</ul>
			</li>
			
			</c:if>
			</c:if>
		</logic:iterate>
		</ul>
	</li>
</ul>
</c:if>
</BODY>
</html:html>






