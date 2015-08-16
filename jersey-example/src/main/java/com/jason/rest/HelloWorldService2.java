package com.jason.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/*
 * Access: http://localhost:8080/rest/hello2/hi
 */
@Path("/hello2")
public class HelloWorldService2 {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) throws Exception {
		String output = "Jersey say : " + msg;
		try{
		throw new ArrayIndexOutOfBoundsException();
		} catch(ArrayIndexOutOfBoundsException e) {
		}
		return Response.status(200).entity(output).build();

	}
 
}