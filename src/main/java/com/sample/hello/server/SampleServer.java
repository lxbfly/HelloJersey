package com.sample.hello.server;

import java.io.IOException;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class SampleServer {
	private static final Logger log = Logger.getLogger(SampleServer.class);
	public static final URI BASE_URI = getBaseURI();

	private static URI getBaseURI() {
		// return UriBuilder.fromUri("http://localhost/").port(9998).build();
		return UriBuilder.fromUri("http://localhost/").port(getPort(9998))
				.build();
	}

	protected static HttpServer startServer() {
		log.debug("Starting Grizzly Server  ... ");
		HttpServer httpServer = null;
		ResourceConfig rc = new PackagesResourceConfig(
				"com.sample.hello.resources");
		try {
			httpServer = GrizzlyServerFactory.createHttpServer(BASE_URI, rc);
		} catch (IllegalArgumentException e) {
			log.debug("exist illegal argument !");
			e.printStackTrace();
		} catch (NullPointerException e) {
			log.debug("Null Poioner !");
			e.printStackTrace();
		} catch (IOException e) {
			log.debug("Don't find Resource !");
			e.printStackTrace();
		}
		return httpServer;
	}

	private static int getPort(int defaultPort) {
		String port = System.getProperty("jersey.test.port");
		if (null != port) {
			try {
				return Integer.parseInt(port);
			} catch (NumberFormatException e) {
				log.debug("port is error!");
				e.getStackTrace();
			}
		}
		return defaultPort;
	}

	public static void main(String[] args) {
		HttpServer httpServer = startServer();
		log.debug(String
				.format("Jersey app started with WADL available at "
						+ "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
						BASE_URI, BASE_URI));
		try {
			System.in.read();
		} catch (IOException e) {
			log.debug("Don't read Resource,Maybe this resource is not exist .");
			e.printStackTrace();
		}
		httpServer.stop();
	}
}
