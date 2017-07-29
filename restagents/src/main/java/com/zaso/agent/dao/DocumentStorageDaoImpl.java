package com.zaso.agent.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.DocumentStorage;
@Repository
public class DocumentStorageDaoImpl implements DocumentStorageDao {

	
		private JdbcTemplate jdbcTemplate;
		 
		  
	    public DocumentStorageDaoImpl()
	    {
	    	super();
	    }
	    @Autowired
	    public DocumentStorageDaoImpl(DataSource dataSource) {
	        jdbcTemplate = new JdbcTemplate(dataSource);
	    }
	    
	    
	    @Override
		public void saveDocument(DocumentStorage document) {
			// TODO Auto-generated method stub
	    	String sql="INSERT INTO document_storage(id,userid,objectkey,documentnumber)" + "values(?,?,?,?)";
	    	
	    	jdbcTemplate.update(sql,document.getDocid(),document.getUserId(),document.getObjectKey(),document.getDocNumber());
	    	
	    
}
	

}
