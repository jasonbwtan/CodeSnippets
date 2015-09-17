package com.jason.embedded_jetty;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("api")
public class MyResource {


	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Hello, Jersey-Rest-Heroku (space)!";
	}


	
//	@GET
//	@Path("/getreport")
//	@Produces("text/plain")
//	public Response getReport() throws IOException {
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd");
//		String dateString = sdf.format(date);
////		File file = new File("foo.txt");
////		FileOutputStream out = new FileOutputStream(file);
////		byte[] data = {1, 2, 3, 4, 5};
////		out.write(data);
////		out.close();
//		PrintWriter writer = new PrintWriter("foo.txt", "UTF-8");
//		writer.println("The first line");
//		writer.println("The second line");
//		writer.close();
//		File file = new File("foo.txt");
//		
//		ResponseBuilder response = Response.ok((Object) file);
//		response.header("Content-Disposition",
//				"attachment; filename=\"space_report_"+dateString+".txt\"");
//		return response.build();
//
//	}

}
