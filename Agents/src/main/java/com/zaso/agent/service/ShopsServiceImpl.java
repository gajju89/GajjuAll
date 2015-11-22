package com.zaso.agent.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zaso.agent.dao.ShopsDao;
import com.zaso.agent.dao.ShopsDaoImpl;
import com.zaso.agent.model.Shops;

@Repository
public class ShopsServiceImpl implements ShopsService {
	
private JdbcTemplate jdbcTemplate;
    
    @Autowired
    ShopsDao sop;
 
   
   @Autowired
   public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public ShopsServiceImpl()
    {
    	super();
    }
    
    @Override
    public void DeleteShop(String emailid)
    {
    	sop.DeleteShop(emailid);
    }

	@Override
	public void saveOrUpdate(Shops shop) {
		// TODO Auto-generated method stub
		sop.saveOrUpdate(shop);
	}

	@Override
	public List<Shops> list() {
		// TODO Auto-generated method stub
		return sop.list();
	}

}
