package com.lms.loan.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.loan.entity.LoanDetail;

public interface LoanRepo extends JpaRepository<LoanDetail, Integer>{
	
	
	LoanDetail findByLoanNumber(String loanNumber);

	
}
