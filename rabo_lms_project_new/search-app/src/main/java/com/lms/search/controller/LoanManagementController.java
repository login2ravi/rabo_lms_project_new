package com.lms.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.search.entity.LoanDetail;
import com.lms.search.model.SearchRequest;
import com.lms.search.service.LoanManagementService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/secure")
@CrossOrigin
@Slf4j
public class LoanManagementController {
	
	 
	
	@Autowired
	LoanManagementService loanManagementService;
	
	
	@GetMapping("/search")
    public List<LoanDetail> search(SearchRequest searchRequest) {
		log.info("Inside Search method");
		return loanManagementService.search(searchRequest);
    }
		
}
