package org.jb.y272.team0.biz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jb.common.biz.BaseBiz;
import org.jb.common.util.PageResult;
import org.jb.y272.team0.entity.SysRight;
import org.jb.y272.team0.entity.SysRole;

/**
 * 角色管理业务逻辑类
 * @author hailong.liu
 */
public class RoleBiz extends BaseBiz {
	/**
	 * 查询所有可用的角色（状态为1）
	 */
	public List getRolesInUse() {
		String hql = "select o from SysRole o where o.roleFlag=1 ";
		List list = this.getCommonDAO().list(hql);
		return list;
	}
	/**
	 * 得到角色列表
	 */
	public void getList(SysRole item, PageResult pageResult) {
		String hql = "select o from SysRole o where 1=1 ";
		if (null!=item){
			if (isNotNullOrEmpty(item.getRoleName())){
				hql += "and o.roleName like '%"
						+item.getRoleName()+"%' ";				
			}
			if (item.getRoleFlag()!=null && item.getRoleFlag()!=-1){
				hql += "and o.roleFlag = "+item.getRoleFlag()+" ";				
			}
		}
		if (isNotNullOrEmpty(pageResult.getOrderBy())){
			String sort = pageResult.getSort();
			hql += "order by " + pageResult.getOrderBy() + " " +sort; 
			if ("asc".equals(sort)){
				pageResult.setSort("desc");
			}else{
				pageResult.setSort("asc");
			}
		}else{
			hql += "order by o.roleId desc,o.roleName asc";
		}		
		this.getCommonDAO().listByPage(hql,pageResult);
	}
	/**
	 * 得到权限列表
	 */
	public List getRights() {
		String hql = "select o from SysRight o order by o.rightCode asc";
		List ret = this.getCommonDAO().list(hql);
		return ret;
	}
	/**
	 * 得到角色，及其拥有的权限
	 */
	public SysRole getRoleWithRights(Long roleId) {
		SysRole ret = null;
		String hql = "select distinct rl from SysRole rl left outer join fetch rl.rights where rl.roleId="+roleId;
		List list = this.getCommonDAO().list(hql);
		if (list.size()>0){
			ret = (SysRole)list.get(0);
		}else{
			ret = null;
		}
		return ret;
	}
	/**
	 * 分配权限
	 */
	@SuppressWarnings("unchecked")
	public void assignRights(Long roleId, String[] selectedRightCodes) {
		SysRole role = (SysRole)this.getCommonDAO().get(SysRole.class, roleId);
		role.getRights().clear();
		if (null!=selectedRightCodes){
			for(int i=0;i<selectedRightCodes.length;++i){
				SysRight right = (SysRight)this.getCommonDAO().get(SysRight.class, selectedRightCodes[i]);
				role.getRights().add(right);
			}
		}
		this.getCommonDAO().update(role);
	}
	/**
	 * 自:查找相同父权限的子权限列表 [菜单权限]
	 * @param rightCode 父权限
	 * @return
	 */
	public List getChildRights(String rightCode){
		String hql="select o from SysRight o where o.rightParentCode='"+rightCode+"'";
		List list=this.getCommonDAO().list(hql);
		return list;
	}
	public List<SysRight> getChild(List<SysRight> list, String parentCode) {
		List<SysRight> newList=new ArrayList<SysRight>();
		Iterator<SysRight> iterator = list.iterator();
		boolean bool = false;
		while (iterator.hasNext()) {
			SysRight t = iterator.next();
			if (parentCode.equals(t.getRightParentCode())) {
				bool = true;
				newList.add(t);
			}
		}
		return newList;
	}
}
