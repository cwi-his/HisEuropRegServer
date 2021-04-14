package com.bvtech.registration.portal.mvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bvtech.integration.bean.LogisticDeliveryInformation;
import com.bvtech.integration.bean.Voucher;
import com.bvtech.integration.bean.VoucherOperationResult;
import com.bvtech.registration.portal.bean.RegistrationCode;
import com.bvtech.registration.portal.bean.ShippingToSend;
import com.bvtech.registration.portal.service.IntegrationService;

@RestController
@Scope("request")
public class IntegrationManagerController {
	
	private static String NO_VOUCHER_LIST = "Voucher list null";
	private static String VOUCHER_INSERTED = "Total voucher [1] - Voucher in error [2]";
	private static String EXISTING_CODE = "Existing Code";
	
	Log log = LogFactory.getLog(this.getClass());

	@Autowired
	IntegrationService integrationService;
	
	@RequestMapping(value = "voucher/manager", method = RequestMethod.POST)
	public VoucherOperationResult vManager(@RequestBody ArrayList<Voucher> vouchers) {
		
		log.info("vManager - Received new vouchers list (start)");
		if(vouchers==null || vouchers.isEmpty()){
			log.info("vManager - "+NO_VOUCHER_LIST+" (end)");
			return new VoucherOperationResult("0101",NO_VOUCHER_LIST, null);
		}
		
		int size = vouchers.size();	
		log.info("vManager - Voucher list size: ["+size+"]");
		String errorcode="0000";
		List<String> duplicatedCodes = new ArrayList<String>();
		RegistrationCode registrationCode;
		for (Voucher voucher : vouchers) {
			registrationCode = new RegistrationCode();
			registrationCode.setCode(voucher.getCode());
			registrationCode.setInsertDate(new Date());
			List<RegistrationCode> rcsFromDB = integrationService.getAllInfoByUserCode(voucher.getCode());
			if(rcsFromDB!=null && rcsFromDB.size()>0){
				integrationService.insertRefusedCodes(registrationCode, EXISTING_CODE);
				log.debug("Rejected Code ["+registrationCode.getCode()+"] Type ["+registrationCode.getTypeCode()+"] - Rejected reason ["+EXISTING_CODE+"] - Code ["+registrationCode.getCode() +"]");
				duplicatedCodes.add(registrationCode.getCode());
				errorcode="0999";
			} else {
				registrationCode.setTypeCode(voucher.getTypCode());
				integrationService.insertRegCod(registrationCode);
				log.debug("Inserted Code ["+registrationCode.getCode()+"] Type ["+registrationCode.getTypeCode()+"]");
			} 
		}
		
		String message = VOUCHER_INSERTED.replaceFirst("1", ""+size).replaceFirst("2", ""+duplicatedCodes.size());
		log.info("vManager - " + message + " (end)");
		
		return new VoucherOperationResult(errorcode, message, duplicatedCodes);
	}
	
	// Raffaele 08.02.2017 - Christian W. Ask me to change RequestMethod from POST to GET for nginx proxy issue
	@RequestMapping(value = "delivery/manager", method = RequestMethod.GET)
	public List<LogisticDeliveryInformation> dManager() {
		
		List<LogisticDeliveryInformation> lds = new ArrayList<LogisticDeliveryInformation>(0);
		
		List<ShippingToSend> ss=  integrationService.getShippingToSend("S"); // thi query take all shipment to send where status is S and patientID is not null
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Iterator<ShippingToSend> it = ss.iterator();
		while (it.hasNext()) {
			
			ShippingToSend sts = it.next();
			LogisticDeliveryInformation ld = new LogisticDeliveryInformation();
			
			ld.setDeliveryAdressFirstName(sts.getFirstname());
			ld.setDeliveryAdressLastName(sts.getLastname());
			ld.setDeliveryAdressStreetName(sts.getAddress());
			ld.setDeliveryAdressHouseNumber(sts.getHouseNumber());
			ld.setDeliveryAdressAdditions(sts.getAdditions());
			ld.setDeliveryAdressCityName(sts.getCity());
			ld.setDeliveryAdressZipCode(sts.getPostalCode());
			ld.setDeliveryAdressAdditionalHints(sts.getAdditionalHints());
			ld.setDeliveryAdressPatientId(sts.getIdPatient());
			

			// Raffaele - 05.01.2017 - Christian W. asks me to add this two fields (skype on 05.01.2017 at 15:50)
			ld.setVoucherCode(sts.getCode());
			ld.setTypeCode(sts.getTypeCode());
			
			// Raffaele - 12.01.2017 - Christian W. asks me to add this one other field
			if (sts.getRegistrationCompleted() != null)
				ld.setRegistrationCompletedDateTime(sdf.format(sts.getRegistrationCompleted()));
			
			lds.add(ld);
			
			//change status tab shipping_to_send
			integrationService.updateShippingStatus(sts.getIdPatient(), "D");
		}
		
		return lds;
	}

	

}
