package com.sapient.tms.cache.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.tms.cache.exception.CachedDataNotFound;
import com.sapient.tms.cache.service.CachingService;

@RestController
public class SessionCacheController {

	@Autowired
	private CachingService cachingService;

	@RequestMapping("/setup")
	public String setup(final @RequestParam(name = "guid", required = true) String guid,
			final @RequestParam(name = "role", required = true) String role) throws Exception {
		cachingService.setupSession(guid, role);
		return cachingService.getAllData(guid).getString("guid");
	}

	@RequestMapping(produces = "application/json", path = "getAttribute/{guid}", method = RequestMethod.GET)
	@ResponseBody
	public String getAttribute(final @PathVariable(name = "guid", required = true) String guid)
			throws CachedDataNotFound, JSONException {
		return cachingService.getAllData(guid).toString();
	}

	@RequestMapping(path = "/putAttribute/{guid}", method = RequestMethod.POST)
	public String putAttribute(final @PathVariable(name = "guid", required = true) String guid,
			@RequestParam(name = "key", required = true) String key,
			@RequestParam(name = "value", required = true) String value) throws CachedDataNotFound, JSONException {
		return cachingService.addToCache(guid, key, value);
	}

	@RequestMapping(path = "/putJSONAttribute/{guid}", method = RequestMethod.POST)
	public String putJSONAttribute(final @PathVariable(name = "guid", required = true) String guid,
			@RequestParam(name = "key", required = true) String key,
			@RequestParam(name = "value", required = true) JSONObject jsonObject)
			throws CachedDataNotFound, JSONException {

		return cachingService.addToCache(guid, key, jsonObject);
	}

}
