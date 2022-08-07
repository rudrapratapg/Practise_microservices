/**
 * 
 */
package com.eazybytes.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.cards.model.Cards;
import com.eazybytes.cards.model.Customer;
import com.eazybytes.cards.repository.CardsRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Eazy Bytes
 *
 */
@Slf4j
@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
		log.info("Calling myCards");
		List<Cards> cards = null;
		try {
			cards = cardsRepository.findByCustomerId(customer.getCustomerId());
			log.info("Cards : {}",cards);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Something went wrong! Error : ",e.getMessage());
		}
		if (cards != null) {
			return cards;
		} else {
			return null;
		}

	}

}
