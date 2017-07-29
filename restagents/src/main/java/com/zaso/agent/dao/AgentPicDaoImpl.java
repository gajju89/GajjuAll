package com.zaso.agent.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.zaso.agent.model.AgentPic;
import com.zaso.agent.model.Agents;
import com.zaso.agent.utils.GeneralUtil;

import java.net.URL;
@Repository
public class AgentPicDaoImpl implements AgentPicDao {

	
	
	
	private JdbcTemplate jdbcTemplate;
	 
	  
    public AgentPicDaoImpl()
    {
    	super();
    }
    @Autowired
    public AgentPicDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
  
   
    
    @Override
   	public void saveOrUpdate(AgentPic agepic) {
   		// implementation details goes here...
    	AgentPicDaoImpl agp=new AgentPicDaoImpl();
    	//String url=agp.uploadToAws(file);
   		String sql = "INSERT INTO agentpic (agentid,picurl)"
   				+ " VALUES (?, ?)";
   		jdbcTemplate.update(sql, agepic.getEmailId(),agepic.getUrl());

   	}
    
   /* @Override
  	public void DeleteAgentPic(String url) {
  		// implementation details goes here...
  		String sql = "DELETE FROM agentpic WHERE picurl="+url;
  		jdbcTemplate.update(sql);

  	}*/
    
    @Override
	public List<AgentPic> list(String agentid) {
		// implementation details goes here...
		String sql = "SELECT picurl FROM agentpic where agentid="+agentid;
		List<AgentPic> agentpic = jdbcTemplate.query(sql, new RowMapper<AgentPic>() {

			@Override
			public AgentPic mapRow(ResultSet rs, int rowNum) throws SQLException {
				AgentPic agentpic1 = new AgentPic();
				agentpic1.setEmailId(rs.getString("emailId"));
				//agentpic1.setUrl(rs.getString("picurl"));
				
				
				return agentpic1;
			}

		});

		return agentpic;

}
    
    

}
