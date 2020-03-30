package com.ktds.targetatom.db.reference;

import java.util.List;
import java.util.Map;

import org.apache.camel.api.management.ManagedResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ktds.targetatom.db.DbProcessor;

@ManagedResource
public class ReferenceProcessor {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	public List<Map<String, Object>> getPathList(JdbcTemplate jdbcTemplate, String instance_id) {
		return jdbcTemplate.queryForList(DbProcessor.getQuery(), instance_id);
	}

	public Map<String, Object> getRouteFile(JdbcTemplate jdbcTemplate, String format) {
		try {
			return jdbcTemplate.queryForMap(DbProcessor.getFetchRoute_query(), format);
		} catch (EmptyResultDataAccessException e) {
			log.error("Route file isn't in FORMAT_ROUTE_MAP_INFO :: format {}", format);
			return null;
		}
	}
}


