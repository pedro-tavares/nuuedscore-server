package com.nuuedscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nuuedscore.domain.Person;

/**
 * Person Repository
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.username = :usernameToFind")
    Person findByUsername(String usernameToFind);
	
    @Query("SELECT p FROM Person p WHERE p.email = :emailToFind")
    Person findByEmail(String emailToFind);

}
