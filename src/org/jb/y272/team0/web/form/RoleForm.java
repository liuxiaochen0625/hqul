package org.jb.y272.team0.web.form;

import org.jb.common.web.form.BaseForm;
import org.jb.y272.team0.entity.SysRole;

public class RoleForm extends BaseForm {
	private static final long serialVersionUID = -1667466188886997322L;
	private SysRole item = null;
	
	public RoleForm(){
		item = new SysRole();
	}
	public SysRole getItem() {
		return item;
	}
	public void setItem(SysRole item) {
		this.item = item;
	}
}
