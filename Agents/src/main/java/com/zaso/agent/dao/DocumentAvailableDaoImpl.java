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
import com.zaso.agent.model.DocumentAvailable;

@Repository
public class DocumentAvailableDaoImpl implements DocumentAvailableDao {

	private JdbcTemplate jdbcTemplate;
	
	public DocumentAvailableDaoImpl()
	{
		super();
	}
	
	
	@Autowired
	public DocumentAvailableDaoImpl(DataSource dataSource)
	{
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	
	
	@Override
	public void saveDocument(DocumentAvailable docAvailable) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO inserted_document(documentnumber,documentname)" + "values(?,?)";
		jdbcTemplate.update(sql,docAvailable.getDocNumber(),docAvailable.getDocName());

	}


	@Override
	public List<DocumentAvailable> findDocument(String docNumber) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM inserted_document where documentnumber="+docNumber;
		List<DocumentAvailable> avail1=jdbcTemplate.query(sql, new RowMapper<DocumentAvailable>(){
			@Override
			public DocumentAvailable mapRow(ResultSet rs, int rowNum) throws SQLException{
				DocumentAvailable avail=new DocumentAvailable();
				
				avail.setDocName(rs.getString("documentname"));
				return avail;
				}
			
		});
		return avail1;
	}
	
	@Override
	public List<DocumentAvailable> findDocument() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM inserted_document";
		List<DocumentAvailable> avail1=jdbcTemplate.query(sql, new RowMapper<DocumentAvailable>(){
			@Override
			public DocumentAvailable mapRow(ResultSet rs, int rowNum) throws SQLException{
				DocumentAvailable avail=new DocumentAvailable();
				
				avail.setDocName(rs.getString("documentnumber"));
				avail.setDocName(rs.getString("documentname"));
				return avail;
				}
			
		});
		return avail1;
	}
	
	

}
