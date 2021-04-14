package com.bvtech.registration.portal.dao;


import com.bvtech.registration.portal.bean.LoginAttempts;
import com.bvtech.registration.portal.generic.dao.GenericDaoHibernateImpl;
import com.bvtech.registration.portal.idao.ILoginAttemptsDao;

public class LoginAttemptsDao extends GenericDaoHibernateImpl<LoginAttempts, String> implements ILoginAttemptsDao{

}
