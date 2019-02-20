package com.sapient.tms.cache.service;

import org.json.JSONException;
import org.json.JSONObject;

import com.sapient.tms.cache.exception.CachedDataNotFound;

public interface CachingService {

	String USERID = "guid";
	String ROLE = "role";
	String SEPERATOR = ",";

	String setupSession(String str, String role) throws JSONException;

	String addToCache(String str, String key, JSONObject obj) throws JSONException, CachedDataNotFound;

	String addToCache(String str, String key, String value) throws JSONException, CachedDataNotFound;

	JSONObject getAllData(String str) throws JSONException, CachedDataNotFound;

	JSONObject invalidate(String str) throws JSONException;

	JSONObject removeFromCache(String str, String key, String value) throws JSONException;

}
