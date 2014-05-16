package com.chandan.resources;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.chandan.configuration.WordCountConfiguration;
import com.chandan.util.AccessLogger;
import com.chandan.util.HttpResponse.HttpStatus;
import com.chandan.util.Result;
import com.chandan.util.WordCount;

@Path("/")
public class WordCountServiceController {


	//private static final Logger logger = LoggerFactory.getLogger(WordCountServiceController.class);



	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
	@Path("crossdomain.xml")
	public Response getAnythingExtra(@Context HttpServletRequest servletRequest) 
	{
		long startTime = System.currentTimeMillis();


		ResponseBuilder responseBuilder;
		String filePath = WordCountConfiguration.INSTANCE.getCrossdomainFilepath();
		if (filePath != "")
		{
			File crossdomain = new File(filePath);
			if (crossdomain.exists())
			{
				responseBuilder = Response.ok(crossdomain, MediaType.APPLICATION_XML);                 
			}
			else
			{
				responseBuilder = Response.status(HttpStatus.SC_404.getCode());
			}
		}       
		else
		{
			responseBuilder = Response.status(HttpStatus.SC_404.getCode());
		}

		Response response = setNoCacheHeaders(responseBuilder).build();
		AccessLogger.INSTANCE.log(servletRequest, response, startTime);

		return response;
	}



	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("echo/{param}")
	public Response getMsg(@Context
			HttpServletRequest servletRequest, @PathParam("param")
	String msg)
	{
		long startTime = System.currentTimeMillis();


		String output = msg;

		// create response
		ResponseBuilder responseBuilder = Response.status(HttpStatus.SC_200.getCode()).entity(output);
		Response response = setNoCacheHeaders(responseBuilder).build();            

		AccessLogger.INSTANCE.log(servletRequest, response, startTime);
		return response;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/getCount")
	public Response countWord(@Context
			HttpServletRequest servletRequest, @QueryParam("query") final String word
			)
	{
		long startTime = System.currentTimeMillis();

		ResponseBuilder responseBuilder;

		int count = 0;

		try {

			count = WordCount.getWordCount(word);
			Result output =  new Result();
			output.setCount(count);
			// create response
			responseBuilder = Response.ok(output, MediaType.APPLICATION_JSON);

			Response response = setNoCacheHeaders(responseBuilder).build();            

			AccessLogger.INSTANCE.log(servletRequest, response, startTime);
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			responseBuilder = Response.status(HttpStatus.SC_500.getCode());
			Response response = setNoCacheHeaders(responseBuilder).build();            

			AccessLogger.INSTANCE.log(servletRequest, response, startTime);
			return response;
		}
	}


	private ResponseBuilder setNoCacheHeaders(ResponseBuilder responseBuilder)
	{
		if (responseBuilder != null)
		{
			return responseBuilder.header(
					"Cache-Control", "no-cache, no-store, private, no-transform, must-revalidate, max-age=0, proxy-revalidate, s-maxage=0")
					.header("Expires", "0").header("Pragma", "no-cache");
		}
		else
		{
			responseBuilder =  Response.status(HttpStatus.SC_500.getCode());
			return setNoCacheHeaders(responseBuilder);
		}
	}



}
