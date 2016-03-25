package org.jb.common.dao.hibimpl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.jb.common.dao.CommonDAO;
import org.jb.common.util.PageResult;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * @author hailong.liu
 */
public class CommonDAOHibImpl extends HibernateDaoSupport implements CommonDAO {
	public Serializable add(Object o){
		return super.getHibernateTemplate().save(o);
	}
	public Object get(Class clazz, Serializable id){
		return super.getHibernateTemplate().get(clazz, id);
	}
	public void del(Class clazz,Serializable id){
		super.getHibernateTemplate().delete(this.get(clazz, id));
	}
	public void update(Object o){
		super.getHibernateTemplate().update(o);		
	}
	public List list(String hql){
		return super.getHibernateTemplate().find(hql);
	}
	@SuppressWarnings("unchecked")
	public void listByPage(String hql, PageResult pageResult) {
		if (null==hql){
			return ;
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(pageResult.getFirstRec());
		query.setMaxResults(pageResult.getPageSize());
		List ret = query.list();
		pageResult.setList(ret);

		String queryString = "";
		if (hql.toUpperCase().indexOf("SELECT") != -1) {
			int i = query.getQueryString().toUpperCase().indexOf("FROM");
			queryString = "Select count(*) " + hql.substring(i,hql.length());
		} else {
			queryString = "Select count(*) " + hql;
		}
		// 去掉ORDER BY 的部分
		int j = queryString.toUpperCase().lastIndexOf("ORDER");
		if (j!=-1){
			queryString = queryString.substring(0, j);
		}		
		Query cquery = this.getSession().createQuery(queryString);
		cquery.setCacheable(true);
		int recTotal = ((Integer)cquery.iterate().next()).intValue();		
		pageResult.setRecTotal(recTotal);
	}

}
