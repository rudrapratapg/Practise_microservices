/**
 * 
 */
package com.eazybytes.loans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.loans.model.Customer;
import com.eazybytes.loans.model.Loans;
import com.eazybytes.loans.repository.LoansRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Eazy Bytes
 *
 */
@Slf4j
@RestController
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		log.info("Calling myLoans...");
		List<Loans> loans = null;
		try {
			loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
			log.info("Loans : {} ",loans);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Something went wrong! Error : ", e.getMessage());
		}
		if (loans != null) {
			return loans;
		} else {
			return null;
		}

	}

}
