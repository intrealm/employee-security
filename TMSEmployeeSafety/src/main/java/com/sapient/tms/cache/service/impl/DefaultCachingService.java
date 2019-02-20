package com.sapient.tms.cache.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.sapient.tms.cache.exception.CachedDataNotFound;
import com.sapient.tms.cache.service.CachingService;

@Component
public class DefaultCachingService implements CachingService {

	private Map<String, JSONObject> allData = new HashMap();

	@Override
	public String setupSession(String str, String role) throws JSONException {

		JSONObject main = new JSONObject();
		main.put(CachingService.USERID, str);
		main.put(CachingService.ROLE, role);
		allData.put(str, main);
		return str;
	}

	private void addToCache(JSONObject obj, String key, JSONObject toAddObj) throws JSONException, CachedDataNotFound {
		/*
		 * if(key.contains(CachingService.SEPERATOR)) { String[] keyTrace =
		 * key.split(CachingService.SEPERATOR);
		 * 
		 * Object nestedObj = obj.get(keyTrace[0]); if(nestedObj==null) { JSONObject
		 * newobj = new JSONObject(); newobj.put(keyTrace[0], newObj); } else {
		 * 
		 * } String[] strsrr = key.split(CachingService.SEPERATOR); cachedData.get }
		 */

	}

	@Override
	public String addToCache(String str, String key, JSONObject obj) throws JSONException, CachedDataNotFound {

		JSONObject cachedData = this.getAllData(str);
		if (cachedData == null)
			throw new CachedDataNotFound();
		cachedData.put(key, obj);
		return str;
	}

	@Override
	public String addToCache(String str, String key, String value) throws JSONException, CachedDataNotFound {

		JSONObject cachedData = this.getAllData(str);
		if (cachedData == null)
			throw new CachedDataNotFound();
		cachedData.put(key, value);
		return str;
	}

	@Override
	public JSONObject getAllData(String str) throws CachedDataNotFound {

		JSONObject jsonObj = this.allData.get(str);
		if (jsonObj == null)
			throw new CachedDataNotFound();
		return jsonObj;
	}

	@Override
	public JSONObject invalidate(String str) {
		return this.allData.remove(str);
	}

	@Override
	public JSONObject removeFromCache(String str, String key, String value) {
		return null;
	}

}
