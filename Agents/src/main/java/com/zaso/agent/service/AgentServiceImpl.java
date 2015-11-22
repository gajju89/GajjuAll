package com.zaso.agent.service;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zaso.agent.dao.AgentDao;
import com.zaso.agent.dao.AgentDaoImpl;
import com.zaso.agent.model.Agents;
import com.zaso.agent.model.MongoAgent;
import com.zaso.agent.model.Position;
import com.zaso.agent.repositories.AgentRepository;
import com.zaso.agent.utils.GeneralUtil;

@Repository
public class AgentServiceImpl implements AgentService {
	@Autowired
	AgentRepository agentrepo;

	private JdbcTemplate jdbcTemplate;
    
    @Autowired
    AgentDao age;
 
   
   @Autowired
   public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public AgentServiceImpl()
    {
    	super();
    }

	@Override
	public void saveOrUpdate(Agents agent) {
		agent.setObjectid(GeneralUtil.getLastUserId());
		// TODO Auto-generated method stub
		age.saveOrUpdate(agent );
		MongoAgent mongoAgent=new MongoAgent();
		mongoAgent.setAddress(agent.getAddress());
		List<Position> locations = new ArrayList<Position>();
		Position position = new Position();
		position.setLat(Double.parseDouble(agent.getLattitude()));
		position.setLon(Double.parseDouble(agent.getLongitude()));
		locations.add(position);
		mongoAgent.setLocation(locations);
		mongoAgent.setObjectid(agent.getObjectid());
		mongoAgent.setSpeciality(agent.getSpeciality());
		agentrepo.save(mongoAgent);
	}
	
	@Override
	 public void DeleteAgent(String emailid)
	 {
		age.DeleteAgent(emailid);
	 }
	@Override
	public List<Agents> list() {
		// TODO Auto-generated method stub
		return age.list();
	}

}
