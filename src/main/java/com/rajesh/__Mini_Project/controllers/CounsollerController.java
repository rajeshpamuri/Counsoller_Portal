package com.rajesh.__Mini_Project.controllers;

import com.rajesh.__Mini_Project.dto.DashBordResponse;
import com.rajesh.__Mini_Project.entity.Counsellor;
import com.rajesh.__Mini_Project.entity.Enquiry;
import com.rajesh.__Mini_Project.services.CounsollerService;
import com.rajesh.__Mini_Project.services.CounsollerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;


@Controller
public class CounsollerController {
	
	@Autowired
	private CounsollerServiceImpl counsollerService;
	
	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("coun", new Counsellor());
		return "index";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("coun") Counsellor counsoller, Model model ,HttpServletRequest request) {
		Counsellor login = counsollerService.login(counsoller.getEmail(), counsoller.getPwd());
		if(login==null) {

			model.addAttribute("Emsg", "Credential Not Found");
			
			return "index";
		}else {
           HttpSession session = request.getSession(true);
			session.setAttribute("counsollerId", login.getCounsellorId());
			return "redirect:/dashBoard";
		}
		
	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Counsellor cobj=new Counsellor();
		model.addAttribute("coun", new Counsellor());
		return "register";
	}
	
	@PostMapping("/rregister")
	public String reditretion(@ModelAttribute("coun") Counsellor counsellor, Model model) {

		Counsellor byEmail = counsollerService.findByEmail(counsellor.getEmail());
		if (byEmail!=null){
         model.addAttribute("Emsg" , "Duplicate Email");
		 return "register";
		}
		boolean register = counsollerService.register(counsellor);

		if(register) {
			model.addAttribute("smsg", "Registration Success");
			return  "register";
		}else {
			model.addAttribute("Emsg", "Registretion Failure");
			return"register";
		}

	}
	@GetMapping("/dashBoard")
	public String dashBoard(HttpServletRequest request ,Model model){
		HttpSession session = request.getSession(false);
		Integer counsollerId = (Integer) session.getAttribute("counsollerId");
		DashBordResponse dashboardInfo = counsollerService.getDashboardInfo(counsollerId);

		model.addAttribute("dashboard", dashboardInfo);

		return "dashBoard";
	}


		
}



