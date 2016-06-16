package com.zaso.agent.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.zaso.agent.model.DocumentStorage;
import com.zaso.agent.repositories.DocumentStorageRepository;
import com.zaso.agent.utils.GeneralUtil;

@Repository
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	DocumentStorageRepository docRepo;
	
	private static String bucketName = "Agentpic";
	private static String objectKey = "AKIAJ65RHJATU3BAIHCQ";
	
	private JdbcTemplate jdbcTemplate;
	
	public DocumentServiceImpl()
	{
		super();
	}
	
	@Autowired
	public DocumentServiceImpl(DataSource dataSource)
	{
		jdbcTemplate=new JdbcTemplate(dataSource);
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
	
public void uploadToAws(File file) throws Exception {
	//URL url=null;
		AWSCredentials credentials = new BasicAWSCredentials("AKIAICRRVE7TOCYTC4TA", "RjMDQiAu7QvnAs3m3R3z1qRReUbXKKyHXihvKOvR");
        AmazonS3 s3client = new AmazonS3Client(credentials);
        AccessControlList acl = new AccessControlList();
        acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        s3client.putObject(new PutObjectRequest(bucketName, file.getName(), file).withAccessControlList(acl));
		//String url = ((AmazonS3Client) s3client).getResourceUrl(bucketName, file.getName());
		//return url;
       /* try {
			
			java.util.Date expiration = new java.util.Date();
			long milliSeconds = expiration.getTime();
			milliSeconds += 1000 * 60 * 60; // Add 1 hour.
			expiration.setTime(milliSeconds);

			GeneratePresignedUrlRequest generatePresignedUrlRequest = 
				    new GeneratePresignedUrlRequest(bucketName, objectKey);
			generatePresignedUrlRequest.setMethod(HttpMethod.GET); 
			generatePresignedUrlRequest.setExpiration(expiration);

			url = s3client.generatePresignedUrl(generatePresignedUrlRequest); 

			//return url;
		} catch (AmazonServiceException exception) {
			exception.getStackTrace();
			
		} catch (AmazonClientException ace) {
			ace.getStackTrace();
			
		}
        
        return url;
       */
	}

	public URL getObjectFromBucket()
	{
		URL url=null;
		AWSCredentials credentials = new BasicAWSCredentials("AKIAICRRVE7TOCYTC4TA", "RjMDQiAu7QvnAs3m3R3z1qRReUbXKKyHXihvKOvR");
        AmazonS3 s3client = new AmazonS3Client(credentials);
        try {
			
			java.util.Date expiration = new java.util.Date();
			long milliSeconds = expiration.getTime();
			milliSeconds += 1000 * 60 * 60; // Add 1 hour.
			expiration.setTime(milliSeconds);

			GeneratePresignedUrlRequest generatePresignedUrlRequest = 
				    new GeneratePresignedUrlRequest(bucketName, objectKey);
			//generatePresignedUrlRequest.setMethod(HttpMethod.GET); 
			generatePresignedUrlRequest.setExpiration(expiration);

			 url = s3client.generatePresignedUrl(generatePresignedUrlRequest); 
			
			

			
		} catch (AmazonServiceException exception) {
			exception.printStackTrace();
			
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			
		}
        
        return url;
	}



	@Override
	public void saveDocument(MultipartFile[] file,String userid) {
		// TODO Auto-generated method stub
		DocumentServiceImpl doc=new DocumentServiceImpl();
		//GeneralUtil docnum=new GeneralUtil();
		DocumentStorage model=new DocumentStorage();
		File[] file1=doc.multipleSave(file);
		for(int i=0;i<file1.length;i++)
		{
			try{
			doc.uploadToAws(file1[i]);
			String uid=userid;
			model.setDocNumber(file1[i].getName());
			model.setObjectKey(objectKey);
			model.setUserId(uid);
			//model.setDocNumber(docNum);
			docRepo.save(model);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		

	}

}
