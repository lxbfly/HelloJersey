package com.sample.hello.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//The Java class will be hosted at the URI path "/hello"
@Path("/hello")
public class HelloResource {
	// The Java method will process HTTP GET requests
	@GET
	// The Java method will produce content identified by the MIME Media
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Hello Jersey";
	}
}
