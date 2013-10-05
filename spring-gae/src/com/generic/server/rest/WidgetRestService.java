package com.generic.server.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.generic.server.model.Widget;
import com.generic.server.services.WidgetService;

@Component
@Path("/widget")
public class WidgetRestService {
	@Autowired WidgetService widgetService;
	
    /**
     * @return All the widgets info.
     * @uri http://localhost:8888/rest/widget/
     */
	@GET @Path("/test") @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody List<Widget> getAll() {
		return widgetService.getAll();
    }
	
	/**
     * @return All the widgets info.
     * @uri http://localhost:8888/rest/widget/
     */
	@GET @Path("/") @Produces(MediaType.TEXT_PLAIN)
    public @ResponseBody String test() {
		return "Sarasa";
    }
}