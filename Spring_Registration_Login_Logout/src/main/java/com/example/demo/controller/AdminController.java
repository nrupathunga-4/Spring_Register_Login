package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class AdminController {
	
	@Autowired
	private UserRepository repository;
	
	@ModelAttribute
	public void commonUser(Principal principal,Model model)
	{
		if(principal!=null)
		{
			String email=principal.getName();
			User user = repository.findByEmail(email);
			model.addAttribute("user", user);
		}
	}
	
	@GetMapping("/admin/profile")
	public String profile()
	{
		return "AdminProfile";
	}
}
