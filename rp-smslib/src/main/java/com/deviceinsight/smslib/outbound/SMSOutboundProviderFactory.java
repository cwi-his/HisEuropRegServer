package com.deviceinsight.smslib.outbound;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SMSOutboundProviderFactory {

	public ISMSOutboundProvider getProviderByFile (String configFile, String name) throws Exception {
		if (!configFile.startsWith("file:")) configFile = "file:"+configFile;
		return (ISMSOutboundProvider) new FileSystemXmlApplicationContext(configFile).getBean(name);
	}
	
	public ISMSOutboundProvider getProviderByClasspath (String classpath, String name) throws Exception {
		return (ISMSOutboundProvider) new ClassPathXmlApplicationContext(classpath).getBean(name);
	}	

}
