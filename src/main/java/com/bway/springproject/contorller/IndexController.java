package com.bway.springproject.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bway.springproject.repository.ProductRepository;

@Controller
public class IndexController {
	
	@Autowired
	private ProductRepository  pRepo;
	
	@GetMapping("/")
	public String  index(Model  model) {
		model.addAttribute("pList",pRepo.findAll());
		return "CustomerDashboard";
	}

}
