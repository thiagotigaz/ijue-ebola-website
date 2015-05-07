package org.ijueebola.controller;

import org.ijueebola.model.User;
import org.ijueebola.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/login")
	public String loginPage(){
		return "admin/login";
	}
	
	@RequestMapping(value={"/"},method=RequestMethod.POST, params={"email"})
	public String subcribe(@RequestParam("email") String email, Model model){
		model.asMap().clear();
		
		if (email.trim().equals("")){
			model.addAttribute("error", "You have entered an invalid email!");
			return "index";
		}
		
		if(userRepo.findByEmailIgnoreCase(email)!=null){
			model.addAttribute("mensagem", "You have already subscribed before!");
		} else{
			User user = new User(email);
			userRepo.save(user);
			model.addAttribute("mensagem", "Thank You for joining us!");
		}
		
		return "index";
	}
}
