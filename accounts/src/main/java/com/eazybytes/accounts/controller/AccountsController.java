package com.eazybytes.accounts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.config.AccountsServiceConfig;
import com.eazybytes.accounts.model.Accounts;
import com.eazybytes.accounts.model.Cards;
import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.model.CustomerDetails;
import com.eazybytes.accounts.model.Loans;
import com.eazybytes.accounts.model.Properties;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountsController {
	
	@Autowired
	AccountsServiceConfig accountsServiceConfig;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	LoansFeignClient loansFeignClient;
	
	@Autowired
	CardsFeignClient cardsFeignClient;
	
	
	  @GetMapping("/allAccounts") 
	  List<Accounts> getAllAccounts(){ 
		  log.info("Calling allAccounts...");
		  List<Accounts> accounts = new ArrayList<>(); 
		  try {
			  accountsRepository.findAll().forEach(account -> accounts.add(account));
			} catch (Exception e) {
				e.printStackTrace();
				log.error("accounts not found, error : ",e.getMessage());
			}
		  log.info("accounts : {}",accounts);
		  return accounts; 
	  }
	 
	
	@PostMapping("/myAccount")
	public Accounts getAccountsDetails(@RequestBody Customer customer) {
		System.out.println(customer);
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if(accounts != null) {
			return accounts;
		} else {
			return null;
		}
	}
	
	@PostMapping("/myCustomerDetails")
	public CustomerDetails myCustomerDetails(@RequestBody Customer customer) {
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<Loans> loans = loansFeignClient.getLoanDetails(customer);
		List<Cards> cards = cardsFeignClient.getCardDetails(customer);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setAccount(account);
		customerDetails.setCards(cards);
		customerDetails.setLoans(loans);
		return customerDetails;
	}
	
	@GetMapping("/accounts/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsServiceConfig.getMsg(), accountsServiceConfig.getBuildVersion(),
				accountsServiceConfig.getMailDetails(), accountsServiceConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
		
	}
	

}
