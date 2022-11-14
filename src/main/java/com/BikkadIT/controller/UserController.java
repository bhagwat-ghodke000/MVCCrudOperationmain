package com.BikkadIT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.BikkadIT.entity.User;
import com.BikkadIT.service.UserServiceI;

@Controller
public class UserController {

	@Autowired
	private UserServiceI userServiceI;
	
	@GetMapping("/welcome")
	public String loadForm() {

		return "registration";

	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
		
	}
	
	@PostMapping("/reg")
	public String userRegistration(User user,Model model) {
		System.out.println("Controller layer Method");
		System.out.println(user);
		int id = userServiceI.saveUser(user);
		
		model.addAttribute("ID", id);
		
		return "regSuccess";
		
		
	}
	
	@PostMapping("/log")
	public String log(User user) {
		
		System.out.println(user);
		boolean loginCheckServiceLayer = userServiceI.loginCheckServiceLayer(user);
		
		if(loginCheckServiceLayer) {
			return "loginSuccess";
		}else {
			return "loginFaild";
		}
		
	}
	
	@GetMapping("/getAllData")
	public String getAllData(Model model) {
		
		List<User> all = userServiceI.getAll();
		
		model.addAttribute("list", all);
		return "getAllData";
		
	}
	
	@GetMapping("/update")
	public String preUpdate() {
		
		return "update";
		
		
	}

	
	@PostMapping("/postupdate")
	public String update(User user,Model model) {
		List<User> update = userServiceI.update(user);
		model.addAttribute("list",update);
		return "updatelist";
		
	}
	

}
