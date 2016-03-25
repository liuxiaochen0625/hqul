package org.jb.common.web.form;

import org.apache.struts.action.ActionForm;
import org.jb.common.util.PageResult;


public class BaseForm extends ActionForm {

	private PageResult pageResult = new PageResult();
	public PageResult getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	
}
