package com.theironyard.invoicify.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.repositories.BillingRecordRepository;

@Controller
@RequestMapping("/billing-records")
public class BillingRecordController {
	
	private BillingRecordRepository recordRepository;

	public BillingRecordController(BillingRecordRepository recordRepository) {
		this.recordRepository = recordRepository;
	}

	@GetMapping("")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("billing-records/list");
		mv.addObject("records", recordRepository.findAll());
		return mv;
	}
	
}
