package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.*;

import org.junit.*;
import org.springframework.web.servlet.ModelAndView;

public class BillingRecordControllerTests {
	
	private BillingRecordController controller;
	
	@Before
	public void setup() {
		controller = new BillingRecordController();
	}

	@Test
	public void test_list() {
		ModelAndView actual = controller.list();
		
		assertThat(actual.getViewName()).isEqualTo("billing-records/list");
	}
	
}
