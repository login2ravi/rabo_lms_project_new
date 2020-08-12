package com.lms.loan.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDetails {
	
	
	private long loanDetailId; 
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String loanNumber;
	
	
	private Long amount;
	
	
	private String address1;
	
	
	private String address2;
	
	
	private String city;
	
	
	private String loanType;
	
	
	private long loanTerm;

	
}
