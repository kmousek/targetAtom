package com.ktds.targetatom.common;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultModelJAXBContextFactory;
import org.apache.camel.model.RoutesDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class RouteLoader {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	static RouteLoader instance = null;
	
	public static RouteLoader getInstance() {
		if(instance == null) {
			instance = new RouteLoader();
		}
		return instance;
	}
	
	public void addRoute(CamelContext camelContext, String routePath) throws Exception {
		// load new route from classpath using JAXB
		JAXBContext jaxb = new DefaultModelJAXBContextFactory().newJAXBContext();
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();

		//Resource rs = new ClassPathResource(routePath);
		Resource rs = new UrlResource("file:"+routePath);
		
		if(!rs.exists()) {
			rs = new ClassPathResource(routePath);
			if (!rs.exists()) {
				log.warn("Route file {} is not exist", routePath);
				throw new Exception("Route file " + routePath + " is not exist");
			}
		}
		log.debug("Camel Dynamic RoutePath = {}", routePath);
		Object value = unmarshaller.unmarshal(rs.getInputStream());

		// it should be a RoutesDefinition (we can have multiple routes in the same XML file)
		RoutesDefinition routes = (RoutesDefinition) value;
		// add the routes to existing CamelContext
		camelContext.addRouteDefinitions(routes.getRoutes());
	}
	
}
