package com.theironyard.invoicify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theironyard.invoicify.models.InvoiceLineItem;

public interface InvoiceLineItemRepository extends JpaRepository<InvoiceLineItem, Long> {

}
