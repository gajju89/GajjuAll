package com.onlinetv.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.AdminPanel;
import com.onlinetv.config.MongoConfig;
import com.onlinetv.model.FreeVideosModel;
import com.onlinetv.model.LastMongoId;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import io.swagger.models.Operation;

@SpringComponent
@UIScope
public class AdminPanelService {

	private static String bucketName = "onlinetvbucket";
	private static String keyName = "AKIAIUNT2LQ62B4HH4EA";

	private MongoOperations operations;

	public void saveVideo(FreeVideosModel panel) {
		if (operations == null) {
			try {
				operations = MongoConfig.getMongoTemplate();
			} catch (Exception e) {

			}
		}

		operations.save(panel);
	}

	public String uploadToAws(File file) throws Exception {

		AWSCredentials credentials = new BasicAWSCredentials("AKIAIUNT2LQ62B4HH4EA",
				"QZXn2rh+JaJoItkHiRRHB9SvYoK/t/GM4N+nmi0V");
		AmazonS3 s3client = new AmazonS3Client(credentials);
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
		s3client.putObject(new PutObjectRequest(bucketName, file.getName(), file).withAccessControlList(acl));
		String url = ((AmazonS3Client) s3client).getResourceUrl(bucketName, file.getName());
		return url;
	}

	public LastMongoId getLast() {
		if (operations == null) {
			try {
				operations = MongoConfig.getMongoTemplate();
			} catch (Exception e) {

			}
		}

		Query searchUserQuery = new Query();
		LastMongoId init = operations.findOne(searchUserQuery, LastMongoId.class);
		if (init == null) {
			init = new LastMongoId();
			init.setLastid(1l);
			operations.insert(init);
		} else {
			init.setLastid(init.getLastid() + 1l);
			operations.updateFirst(searchUserQuery, Update.update("lastid", init.getLastid()), LastMongoId.class);
		}
		return init;
	}
	
	public List<FreeVideosModel> getAllFreeVideos()
	{
		//List<FreeVideosModel> freeList=new ArrayList<FreeVideosModel>();
		List<FreeVideosModel> allList=new ArrayList<FreeVideosModel>();
		if (operations == null) {
			try {
				operations = MongoConfig.getMongoTemplate();
			} catch (Exception e) {

			}
		}
		Query searchUserQuery = new Query();
		searchUserQuery.addCriteria(Criteria.where("paid").is(false));
		allList=operations.find(searchUserQuery, FreeVideosModel.class);
		return allList;
		
	}
	
	public void deleteSheduled(String url)
	{
		if (operations == null) {
			try {
				operations = MongoConfig.getMongoTemplate();
			} catch (Exception e) {

			}
		}
		Query searchUserQuery = new Query();
		searchUserQuery.addCriteria(Criteria.where("videoUrl").is(url));
		operations.remove(searchUserQuery,FreeVideosModel.class);
		
	}

}
