package com.chandan.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordCountConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(WordCountConfiguration.class);
	
	public static WordCountConfiguration INSTANCE 	= new WordCountConfiguration();
	private String corpusFilePath  					= "./data";
	
	private String hostAddr 						= "";
	private String crossdomainFilepath  			= "crossdomain.xml";
	
	//Private non-parameterised Constructor
	private WordCountConfiguration(){
		
	}
	
	public void loadWordCountProperties() throws Exception
	{
		PropertyConfigurator.configure("config/log4j.properties");
		File f = new File("config/config.properties");
		Properties p = new Properties();

		try
		{
			p.load(new FileInputStream(f));
			setCorpusFilePath(p.getProperty("corpusPath"));
			setHostAddr(p.getProperty("hostAddress"));            
			setCrossdomainFilepath(p.getProperty("crossdomainFilepath"));

		}
		catch(Exception e)
		{
			logger.error("Failed to read the config properties [{}]", new Object[] {e.getMessage()});
			throw e;
		}
	}
	public void setCorpusFilePath(String path){
		corpusFilePath = path;
	}
	
	public String getCorpusFilePath(){
		return corpusFilePath;
	}
	
	public void setHostAddr(String hostAddr) throws Exception 
	{
		// TODO
		if (hostAddr == null || hostAddr.isEmpty())			
		{
			logger.error("Host address not given.");
			throw new Exception("Host address not given.");
		}
		this.hostAddr = hostAddr;		
	}

	public String getHostAddr() 
	{		
		return this.hostAddr;
	}


	public void setCrossdomainFilepath(String crossdomainFilepath)
	{
		if (crossdomainFilepath != null && !crossdomainFilepath.isEmpty())
		{
			this.crossdomainFilepath = crossdomainFilepath;
		}
	}
	
	public String getCrossdomainFilepath() 
	{
		return crossdomainFilepath;
	}
}
