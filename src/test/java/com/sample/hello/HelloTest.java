package com.sample.hello;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

public class HelloTest extends JerseyTest {

	public HelloTest() {
		super("com.sample.hello.resources");
	}

	@Test
	public void testHello() {
		// 通过Client获取资源
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:9998");

		String responseMsg = webResource.path("hello").get(String.class);
		assertEquals("Hello Jersey", responseMsg);
	}

}
