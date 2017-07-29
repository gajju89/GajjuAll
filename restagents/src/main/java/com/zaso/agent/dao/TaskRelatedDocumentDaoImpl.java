package com.zaso.agent.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.TaskRelatedDocument;

@Repository
public class TaskRelatedDocumentDaoImpl implements TaskRelatedDocumentDao {
	
	private JdbcTemplate jdbcTemplate;
	 
	  
    public TaskRelatedDocumentDaoImpl()
    {
    	super();
    }
    @Autowired
    public TaskRelatedDocumentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    

	@Override
	public void saveTaskRelatedDocument(TaskRelatedDocument taskDocument) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO task_related_doc(taskid,task, document_number)" + "values(?,?,?)";
		jdbcTemplate.update(sql,taskDocument.getTaskId(),taskDocument.getTask(),taskDocument.getDocNumber());
		

	}
	
	public List<TaskRelatedDocument> getAllTask()
	{
		String sql="SELECT * FROM task_related_doc";
		List<TaskRelatedDocument> task1=jdbcTemplate.query(sql,new RowMapper<TaskRelatedDocument>(){
			@Override
			public TaskRelatedDocument mapRow(ResultSet rs,int numRow)throws SQLException{
				TaskRelatedDocument task=new TaskRelatedDocument();
				task.setTaskId(rs.getString("taskid"));
				task.setTask(rs.getString("task"));
				task.setDocNumber(rs.getString("document_number"));
				return task;
				
			}
		});
				return task1;
	}

	public HashMap<String, List<TaskRelatedDocument>> getTaskDocument(String task)
	{
		String sql="SELECT document_number FROM task_related_doc where task=" +task;
		HashMap<String, List<TaskRelatedDocument>> taskMap1=new HashMap<String, List<TaskRelatedDocument>>();
				List<TaskRelatedDocument> task2=jdbcTemplate.query(sql,new RowMapper<TaskRelatedDocument>(){
			@Override
			public TaskRelatedDocument mapRow(ResultSet rs,int numRow)throws SQLException{
				TaskRelatedDocument task1=new TaskRelatedDocument();
				//task1.setTaskId(rs.getString("taskid"));
				//task1.setTask(rs.getString("task"));
				task1.setDocNumber(rs.getString("document_number"));
				return task1;
			}
		});
		taskMap1.put(task,task2);		
		return taskMap1;
	}
}
