package com.zaso.agent.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.ForgotPassword;
import com.zaso.agent.model.OtpForMobileVarification;
import com.zaso.agent.model.UserRegistration;

@Repository
public class ForgotPasswordServiceImpl {
	
	@Autowired
	MailSenderService mailServ;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	MongoOperations operations ;
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		operations = (MongoOperations) mongoTemplate;
		
	}
	
	
	
	
	
	public void saveVarificationCode(String email,String varCode)
	{
		ForgotPassword pass=new ForgotPassword();
		pass.setEmailid(email);
		pass.setVarificationCode(varCode);
		
			operations.save(pass);
		mailServ.sendMail(email, varCode);
		
		
	}
	
	public void deleteVarificationCode(String email)
	{
		Query deleteQuery=new Query();
		deleteQuery.addCriteria(Criteria.where("emailid").is(email));
		operations.remove(deleteQuery,ForgotPassword.class);
	}
	
	public String findVericationCode(String email)
	{
		Query query=new Query(Criteria.where("emailid").is(email));
		ForgotPassword pass=operations.findOne(query, ForgotPassword.class);
		return pass.getVarificationCode();
	}





	public void saveOtp(String email, String code) {
		// TODO Auto-generated method stub
		OtpForMobileVarification mobileOtp=new OtpForMobileVarification();
		mobileOtp.setUserEmail(email);
		mobileOtp.setOtp(code);
		Query query=new Query();
		operations.save(mobileOtp);
		
		mailServ.sendOtp(email, code);
	}
	
	public void deleteOtp(String email,String otp)
	{
		Query deleteQuery=new Query();
		deleteQuery.addCriteria(Criteria.where("userEmail").is(email));
		deleteQuery.addCriteria(Criteria.where("otp").is(otp));
		boolean ifExist=operations.exists(deleteQuery, OtpForMobileVarification.class);
		if(ifExist)
		{
			operations.remove(deleteQuery,OtpForMobileVarification.class);
			UserRegistration reg=new UserRegistration();
			reg.setMobileNumberVarified(true);
			Query updateQuery=new Query();
			operations.updateFirst(updateQuery,Update.update("mobileNumberVarified",reg.isMobileNumberVarified()),UserRegistration.class);
		}
		
	}

}
