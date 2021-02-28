package com.nuuedscore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nuuedscore.domain.Site;

/**
 * Site Repository
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
public interface SiteRepository extends JpaRepository<Site, Long> {

    @Query("SELECT s FROM Site s WHERE s.url = :urlToFind")
    Site findByUrl(String urlToFind);

}