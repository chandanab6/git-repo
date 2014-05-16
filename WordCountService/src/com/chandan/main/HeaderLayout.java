package com.chandan.main;

import org.apache.log4j.PatternLayout;

public class HeaderLayout extends PatternLayout
{  
    
    private String header;  
  
    public void setHeader(String header)
    {  
        this.header = header;  
    }  
  
    public String getHeader()
    {  
    	StringBuilder sb = new StringBuilder("\n\n*******************************************************************************************************************************");
        if (this.header != null && !this.header.isEmpty())
        {
        	sb.append("\n 							"+this.header);        	        	
        }
        sb.append("\n 							WordCountService Starting										 ");
        sb.append("\n*******************************************************************************************************************************\n");
        return sb.toString();
    }  
}
