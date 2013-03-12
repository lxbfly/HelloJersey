package com.sample.hello.resources;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.sample.hello.bean.Staff;
import com.sample.hello.store.StaffStore;
import com.sun.jersey.api.NotFoundException;

@Path("/staff")
public class StaffResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void createStaff(@FormParam("name") String name,
            @FormParam("age") String age,
            @FormParam("career") String career,
            @Context HttpServletResponse servletResponse) throws IOException {
        Staff staff = new Staff(name, Integer.valueOf(age), career);
        StaffStore.getStore().put(name,staff);

        URI uri = uriInfo.getAbsolutePathBuilder().path(name).build();
        Response.created(uri).build();

        servletResponse.sendRedirect("../pages/new_contact.html");
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Staff> getStaffs() {
        List<Staff> staff = new ArrayList<Staff>();
        staff.addAll(StaffStore.getStore().values());
        return staff;
    }
    
    @Path("{staff}")
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Staff getStaff(@PathParam("staff") String staff) {
           Staff staffEbo = StaffStore.getStore().get(staff);
           if(staffEbo == null) {
               throw new NotFoundException("Can't find " + staff + " staff !");
           }
           return staffEbo;
    }
    
    //@Path("{staff}")
    @DELETE
    public void deleteStaff(@FormParam("name") String staffName) {
        Staff staff = StaffStore.getStore().get(staffName);
        if(staff == null) {
            throw new NotFoundException("Can't find " + staff + " staff !");
        }
        StaffStore.getStore().remove(staffName);
    }
}
