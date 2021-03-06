package com.nuuedscore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuuedscore.domain.Person;
import com.nuuedscore.exception.PersonEmailCannotBeNullException;
import com.nuuedscore.exception.PersonExistsException;
import com.nuuedscore.service.IPersonService;

import lombok.extern.slf4j.Slf4j;

/**
 * Person Front Controller
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {
	
	@Autowired
	IPersonService personService;

	/**
	 * Get all 
	 * 
	 * @return all the Person
	 */
    @GetMapping("/")
    public ResponseEntity<List<Person>> all() {
        return new ResponseEntity<List<Person>>((List<Person>) personService.findAll(), HttpStatus.OK);
    }

    /**
     * Person Register
     * 
     * @param person
     * @return registration outcome
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Person person) {
    	log.info("register:{}", person.getEmail());
    	
    	ResponseEntity<String> response = null;
        try {	
        	Person registeredPerson = personService.register(person);
        	log.info("registeredPerson:{}", registeredPerson.toString());
        	
        	response = new ResponseEntity<String>("Person has been Succesfully Registered", HttpStatus.OK);
			
		} catch (PersonEmailCannotBeNullException | PersonExistsException e) {
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} 
        return response;
    }    

    /**
     * Login
     * Provided by Spring Security
     * 
     * @PostMapping("/login")
     */

    /**
     * Save Person
     * 
     * @param person
     * @return service outcome
     */
    @PostMapping("/save")
    public ResponseEntity<Person> save(@RequestBody Person person) {
    	log.info("save:{}", person.toString());
    	
        return new ResponseEntity<Person>(personService.save(person), HttpStatus.OK);
    }    
 
}
