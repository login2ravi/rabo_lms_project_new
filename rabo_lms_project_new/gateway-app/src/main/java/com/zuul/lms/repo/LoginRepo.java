package com.zuul.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zuul.lms.entity.User;



public interface LoginRepo extends JpaRepository<User, Integer>{
	
	User findByUserName(String userName);
	

}
