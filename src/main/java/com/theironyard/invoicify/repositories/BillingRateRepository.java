package com.theironyard.invoicify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theironyard.invoicify.models.BillingRecord;

public interface BillingRateRepository extends JpaRepository<BillingRecord, Long> {

}
