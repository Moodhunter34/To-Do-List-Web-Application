package com.qa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qa.dto.UserDTO;

@Controller
public class BaseController {
	
	@GetMapping("/test")
	@ResponseBody
	public UserDTO returnSomething() {
		return new UserDTO(3,"nikos", "pap", "nikpap");
	}

}
