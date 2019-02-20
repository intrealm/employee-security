package com.sapient.tms.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.tms.repository.UserRepository;
import com.sapient.tms.user.entity.UserEntity;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;

	public static final String ADMIN_ROLE_ID = "admin";

	/* @CrossOrigin(origins="http://:3030") */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(HttpServletRequest request, @RequestParam(name = "username") String userName,
			@RequestParam(name = "password") String password) {
		Map<String, String> roleSessionMap = new HashMap<>();
		// System.out.println(map);
		// UserEntity user =
		// this.userRepository.findByUserNameAndEncryptedPasswordIn(request.getParameter("username"),request.getParameter("password"));
		UserEntity user = this.userRepository.findByUserNameAndEncryptedPasswordIn(userName, password);

		if (null != user) {
			String sessionId = generateSessionId(request);
			roleSessionMap.put("roleId", user.getRoleId());
			roleSessionMap.put("sessionId", sessionId);
			return roleSessionMap;
		}

		return new HashMap<>();
	}

	private String generateSessionId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (!StringUtils.isEmpty(session.getId())) {
			return session.getId();
		}
		return null;

	}
}
