package org.jb.y272.team0.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jb.common.web.action.BaseAction;
import org.jb.y272.team0.biz.RoleBiz;
import org.jb.y272.team0.biz.UserBiz;
import org.jb.y272.team0.entity.SysRole;
import org.jb.y272.team0.entity.SysUser;
import org.jb.y272.team0.web.form.UserForm;

public class UserAction extends BaseAction {
	private UserBiz userBiz = null;
	private RoleBiz roleBiz = null;
	public void setUserBiz(UserBiz userBiz){
		this.userBiz = userBiz; 
	}
	public void setRoleBiz(RoleBiz roleBiz){
		this.roleBiz = roleBiz; 
	}
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		this.userBiz.getList(myForm.getItem(), myForm.getPageResult());
		return mapping.findForward("list");
	}
	/**
	 *删除用户
	 */
	public ActionForward doDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		String id = request.getParameter("id");
		Long lid=Long.parseLong(id);
		SysUser user=(SysUser) request.getSession().getAttribute("USER");
		if(!user.getUsrId().equals(lid));
			this.userBiz.del(lid);
		response.sendRedirect("user.do?o=toList");
		return null;
	}
	/**
	 * 增加用户
	 */
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		myForm.setItem(new SysUser());
		return mapping.findForward("add");		
	}
	public ActionForward doAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		if (!this.userBiz.add(myForm.getItem())){
			super.AddError("errors.user.allready_exists",myForm.getItem().getUsrName());
			return mapping.findForward("add");
		}
		response.sendRedirect("user.do?o=toList");
		return null;
	}
	/**
	 * 修改用户
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		
		String id = request.getParameter("id");
		SysUser item = this.userBiz.get(Long.parseLong(id));
		myForm.setItem(item);
		
		return mapping.findForward("edit");		
	}
	public ActionForward doEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		if (!this.userBiz.update(myForm.getItem())){
			super.AddError("errors.user.allready_exists",myForm.getItem().getUsrName());
			return mapping.findForward("edit");
		}		
		response.sendRedirect("user.do?o=toList");
		return null;
	}
	/**
	 * 登录
	 */
	public ActionForward doLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		SysUser user = this.userBiz.checkUser(myForm.getItem());
		if (null!=user){
			Long roleId = user.getUsrRole().getRoleId();
			SysRole role = this.roleBiz.getRoleWithRights(roleId);
			user.setUsrRole(role);
			request.getSession().setAttribute("USER", user);
			super.buildMenuString(request,roleBiz);
			
			response.sendRedirect("main.jsp");
		}else{
			response.sendRedirect("index.jsp?error=1");
		}		
		return null;		
	}
	/**
	 * 注销
	 */
	public ActionForward doLogout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		request.getSession().removeAttribute("USER");
		String script = "<script>window.parent.location.href='index.jsp';</script>";
		response.getWriter().print(script);
		return null;		
	}
	/**
	 * 分配角色
	 */
	public ActionForward toAssignRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		// 加载要指定角色的用户
		String id = request.getParameter("id");
		SysUser item = this.userBiz.get(Long.parseLong(id));
		if (null==item.getUsrRole()){
			item.setUsrRole(new SysRole());
		}
		myForm.setItem(item);
		// 查询所有可用的角色作为候选项
//		List roles = super.getRolesOptions(this.getServlet().getServletConfig().getServletContext(), this.roleBiz);
		@SuppressWarnings("rawtypes")
		List roles = super.getRolesOptions(request.getSession().getServletContext(), roleBiz);
		request.setAttribute("ROLES_OPTIONS", roles);
		return mapping.findForward("assign_role");		
	}
	public ActionForward doAssignRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		this.userBiz.assignRole(myForm.getItem());			
		response.sendRedirect("user.do?o=toAssignRole&id="+myForm.getItem().getUsrId());
		return null;
	}
	/**
	 * 察看用户明细
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		UserForm myForm = (UserForm)form;
		
		String id = request.getParameter("id");
		SysUser item = this.userBiz.get(Long.parseLong(id));
		myForm.setItem(item);
		
		return mapping.findForward("detail");		
	}
}
