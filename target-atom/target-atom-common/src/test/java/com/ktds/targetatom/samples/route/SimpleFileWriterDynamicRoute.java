package com.ktds.targetatom.samples.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.ktds.targetatom.common.RouteLoader;

@Component
public class SimpleFileWriterDynamicRoute extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		RouteLoader.getInstance().addRoute(super.getContext(), "routes/file-read-and-write-with-component/FileCopyAndWriteWithComponent.xml");
	}
}
