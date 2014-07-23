package com.alagezia37.archivedocuments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alagezia37.archivedocuments.model.User;
import com.alagezia37.archivedocuments.services.UsersService;

@Controller
public class ArchiveDocumentsController {
	private UsersService usersService;

	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@RequestMapping(value="/usersroom")
	public String usersroom(Model model) {
		return "usersroom";
	}
	
	@RequestMapping(value="/adminsroom")
	public String adminsroom(Model model) {
		List<User> users = usersService.getUsers();
		model.addAttribute("users", users);
		return "adminsroom";
	}
	
	@RequestMapping(value="/response")
	public String response() {
		return "response";	
	}
}