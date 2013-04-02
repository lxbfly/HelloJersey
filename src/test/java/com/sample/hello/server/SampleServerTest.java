package com.sample.hello.server;

import static org.junit.Assert.*;

import org.junit.Test;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.JerseyTest;

public class SampleServerTest extends JerseyTest {

	public SampleServerTest() {
		super("com.sample.hello.resources");
	}

	@Test
	public void testHello() {
		// 通过Client获取资源
		// Client client = Client.create();
		// Client client = client();
		// WebResource webResource = client.resource("http://localhost:9998");

		// JerseyTest Method
		WebResource webResource = resource();
		String responseMsg = webResource.path("hello").get(String.class);
		assertEquals("Hello Jersey", responseMsg);
		// 是错误 的情况
		// assertEquals("Hello Jer2sey", responseMsg);
	}

	@Test
	public void testApplicationWadl() {
		WebResource webResource = resource();
		String serviceWadl = webResource.path("application.wadl")
				.accept(MediaTypes.WADL).get(String.class);

		assertTrue(serviceWadl.length() > 0);
	}
}
