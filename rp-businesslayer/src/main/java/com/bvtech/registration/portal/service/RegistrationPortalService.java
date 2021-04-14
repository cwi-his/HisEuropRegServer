package com.bvtech.registration.portal.service;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvtech.registration.portal.bean.Config;
import com.bvtech.registration.portal.bean.LoginAttempts;
import com.bvtech.registration.portal.bean.PatientToSend;
import com.bvtech.registration.portal.bean.PersonalInformation;
import com.bvtech.registration.portal.bean.ProcessInformation;
import com.bvtech.registration.portal.bean.ProcessTypeAuthentication;
import com.bvtech.registration.portal.bean.RegistrationCode;
import com.bvtech.registration.portal.bean.ShippingToSend;
import com.bvtech.registration.portal.dao.PersonalInformationDao;
import com.bvtech.registration.portal.enumeration.Configs;
import com.bvtech.registration.portal.enumeration.RegistrationCodeStatus;
import com.bvtech.registration.portal.enumeration.UserStatus;
import com.bvtech.registration.portal.idao.IConfigDao;
import com.bvtech.registration.portal.idao.ILoginAttemptsDao;
import com.bvtech.registration.portal.idao.IPatientToSendDao;
import com.bvtech.registration.portal.idao.IPersonalInformationDao;
import com.bvtech.registration.portal.idao.IProcessInformationDao;
import com.bvtech.registration.portal.idao.IProcessTypeAuthenticationDao;
import com.bvtech.registration.portal.idao.IRegistrationCodeDao;
import com.bvtech.registration.portal.idao.IShippingToSendDao;

@Service
public class RegistrationPortalService {

	@Autowired					 
	private IRegistrationCodeDao registrationCodeDao;
	
	@Autowired
	private IProcessInformationDao processInformationDao;
	
	@Autowired
	private IPersonalInformationDao personalInformationDao;
	
	@Autowired
	private IProcessTypeAuthenticationDao processTypeAuthenticationDao;
	
	@Autowired
	private ILoginAttemptsDao loginAttemptsDao;
	
	@Autowired
	private ICommonUtility commonUtility;
	
	@Autowired
	private IPatientToSendDao patientToSendDao;
	
	@Autowired
	private IShippingToSendDao shippingToSendDao;
	
	@Autowired
	private IConfigDao configDao;

	final static Log log = LogFactory.getLog(RegistrationPortalService.class);
	
	public Boolean checkLoginAttempts(String ip) {

		String[] names =  {"idIp"};
		Object[] values =  {ip};
		LoginAttempts la= loginAttemptsDao.namedQuerySingleResult(LoginAttempts.NQ_GET_ALL_INFO_IP_ATTEMPTS, names, values);
		
		long diff = new Date().getTime() - la.getLastUpdate().getTime();
		long diffHours = diff / (60 * 60 * 1000);
		
		Integer lockedHours=Integer.valueOf(commonUtility.getConfigInfo(Configs.LOCKED_HOURS.getValue()).getValue());
		Integer maxLoginAttempts=Integer.valueOf(commonUtility.getConfigInfo(Configs.MAX_ATTEMPT_LOGIN.getValue()).getValue())-1;

		/*
		Config cn = commonService.getConfigInfo(Configs.LOCKED_HOURS.getValue());
		Integer lockedHours=Integer.valueOf(cn.getValue());
		cn = commonService.getConfigInfo(Configs.MAX_ATTEMPT_LOGIN.getValue());
		Integer maxLoginAttempts=Integer.valueOf(cn.getValue())-1;
		*/
		
		if (la.getAttempts()>maxLoginAttempts && diffHours<lockedHours) return false;
		return true;
	}
	
	public void insertLoginAttempts(String ip) {
		String[] names = {"idIp"};
		Object[] values = {ip};
		LoginAttempts la= loginAttemptsDao.namedQuerySingleResult(LoginAttempts.NQ_GET_ALL_INFO_IP_ATTEMPTS, names, values);
		
		if (la==null) {
			//insert login attempts
			LoginAttempts insLA = new LoginAttempts();
			insLA.setIdIp(ip);
			insLA.setAttempts(1);
			loginAttemptsDao.create(insLA);
		}
		else {
			long diff = new Date().getTime() - la.getLastUpdate().getTime();
			long diffHours = diff / (60 * 60 * 1000);
			
			Integer lockedHours=Integer.valueOf(commonUtility.getConfigInfo(Configs.LOCKED_HOURS.getValue()).getValue());
			Integer maxLoginAttempts=Integer.valueOf(commonUtility.getConfigInfo(Configs.MAX_ATTEMPT_LOGIN.getValue()).getValue())-1;
			
			/*
			Config cn = commonService.getConfigInfo(Configs.LOCKED_HOURS.getValue());
			Integer lockedHours=Integer.valueOf(cn.getValue());
			cn = commonService.getConfigInfo(Configs.MAX_ATTEMPT_LOGIN.getValue());
			Integer maxLoginAttempts=Integer.valueOf(cn.getValue())-1;
			*/
			
			if (la.getAttempts()>maxLoginAttempts && diffHours<lockedHours) return; // tentativi esauriti e entro le due ore dall'ultimo tentativo
			Integer attempts=la.getAttempts()+1;
			if (la.getAttempts()>maxLoginAttempts && diffHours>lockedHours){  // tentativi esauriti e dopo le due ore dall'ultimo tentativo
				attempts=1;
			}
			
			//aggiornare  login attempts
			names = new String[] {"attempts","lastUpdate","idIp"};
			values = new Object[] {attempts,getCurrentDate(),ip};
			loginAttemptsDao.update(LoginAttempts.NQ_UPD_IP_ATTEMPTS, names, values);
		}
	}
	

	
	private String getCurrentDate(){
		
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	public RegistrationCode CreateRegistrationCode() {
		RegistrationCode rc = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] timestamp = Long.toString(System.currentTimeMillis()).getBytes();
			byte[] md5data = md.digest(timestamp);
			String md5dataHex = "";
			
			for (int i = 0; i < md5data.length; i++) {
				md5dataHex += String.format("%02x", md5data[i]);
			}
			
			rc = new RegistrationCode();
			rc.setCode(md5dataHex);
			rc.setTypeCode("U");
			rc.setIdStaPro(1);
			rc.setSendEmail(0);
			
			registrationCodeDao.create(rc);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rc;
	}
	
	public RegistrationCode CreateRegistrationCode(String code) {
		RegistrationCode rc = null;
		rc = new RegistrationCode();
		rc.setCode(code);
		rc.setTypeCode("U");
		rc.setIdStaPro(1);
		rc.setSendEmail(0);
		
		registrationCodeDao.create(rc);
		
		return rc;
	}
	
	public RegistrationCode CreateRegistrationCode(String code, String vnr, PersonalInformation pi) {
		RegistrationCode rc = new RegistrationCode();
		rc.setCode(code);
		rc.setTypeCode("U");
		rc.setIdStaPro(1);
		rc.setSendEmail(0);
		rc.setVnr(vnr);
		
		registrationCodeDao.create(rc);
		
		//insert process info
		ProcessInformation procinf = new ProcessInformation();
		procinf.setIdRegCod(rc.getIdRegCod());
		procinf.setTargetUrl("/secure/screen3.html");
		
		processInformationDao.create(procinf);
		
		pi.setIdPerInf(procinf.getIdPerInf());
		Integer idPerInf= personalInformationDao.create(pi);
		
		procinf.setIdPerInf(idPerInf);
		
		processInformationDao.update(procinf);
		
		return rc;
	}
	
	public void RegistrationCode(List<Object> row) {
		
		//insert process info
		RegistrationCode rc = new RegistrationCode();
		rc.setIdRegCod((Integer) row.get(0));
		
		registrationCodeDao.create(rc);
	}
	
	public RegistrationCode getInfo(String registrationCode) {
		String[] names = { "registrationCode" };
		Object[] values = { registrationCode };
		
		return registrationCodeDao.namedQuerySingleResult(RegistrationCode.NQ_GET_ALL_INFO_BY_CODE, names, values);
	}
	
	public RegistrationCode getReRegistrationInfo(String reregisterCode) {
		String[] names = { "reregisterCode" };
		Object[] values = { reregisterCode };
		
		return registrationCodeDao.namedQuerySingleResult(RegistrationCode.NQ_GET_ALL_INFO_BY_REREGISTRATION_CODE, names, values);
	}
	
	public RegistrationCode getRejectCodeInfo(String rejectCode) {
		String[] names = { "rejectCode" };
		Object[] values = { rejectCode };
		
		return registrationCodeDao.namedQuerySingleResult(RegistrationCode.NQ_GET_ALL_INFO_BY_REJECT_CODE, names, values);
	}
	
	public ProcessInformation getProcessInformation(Integer registrationCodeId) {
		String[] names = { "registrationCodeId" };
		Object[] values = { registrationCodeId };
		
		return processInformationDao.namedQuerySingleResult(ProcessInformation.NQ_GET_PRO_INF_BY_CODE, names, values);
	}
	
	public ProcessTypeAuthentication getProcessTypeAuthentication(Integer idProInf) {
		String[] names = { "idProInf" };
		Object[] values = { idProInf };
		
		return processTypeAuthenticationDao.namedQuerySingleResult(ProcessTypeAuthentication.NQ_GET_ALL_PRO_TYP_AUT_BY_CODE, names, values);
	}
	
	public void insertSecuredInsertCode(Integer id) {
		//aggiornare registration code
		
		String[] names = { "idStaPro","lastUpdate","idRegCod"};
		Object[] values = { RegistrationCodeStatus.PARTIALLY_USED.getValue(),getCurrentDate(),id};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
		
//		//insert process info
//		ProcessInformation pi = new ProcessInformation();
//		pi.setIdRegCod(id);
//		pi.setTargetUrl("/secure/screen3.html");
//		processInformationDao.create(pi);
	}

	public void insertScreen3(Integer id,Integer idStaPro,Integer idTypDia,Integer idStaUse) {
	
		//aggiornare registration code
		String[] names = { "idStaPro","lastUpdate","idRegCod"};
		Object[] values = { idStaPro,getCurrentDate(),id};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
		
		String targetUrl="/secure/screen5.html";
		if (idStaUse.equals(UserStatus.DISQUALIFIED_IMMEDIATELY.getValue())) {
			registrationCodeDao.update(RegistrationCode.NQ_UPD_REG_COMPLETED, 
					new String[]{"registrationCompleted","idRegCod"},
					new Object[]{getCurrentDate(),id});
			
			targetUrl="/secure/endProcess.html";
		}
				
				
		//update process info
	
		String[] setColsNames={"targetUrl","idTypDia","idStaUse","acceptanceC"};
		String[] updNames = {"targetUrl","idTypDia","idStaUse","acceptanceC","idRegCod"};
		Object[] updValues = {targetUrl,idTypDia,idStaUse,1,id};
		
		processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
		
	}

	/**
	 * Metodo creato per gestire update multipli su processes_informations
	 * @return update formattata
	 */
	private String createHqlQueryProInf (String[] colsNames) {
		
		String setName="";
		for (String colName : colsNames) {
			setName= setName + " " + colName + "= :"+ colName +", ";
		}
		setName=setName.substring(0, setName.length()-2);
		return "UPDATE ProcessInformation pi SET " + setName + " WHERE pi.idRegCod= :idRegCod";
	}
	
	public void insertScreen5(PersonalInformation pi,Integer idRegCod) throws Exception {
		try {
			personalInformationDao.update(pi);
			
			//aggiornare registration code
			String[] names = { "idStaPro","lastUpdate","idRegCod"};
			Object[] values = {RegistrationCodeStatus.PARTIALLY_USED.getValue(),getCurrentDate(),idRegCod};
			registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
			
			//Integer idPerInf= personalInformationDao.create(pi);
			
			//aggiornare ProcessInformation
			String[] setColsNames={"targetUrl"};
			String[] updNames = {"targetUrl","idRegCod"};
			Object[] updValues = {"/secure/screen5b.html",idRegCod};
			
			processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertScreen5b(PersonalInformation pi,Integer idRegCod) throws Exception {
		try {
			personalInformationDao.update(pi);
			
			//aggiornare registration code
			String[] names = { "idStaPro","lastUpdate","idRegCod"};
			Object[] values = {RegistrationCodeStatus.PARTIALLY_USED.getValue(),getCurrentDate(),idRegCod};
			registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
			
			//Integer idPerInf= personalInformationDao.create(pi);
			
			//aggiornare ProcessInformation
			String[] setColsNames={"targetUrl"};
			String[] updNames = {"targetUrl","idRegCod"};
			Object[] updValues = {"/secure/screen7.html",idRegCod};
			
			processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ProcessInformation getIdPerInf(Integer idRegCod) {
		
		String[] names = {"registrationCodeId"};
		Object[] values = {idRegCod};
		
		return processInformationDao.namedQuerySingleResult(ProcessInformation.NQ_GET_PRO_INF_BY_CODE, names, values);
	}
	
	public void update(ProcessInformation processInformation) {
		
		processInformationDao.update(processInformation);
	}
	
	
	public ProcessInformation getIdProInf(Integer idRegCod) {
		
		String[] names = {"registrationCodeId"};
		Object[] values = {idRegCod};
		
		return processInformationDao.namedQuerySingleResult(ProcessInformation.NQ_GET_PRO_INF_BY_CODE, names, values);
	}
	
	
	public PersonalInformation getPersonalInformation(Integer idPerInf) {
		
		String[] names = {"idPerInf"};
		Object[] values = {idPerInf};
		PersonalInformation pi= personalInformationDao.namedQuerySingleResult(PersonalInformation.NQ_GET_ALL_PER_INF_BY_CODE, names, values);
		
		return pi;
	}

	public void insertScreen7(Integer idRegCod,Integer idTypSec1a,Integer idTypSec1b) {
		//aggiornare registration code
		String[] names = { "idStaPro","lastUpdate","idRegCod"};
		Object[] values = { RegistrationCodeStatus.PARTIALLY_USED.getValue(),getCurrentDate(),idRegCod};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
		
		
		//update process info
		String[] setColsNames={"targetUrl","questionSection1A","questionSection1B"};
		String[] updNames = {"targetUrl","questionSection1A","questionSection1B","idRegCod"};
		Object[] updValues = {"/secure/screen8.html",idTypSec1a,idTypSec1b,idRegCod};
		
		processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
		
	}
	
	public void insertScreen8(Integer idRegCod,Integer idTypSec2a,Integer idTypSec2b) {
		
		//aggiornare registration code
		String[] names = { "idStaPro","lastUpdate","idRegCod"};
		Object[] values = { RegistrationCodeStatus.PARTIALLY_USED.getValue(),getCurrentDate(),idRegCod};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
				
		//update process info
		String[] setColsNames={"targetUrl","questionSection2A","questionSection2B"};
		String[] updNames = {"targetUrl","questionSection2A","questionSection2B","idRegCod"};
		Object[] updValues = {"/secure/screen9.html",idTypSec2a,idTypSec2b,idRegCod};
		
		processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
		
	}
	
	public void insertScreen9(Integer idRegCod,Integer idTypBmiWeight,Integer idTypBmiHeight,double bmi) {
		
		//aggiornare registration code
		String[] names = { "idStaPro","lastUpdate","idRegCod"};
		Object[] values = { RegistrationCodeStatus.PARTIALLY_USED.getValue(),getCurrentDate(),idRegCod};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
		
		//update process info
		String[] setColsNames={"targetUrl","questionWeight","questionHeight","bmi"};
		String[] updNames = {"targetUrl","questionWeight","questionHeight", "bmi", "idRegCod"};
		Object[] updValues = {"/secure/screen10.html",idTypBmiWeight,idTypBmiHeight, bmi, idRegCod};
		
		processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
		
	}
	
	public void insertScreen10(Integer idRegCod,Integer questionInsuline ,Integer questionInsulineB) {
		
		//aggiornare registration code
		String[] names = { "idStaPro","lastUpdate","idRegCod"};
		Object[] values = { RegistrationCodeStatus.PARTIALLY_USED.getValue(),getCurrentDate(),idRegCod};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
		
		//update process info
	
		String[] setColsNames={"targetUrl","questionInsuline","questionInsulineB"};
		String[] updNames = {"targetUrl","questionInsuline", "questionInsulineB", "idRegCod"};
		Object[] updValues = {"/secure/screen11.html",questionInsuline, questionInsulineB, idRegCod};
		
		processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
		
	}
	
	public void updateScreen11b(Integer idPerInf,HashMap<String, Object> data) {
		
		//aggiornare ProcessInformation
		String[] names = {"name","surname","address","houseNumber","postalCode","city","addressAdditional",
				"note","optName","optSurname","optAddress","optAddressAdditional","optHouseNumber","optNote","optCity","optPostalCode",
				"corrBD","corrDA","idPerInf"};
		Object[] values = {data.get("name"),data.get("surname"),data.get("address"),data.get("houseNumber"),data.get("postalCode"),data.get("city"),data.get("addressAdditional"),
				data.get("note"),data.get("optName"),data.get("optSurname"),data.get("optAddress"),data.get("optAddressAdditional"),data.get("optHouseNumber"),
				data.get("optNote"),data.get("optCity"),data.get("optPostalCode"),
				data.get("corrBD"), data.get("corrDA"),idPerInf};

		personalInformationDao.update(PersonalInformation.NQ_UPD_PER_INFO_DELIVERY_ADDRESS, names, values);
				
	}
	
	public void endProcess(Integer idRegCod, Integer idStaUse) {
		
		String[] names= {"idStaPro","lastUpdate","idRegCod"};
		Object[] values = {RegistrationCodeStatus.USED.getValue(),getCurrentDate(),idRegCod};
		//aggiornare registration code
		registrationCodeDao.update(RegistrationCode.NQ_UPD_STA, names, values);
		
		names= new String[]{"registrationCompleted","idRegCod"};
		values = new Object[]{getCurrentDate(),idRegCod};
		//aggiornare registration code
		registrationCodeDao.update(RegistrationCode.NQ_UPD_REG_COMPLETED, names, values);
		
		
		//update process info
		String[] setColsNames={"targetUrl","idStaUse"};
		String[] updNames = {"targetUrl", "idStaUse","idRegCod"};
		Object[] updValues = {"/secure/end_process.html",idStaUse, idRegCod};
				
		processInformationDao.executeHqlUpdateQuery(createHqlQueryProInf(setColsNames),updNames, updValues);
		
		ProcessInformation pi = getProcessInformation(idRegCod);
		
		
		PatientToSend ps = new PatientToSend();
		ps.setIdRegCod(idRegCod);
		// GHS Change Request 22.10.2020
		//ps.setBodyHeight(pi.getQuestionHeight().toString());
		//ps.setBodyWeight(pi.getQuestionWeight().toString());
		
		PersonalInformation persInfo = getPersonalInformation(pi.getIdPerInf());
		
		log.info("endProcess getIdPerInf() " +persInfo.getName());
		log.info("endProcess getIdPerInf() " +persInfo.getOptName());
		
		
		ps.setGender(persInfo.getSex());
		ps.setFirstname(persInfo.getName());
		ps.setLastname(persInfo.getSurname());
		ps.setBirthDate(persInfo.getDateBirth());
		ps.setStatus("S");
		
		//insert patient to send
		if(idStaUse==UserStatus.ACCEPTED.getValue())
			patientToSendDao.create(ps);
		
		ShippingToSend ss = new ShippingToSend();
		
		// Raffaele - 05.01.2017 - Christian W. asks me to add this two field (skype on 05.01.2017 at 15:50)
		RegistrationCode rc = registrationCodeDao.findById(idRegCod);
		ss.setIdRegCod(idRegCod);
		ss.setCode(rc.getCode());
		ss.setTypeCode(rc.getTypeCode());
		ss.setRegistrationCompleted(rc.getRegistrationCompleted());
		
		if(persInfo.getOptName() != null && !persInfo.getOptName().trim().isEmpty()) {
				//get optional info
				ss.setFirstname(persInfo.getOptName());
				ss.setLastname(persInfo.getOptSurname());
				ss.setAddress(persInfo.getOptAddress());
				ss.setHouseNumber(persInfo.getOptHouseNumber());
				ss.setCity(persInfo.getOptCity());
				ss.setPostalCode(persInfo.getOptPostalCode());
				ss.setAdditions(persInfo.getOptAddressAdditional());
				ss.setAdditionalHints(persInfo.getOptNote());
			}else {
				ss.setFirstname(persInfo.getName());
				ss.setLastname(persInfo.getSurname());
				ss.setAddress(persInfo.getAddress());
				ss.setHouseNumber(persInfo.getHouseNumber());
				ss.setCity(persInfo.getCity());
				ss.setPostalCode(persInfo.getPostalCode());
				ss.setAdditions(persInfo.getAddressAdditional());
				ss.setAdditionalHints(persInfo.getNote());
			}
		
		ss.setStatus("S");

		if(idStaUse==UserStatus.ACCEPTED.getValue())
			shippingToSendDao.create(ss);
		
	}

	public void useReRegisterCodeByCoach(Integer idRegCod){

		ProcessInformation pi = getProcessInformation(idRegCod);
		PatientToSend ps = new PatientToSend();
		ps.setIdRegCod(idRegCod);
		ps.setBodyHeight(pi.getQuestionHeight().toString());
		ps.setBodyWeight(pi.getQuestionWeight().toString());
		
		PersonalInformation persInfo = getPersonalInformation(pi.getIdPerInf());
		ps.setGender(persInfo.getSex());
		ps.setFirstname(persInfo.getName());
		ps.setLastname(persInfo.getSurname());
		ps.setBirthDate(persInfo.getDateBirth());
		ps.setStatus("S");
		
		patientToSendDao.create(ps);			
		
		ShippingToSend ss = new ShippingToSend();
		
		// Raffaele - 05.01.2017 - Christian W. asks me to add this two field (skype on 05.01.2017 at 15:50)
		RegistrationCode rc = registrationCodeDao.findById(idRegCod);
		ss.setIdRegCod(idRegCod);
		ss.setCode(rc.getCode());
		ss.setTypeCode(rc.getTypeCode());
		ss.setRegistrationCompleted(rc.getRegistrationCompleted());
		
		if (!persInfo.getOptName().equals("")) {
			//get optional info
			ss.setFirstname(persInfo.getOptName());
			ss.setLastname(persInfo.getOptSurname());
			ss.setAddress(persInfo.getOptAddress());
			ss.setHouseNumber(persInfo.getOptHouseNumber());
			ss.setCity(persInfo.getOptCity());
			ss.setPostalCode(persInfo.getOptPostalCode());
			ss.setAdditions(persInfo.getOptAddressAdditional());
			ss.setAdditionalHints(persInfo.getOptNote());
		}
		else {
			ss.setFirstname(persInfo.getName());
			ss.setLastname(persInfo.getSurname());
			ss.setAddress(persInfo.getAddress());
			ss.setHouseNumber(persInfo.getHouseNumber());
			ss.setCity(persInfo.getCity());
			ss.setPostalCode(persInfo.getPostalCode());
			ss.setAdditions(persInfo.getAddressAdditional());
			ss.setAdditionalHints(persInfo.getNote());
		}
		ss.setStatus("S");

		shippingToSendDao.create(ss);
		
	}
	
	public void insertGenCode(String reregister,String reject, Integer idRegCod) {

		//aggiornare registration code
		String[] names = {"reregisterCode","rejectCode","idRegCod"};
		Object[] values = {reregister,reject,idRegCod};
		registrationCodeDao.update(RegistrationCode.NQ_UPD_GEN_CODE, names, values);
		
	}
	
	public void update(RegistrationCode rc){
		registrationCodeDao.update(rc);
	}
	
	// Aggiunto per la gestione corretta del link di validazione
	public Integer getIdRegCodeByIdProInf(String idProInf) {
		String[] names = { "idProInf"};
		Object[] values = { idProInf };
		
		ProcessInformation proinf= processInformationDao.namedQuerySingleResult(ProcessInformation.NQ_GET_CODE_BY_PRO_INF,names, values);
		return (proinf==null?null:proinf.getIdRegCod());
	}
}