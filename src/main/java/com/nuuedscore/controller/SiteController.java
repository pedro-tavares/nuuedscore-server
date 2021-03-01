package com.nuuedscore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuuedscore.domain.Site;
import com.nuuedscore.service.impl.SiteService;

import lombok.extern.slf4j.Slf4j;

/**
 * Site Front Controller
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@RestController
@RequestMapping("/site")
public class SiteController extends BaseController {
	
	@Autowired
	SiteService siteService;

	/**
	 * Get all 
	 * 
	 * @return all the Site
	 */
    @GetMapping("/")
    public ResponseEntity<List<Site>> all() {
        return new ResponseEntity<List<Site>>((List<Site>) siteService.findAll(), HttpStatus.OK);
    }
 
}
