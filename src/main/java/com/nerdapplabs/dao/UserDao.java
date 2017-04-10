package com.nerdapplabs.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

import com.nerdapplabs.model.User;

public interface UserDao extends CrudRepository<User , String>{
	
            public User findByEmail(String email);
            
            //public User findOne(String email);
          //  @Modifying
            //@Query("UPDATE user u SET u.firstname = :firstname, u.lastname = :lastname, u.email WHERE c.id = :companyId")
            //int updateAddress(@RequestParam("companyId") int companyId, @RequestParam("address") String address);

            
		
	}

