package com.nuuedscore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuuedscore.domain.Privilege;
import com.nuuedscore.service.impl.PrivilegeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Role Front Controller
 * 
 * @author PATavares
 * @since Feb 2021
 * 
 */
@Slf4j
@RestController
@RequestMapping("/privilege")
public class PrivilegeController extends BaseController {
	
	@Autowired
	PrivilegeService privilegeService;

	/**
	 * Get all 
	 * 
	 * @return all the Privilege
	 */
    @GetMapping("/")
    public ResponseEntity<List<Privilege>> all() {
        return new ResponseEntity<List<Privilege>>((List<Privilege>) privilegeService.findAll(), HttpStatus.OK);
    }
 
}
