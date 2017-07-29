package com.zaso.agent.dao;

import java.util.List;
import com.zaso.agent.model.Shops;

public interface ShopsDao {
	
	
	
 public void saveOrUpdate(Shops shop);
     
	 public List<Shops> list();
	 
	 public void DeleteShop(String emailid);


}
