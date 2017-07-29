package com.zaso.agent.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.zaso.agent.model.AgentPic;

import com.zaso.agent.repositories.AgentPicRepository;

import com.zaso.agent.utils.GeneralUtil;

import redis.clients.jedis.Jedis;

@Repository
public class AgentPicServiceImpl  {
	@Autowired
	AgentPicRepository agentrepo;
	
	private static String bucketName = "";
	private static String keyName = "";

	/*private JdbcTemplate jdbcTemplate;

	@Autowired
	AgentPicDao age;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public AgentPicServiceImpl() {
		super();
	}*/

	public File singleSave(MultipartFile multiPartFile,String filetype) {
		// System.out.println("File Description:"+desc);
		String fileName = null;
		if (!multiPartFile.isEmpty()) {
			try {
				

			String filename = "/tmp/"+GeneralUtil.generateUUid()+"."+filetype;
				//String filename = GeneralUtil.generateUUid()+"."+filetype;
				byte[] bytes = multiPartFile.getBytes();
				File file = new File(filename);
				if(!file.exists())
				{
					file.createNewFile();
				}
				BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(file));
				buffStream.write(bytes);
				buffStream.close();

				return file;
			} catch (Exception e) {
				System.out.println("Hello");
			}

		} /*
			 * else { return null; }
			 */
		return null;

	}

	public File[] multipleSave(MultipartFile[] multipartFiles) {
		String fileName[] = null;
		File[] file = null;
		// String msg = "";
		if (multipartFiles != null && multipartFiles.length > 0) {
			for (int i = 0; i < multipartFiles.length; i++) {
				try {
					fileName[i] = GeneralUtil.generateUUid();
					byte[] bytes = multipartFiles[i].getBytes();
					//File[] file = null;
					file[i] = new File(fileName[i]);
					BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(file[i]));
					buffStream.write(bytes);
					buffStream.close();
					//return file;
					// msg += "You have successfully uploaded " + fileName
					// +"<br/>";
				} catch (Exception e) {
					// return "You failed to upload " + fileName + ": " +
					// e.getMessage() +"<br/>";
				}
			}
			//return file;
		}
		/*
		 * else { return null; }
		 */
		return file;
	}

	public String uploadToAws(File file) throws Exception {
		
		AWSCredentials credentials = new BasicAWSCredentials("", "");
        AmazonS3 s3client = new AmazonS3Client(credentials);
        AccessControlList acl = new AccessControlList();
        acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        s3client.putObject(new PutObjectRequest(bucketName, file.getName(), file).withAccessControlList(acl));
		String url = ((AmazonS3Client) s3client).getResourceUrl(bucketName, file.getName());
		return url;
	}

	/*
	 * public String[] uploadMultipleToAws(File[] file)throws Exception {
	 * String[] url=null; AmazonS3 s3client = new AmazonS3Client(new
	 * ProfileCredentialsProvider()); for(int i=0;i<file.length;i++){
	 * s3client.putObject(new PutObjectRequest(bucketName, keyName, file[i]));
	 * url[i]= ((AmazonS3Client) s3client).getResourceUrl("bucketName",
	 * file[i].getName()); } return url; }
	 */



	
	
	public void saveOrUpdate(String agentemailid,MultipartFile file,String filetype)  {
		// TODO Auto-generated method stub
		//String emailid=null;
		
		AgentPic agepic=new AgentPic();
		//MultipartFile file=null;
		agepic.setEmailId(agentemailid);
		
		AgentPicServiceImpl ageimpl=new AgentPicServiceImpl();
		File file1=ageimpl.singleSave(file,filetype);
		try
		{
		String url=ageimpl.uploadToAws(file1);
		
		agepic.setUrl(url);
		agentrepo.insert(agepic);
		}
		catch(Exception e){
			
	   System.out.println(e.getMessage());
		}
		

	}

	/*
	 * @Override public void DeleteAgentPic(String url) { // TODO Auto-generated
	 * method stub age.DeleteAgentPic(url); }
	 */
	
	public List<String> list(String agentid) {
		// TODO Auto-generated method stub
		List<String> urlList=new ArrayList<String>();
		urlList=getPicUrlList(agentid);
		return urlList;
	}
	
	public List<String> getPicUrlList(String agentid)
	{
		List<String> urlList=new ArrayList<String>();
		List<AgentPic> listPic=agentrepo.findByemailId(agentid);
		
		Iterator<AgentPic> iterator=listPic.iterator();
		while(iterator.hasNext())
		{
			String url=iterator.next().getUrl().toString();
			urlList.add(url);
		}
		
		return urlList;
	}

}
