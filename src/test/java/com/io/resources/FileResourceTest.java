package com.io.resources;

import static org.junit.Assert.*;
import org.junit.Test;
import com.sun.jersey.api.client.Client;

// import org.glassfish.jersey.media.multipart.FormDataMultiPart;
// import org.glassfish.jersey.media.multipart.MultiPartFeature;
// import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class FileResourceTest {

    @Test
    public void testUploadFile() {
        // Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        //
        // WebTarget target = client.target("http://localhost:8080/JerseyStudy").path("file/uploadFile");
        // FileDataBodyPart bodyPart = new FileDataBodyPart("file", new File("D:\\", "maven.txt"));
        // FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        // formDataMultiPart.field("fileName", "mavenCopy").bodyPart(bodyPart);
        // System.out.println(formDataMultiPart.getMediaType());
        // System.out.println(MediaType.MULTIPART_FORM_DATA);
        // String result =
        // target.request(MediaType.TEXT_HTML_TYPE).post(Entity.entity(formDataMultiPart,
        // formDataMultiPart.getMediaType()),
        // String.class);
        // System.out.println(result);
    }
}
