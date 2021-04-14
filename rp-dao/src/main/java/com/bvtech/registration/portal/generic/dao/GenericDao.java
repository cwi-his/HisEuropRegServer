package com.bvtech.registration.portal.generic.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface GenericDao <T, PK extends Serializable> {

    PK create(T newInstance);

    T findById(PK id);

    void update(T transientObject);

    void delete(T persistentObject);
    
    void deleteById(PK id);
    
    int deleteBySql(String sql);
    
    List findByExample(T entity);
    
    List findByExampleAndRangeDate(T entity, String field, Date from, Date to);
    
    long executeSqlNumberQuery(String nativeQuery);
    
    List executeSql(String nativeQuery);
    
    public int update(String namedQuery, String[] params, Object[] values);
    public void flush();
    List namedQuery(String namedQuery, String[] params, Object[] values);
    List namedQuery(String namedQuery);
    int delete(String namedQuery, String[] params, Object[] values);
    T namedQuerySingleResult(String namedQuery, String[] params, Object[] values);

    List getList();
    
    public List findByExampleAndSort(T entity, String field, boolean isAsc);
    
    public int executeHqlUpdateQuery(String hqlQuery,String[] params, Object[] values);
    
}