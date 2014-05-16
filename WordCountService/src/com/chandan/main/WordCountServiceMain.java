package com.chandan.main;

import org.apache.log4j.Logger;

import com.chandan.configuration.WordCountConfiguration;
import com.chandan.util.WordCountServiceConstants;
import com.chandan.util.WordCountServiceConstants.EnumTrueFalse;
import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class WordCountServiceMain {

	/**
	 * @param args
	 */
	private static Logger logger = Logger.getLogger(WordCountServiceMain.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			WordCountConfiguration.INSTANCE.loadWordCountProperties();




			logger.info("Starting grizzly...");

			GrizzlyWebServer server = new GrizzlyWebServer(getBasePort());
			server.setCoreThreads( WordCountServiceConstants.MAX_CORE_THREADS );
			server.setMaxThreads( WordCountServiceConstants.MAX_THREADS );


			// Create our REST service
			ServletAdapter jerseyAdaptor = new ServletAdapter();
			jerseyAdaptor.setServletInstance( new ServletContainer() );

			// Tell jersey where to find REST resources
			jerseyAdaptor.addInitParameter( "com.sun.jersey.config.property.packages",
					"com.chandan.resources" );
			jerseyAdaptor.addInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", 
					"com.sun.jersey.api.container.filter.LoggingFilter");
			jerseyAdaptor.addInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters",
					"com.sun.jersey.api.container.filter.LoggingFilter");
			jerseyAdaptor.addInitParameter("com.sun.jersey.config.feature.logging.DisableEntitylogging",
					EnumTrueFalse.TRUE.toString());

			jerseyAdaptor.setServletPath( "" );

			server.addGrizzlyAdapter( jerseyAdaptor, new String[]{"/"} );

			server.start();

			logger.info("Grizzly Started...");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int getBasePort()
	{
		String hostAddr =  WordCountConfiguration.INSTANCE.getHostAddr();
		String[] hostAddrSplit = hostAddr.split(":");
		if(hostAddrSplit.length == 2)
		{
			return Integer.parseInt(hostAddrSplit[1]);
		}

		return WordCountServiceConstants.DEFAULT_PORT;
	}
}
