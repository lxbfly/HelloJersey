package com.io.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sample.hello.utils.FileUtils;
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
			@Context HttpServletRequest httpRequest,
			@Context HttpServletResponse httpResponse) {
		log.debug("mobileNo : " + mobileNo
				+ "enter uploadResource method at Class of IoResource ");
		try {
			log.debug("fileInfo : " + fileInfo);
			if (uploadedInputStream == null) {
				throw new NotFoundException("Don't find file !");
			}
			String fileName = fileInfo.getFileName();
			byte[] inStream = IOUtils.toByteArray(uploadedInputStream);
			if (inStream == null || "".equals(fileName)) {
				throw new NotFoundException("upload file is Null !");
			}
			// output server upload directoty
			String outFilePath = httpRequest.getSession().getServletContext()
					.getRealPath("upload");
			FileUtils.writeFile(inStream, outFilePath + File.separator
					+ fileName);
			URI uri = uriInfo.getAbsolutePathBuilder().build();
			Response.created(uri).build();
			httpResponse
					.sendRedirect("http://localhost:8080/HelloJersey/pages/uploadfile.jsp");

			/**
			 * get Server variable path
			 */
			System.out.println("Scheme : " + httpRequest.getScheme());
			System.out.println("serverName : " + httpRequest.getServerName());
			System.out.println("serverPort : " + httpRequest.getServerPort());
			System.out.println("contextPath : " + httpRequest.getContextPath());
			System.out.println("servletPath : " + httpRequest.getServletPath());
			System.out.println("RequestURL : " + httpRequest.getRequestURL());
			System.out.println("RequestURI : " + httpRequest.getRequestURI());
			System.out.println("RealPath : "
					+ httpRequest.getSession().getServletContext()
							.getRealPath(""));
		} catch (Exception e) {
			log.debug("upload faild !", e);
		}
		return null;
	}

	@GET
	@Path("download/{ rsId }")
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	public HttpServletResponse downloadResource(@PathParam("rsId") String rsId,
			@Context HttpServletRequest httpRequest,
			@Context HttpServletResponse response)
			throws WebApplicationException {
		InputStream inStream = null;
		OutputStream outStream = null;
		try {
			String filePath = httpRequest.getSession().getServletContext()
					.getRealPath("upload");
			if ("".equals(rsId)) {
				rsId = "abc.log";
			}
			File file = new File(filePath + File.separator + rsId.trim());
			if (!file.exists()) {
				throw new NotFoundException("Don't find " + rsId + " Resource.");
			}
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ rsId + "\"");
			response.setContentType("application/octet-stream");

			inStream = new BufferedInputStream(new FileInputStream(file));
			outStream = new BufferedOutputStream(response.getOutputStream());
			byte[] b = new byte[1024 * 10];
			int len = 0;
			while ((len = inStream.read(b)) != -1) {
				outStream.write(b, 0, len);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			FileUtils.close(inStream);
			FileUtils.close(outStream);
		}
		return null;
	}
}
