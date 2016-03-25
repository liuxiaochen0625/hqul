package org.jb.y272.team0.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jb.common.web.action.BaseAction;

public class HomeAction extends BaseAction {
	
	public ActionForward header(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		return mapping.findForward("header");
	}
	public ActionForward footer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		return mapping.findForward("footer");
	}
	public ActionForward menu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{			
		return mapping.findForward("menu");
	}
	public ActionForward welcome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		return mapping.findForward("welcome");
	}
}
