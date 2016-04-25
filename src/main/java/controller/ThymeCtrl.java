package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Contact;

@Controller
public class ThymeCtrl {
	
	@RequestMapping("/thyme")
	public String index(Model model) {
		Contact c = new Contact(1);
		model.addAttribute("contact", c);
		return "index";
	}
}
