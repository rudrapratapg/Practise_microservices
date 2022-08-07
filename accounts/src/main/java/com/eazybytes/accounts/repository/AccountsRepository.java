package com.eazybytes.accounts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.accounts.model.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long>//provided by JPA 
{
	//we have not provided any definition of this method
	Accounts findByCustomerId(int customerId);

	
	/*
	 * Reason is not only we are in interface
	 * But also, this is the power of JPA
	 * We need not to write anything
	 * JPA will itself go and look into the Accounts table for customerId
	 * 
	 * 
	 * if we name out function as findByCustomeridAndEmailId(int customerId, String emailId)
	 * then JPA will go to the table and look into Accounts table for customer id 
	 * and Email id as provided in parameter
	 * 
	 * We dont have to write a lot of code to interact with the database
	 * */
	
	
}
