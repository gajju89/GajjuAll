package com.zaso.agent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.Shops;
@Repository
public class ShopsDaoImpl implements ShopsDao {

	private JdbcTemplate jdbcTemplate;
	 
	  
    public ShopsDaoImpl()
    {
    	super();
    }
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    

    @Override
 	public void saveOrUpdate(Shops shop) {
 		// implementation details goes here...
 		String sql = "INSERT INTO shops (objectid,address,email,fname,lname,mobile,speciality)"
 				+ " VALUES (?, ?, ?, ?,?,?,?)";
 		jdbcTemplate.update(sql, shop.getObjectid(),shop.getAddress(),shop.getEmail(),shop.getFname(),
 				shop.getLname(),shop.getMobile(),shop.getSpeciality());

 	}
    

	 @Override
	  	public void DeleteShop(String emailid) {
	  		// implementation details goes here...
	  		String sql = "DELETE FROM category WHERE categoryname ="+emailid;
	  		jdbcTemplate.update(sql);

	  	}

 	@Override
 	public List<Shops> list() {
 		// implementation details goes here...
 		String sql = "SELECT * FROM shops";
 		List<Shops> shop = jdbcTemplate.query(sql, new RowMapper<Shops>() {

 			@Override
 			public Shops mapRow(ResultSet rs, int rowNum) throws SQLException {
 				Shops shop1 = new Shops();
 				shop1.setObjectid(rs.getString("objectid"));
 				shop1.setAddress(rs.getString("address"));
 				shop1.setEmail(rs.getString("email"));
 				shop1.setFname(rs.getString("fname"));
 				shop1.setLname(rs.getString("lname"));
 				shop1.setMobile(rs.getString("mobile"));
 				shop1.setSpeciality(rs.getString("speciality"));
 			
 				return shop1;
 			}

 		});

 		return shop;

 }

}
