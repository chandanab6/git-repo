package com.chandan.util;

import org.apache.log4j.Logger;


public class HttpResponse
{
	private static Logger logger = Logger.getLogger(HttpResponse.class);
	private HttpStatus httpStatus;
	private String description;
	
	public HttpResponse()
	{
		this.httpStatus = HttpStatus.SC_200;
		this.description = WordCountServiceConstants.EMPTY_STRING;
	}
	
	public HttpResponse(HttpStatus statusCode)
	{
		this.httpStatus = statusCode;
		this.description = WordCountServiceConstants.EMPTY_STRING;
	}
	
	public HttpResponse(HttpStatus statusCode, String description)
	{
		this.httpStatus = statusCode;
		this.description = description;
	}
	
	public String toString()
    {
        return (httpStatus.message + " " + description);
    }
	
	public HttpStatus getHttpStatus()
	{
		return this.httpStatus;
	}

	public boolean setHttpStatus(HttpStatus statusCode)
	{
		this.httpStatus = statusCode;
		return true;
	}
	
	public int getCode()
    {
        return httpStatus.code;
    }
	
	public String getDescription()
	{
		return this.description;
	}
    
	public void setDescription(String desc)
	{
		this.description = (desc != null) ? desc : WordCountServiceConstants.EMPTY_STRING;
	}
	
	public void overrideFrom(HttpResponse httpResponse)
	{
		if (httpResponse != null)
		{
			this.httpStatus = httpResponse.httpStatus;
			this.description = httpResponse.getDescription();
		}
	}
	
	public boolean isFailureStatus()
	{
		if( this.httpStatus.code >= 500 )
		{
			return true;
		}
		
		return false;
	}
    
    public enum HttpStatus
	{
    	SC_200(200, "Ok."),
		SC_201(201, "Created."),
		SC_202(202, "Accepted."),
		SC_204(204, "No Content."),
		SC_304(304, "Not Modified."),
		SC_400(400, "Bad Request."), 
	    SC_401(401, "Unauthorized."),
	    SC_403(403, "Forbidden."),
	    SC_404(404, "Not found."),
	    SC_405(405, "Method Not Allowed."), 
	    SC_408(408, "Request Timeout."),
	    SC_409(409, "Conflict."),
	    SC_415(415, "Unsupported Media Type."), 
	    SC_500(500, "Internal Server Error."),
	    SC_501(501, "Not Implemented."),
	    SC_503(503, "Service Unavailable."),
	    SC_507(507, "Insufficient Storage.");
		
		private int code;
	    private String message;
		
		private HttpStatus(int code, String message)
	    {
	        this.code = code;
	        this.message = message;
	    }
		
		public int getCode()
	    {
	        return code;
	    }

	    public String getMessage()
	    {
	        return message;
	    }
	    
	    public static HttpStatus fromErrorCode(int errorCode)
	    {
	    	switch(errorCode)
	    	{
	    		case 200:
	    			return SC_200;
	    		case 201:
	    			return SC_201;
	    		case 202:
	    			return SC_202;
	    		case 204:
	    			return SC_204;
	    		case 304:
	    			return SC_304;
	    		case 400:
	    			return SC_400;
	    		case 401:
	    			return SC_401;
	    		case 403:
	    			return SC_403;
	    		case 404:
	    			return SC_404;
	    		case 405:
	    			return SC_405;
	    		case 408:
	    			return SC_408;
	    		case 409:
	    			return SC_409;
	    		case 415:
	    			return SC_415;
	    		case 500:
	    			return SC_500;
	    		case 501:
	    			return SC_501;
	    		case 503:
	    			return SC_503;
	    		case 507:
	    			return SC_507;
	    		default:
	    			logger.error("Unmapped error code: " + errorCode );
	    			return SC_500;
	    	}
	    }
	}
}
