package com.theironyard.invoicify.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

public class FlatFeeBillingRecordTests {

	private FlatFeeBillingRecord record;
	
	@Before
	public void setup() {
		record = new FlatFeeBillingRecord();
	}
	
	@Test
	public void test_getters_and_setters() {
		Configuration configuration = new ConfigurationBuilder()
			.ignoreProperty("createdOn")
			.build();
		new BeanTester().testBean(FlatFeeBillingRecord.class, configuration);
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
		double amount = Math.random();
		record.setAmount(amount);
		
		double actual = record.getTotal();
		
		assertThat(actual).isEqualTo(amount);
	}
}
