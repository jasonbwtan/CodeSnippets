package com.jason.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/read")
public class ReadRelativeFilePathService {
 
	/*
	 *http://localhost:8080/rest/read/file
	 */
	@GET
	@Path("/file")
	public Response getMsg(@QueryParam("param") String msg) throws IOException {
 
		BufferedReader br = new BufferedReader(new FileReader(new File("src/main/resources/geodata.csv")));
		String line = "";
		String output = "";
		while((line=br.readLine()) != null){
			output = output + line;
		}
		
		return Response.status(200).entity(output).build();
 
	}
 
}