/**
 * 
 */
package com.niit.profile.restbackend.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.uri.UriComponent;

/**
 * @author Deepika
 *
 */
@Path("/profile")
public class UploadServiceDaoImpl implements UploadServiceDao {

	private static final String LOCATION = "D:/Documents/Images/Uploads/";
	@Context
	private HttpServletRequest request;
	/**
	 * 
	 * 
	 * @see com.niit.profile.restbackend.service.UploadServiceDao#upload(java.io.InputStream,
	 *      org.glassfish.jersey.media.multipart.FormDataContentDisposition)
	 */
	@POST
	@Path("/image")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Override
	public Response upload(@FormDataParam("file") InputStream inputStream, @FormDataParam("file") FormDataContentDisposition fileDetails) {
		String imageLocation = LOCATION + fileDetails.getFileName();
		HttpSession session = request.getSession();
		System.out.println(session.toString());
		
		try (OutputStream outputStream = new FileOutputStream(new File(imageLocation))) {
			int read = 0;
			byte[] buffer = new byte[1048576];
			while ((read = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
			}
		} catch (IOException e) {
			System.err.println("Error Uploading File....");
			System.err.println(e.getMessage());
		}
		/*String text = "File uploaded to " + imageLocation;
		return Response.ok(text).build();*/
		String message = UriComponent.encode("Image Uploaded Successfully!", UriComponent.Type.QUERY_PARAM_SPACE_ENCODED);
		/*UriBuilder builder = UriBuilder.fromPath("http://localhost:10080/collaboration-frontend");
		builder.path("/");
		System.out.println(builder.toString());*/
		return Response.seeOther(URI.create("http://localhost:10080/collaboration-frontend/?message=" + message)).build();
	}
	/*
	@Override
	public Response redirect(@Context ServletContext context) {
		System.out.println(context.getContextPath());
		UriBuilder builder = UriBuilder.fromPath("http://localhost:10080/collaboration-restbackend");
		builder.path("/");
		System.out.println(builder.toString());
		return Response.seeOther(builder.build()).build();
	}
*/
}
