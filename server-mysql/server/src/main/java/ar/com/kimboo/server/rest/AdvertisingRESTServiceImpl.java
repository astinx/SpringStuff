
package ar.com.kimboo.server.rest;

import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
@Path("/advertising")
public class AdvertisingRESTServiceImpl {
    @Autowired AdvertisingServiceImpl advertisingService;

    private static final Logger logger = LoggerFactory.getLogger(AdvertisingRESTServiceImpl.class);
    /** this is the absolute path of the backup folder*/
	private static final String OS_ADVERTISING_IMAGES_PATH = "/home/astinx/advertising_images/";
    /** this is the url of the images */
	private static final String PUBLIC_ADVERTISINGS_IMAGES_URL = "/server/rest/util/image/";
	
	
	/**
     * @return json.
     * @uri http://localhost:8080/server/rest/advertising/{deviceId}/{idApp}/{tagId}
     */
	@GET @Path("/{deviceId}/{idApp}/{tagId}") @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<Advertising> getAdvertisingForDeviceAndAppAndTag(@PathParam("idApp") String idApp, 
    		@PathParam("deviceId") String deviceId,
    		@PathParam("tagId") String tagId) {
        return advertisingService.getAllForDeviceAndAppAndTag(deviceId, idApp, tagId);
    }
	
	/**
     * @return json.
     * @uri http://localhost:8080/server/rest/advertising/{deviceId}/{idApp}/
     */
	@GET @Path("/{deviceId}/{idApp}/") @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<Advertising> getAdvertisingForDeviceAndApp(@PathParam("deviceId") String deviceId, @PathParam("idApp") String idApp) {
        return advertisingService.getAllForDeviceAndApp(deviceId, idApp);
    }
	
	/**
     * @return json.
     * @uri http://localhost:8080/server/rest/advertising/{deviceId}/
     */
	@GET @Path("/{deviceId}/") @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<Advertising> getAdvertisingForDevice(@PathParam("deviceId") String deviceId) {
		return advertisingService.getAllForDevice(deviceId);
    }
	
	/**
     * @return json.
     * @uri http://localhost:8080/server/rest/advertising/
     */
	@GET @Path("/") @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<Advertising> getAll() {
		return advertisingService.getAll();
    }
	
	/**
     * @return json.
     * @uri http://localhost:8080/server/rest/advertising/
     */
	@DELETE @Path("/")  @Consumes(MediaType.APPLICATION_JSON)
    public @ResponseBody Response delete(Advertising advertising) {
		try {
			advertisingService.delete(advertising);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.OK).entity("Advertising has been deleted").build();
    }
	
    /**
     * @param newAdvertising: The new Advertising to add to the database.
     * @return Response to the client.
     * @uri http://localhost:8080/server/rest/advertising/
     */
	@POST @Path("/") @Produces(MediaType.APPLICATION_JSON)
    public Response addAdvertising(@RequestBody Advertising newAdvertising) {
		try {
			advertisingService.saveAdvertising(newAdvertising);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.OK).entity("Advertising has been persisted").build();
    }

    /**
     * This rest method process the form of a new advertising.
     * @return Response to the client.
     * @uri http://localhost:8080/server/rest/advertising/add/
     */
	@POST @Path("/add") @Consumes(MediaType.MULTIPART_FORM_DATA) 
    public Response formHandler(
    		@FormDataParam("description") String description,
    		@FormDataParam("deviceId") String deviceId,
    		@FormDataParam("appId") String appId,
    		@FormDataParam("advertisingHref") String advertisingHref,
    		@FormDataParam("advertisingTag") String advertisingTag,
            @FormDataParam("imageFile") InputStream imageFileStream,
            @FormDataParam("imageFile") FormDataContentDisposition fileDetail) {
		try {
			if (appId == null) {
				appId = "all";
			}
			Advertising advertising = new Advertising(null, appId, 
					Calendar.getInstance().getTime(), description, deviceId, advertisingHref, null, advertisingTag);
			//We save the image in the backup and in the server
			String extension = (fileDetail.getFileName().split("\\."))[1];
			File outputFolder = new File(OS_ADVERTISING_IMAGES_PATH);
			if (!outputFolder.exists())
				outputFolder.mkdirs();
			advertising.setPath(PUBLIC_ADVERTISINGS_IMAGES_URL + advertising.getAppId() + "_" + advertising.getDevice() + "_" + advertising.getTag() + "." + extension);
			advertisingService.writeToFile(imageFileStream, OS_ADVERTISING_IMAGES_PATH + advertising.getAppId() + "_" + advertising.getDevice() + "_" + advertising.getTag() + "." + extension);
			advertisingService.saveAdvertising(advertising);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.status(Response.Status.OK).entity("Advertising has been persisted").build();
    }
	
	
    /**
     * @param advertising: The Advertising thats gonna to be updated.
     * @uri http://localhost:8080/server/rest/advertising/
     */
	@PUT @Path("/") @Produces(MediaType.APPLICATION_JSON)
    public Response updateAdvertising(Advertising advertising) {
        try {
        	advertisingService.saveAdvertising(advertising);
        } catch (Exception e) {
        	e.printStackTrace();
        	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.OK).entity("Advertising has been updated").build();
    }
	
	/**
	 * Datatables need feed of json that is in an aaData field as a list.
	 * @return Datatables formatted json
	 */
	@GET @Path("/dt/") @Produces(MediaType.APPLICATION_JSON)
	public HashMap<String, List<Advertising>> getAllAdvertisingsToDatatable() {
		List<Advertising> provs = advertisingService.getAll();
		HashMap<String, List<Advertising>> map = new HashMap<String, List<Advertising>>();
		map.put("aaData", provs);
		return map;
	}


}
