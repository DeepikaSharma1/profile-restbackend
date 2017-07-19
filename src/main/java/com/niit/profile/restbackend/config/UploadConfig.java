/**
 * 
 */
package com.niit.profile.restbackend.config;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.niit.profile.restbackend.service.UploadServiceDaoImpl;

/**
 * @author Deepika
 *
 */
@ApplicationPath("/upload")
public class UploadConfig extends Application {

	/**
	 * 
	 * 
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<>();
		s.add(UploadServiceDaoImpl.class);
		s.add(MultiPartFeature.class);
		return s;
	}

	/**
	 * 
	 * 
	 * @see javax.ws.rs.core.Application#getProperties()
	 */
	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("jersey.config.server.provider.classnames",
				"org.glassfish.jersey.media.multipart.MultiPartFeature");
		return properties;
	}

}
