package com.theironyard.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("invoices/list");
		return mv;
	}
	
}
