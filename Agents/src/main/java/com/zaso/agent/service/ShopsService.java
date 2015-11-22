package com.zaso.agent.service;

import java.util.List;

import com.zaso.agent.model.Shops;

public interface ShopsService {
	
	public void saveOrUpdate(Shops shop);
    
	 public List<Shops> list();
	 
	 public void DeleteShop(String emailid);


}
