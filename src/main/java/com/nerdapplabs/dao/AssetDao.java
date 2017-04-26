package com.nerdapplabs.dao;

import org.springframework.data.repository.CrudRepository;

import com.nerdapplabs.model.Asset;



public interface AssetDao extends CrudRepository<Asset , Long>{
    
	 public Asset findBySerialnumber(String serialnumber);
	 
}
