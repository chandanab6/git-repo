package com.chandan.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.core.Response;

/**
 * DOCUMENT ME!
 *
 * @author 
 */
public enum AccessLogger
{
	INSTANCE;
	
    private static final Logger logger = LoggerFactory.getLogger(AccessLogger.class);

    public void log(HttpServletRequest servletRequest, Response response, long startTime)
    {
        log(servletRequest, null, response, 0,startTime);
    }
    
    public void log(HttpServletRequest servletRequest, String extraInformation, Response response, long startTime)
    {
        log(servletRequest, extraInformation, response, 0, startTime);
    }
    
    public void log(HttpServletRequest servletRequest, Response response, long responseSize, long startTime)
    {
        log(servletRequest, null, response, responseSize, startTime);
    }
    
    public void log(HttpServletRequest servletRequest, String extraInformation, Response response, long responseSize, long startTime)
    {
        // Get remoteIP and Port.
        StringBuilder msg = new StringBuilder("remoteIP=" + servletRequest.getRemoteAddr() + ":" + servletRequest.getRemotePort());
        // Append Method (GET/POST/DELETE)
        msg.append(WordCountServiceConstants.TAB + "method=" + servletRequest.getMethod());
        // Append requested URL.
        msg.append(WordCountServiceConstants.TAB + "url=" + servletRequest.getRequestURL().toString());

        //Add extra information
        if(extraInformation != null)
        {
        	msg.append(extraInformation);
        }

        // Add request size
        int requestSize = servletRequest.getContentLength();
    	msg.append(WordCountServiceConstants.TAB + "requestSize=" + (requestSize >=0 ? requestSize : 0));
        
        // Add response size
    	msg.append(WordCountServiceConstants.TAB + "responseSize=" + responseSize);
    	
    	// add response code + time taken to server request
        msg.append(WordCountServiceConstants.TAB + "status=" + response.getStatus() + WordCountServiceConstants.TAB + "respTime=" + (System.currentTimeMillis() - startTime));
        logger.info(msg.toString());
    }
}
