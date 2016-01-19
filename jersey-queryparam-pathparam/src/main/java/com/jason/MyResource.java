package com.jason;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/members")
public class MyResource {

	//http://localhost:8080/webapi/members/1
	@GET
	@Path("{id}")
	public Response getUser(@PathParam(value = "id") int id){
		return Response.status(200).entity("Hi there user:"+id).build();
	}
	
	http://localhost:8080/webapi/members/message?param=hey there
	@GET
	@Path("/message")
	public Response getMsg(@QueryParam("param") String msg) {
 
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
}
