
package ar.com.kimboo.server.rest;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.kimboo.model.Advertising;
import ar.com.kimboo.server.services.AdvertisingServiceImpl;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Component
@Path("/util")
public class UtilRESTServiceImpl {
    /** this is the absolute path of the backup folder*/
	private static final String OS_ADVERTISING_IMAGES_PATH = "/home/astinx/advertising_images/";
	
	/**
     * @return the image.
     * @uri http://localhost:8080/server/rest/util/image/{name}/
     */
	@GET @Path("/image/{name}") @Produces("image/png")
    public @ResponseBody Response getImage(@PathParam("name") String name) {
        File file = new File(OS_ADVERTISING_IMAGES_PATH + name);
		return Response.ok(file).build();
    }

}
