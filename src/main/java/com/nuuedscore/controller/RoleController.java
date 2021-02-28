package com.nuuedscore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nuuedscore.domain.Role;
import com.nuuedscore.service.impl.RoleService;

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
@RequestMapping("/role")
public class RoleController extends BaseController {
	
	@Autowired
	RoleService roleService;

	/**
	 * Get all 
	 * 
	 * @return all the Role
	 */
    @GetMapping("/")
    public ResponseEntity<List<Role>> all() {
        return new ResponseEntity<List<Role>>((List<Role>) roleService.findAll(), HttpStatus.OK);
    }
 
}
