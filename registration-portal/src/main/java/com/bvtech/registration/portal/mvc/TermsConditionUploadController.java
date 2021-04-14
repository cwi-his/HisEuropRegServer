package com.bvtech.registration.portal.mvc;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvtech.registration.portal.service.IntegrationService;

@RestController
@Scope("request")
public class TermsConditionUploadController {

	Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * Upload single file using Spring Controller
	 */
	@Autowired
	IntegrationService integrationService;
	
	@RequestMapping(value = "/termsconditions/upload", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") File file, String typeCode) {
		if(file==null) return "Your file is null";
		if(typeCode==null) return "File type is null";
		if(!typeCode.equals("1")&&
		 !typeCode.equals("2")&&
		 !typeCode.equals("3")
		 &&!typeCode.equals("4")) return "file type not found"; 
				
		if (integrationService.writeFileTermsConditions(file, typeCode)) {
			return "You successfully uploaded file=" + file.getName();
		} else {return "You failed to upload " + file.getName();}
			
	}
	
}
