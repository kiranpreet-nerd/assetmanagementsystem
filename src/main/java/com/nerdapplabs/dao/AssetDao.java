package com.nerdapplabs.dao;

import org.springframework.data.repository.CrudRepository;

import com.nerdapplabs.model.AssetRequest;

public interface AssetDao  extends CrudRepository<AssetRequest, Long> {
	
	public AssetRequest findById(Long id);

}
