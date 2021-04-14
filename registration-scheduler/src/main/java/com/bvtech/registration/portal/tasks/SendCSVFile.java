package com.bvtech.registration.portal.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.bvtech.registration.portal.bean.Tasks;
import com.bvtech.registration.portal.idao.ITasksDao;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

@Component("sendCSVFile")
public class SendCSVFile {

	@Autowired
	ITasksDao tasksDao;
	
	@Value("${europassistance.sftp.ip}")
	String SFTPHOST;

	@Value("${europassistance.sftp.port}")
	int SFTPPORT;

	@Value("${europassistance.sftp.user}")
	String SFTPUSER;

	@Value("${europassistance.sftp.pwd}")
	String SFTPPASS;

	@Value("${europassistance.sftp.remote.folder}")
	String SFTPWORKINGDIR;

	@Value("${europassistance.local.work.folder}")
	String LOCAL_FOLDER;
	
	@Value("${europassistance.local.archive.folder}")
	String ARCHIVE_FOLDER;

	Log log = LogFactory.getLog(this.getClass());
	
	private static String TASK_NAME = "SEND_CSV_JOB";

	SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");

	public void execute() throws JobExecutionException {
		log.info("SendCSVFile - TASK ESXECUTION START");
		File folder = new File(LOCAL_FOLDER);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles == null || listOfFiles.length == 0) {
			log.info("SendCSVFile - Nothing to do");
			return;
		}
		log.info("SendCSVFile - ["+listOfFiles.length+"] csv to send");
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()){
				File file = listOfFiles[i];
				log.debug("SendCSVFile - file "+file.getAbsoluteFile()+"] will be sent");
				sendCSVFile(file);
				log.debug("SendCSVFile - file "+listOfFiles[i].getAbsoluteFile()+"] is sent");
				
//				file.renameTo(new File(ARCHIVE_FOLDER+File.separator+sdf.format(new Date())+"-"+file.getName()));
				try {
					File targetFile = new File(ARCHIVE_FOLDER+File.separator+sdf.format(new Date())+"-"+file.getName());
				    Files.move(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException ex) {
				    log.error("Can't move to Archive Directory CSV file ["+file.getName()+"]");
				}
//				if(file.exists())
//					file.delete();
			}
		}
		updateTaskInfo();
		log.info("SendCSVFile - TASK EXECUTION END");
	}

	public void sendCSVFile(File file) {

		Session session = null;
		Channel channel = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR);
			channelSftp.put(new FileInputStream(file), file.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	private void updateTaskInfo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String[] params = {"token","idTask"};
		Object[] values = {sdf.format(new Date()), TASK_NAME};
		tasksDao.update(Tasks.UPDATE_TASK, params, values);
	}

}
