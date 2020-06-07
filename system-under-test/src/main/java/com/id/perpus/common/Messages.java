package com.id.perpus.common;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Messages {

	private final Logger logger = LoggerFactory.getLogger(Messages.class);
	
	@Autowired
    private MessageSource messageSource;

    public String get(String code) {
    	Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code,null, locale);
    }
    
    public String find(String code){
    	try {
    		return get(code);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
    }
    
    public String find(String code, String param){
    	try {
    		String message = get(code);
        	return Common.toString(message).replace(":0",param);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
    }
    
    public String find(String code, String param, String param2){
    	try {
    		String message = get(code);
    		return Common.toString(message).replace(":0",param).replace(":1",param2);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
    }
    
    public String find(String code, String param, String param2, String param3){
    	try {
    		String message = get(code);
    		return Common.toString(message).replace(":0",param).replace(":1",param2).replace(":2",param3);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "";
		}
    }
    
}
