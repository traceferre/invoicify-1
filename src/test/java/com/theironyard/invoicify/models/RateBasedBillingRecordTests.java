package com.theironyard.invoicify.models;

import static org.assertj.core.api.Assertions.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

public class RateBasedBillingRecordTests {

	private RateBasedBillingRecord record;
	
	@Before
	public void setup() {
		record = new RateBasedBillingRecord();
	}
	
	@Test
	public void test_getters_and_setters() {
		Configuration configuration = new ConfigurationBuilder()
			.ignoreProperty("createdOn")
			.build();
		new BeanTester().testBean(RateBasedBillingRecord.class, configuration);
	}
	
	@Test
	public void test_createdOn_is_null_by_default() {
		Date actual = record.getCreatedOn();
		
		assertThat(actual).isNull();
	}
	
	@Test
	public void test_createdOn_gets_and_sets() {
		Date date = Date.valueOf("2017-02-02");
		record.setCreatedOn(date);
		
		Date actual = record.getCreatedOn();
		
		assertThat(actual).isEqualTo(date);
	}
	
	@Test
	public void test_getTotal_returns_product_of_rate_and_quantity() {
		double rate = Math.random();
		double quantity = Math.random();
		record.setRate(rate);
		record.setQuantity(quantity);
		
		double actual = record.getTotal();
		
		assertThat(actual).isCloseTo(rate * quantity, within(0.00001));
	}
	
}
