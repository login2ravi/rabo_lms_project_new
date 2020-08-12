package com.lms.loan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.loan.entity.LoanDetail;
import com.lms.loan.model.CustomerDetails;
import com.lms.loan.service.LoanManagementService;

@RestController
@RequestMapping("/secure")
@CrossOrigin
public class LoanManagementController {
	
	 Logger logger = LoggerFactory.getLogger(LoanManagementController.class);
	
	@Autowired
	LoanManagementService loanManagementService;
	
	
	@GetMapping("/getloandetails/{loanNumber}")
   public LoanDetail getLoanDetails(@PathVariable String loanNumber) {
		return loanManagementService.getLoanDetails(loanNumber);
        
    }

	@PostMapping("/addloan")
	public ResponseEntity<LoanDetail> addLoan(@RequestBody CustomerDetails customerDetails) {
        
		return new ResponseEntity<>(loanManagementService.addLoan(customerDetails), HttpStatus.OK);
        
    }
	
	@PutMapping("/updateloan")
	public ResponseEntity<LoanDetail> updateLoan(@RequestBody CustomerDetails customerDetails) {
		LoanDetail loanDetail = loanManagementService.updateLoan(customerDetails);  
		 
		 return new ResponseEntity<>(loanDetail, HttpStatus.OK);
	}
	
}
