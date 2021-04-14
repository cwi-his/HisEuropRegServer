package com.bvtech.registration.portal.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bvtech.registration.portal.bean.Config;
import com.bvtech.registration.portal.idao.IConfigDao;

public class CommonUtility implements ICommonUtility{

	@Autowired
	private IConfigDao configDao;
	
	public Config getConfigInfo(String id) {
		return configDao.findById(id);
		
	}
	
	
}
