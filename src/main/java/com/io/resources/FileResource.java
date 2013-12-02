package com.io.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("file")
public class FileResource {

    @GET
    @Path("{imageName}")
    @Produces("image/*")
    public Response getImage(@PathParam("imagename") String imageName, @Context ServletContext application) {
        String realPath = application.getRealPath("/images");
        File file = new File(realPath, "xxx.jpg");
        if (!file.exists()) {
            throw new WebApplicationException(404);
        }
        String mt = new MimetypesFileTypeMap().getContentType(file);
        return Response.ok(file, mt).header("", "").build();
    }

    @GET
    @Path("pdf/{pdf}")
    @Produces("pdf/*")
    public Response getPdf(@PathParam("pdf") String pdf, @Context ServletContext application) {
        String realPath = application.getRealPath("/images");
        File file = new File(realPath, "http.pdf");
        if (!file.exists()) {
            throw new WebApplicationException(404);
        }
        String mt = new MimetypesFileTypeMap().getContentType(file);
        return Response.ok("file", mt).header("Content-disposition", "attachment;filename=" + pdf + ".pdf").header("ragma",
                                                                                                                   "No-cache").header("Cache-Control",
                                                                                                                                      "no-cache").build();
    }

    @POST
    @Path("uploadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String uploadFile(@FormDataParam("file") InputStream file,
                             @FormDataParam("file") FormDataContentDisposition fileDisposition,
                             @FormDataParam("fileName") String fileName) {
        OutputStream outputStream = null;
        String fileFullName = fileDisposition.getFileName();
        try {
            outputStream =
                new FileOutputStream(new File("D:\\upload", fileName
                    + fileFullName.substring(fileFullName.indexOf("."), fileFullName.length())));
            int length = 0;
            byte[] buff = new byte[256];
            while (-1 != (length = file.read(buff))) {
                outputStream.write(buff, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileFullName;
    }
}
