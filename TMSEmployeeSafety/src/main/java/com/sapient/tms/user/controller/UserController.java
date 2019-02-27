package com.sapient.tms.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.tms.cache.service.CachingService;
import com.sapient.tms.repository.UserRepository;
import com.sapient.tms.user.entity.UserEntity;
import com.sapient.tms.utils.PasswordUtils;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CachingService cachingService;
	
	public static final String ADMIN_ROLE_ID = "admin";
	Logger logger = Logger.getLogger(UserController.class.getName());
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request, @RequestParam(name = "username") String userName,
			@RequestParam(name = "password") String password) {
		Map<String, String> roleSessionMap = new HashMap<>();
		String salt=getSalt(userName);
		if(!StringUtils.isEmpty(salt))
		{
		String mySecurePassword= PasswordUtils.generateSecurePassword(password, salt);		
		UserEntity validatedUser = this.userRepository.findByUserNameAndEncryptedPasswordIn(userName, mySecurePassword);	
		if (null != validatedUser) {			 		
			String sessionId = generateSessionId(request);
			roleSessionMap.put("roleId", validatedUser.getRoleId());
			roleSessionMap.put("sessionId", sessionId);
            try {
				cachingService.setupSession(sessionId, validatedUser.getRoleId());
			} catch (JSONException e) {
				logger.info(userName+" Unable to login due to :"+e.getMessage());
			}
            return roleSessionMap;
			} 
		}
		return new HashMap<>();
	}

	
	
	private String getSalt(String userName) {
	   UserEntity user=this.userRepository.findByUserName(userName);
	   String salt=null;
	   if(null != user)
	   {
		   salt=user.getSalt();
	   }
	   return salt;
	}

	private String generateSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (!StringUtils.isEmpty(session.getId())) {
			return session.getId();
		}
		return null;

	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request) {
		HttpSession session=request.getSession(false);				
		if(session!=null)
		{
			String sessionId=session.getId();
			try {
				cachingService.invalidate(sessionId);
			} catch (JSONException e) {
				logger.info("Unable to logout from application due to : "+e.getMessage());
			}
		}
	
	}
	
	@RequestMapping(value = "/getCoordinates/{routeId}", method = RequestMethod.GET,produces="application/json")
	public String getCoordinates(@PathVariable(name = "routeId") int routeId) throws Exception {
		String latitude="28.535517";
		String longitude="77.3910";
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("latitude", latitude);
		jsonObject.put("longitude", longitude);
		return jsonObject.toString();
	}
}
	
