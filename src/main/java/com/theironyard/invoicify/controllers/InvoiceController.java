package com.theironyard.invoicify.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.models.Invoice;
import com.theironyard.invoicify.models.InvoiceLineItem;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;
import com.theironyard.invoicify.repositories.InvoiceRepository;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
	
	private CompanyRepository companyRepo;
	private BillingRecordRepository	billingRepo;
	private InvoiceRepository invoiceRepo;
	
	public InvoiceController(CompanyRepository companyRepo, BillingRecordRepository billingRepo,
							 InvoiceRepository invoiceRepo) {
		this.companyRepo = companyRepo;
		this.billingRepo = billingRepo;
		this.invoiceRepo = invoiceRepo;
	}

	@GetMapping("")
	public ModelAndView list(Authentication auth) {
		User user = (User) auth.getPrincipal();
		ModelAndView mv = new ModelAndView("invoices/list");
		mv.addObject("user", user);
		mv.addObject("invoices", invoiceRepo.findAll());
		return mv;
	}
	
	@GetMapping("/new")
	public ModelAndView clients() {
		ModelAndView mv = new ModelAndView("invoices/step-1");
		mv.addObject("companies", companyRepo.findAll());
		return mv;
	}
	
	@PostMapping("/new")
	public ModelAndView clientsInvoice(Long clientId) {
		ModelAndView mv = new ModelAndView("invoices/step-2");
		
//		List<BillingRecord> allRecords = new ArrayList<BillingRecord>();
//		allRecords = billingRepo.findAll();
//		List<BillingRecord> records = new ArrayList<BillingRecord>();
//		for (BillingRecord record : allRecords) {
//			if (record.getClient().getId() == clientId) {
//				records.add(record);
//			}
//		}
//		mv.addObject("records", records);
		
		mv.addObject("records", billingRepo.findByClientIdAndLineItemIdIsNull(clientId));
		mv.addObject("clientId", clientId);
		return mv;
	}
	
	@PostMapping("/create")
	public ModelAndView createInvoice(Invoice invoice, long clientId, 
							    long[] recordIds, Authentication auth) {
		try {
			User user = (User) auth.getPrincipal();
			List<BillingRecord> records = billingRepo.findByIdIn(recordIds);
			Long now = Calendar.getInstance().getTimeInMillis();
			Date nowDate = new Date(now);
			
			List<InvoiceLineItem> items = new ArrayList<InvoiceLineItem>();
			for (BillingRecord record : records) {
				InvoiceLineItem lineItem = new InvoiceLineItem();
				lineItem.setBillingRecord(record);
				lineItem.setCreatedBy(user);
				lineItem.setCreatedOn(nowDate);
				lineItem.setInvoice(invoice);
				items.add(lineItem);			
			}
			
			invoice.setLineItems(items);
			invoice.setCreatedBy(user);
			invoice.setCreatedOn(nowDate);
			invoice.setCompany(companyRepo.findOne(clientId));
			invoiceRepo.save(invoice);
			ModelAndView mv = new ModelAndView("redirect:/invoices");
			return mv;
		} catch (InvalidDataAccessApiUsageException idaaue) {
			ModelAndView mv = new ModelAndView("/invoices/step-2");
			mv.addObject("records", billingRepo.findByClientIdAndLineItemIdIsNull(clientId));
			mv.addObject("clientId", clientId);
			mv.addObject("error", "Please select at least one billing record.");
			return mv;
		}	
	}
}

















