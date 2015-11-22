package com.zaso.agent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.zaso.agent.model.Agents;
import com.zaso.agent.model.Category;


@Repository
public class AgentDaoImpl implements AgentDao {
	
	private JdbcTemplate jdbcTemplate;
	 
	  
    public AgentDaoImpl()
    {
    	super();
    }
    @Autowired
    public AgentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
	public void saveOrUpdate(Agents agent) {
		// implementation details goes here...
		String sql = "INSERT INTO agents (objectid,address,email,fname,lname,mobile,speciality,longitude,lattitude)"
				+ " VALUES (?, ?, ?, ?,?,?,?,?,?)";
		jdbcTemplate.update(sql, agent.getObjectid(),agent.getAddress(),agent.getEmail(),agent.getFname(),
				agent.getLname(),agent.getMobile(),agent.getSpeciality(),agent.getLongitude(),agent.getLattitude());

	}

    @Override
  	public void DeleteAgent(String emailid) {
  		// implementation details goes here...
  		String sql = "DELETE FROM agents WHERE email ="+emailid;
  		jdbcTemplate.update(sql);

  	}
    
    
	@Override
	public List<Agents> list() {
		// implementation details goes here...
		String sql = "SELECT * FROM agents";
		List<Agents> agent = jdbcTemplate.query(sql, new RowMapper<Agents>() {

			@Override
			public Agents mapRow(ResultSet rs, int rowNum) throws SQLException {
				Agents agent1 = new Agents();
				agent1.setObjectid(rs.getString("objectid"));
				agent1.setAddress(rs.getString("address"));
				agent1.setEmail(rs.getString("email"));
				agent1.setFname(rs.getString("fname"));
				agent1.setLname(rs.getString("lname"));
				agent1.setMobile(rs.getString("mobile"));
				agent1.setSpeciality(rs.getString("speciality"));
				agent1.setLongitude(rs.getString("longitude"));
				agent1.setLattitude(rs.getString("lattitude"));
				return agent1;
			}

		});

		return agent;

}
}
