package com.jason.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/postcode")
public class PostcodeMappingService {

	/*
	 * http://localhost:8080/rest/postcode/message?param=ZX0O 8AD
	 */
	@SuppressWarnings("finally")
	@GET
	@Path("/message")
	public Response getMsg(@QueryParam("param") String msg) throws IOException {

		String csvFile = "src/main/resources/geodata.csv";
		BufferedReader br = null;
		String line = "";
		String match = "";
		try {

			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
                if(msg.equals(tokens[3])){
                	match = tokens[5]+","+tokens[4]+","+tokens[3];
                	break;
                }
			
			}
			
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br.close();
			return Response.status(200).entity(match).build();	
		}
		


	}
	
	public static void main(String[] args) {
		String msg = "ZX0O 8AD";
		String csvFile = "src/main/resources/geodata.csv";
		BufferedReader br = null;
		String line = "";
		
		try {

			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split("\\|");
                if(msg.equals(tokens[3])){
                	break;
                }
			
			}
			System.out.println(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
