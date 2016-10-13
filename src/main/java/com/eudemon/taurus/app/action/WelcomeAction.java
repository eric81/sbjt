package com.eudemon.taurus.app.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 欢迎页跳转 
 * @author eric
 *
 */
@Controller
@RequestMapping("/")
public class WelcomeAction {
	@GetMapping
	public String index(){
		return "index";
	}
}