package com.bway.springproject.contorller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@GetMapping("/upload")
	public   String   getUpload() {
		
		return "UploadForm";
	}
	
	@PostMapping("/upload")
	public  String  postUpload(@RequestParam MultipartFile image, Model model) {
		
		  if(!image.isEmpty()) {
			  
			  try {
				  
				    long  fs = image.getSize()/1024;
				    String ext = image.getOriginalFilename().split("\\.")[1]; //tiger.jpg
				    
				     if(!ext.equalsIgnoreCase("png")) {
				    	 model.addAttribute("message","only PNG file supported");
						  return "UploadForm";
			    	   }
				    
				    if(fs>=200) {
				    	 model.addAttribute("message","MAX-SIZE 200kb");
						  return "UploadForm";
				    }
				  
				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/image/"+image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
				  model.addAttribute("message","upload success");
				  return "UploadForm";
			  
			  } catch (IOException e) {
				e.printStackTrace();
			}  
		  }
		
		  model.addAttribute("message","upload failed");
		return "UploadForm";
	}

}
