package com.ywa.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ywa.helper.RoleConstant;

@RestController
@RequestMapping("/protect")
public class ProtectedController {
	
	@Secured({RoleConstant.ROLE_ADMIN})
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminPage(ModelMap model) {
		return "return ADMIN";
	}
	
	@Secured({RoleConstant.ROLE_EDITOR})
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public String getEditorPage(ModelMap model) {
		return "return EDITOR";
	}

	@Secured({RoleConstant.ROLE_USER})
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUserPage(ModelMap model) {
		return "return USER";
	}
	
	
}
