package com.scrum.website.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="/show", method = RequestMethod.GET)
	public void show(Model model) {
		LOG.info("Displaying home page");
	}
}
