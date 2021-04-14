package com.bvtech.job.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bvtech.registration.portal.tasks.CreateCSVFile;
import com.bvtech.registration.portal.tasks.PurgerUserData;
import com.bvtech.registration.portal.tasks.SendCSVFile;
import com.bvtech.registration.portal.tasks.SendUserData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:**/application-context-bl-test.xml",
		"classpath*:**/application-context-datasource-test.xml", 
		"classpath*:**/application-context-dao-test.xml",
		"classpath*:**/application-context-quartz-test.xml" })
public class JobExecuteMethodTest {

	@Autowired
	CreateCSVFile createCSVFile;

	@Autowired
	SendCSVFile sendCSVFile;
	
	@Autowired
	SendUserData sendUserData;
	
	@Autowired
	PurgerUserData purgerUserData;
	
	@Test
	public void createCSVFileTest() {
		try {
			createCSVFile.execute();
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sendCSVFileTest() {
		try {
			sendCSVFile.execute();
		} catch (JobExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sendUserDataTest() {
		try {
			sendUserData.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void purgerUserDataTest() {
		try {
			purgerUserData.execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
