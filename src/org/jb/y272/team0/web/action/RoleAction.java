package org.jb.y272.team0.web.action;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jb.common.web.action.BaseAction;
import org.jb.y272.team0.biz.RoleBiz;
import org.jb.y272.team0.entity.SysRight;
import org.jb.y272.team0.entity.SysRole;
//import org.jb.y272.team0.biz.UserBiz;
import org.jb.y272.team0.web.form.RoleForm;

public class RoleAction extends BaseAction {
	//private UserBiz userBiz = null;
	private RoleBiz roleBiz = null;
//	public void setUserBiz(UserBiz userBiz){
//		this.userBiz = userBiz; 
//	}
	public void setRoleBiz(RoleBiz roleBiz){
		this.roleBiz = roleBiz; 
	}
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		return mapping.findForward("add");
	}
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RoleForm myForm = (RoleForm)form;
		this.roleBiz.getList(myForm.getItem(), myForm.getPageResult());
		return mapping.findForward("list");
	}
	public ActionForward toAssignRights(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		RoleForm myForm = (RoleForm)form;
		String id = request.getParameter("id");
		SysRole role = this.roleBiz.getRoleWithRights(Long.parseLong(id));
		myForm.setItem(role);
		
		List allRights = this.roleBiz.getRights();
		Iterator it = allRights.iterator();
		while(it.hasNext()){
			SysRight right = (SysRight)it.next();
			if (role.getRights().contains(right)){
				right.setIsSelected("checked=\"on\"");
			}
		}
		request.setAttribute("ALL_RIGHTS_LIST", allRights);

		return mapping.findForward("assign_rights");
	}
	public ActionForward doAssignRights(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		String[] selectedRightCodes = request.getParameterValues("selectedRights");
		RoleForm myForm = (RoleForm)form;
		Long roleId = myForm.getItem().getRoleId();
		
		this.roleBiz.assignRights(roleId,selectedRightCodes);
		
		response.sendRedirect("role.do?o=toAssignRights&id="+roleId);
		return null;
	}
}
