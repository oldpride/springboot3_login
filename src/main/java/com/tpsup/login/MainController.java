package com.tpsup.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("")
	public String showHomePage() {
		// System.out.println("main controller"); // use this to test devtools
		return "index";  // this points to templates/index.html
	}

}
