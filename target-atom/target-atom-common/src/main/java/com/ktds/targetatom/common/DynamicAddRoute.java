package com.ktds.targetatom.common;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;

public class DynamicAddRoute extends RouteBuilder {
	private String from;
	private String to;
	
	public DynamicAddRoute(CamelContext context, String from, String to) {
		super(context);
		this.from = from;
		this.to = to;
	}
	
	@Override
	public void configure() throws Exception {
		from(from).to(to);
	}

}

