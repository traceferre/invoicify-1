package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.models.FlatFeeBillingRecord;
import com.theironyard.invoicify.models.User;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

public class FlatFeeBillingRecordControllerTests {
	
	private FlatFeeBillingRecordController controller;
	private BillingRecordRepository repo;
	private CompanyRepository companyRepo;
	
	@Before
	public void setup() {
		repo = mock(BillingRecordRepository.class);
		companyRepo = mock(CompanyRepository.class);
		controller = new FlatFeeBillingRecordController(repo, companyRepo);
	}

	@Test
	public void test_createFlatFeeBillingRecord() {
		Company company = new Company();
		when(companyRepo.findOne(1l)).thenReturn(company);
		FlatFeeBillingRecord record = new FlatFeeBillingRecord();
		Authentication auth = mock(Authentication.class);
		User user = new User();
		when(auth.getPrincipal()).thenReturn(user);
		
		ModelAndView actual = controller.createFlatFeeBillingRecord(record, 1l, auth);
		
		verify(companyRepo).findOne(1l);
		verify(repo).save(record);
		assertThat(actual.getViewName()).isEqualTo("redirect:/billing-records");
	}
	
}
