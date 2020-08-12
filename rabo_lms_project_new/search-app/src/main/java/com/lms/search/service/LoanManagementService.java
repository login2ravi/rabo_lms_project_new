package com.lms.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.search.entity.LoanDetail;
import com.lms.search.model.SearchRequest;
import com.lms.search.repo.LoanRepo;

@Service
public class LoanManagementService {
	
	
	@Autowired
	private LoanRepo loanRepo;
	
	public List<LoanDetail> search(SearchRequest searchRequest) {
		return loanRepo.findByFirstNameAndLastNameAndLoanNumber(searchRequest.getFirstName(), searchRequest.getLastName(), searchRequest.getLoanNumber()); 
		
	}
	

}
