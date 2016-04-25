package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class homeCtrl {
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
