package org.mycore.mir.backend.restservices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

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

	private static final String DEFAULT_NAVIGATION = "config/navigation.xml";

	@Context
	HttpServletRequest request;

	@GET
	@Path("/getDefault")
	@Produces(MediaType.APPLICATION_XML)

	/**
	 * 
	 * http://localhost:8080/mirServices/navigationService/getDefault
	 * 
	 * @return
	 */
	public String getDefaultNavigation() {

		ClassLoader classLoader = getClass().getClassLoader();
		URL navigationResource = classLoader.getResource(DEFAULT_NAVIGATION);

		/*
		 * look for best performance reading stream
		 * https://stackoverflow.com/questions/309424/read-convert-an-
		 * inputstream-to-a-string
		 */
		ByteArrayOutputStream result = new ByteArrayOutputStream();

		try {
			InputStream navigationStream = navigationResource.openStream();

			byte[] buffer = new byte[1024];
			int length;
			while ((length = navigationStream.read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			// StandardCharsets.UTF_8.name() > JDK 7

		} catch (IOException e) {

			logger.error("Input Output Error to get default navigation.xml");
		}

		try {
			return result.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {

			logger.error("Unsupported Encoding UTF-8");
		}

		return result.toString();
	}
}
