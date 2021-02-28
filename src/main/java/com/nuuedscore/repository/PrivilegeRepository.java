package com.nuuedscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuuedscore.domain.Privilege;

/**
 * Privilege Repository
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);

}