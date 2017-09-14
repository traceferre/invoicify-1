package com.theironyard.invoicify.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.FlatFeeBillingRecord;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

@Controller
@RequestMapping("/billing-records/flat-fee-billing-records")
public class FlatFeeBillingRecordController {
	
	private BillingRecordRepository recordRepo;
	private CompanyRepository companyRepo;
	
	public FlatFeeBillingRecordController(BillingRecordRepository recordRepo, CompanyRepository companyRepo) {
		this.recordRepo = recordRepo;
		this.companyRepo = companyRepo;
	}
	
	@PostMapping("")
	public ModelAndView createFlatFeeBillingRecord(FlatFeeBillingRecord record, long clientId, Authentication auth) {
		User user = (User) auth.getPrincipal();
		record.setCreatedBy(user);
		ModelAndView mv = new ModelAndView("redirect:/billing-records");
		record.setClient(companyRepo.findOne(clientId));
		recordRepo.save(record);
		return mv;
	}

}
