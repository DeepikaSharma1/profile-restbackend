/**
 * 
 */
package com.niit.profile.restbackend.service;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 * @author Deepika
 *
 */
public interface UploadServiceDao {

	Response upload(InputStream inputStream, FormDataContentDisposition fileDetails);

	Response redirect(ServletContext context);
	
}
