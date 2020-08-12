package com.lms.search.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.search.entity.LoanDetail;

public interface LoanRepo extends JpaRepository<LoanDetail, Integer>{
	
	List<LoanDetail> findByFirstNameAndLastNameAndLoanNumber(String firstName,
						String lastName,
						String loanNumber);
	
	
}
