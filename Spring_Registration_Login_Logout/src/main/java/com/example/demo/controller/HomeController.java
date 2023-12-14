package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService service;
	
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
	@GetMapping("/")
	public String index()
	{
		return "index";
	}
	
	@GetMapping("/signup")
	public String register()
	{
		return "register";
	}
	
	@GetMapping("/signin")
	public String login()
	{
		return "login";
	}
	
//	@GetMapping("/user/home")
//	public String home()
//	{
//		return "home";
//	}
//	
//	@GetMapping("/user/profile")
//	public String profile(Principal principal,Model model)
//	{ 
//		String email = principal.getName();
//        User user=repository.findByEmail(email);
//        model.addAttribute("user",user);
//		return "profile";
//	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user,HttpSession session)
	{
		User user2 = service.saveUser(user);
		
		if(user!=null)
		{
			/* System.out.println("Saved SucessFully"); */
			session.setAttribute("msg", "Registerd SuccessFully");
		}
		else
		{
			/* System.out.println("Error Has Occured"); */
              session.setAttribute("msg", "Something Wrong in Server");
	
		}
		return "redirect:/signup";
	}
	
}
