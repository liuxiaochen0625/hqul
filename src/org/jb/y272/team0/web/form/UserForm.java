package org.jb.y272.team0.web.form;

import org.jb.common.web.form.BaseForm;
import org.jb.y272.team0.entity.SysUser;

public class UserForm extends BaseForm {
	private SysUser item = null;
	
	public UserForm(){
		item = new SysUser();
	}
	public SysUser getItem() {
		return item;
	}
	public void setItem(SysUser item) {
		this.item = item;
	}
}
