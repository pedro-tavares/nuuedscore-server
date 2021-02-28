package com.nuuedscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nuuedscore.domain.Role;

/**
 * Role Repository
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}