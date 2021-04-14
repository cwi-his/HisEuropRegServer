package com.bvtech.registration.portal.tasks;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bvtech.registration.portal.bean.RegistrationCode;
import com.bvtech.registration.portal.bean.RowsCsvView;
import com.bvtech.registration.portal.bean.Tasks;
import com.bvtech.registration.portal.idao.IRegistrationCodeDao;
import com.bvtech.registration.portal.idao.ITasksDao;
import com.bvtech.registration.portal.service.IntegrationService;

@Component("createCSVFile")
public class CreateCSVFile {

	private static String TASK_NAME = "CREATE_CSV_JOB";

	@Autowired
	IRegistrationCodeDao registrationCodeDao;
	
	@Autowired
	IntegrationService integrationService;
	
	@Autowired
	ITasksDao tasksDao;

	@Value("${europassistance.local.work.folder}")
	String WORK_FOLDER;
	
	@Value("${europassistance.file.system.suffix}")
	String SYSTEM_SUFFIX;
	
	Log log = LogFactory.getLog(this.getClass());
	 
	
	public void execute() throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		log.info("CreateCSVFile - TASK ESXECUTION START - work folder ["+WORK_FOLDER+"]");

		List<RowsCsvView> users = integrationService.getAllDataForCsv();
		
		if(users==null || users.size()==0){
			log.info("CreateCSVFile - nothing to do");
			return;
		}
		
		log.debug("CreateCSVFile - Find ["+users.size()+"] users");
		
		String pathCSV = getCSVFilename();
		Date lastUpdate = integrationService.createCSVFile(users, pathCSV);
		
		if(lastUpdate != null){
			String[] params = {"token","idTask"};
			Object[] values = {sdf.format(lastUpdate), TASK_NAME};
			tasksDao.update(Tasks.UPDATE_TASK, params, values);
		}
		
		log.info("CreateCSVFile - TASK EXECUTION END - Last update is ["+lastUpdate+"] - end task");
	}
	
	private String getCSVFilename(){
		/**
		 * Old Version: a file every 4 hour (but HIS don't like that a file is overwritten on sftp server, so every time a file 
		 * is sent than a new file is created
		 * 
		 * Calendar rightNow = Calendar.getInstance();
		 * int fileSuffix = rightNow.get(Calendar.HOUR_OF_DAY) % 4; // this suffix is a number module 4  to append to file name
		 * String prefix= ""+rightNow.get(Calendar.YEAR)+(rightNow.get(Calendar.MONTH)+1)+rightNow.get(Calendar.DAY_OF_MONTH);
		 * String filename = "-rp-users-"+fileSuffix+".csv";
		 */
		
		File dir = new File(WORK_FOLDER);
		
		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".csv")) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		File[] filesList = dir.listFiles(textFilter);
		if(filesList!=null && filesList.length>0) // if there is a CSV file
			return filesList[0].getAbsolutePath();
		
		// If a file .csv isn't in the folder then create a new file
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename_staticPath="-"+(SYSTEM_SUFFIX!=null?SYSTEM_SUFFIX:"rp-users")+".csv";
		
		String prefix= sdf.format(new Date());
		return WORK_FOLDER+File.separator+prefix+filename_staticPath; // if there isn't the CSV file then create a new file 
	}

}
