package com.bvtech.registration.portal.provider;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionListener implements HttpSessionListener {

	Log log = LogFactory.getLog(this.getClass());
	
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // ..   event.getSession().getAttribute("xxxx")
        event.getSession().setMaxInactiveInterval(30*60); // 30 min session timeout
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
       log.debug("Session ["+event.getSession().getId()+"] is destroyed");
    }
}