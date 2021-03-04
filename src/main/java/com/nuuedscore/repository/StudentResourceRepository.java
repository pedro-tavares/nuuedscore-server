package com.nuuedscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nuuedscore.domain.StudentResource;

/**
 * Student Resource Repository
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
public interface StudentResourceRepository extends JpaRepository<StudentResource, Long> {

    @Query("SELECT s FROM StudentResource s WHERE s.resource = :resourceToFind")
    StudentResource findByResource(String resourceToFind);

}