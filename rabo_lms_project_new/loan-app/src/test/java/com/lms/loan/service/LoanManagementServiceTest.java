package com.lms.loan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import com.lms.loan.entity.LoanDetail;
import com.lms.loan.exception.BusinessException;
import com.lms.loan.exception.ErrorCode;
import com.lms.loan.model.CustomerDetails;
import com.lms.loan.repo.LoanRepo;






@ExtendWith(MockitoExtension.class)
class LoanManagementServiceTest {

	@InjectMocks
	LoanManagementService loanManagementService;
	
	@Mock
	private LoanRepo loanRepo;
	
	
	
	@Test
	void getLoanDetails() {
		LoanDetail loanDetail = getLoanDetail();
		Mockito.when(loanRepo.findByLoanNumber("HL-1001")).thenReturn(loanDetail);
		LoanDetail loanDetails = loanManagementService.getLoanDetails("HL-1001");
		assertEquals(loanDetails, loanDetail);
	}
	
	@Test
	void addLoan() {
		CustomerDetails customerDetails =  new CustomerDetails();
		customerDetails.setLoanNumber("HL-1001");
		LoanDetail loanDetail = new LoanDetail();
		loanDetail.setLoanNumber("HL-1001");
		loanDetail.setFirstName("fname");
		Mockito.when(loanRepo.save(Mockito.any(LoanDetail.class))).thenReturn(loanDetail);
		Mockito.when(loanRepo.findByLoanNumber(customerDetails.getLoanNumber())).thenReturn(null);
		
		LoanDetail actual = loanManagementService.addLoan(customerDetails);
		Assertions.assertNotNull(actual);
				
	
	}
	
	@Test
	void addLoanException() {
		CustomerDetails customerDetails =  new CustomerDetails();
		customerDetails.setLoanNumber("HL-1001");
		LoanDetail loanDetail = new LoanDetail();
		loanDetail.setLoanNumber("HL-1001");
		loanDetail.setFirstName("fname");
		//Mockito.when(loanRepo.save(loanDetail)).thenThrow(new RuntimeException(""));
		Mockito.when(loanRepo.findByLoanNumber(customerDetails.getLoanNumber())).thenReturn(loanDetail);
		
		
		BusinessException exception = assertThrows(BusinessException.class, () -> {
		loanManagementService.addLoan(customerDetails); });
		
		assertEquals("Loan number already exists",exception.getMessage());
		 
		
	}
	
	
	@Test
	void updateLoan() {
		
		CustomerDetails customerDetails =  new CustomerDetails();
		customerDetails.setLoanNumber("HL-1001");
		
		LoanDetail loanDetail = getLoanDetail();
		Mockito.when(loanRepo.findByLoanNumber(customerDetails.getLoanNumber())).thenReturn(loanDetail);
		
		loanManagementService.updateLoan(customerDetails);
		verify(loanRepo,times(1)).save(loanDetail);
	}
	
	private List<LoanDetail> getResultLoanList(){
		List<LoanDetail> loanDetailList = new ArrayList<LoanDetail>();
		loanDetailList.add(getLoanDetail());
		return loanDetailList;
		
	}
	
	private LoanDetail getLoanDetail() {
		LoanDetail loanDetail = new LoanDetail();
		loanDetail.setFirstName("Rahul");
		loanDetail.setLastName("Dravid");
		loanDetail.setLoanNumber("HL-1001");
		loanDetail.setCity("Chennai");
		loanDetail.setAmount(50000L);
		return loanDetail;
				
		
	}
	
	
}
