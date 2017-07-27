package org.mycore.mir.backend.restservices;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Path("/navigationService")
public class NavigationService {

	private static final Logger logger = LogManager.getLogger(NavigationService.class);

	@Context
	HttpServletRequest request;
	
	@GET
	@Path("/getDefault")
	@Produces(MediaType.APPLICATION_JSON)
	
	/**
	 * 
	 * http://localhost:8080/mirServices/navigationService/getDefault
	 * @return
	 */
	public String helloWorldService() {

		return "Hello World";
	}
}
