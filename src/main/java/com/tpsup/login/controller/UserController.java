package com.tpsup.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tpsup.login.entity.User;
import com.tpsup.login.repo.RoleRepository;
import com.tpsup.login.service.UserService;

@Controller
public class UserController {
	@Autowired 
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@GetMapping("/admin/users")
	public String showUserLIst(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
		
	}

	@GetMapping("/admin/users/new")
	public String showNewForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("pageTitle", "Add New User");
		model.addAttribute("roles", roleRepo.findAll());
		return "user_form";		
	}
	
	@PostMapping("/admin/users/save")
	public String saveUser(User user, RedirectAttributes ra) {
		// user is from th:object="${user}" in user_form.html
		userService.save(user);
		ra.addFlashAttribute("message", "The user (ID=" + user.getId() + ") has been saved successfully."); // used by users.html
		return "redirect:/admin/users";		
	}
	
	@GetMapping("/admin/users/edit/{id}")
	public String showEditForm(@PathVariable("id") String id, Model model, RedirectAttributes ra) {
		try {
			User user = userService.get(id);
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit User ID " + id);
			model.addAttribute("roles", roleRepo.findAll());
			return "user_form";
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage()); 
			return "redirect:/admin/users";
		} catch (Exception e) {
			ra.addFlashAttribute("message", e.getMessage()); 
			return "redirect:/admin/users";
		}	
	}
	
	@GetMapping("/admin/users/delete/{id}")
	public String deleteUser(@PathVariable("id") String id, RedirectAttributes ra) {
		try {
			userService.delete(id);
			ra.addFlashAttribute("message", "The user (ID=" + id + ") has been deleted successfully."); // used by users.html
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage()); 			
		}	
		return "redirect:/admin/users";
	}
}
