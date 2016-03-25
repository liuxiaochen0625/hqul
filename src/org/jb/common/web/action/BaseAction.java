package org.jb.common.web.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jb.y272.team0.biz.RoleBiz;
import org.jb.y272.team0.entity.SysRight;
import org.jb.y272.team0.entity.SysUser;

public class BaseAction extends DispatchAction {
	protected ActionMessages errors = new ActionMessages();
	protected void clearErrors(){
		errors.clear();
	}
	protected void SaveErrors(HttpServletRequest request){
		if (!errors.isEmpty()){
			super.saveErrors(request, errors);
		}
	}
	
	protected void AddError(String resKey){
		this.errors.add("ERROR_KEY",new ActionMessage(resKey));		
	}
	protected void AddError(String resKey,String msg){
		this.errors.add("ERROR_KEY", new ActionMessage(resKey,msg) );	
	}
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		clearErrors();
		String errorCode = request.getParameter("error_code");
		if (null!=errorCode){
			AddError(errorCode);
		}
		ActionForward af = super.execute(mapping, form, request, response);
		SaveErrors(request);
		return af;
	}
	/**
	 * 得到所有可用角色
	 */
	protected List getRolesOptions(ServletContext application,RoleBiz roleBiz){				
		List ret = (List)application.getAttribute("ROLES_OPTIONS");
		if (null==ret){
			ret = roleBiz.getRolesInUse();
			application.setAttribute("ROLES_OPTIONS", ret);
		}
		return ret;
	}
	/**
	 * 构建系统菜单
	 */
	/*protected void buildMenuString(HttpServletRequest request) {		
		SysUser user = (SysUser)request.getSession().getAttribute("USER");
		if (user!=null){
			StringBuffer sb = new StringBuffer();
			List rights = user.getUsrRole().getRights();
			Iterator it = rights.iterator();
			while(it.hasNext()){
				SysRight right = (SysRight)it.next();
				sb.append("\r\n");
				sb.append(right.getRightCode()).append(" = theMenu.addChild(")
				.append(right.getRightParentCode()).append(", \"")
				.append(right.getRightType()).append("\", \"")
				.append(right.getRightText()).append("\", \"")
				.append(right.getRightUrl()).append("\", \"")
				.append(right.getRightTip()).append("\");\r\n");
			}
			request.getSession().setAttribute("MENU_STRING", sb.toString());
		}
	}*/
	/*protected void buildMenuString(HttpServletRequest request,RoleBiz roleBiz) {		
		SysUser user = (SysUser)request.getSession().getAttribute("USER");
		if (user!=null){
			List<MyMenu> list=new ArrayList<MyMenu>();
			List rights = user.getUsrRole().getRights();
			Iterator it = rights.iterator();
			while(it.hasNext()){
				SysRight right = (SysRight)it.next();
				String rightCode=right.getRightCode();
				List<SysRight> childRights=roleBiz.getChildRights(rightCode);
				String rightParentCode=right.getRightType();
				String rightText=right.getRightText();
				String rightTip=right.getRightTip();
				String rightType=right.getRightType();
				String rightUrl=right.getRightUrl();
				MyMenu menu=new MyMenu();
				if(childRights!=null && childRights.size()>0){
					menu.setParent(true);
				} else {
					menu.setParent(false);
				}
				menu.setRightCode(rightCode);
				menu.setRightParentCode(rightParentCode);
				menu.setRightText(rightText);
				menu.setRightTip(rightTip);
				menu.setRightType(rightType);
				menu.setRightUrl(rightUrl);
				menu.setChildRights(childRights);
				list.add(menu);
			}
			request.getSession().setAttribute("MENU_LIST", list);
		}
	}*/
	protected void buildMenuString(HttpServletRequest request,RoleBiz roleBiz) {		
		SysUser user = (SysUser)request.getSession().getAttribute("USER");
		if (user!=null){
			List<SysRight> list=new ArrayList<SysRight>();
			List rights = user.getUsrRole().getRights();
			Iterator it = rights.iterator();
			while(it.hasNext()){
				SysRight right = (SysRight)it.next();
				String rightCode=right.getRightCode();
				List<SysRight> childRights=roleBiz.getChild(rights, rightCode);
				if(childRights.size()>0){
					right.setParent(true);
				} else {
					right.setParent(false);
				}
				right.setChildRights(childRights);
			}
			request.getSession().setAttribute("MENU_LIST", rights);
		}
	}
	/*private List<SysRight> getChild(List<SysRight> list, String parentCode) {
		List<SysRight> newList=new ArrayList<SysRight>();
		Iterator<SysRight> iterator = list.iterator();
		while (iterator.hasNext()) {
			SysRight chindRight = iterator.next();
			if (parentCode.equals(chindRight.getRightParentCode())) {
				newList.add(chindRight);
			}
		}
		return newList;
	}*/
}
