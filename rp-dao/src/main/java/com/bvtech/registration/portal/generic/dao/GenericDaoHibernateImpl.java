package com.bvtech.registration.portal.generic.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings(value = "unchecked")
@Transactional
public abstract class GenericDaoHibernateImpl<T, PK extends Serializable> extends HibernateDaoSupport
		implements GenericDao<T, PK> {

	protected Class<T> clazz;

	@Autowired
	protected SessionFactory sessionFactory;

	public GenericDaoHibernateImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T findById(PK id) {
		// TODO Select by ID
		return (T) getCurrentSession().get(clazz, id);
	}

	public List getList() {
		// TODO Select as list
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public List search(Map<String, Object> parameterMap) {
		// TODO For search purpose
		Criteria criteria = getCurrentSession().createCriteria(clazz);
		Set<String> fieldName = parameterMap.keySet();
		for (String field : fieldName) {
			criteria.add(Restrictions.ilike(field, parameterMap.get(field)));
		}
		return criteria.list();
	}

	public PK create(T entity) {
		return (PK) getCurrentSession().save(entity);
	}

	public void update(T entity) {
		getCurrentSession().update(entity);
	}

	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	public void deleteById(PK id) {
		delete(findById(id));
	}
	public void flush() {
		getCurrentSession().flush();
	}
	public List findByExample(T entity) {
		Example customerExample = Example.create(entity).excludeZeroes();
		Criteria criteria = getCurrentSession().createCriteria(entity.getClass());
		criteria.add(customerExample);
		return criteria.list();
	}

	public List findByExampleAndRangeDate(T entity, String field, Date from, Date to) {
		Example customerExample = Example.create(entity).excludeZeroes();
		Criteria criteria = getCurrentSession().createCriteria(entity.getClass());
		criteria.add(customerExample);
		if (from != null && field != null)
			criteria.add(Restrictions.ge(field, from));
		if (to != null && field != null)
			criteria.add(Restrictions.le(field, to));
		return criteria.list();

	}

	public List findByExampleAndSort(T entity, String field, boolean isAsc) {
		Example customerExample = Example.create(entity).excludeZeroes();
		Criteria criteria = getCurrentSession().createCriteria(entity.getClass());
		criteria.add(customerExample);
		if (field != null) {
			if (isAsc)
				criteria.addOrder(Order.asc(field));
			else
				criteria.addOrder(Order.desc(field));
		}
		return criteria.list();

	}

	public long executeSqlNumberQuery(String nativeQuery) {
		Query query = getCurrentSession().createSQLQuery(nativeQuery);
		return ((Integer) query.iterate().next()).intValue();
	}

	public long namedQueryNumber(String namedQuery, String[] params, Object[] values) {
		Query query = getCurrentSession().getNamedQuery(namedQuery);
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof String)
				query.setString(params[i], String.valueOf(values[i]));
			else if (values[i] instanceof Long)
				query.setLong(params[i], (Long) values[i]);
		}
		return Long.parseLong(String.valueOf(query.uniqueResult()));
	}

	public List namedQuery(String namedQuery, String[] params, Object[] values) {
		Query query = getCurrentSession().getNamedQuery(namedQuery);
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Long)
				query.setLong(params[i], (Long) values[i]);
			else if (values[i] instanceof Date)
				query.setDate(params[i], (Date) values[i]);
			else query.setString(params[i], String.valueOf(values[i]));
		}
		return query.list();
	}
	
	public List namedQuery(String namedQuery) {
		Query query = getCurrentSession().getNamedQuery(namedQuery);
		return query.list();
	}
	
	public int delete(String namedQuery, String[] params, Object[] values){
		 
		Query query = getCurrentSession().getNamedQuery(namedQuery);
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Long)
				query.setLong(params[i], (Long) values[i]);
			else if (values[i] instanceof Date)
				query.setDate(params[i], (Date) values[i]);
			else query.setString(params[i], String.valueOf(values[i]));
		}
		 
		return query.executeUpdate();
		
	}
	
    public int deleteBySql(String sql){
    	Query query = getCurrentSession().createSQLQuery(sql);
		return query.executeUpdate();
    }

    
	public int update(String namedQuery, String[] params, Object[] values){
		 
		Query query = getCurrentSession().getNamedQuery(namedQuery);
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Long)
				query.setLong(params[i], (Long) values[i]);
			else if (values[i] instanceof Date)
				query.setDate(params[i], (Date) values[i]);
			else query.setString(params[i], String.valueOf(values[i]));
		}
		 
		int rowUpdated = query.executeUpdate();
		getCurrentSession().flush();
		return rowUpdated;
	}
	
	public T namedQuerySingleResult(String namedQuery, String[] params, Object[] values){
		List list = namedQuery(namedQuery,params,values);
		if(list==null || list.isEmpty())
			return null;
		return (T) list.get(0);
	}

	public List executeSql(String nativeQuery) {
		Query query = getCurrentSession().createSQLQuery(nativeQuery);
		return query.list();
	}
	
	public int executeHqlUpdateQuery(String hqlQuery, String[] params, Object[] values){
		Query query = getCurrentSession().createQuery(hqlQuery);
		
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Long)
				query.setLong(params[i], (Long) values[i]);
			else if (values[i] instanceof Date)
				query.setDate(params[i], (Date) values[i]);
			else query.setString(params[i], String.valueOf(values[i]));
		}
		
		int rowUpdated = query.executeUpdate();
		getCurrentSession().flush();
		return rowUpdated;
	}
	
	protected DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(clazz);
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}