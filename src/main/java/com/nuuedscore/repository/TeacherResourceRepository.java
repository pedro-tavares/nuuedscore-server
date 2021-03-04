package com.nuuedscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nuuedscore.domain.TeacherResource;

/**
 * Teacher Resource Repository
 * 
 * @author PATavares
 * @since Mar 2021
 * 
 */
public interface TeacherResourceRepository extends JpaRepository<TeacherResource, Long> {

    @Query("SELECT s FROM TeacherResource s WHERE s.resource = :resourceToFind")
    TeacherResource findByResource(String resourceToFind);

}