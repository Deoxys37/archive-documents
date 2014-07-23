package com.alagezia37.archivedocuments.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alagezia37.archivedocuments.model.User;
import com.alagezia37.archivedocuments.services.UsersService;

@Controller
public class LoginController {
	private UsersService usersService;
	
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/loginverify", method=RequestMethod.POST)
	public String loginsuccess(Model model,@Valid User user, BindingResult result) {
		if (result.hasErrors() || !usersService.login(user)) {
			return "login";
		} else {
			user.setEnabled(true);
			
			return "loginsuccess";
		}
	}
	
	@RequestMapping(value="/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	@RequestMapping(value="/registrationverify", method=RequestMethod.POST)
	public String create(Model model,@Valid User user, BindingResult result) {
		
		if(usersService.exists(user.getPassport())) {
			result.rejectValue("passport", "DuplicateKey.user.passport");
			return "registration";
		} else if (result.hasErrors() || !usersService.create(user)) {
			return "registration";
		} else {
			
			return "registrationsuccess";
		}
	}
	
	@RequestMapping(value="/loginsuccess")
	public String loginsuccess() {
		return "loginsuccess";
	}
	
	@RequestMapping(value="/logoutsuccess")
	public String logoutsuccess() {
		return "logoutsuccess";
	}
	
	@RequestMapping(value="/denied")
	public String denied() {
		return "denied";
	}
}