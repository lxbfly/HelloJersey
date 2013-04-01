package com.io.resources;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/io")
public class IoResource {
	private static final Logger log = Logger.getLogger(IoResource.class);
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@POST
	@Path("upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadResource(@FormDataParam("mobileNo") String mobileNo,
			@FormDataParam("fileUpload") InputStream uploadedInputStream,
			@FormDataParam("fileUpload") FormDataContentDisposition fileInfo,
			@Context HttpServletRequest httpRequest) {
		log.debug("mobileNo : " + mobileNo
				+ "enter uploadResource method at Class of IoResource ");
		try {
			log.debug("fileInfo : " + fileInfo);
			if (uploadedInputStream == null) {
				throw new NotFoundException("Don't find file !");
			}
			String fileName = fileInfo.getFileName();
			byte[] inStream = IOUtils.toByteArray(uploadedInputStream);
			System.out.println(httpRequest.getServletPath());
			// output server upload directoty
			// 获取类的当前目录

			// ResponseBuilder builder = Response.ok().language("en");
			// builder.header("dlUrl", "http://lxbfly.sinaapp.com/");
			// return builder.build();
		} catch (Exception e) {
			log.debug("upload faild !", e);
		}
		return null;
	}
}
