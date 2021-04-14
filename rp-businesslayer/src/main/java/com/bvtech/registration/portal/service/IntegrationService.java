package com.bvtech.registration.portal.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvtech.registration.portal.bean.PatientToSend;
import com.bvtech.registration.portal.bean.ProcessInformation;
import com.bvtech.registration.portal.bean.RefusedCode;
import com.bvtech.registration.portal.bean.RegistrationCode;
import com.bvtech.registration.portal.bean.RowsCsvView;
import com.bvtech.registration.portal.bean.ShippingToSend;
import com.bvtech.registration.portal.enumeration.Configs;
import com.bvtech.registration.portal.enumeration.RegistrationCodeStatus;
import com.bvtech.registration.portal.idao.IPatientToSendDao;
import com.bvtech.registration.portal.idao.IPersonalInformationDao;
import com.bvtech.registration.portal.idao.IProcessInformationDao;
import com.bvtech.registration.portal.idao.IRefusedCodeDao;
import com.bvtech.registration.portal.idao.IRegistrationCodeDao;
import com.bvtech.registration.portal.idao.IRowsCsvViewDao;
import com.bvtech.registration.portal.idao.IShippingToSendDao;

@Service
public class IntegrationService {

	Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IRefusedCodeDao refusedCodeDao;

	@Autowired
	private IRegistrationCodeDao registrationCodeDao;
	
	private char CSV_SEPARATOR = ';';

	@Autowired
	private IProcessInformationDao processInformationDao;

	@Autowired
	private ICommonUtility commonUtility;

	@Autowired
	private IPatientToSendDao patientToSendDao;
	
	@Autowired
	private IShippingToSendDao shippingToSendDao;

	@Autowired
	private IRowsCsvViewDao rowsCsvViewDao;
	
	@Autowired
	private IPersonalInformationDao personalInformationDao;
	
	/*
	 * Registration Platform --> EuropAssistance: Csv retrieve Data
	 * 
	 */
	public Date createCSVFile(List<RowsCsvView> users, String path){
		log.debug("createCSVFile - IntegrationService - Start");
		Date lastUpdateDate = null;
		FileWriter writer = null; 
		try {
			File csvFile = new File(path);
			if(!csvFile.exists())
				csvFile.createNewFile(); 
			writer = new FileWriter(csvFile, true); 
			// feed in your array (or convert your data to an array)
			for (RowsCsvView user : users) {	
				log.debug("createCSVFile - IntegrationService - Starting for VoucerCode ["+user.getCode()+"] - last Update ["+user.getLastUpdate()+"]");
				if(lastUpdateDate==null)
					lastUpdateDate=user.getLastUpdate();
				if(user.getLastUpdate()!=null)
					lastUpdateDate = (user.getLastUpdate().after(lastUpdateDate)?user.getLastUpdate():lastUpdateDate);
				String userRow=user.getCsvLine(CSV_SEPARATOR);
				writer.append(userRow);
				writer.append(System.getProperty("line.separator"));
				log.debug(userRow);
				log.debug("createCSVFile - IntegrationService - Ending for VoucerCode ["+user.getCode()+"]");
				
				if(user.getIdStaUse().equals(2)) {
					registrationCodeDao.delete(RegistrationCode.DELETE_BY_CODE, new String[]{"code"}, new Object[]{user.getCode()});
					log.info("createCSVFile - User accepted. Removed user information for code ["+user.getCode()+"]");
				}
			}
			
			writer.flush();
		    writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		log.debug("createCSVFile - IntegrationService - Last update is ["+lastUpdateDate+"] - End");
		return lastUpdateDate;
	}
	
	public List<RowsCsvView> getAllDataForCsv(){
		return rowsCsvViewDao.namedQuery(RowsCsvView.RETRIEVE_CSV_ROWS);
	}
		/*
	 * HIS --> Registration Platform New incoming RegistrationCodes Code, Type
	 * (U for user, C for Coach)
	 * 
	 * in json list
	 */
	public void insertRegCod(RegistrationCode rc) {

		rc.setIdStaPro(RegistrationCodeStatus.OK.getValue());
		registrationCodeDao.create(rc);
	}

	public void insertRefusedCodes(RegistrationCode rc, String errorMessage) {

		RefusedCode rcd = new RefusedCode();
		rcd.setCode(rc.getCode());
		rcd.setTypeCode(rc.getTypeCode());
		rcd.setDescription(errorMessage);
		rcd.setInsertDate(rc.getInsertDate());

		refusedCodeDao.create(rcd);
	}
	
	public List<RegistrationCode> getAllInfoByUserCode(String code) {
		String[] params = {"registrationCode"};
		Object[] values = {code};
		
		return registrationCodeDao.namedQuery(RegistrationCode.NQ_GET_ALL_INFO_BY_CODE, params, values );
	}
	// End HIS --> Registration Portal
	
	
	public Boolean writeFileTermsConditions(File file, String typeCode) {
		try {

			byte[] bytes = new byte[(int) file.length()];
			// Creating the directory to store file
			// String rootPath = System.getProperty("catalina.home");
			String pathPdf = commonUtility.getConfigInfo(Configs.PATH_TERMS_CONDITIONS.getValue()).getValue();
			File dir = new File(pathPdf);
			if (!dir.exists())
				dir.mkdirs();

			// Remove file on server
			for(File f: dir.listFiles())
			    if(f.getName().startsWith(typeCode+"_"))
			        if(f.exists() && !f.delete())
			        	return false;
			
			// Create the file on server
			//File serverFile = new File(dir.getAbsolutePath() + File.separator + typeCode + "_" + file.getName());
			File serverFile = new File(pathPdf + File.separator + typeCode + "_" + file.getName());
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String readFileTermsConditions(final String typeCode) {

		/*
		 * try { String rootPath = System.getProperty("catalina.home"); File dir
		 * = new File(rootPath + File.separator + "tmpFiles"); // Create the
		 * file on server File serverFile = new File(dir.getAbsolutePath() +
		 * File.separator); BufferedOutputStream stream = new
		 * BufferedOutputStream(new FileOutputStream(serverFile)); byte[] bytes
		 * = null; stream.write(bytes); serverFile. stream. stream.close();
		 * 
		 * return ""; } catch (Exception e) { return ""; }
		 * 
		 */

		// String rootPath = System.getProperty("catalina.home");
		// File dir = new File(pathPdf);
		String pathPdf = commonUtility.getConfigInfo(Configs.PATH_TERMS_CONDITIONS.getValue()).getValue();
		File path = new File(pathPdf + File.separator);
		File[] listFiles = path.listFiles(new FileFilter() {

			public boolean accept(File file) {

				String name = file.getName().toLowerCase();
				return name.startsWith(typeCode + "_");

			}

		});

		if (listFiles.length == 0) {
			return "";
		} else {
			return listFiles[0].getAbsolutePath();
		}

	}

	@SuppressWarnings("unchecked")
	public List<PatientToSend> getPatientToSend(String status) {

		String[] names = { "status" };
		Object[] values = { status };
		
		//PatientToSend ps = patientToSendDao.namedQuerySingleResult(PatientToSend.NQ_GET_PATIENT_STATUS, names, values);
		

		return patientToSendDao.namedQuery(PatientToSend.NQ_GET_PATIENT_STATUS, names, values);
	}

	@SuppressWarnings("unchecked")
	public List<ShippingToSend> getShippingToSend(String status) {

		String[] names = {"status"};
		Object[] values = {status};
		

		return shippingToSendDao.namedQuery(ShippingToSend.NQ_GET_SHIPPING_TO_SEND, names, values);
	}
	public void insertExternalCode(Integer idRegCod, String externalUuid, String externalNodeid) {
		String[] names = { "externalUuid", "externalNodeid", "idRegCod" };
		Object[] values = { externalUuid, externalNodeid, idRegCod };
		processInformationDao.update(ProcessInformation.NQ_UPD_EXTERNAL_CODE, names, values);
	}

	public void updateRegCodLastUpd(Integer idRegCod) {
		String[] names = { "idStaPro","lastUpdate","idRegCod"};
		Object[] values = { RegistrationCodeStatus.USED.getValue(),getCurrentDate(),idRegCod};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
	}
	
	public void updatePatientStatus(Integer idRegCod, String status) {
		String[] names = { "status","idRegCod"};
		Object[] values = { status,idRegCod};
		patientToSendDao.update(PatientToSend.NQ_UPD_PATIENT_STATUS, names, values);
	}
	
	public void updateShippingStatus(String idPatient, String status) {
		String[] names = { "status","idPatient"};
		Object[] values = { status,idPatient};
		shippingToSendDao.update(ShippingToSend.NQ_UPD_SHIPPING_STATUS, names, values);
	}
	
	public void updateShippingPatientId(Integer idRegCod, String Uuid) {
		String[] names = { "idPatient","idRegCod"};
		Object[] values = { Uuid,idRegCod};
		shippingToSendDao.update(ShippingToSend.NQ_UPD_SHIPPING_PATIENT, names, values);
	}
	
	private String getCurrentDate() {

		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	@SuppressWarnings("unchecked")
	public List<RegistrationCode> getListCodeExpired() {
		String sql="SELECT id_reg_cod FROM("+
						"SELECT id_reg_cod, (CASE WHEN (DATE_ADD(a.last_update , INTERVAL b.value DAY) < now()) "+
						"THEN 1 ELSE 0 END) del_row "+
						 "FROM registrations_codes a "+
						 "JOIN configs b "+
						 " WHERE a.id_sta_pro= "+ RegistrationCodeStatus.OK.getValue() +
						 " AND b.id_con='"+ Configs.CODE_EXPIRED_AFTER_DAYS.getValue() +"') a "+
				  "WHERE del_row=1";

		return registrationCodeDao.executeSql(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getListCodeSendEmail() {
		String sql="SELECT id_reg_cod FROM("+
						"SELECT id_reg_cod, (CASE WHEN (DATE_ADD(a.last_update , INTERVAL b.value HOUR) < now()) "+
						"THEN 1 ELSE 0 END) del_row "+
						 "FROM registrations_codes a "+
						 "JOIN configs b "+
						 " WHERE a.id_sta_pro= "+ RegistrationCodeStatus.PARTIALLY_USED.getValue() +
						 " AND a.send_email=0" +
						 " AND b.id_con='"+ Configs.SEND_EMAIL_AFTER_HOURS.getValue() +"') a "+
				  "WHERE del_row=1";

		return registrationCodeDao.executeSql(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getListCodeInterruptedReg() {
		String sql="SELECT id_reg_cod FROM("+
						" SELECT id_reg_cod, (CASE WHEN (DATE_ADD(a.last_update , INTERVAL b.value DAY) < now()) "+
						" THEN 1 ELSE 0 END) del_row "+
						 " FROM registrations_codes a "+
						 " JOIN configs b "+
						 " WHERE a.id_sta_pro= "+ RegistrationCodeStatus.PARTIALLY_USED.getValue() +
						 " AND b.id_con='"+ Configs.DEL_DATA_INTERRUPTED_REG_AFTER_DAYS.getValue() +"') a "+
				  " WHERE del_row=1";

		return registrationCodeDao.executeSql(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getListCodePartiallyRefused() {
		String sql="SELECT id_reg_cod FROM("+
						" SELECT id_reg_cod, (CASE WHEN (DATE_ADD(a.last_update , INTERVAL b.value DAY) < now()) "+
						" THEN 1 ELSE 0 END) del_row "+
						 " FROM registrations_codes a "+
						 " JOIN configs b "+
						 " WHERE a.id_sta_pro= "+ RegistrationCodeStatus.PARTIALLY_USED.getValue() +
						 " AND b.id_con='"+ Configs.DEL_DATA_PARTIALLY_REFUSED_AFTER_DAYS.getValue() +"') a "+
				  " WHERE del_row=1";

		return registrationCodeDao.executeSql(sql);
	}
	
	public void delListRegCod(List<Integer>  regCodes) {
		Iterator<Integer> regCod = regCodes.iterator();
		while (regCod.hasNext()) {
			registrationCodeDao.deleteById(regCod.next());
		}
	}

	public void updListRegCodToExpired(List<RegistrationCode>  regCodes) {
		Iterator<RegistrationCode> regCod = regCodes.iterator();
		while (regCod.hasNext()) {
			String[] names = { "idStaPro","lastUpdate","idRegCod"};
			Object[] values = {RegistrationCodeStatus.EXPIRED.getValue(),getCurrentDate(),regCod.next()};
			registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
		}
	}
	
	
	public void updListRegCodToSendEmail(Integer idRegCod) {
	
			String[] names = { "sendEmail","idRegCod"};
			Object[] values = {1,idRegCod};
			registrationCodeDao.update(RegistrationCode.NQ_UPD_SEND_EMAIL, names, values);
	}

	public void deleteOrphanPersonalInformation() {
		String sqlQuery = "delete from personals_informations where id_per_inf not in "
				+ "(select id_per_inf from processes_informations)";
		
		int i = personalInformationDao.deleteBySql(sqlQuery);
		log.debug("Removed ["+i+"] orphan personal informations");
	}
	
	
}