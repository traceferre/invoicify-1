package com.theironyard.invoicify.controllers;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.models.UserRole;
import com.theironyard.invoicify.repositories.UserRepository;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {
	
	private UserRepository userRepo;
	
	public RoleController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("")
	public ModelAndView getAll() {
		ModelAndView mv = new ModelAndView("/roles/list");
		mv.addObject("users", userRepo.findAll(new Sort("username")));
		return mv;
	}
		
	@PostMapping("")
	public ModelAndView associateNewRole(Long userId, String role) {
		ModelAndView mv = new ModelAndView("redirect:/admin/roles");
		User user = new User();
		user = userRepo.findOne(userId);
		
		UserRole userRole = new UserRole(role.toUpperCase(), user);
		List<UserRole> roles = user.getRoles();
		roles.add(userRole);
		user.setRoles(roles);
		
		userRepo.save(user);
		
		return mv;
	}

}














