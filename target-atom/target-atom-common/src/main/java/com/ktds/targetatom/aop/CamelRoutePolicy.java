package com.ktds.targetatom.aop;

import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.support.RoutePolicySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamelRoutePolicy extends RoutePolicySupport {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void onStart(Route route) {
		log.trace("[>== onStart {}:{}]", route.getId());
		super.onStart(route);
		log.debug("[<== onStart {}:{}]", route.getId());
	}

	@Override
	public void onStop(Route route) {
		log.trace("[>== onStop {}:{}]", route.getId());
		super.onStop(route);
		log.debug("[<== onStop {}:{}]", route.getId());
	}

	@Override
	public void onExchangeBegin(Route route, Exchange exchange) {
		// TODO Auto-generated method stub
		super.onExchangeBegin(route, exchange);
	}
}
