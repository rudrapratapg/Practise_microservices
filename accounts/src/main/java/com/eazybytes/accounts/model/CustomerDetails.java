package com.eazybytes.accounts.model;

import java.util.List;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CustomerDetails {
	
	Accounts account;
	List<Cards> cards;
	List<Loans> loans;	
	

}
