package com.bway.springproject.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bway.springproject.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository prodRepo;
	
	@GetMapping("/productGallery")
	public String getImages(Model  model) {
		
		model.addAttribute("pList",prodRepo.findAll());
		return "ProductGalleryForm";
	}

}
