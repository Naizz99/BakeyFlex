package com.naizzbakers.bms.controller;

import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naizzbakers.bms.model.User;
import com.naizzbakers.bms.service.InventoryService;
import com.naizzbakers.bms.service.UserService;


@Controller
public class WebController {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String home(Model model) {
		return "customer/index";
	}
	
	@GetMapping("/customer/register")
	public String customerRegister(Model model){
		return "customer/register";
	}
	
	@GetMapping("/dashboard")
	public String viewdashoboard(Model model, Authentication authentication,HttpSession session) {
		User user = userService.findByUsername(authentication.getName());
		session.setAttribute("user",user);
		return "admin/index";
	}
	
	@GetMapping("/login")
	public String login(Model model){
		return "/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "/login";
	}
	
	@GetMapping("/customer/forgotPassword")
	public String customerForgotPassword() {
		return "customer/forgot-password";
	}
	
	@GetMapping("/user/forgotPassword")
	public String dashboardForgotPassword() {
		return "admin/forgot-password";
	}
		
	@GetMapping("/customer/profile")
	public ModelAndView customerProfile() {
		ModelAndView mav = new ModelAndView("customer/profile");
		return mav;
	}
	
	@GetMapping("/user/profile")
	public ModelAndView userProfile() {
		ModelAndView mav = new ModelAndView("admin/profile");
		return mav;
	}
	
	@GetMapping("/menu")
	public ModelAndView menu() {
		ModelAndView mav = new ModelAndView("customer/menu");
		return mav;
	}
	
	@GetMapping("/contact")
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("customer/contact");
		return mav;
	}
	
	@GetMapping("/deals")
	public ModelAndView deals() {
		ModelAndView mav = new ModelAndView("customer/menu");
		return mav;
	}
	
	@GetMapping("/blog")
	public ModelAndView blog() {
		ModelAndView mav = new ModelAndView("customer/blog");
		return mav;
	}
	
	@GetMapping("/gallery")
	public ModelAndView gallery() {
		ModelAndView mav = new ModelAndView("customer/gallery");
		return mav;
	}
	
	@GetMapping("/about-us")
	public ModelAndView aboutUs() {
		ModelAndView mav = new ModelAndView("customer/about-us");
		return mav;
	}
	
	@GetMapping("/order")
	public ModelAndView order() {
		ModelAndView mav = new ModelAndView("customer/menu");
		return mav;
	}
		
}
