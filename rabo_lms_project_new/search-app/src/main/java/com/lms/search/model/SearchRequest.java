package com.lms.search.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
	
	private String firstName;
	private String lastName;
	private String loanNumber;
	
}
