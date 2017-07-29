package com.zaso.agent.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.ForgotPassword;
import com.zaso.agent.model.LastAddressId;
import com.zaso.agent.model.Response;
import com.zaso.agent.model.UserAddressModel;
import com.zaso.agent.model.UserAddressWithAddressIdModel;
import com.zaso.agent.model.UserAuthentication;
import com.zaso.agent.model.UserRegistration;
import com.zaso.agent.model.UserUpdate;
import com.zaso.agent.repositories.UserAuthenticationRepository;
import com.zaso.agent.repositories.UserRegistrationRepository;
import com.zaso.agent.utils.GeneralUtil;

@Repository
public class UserRegistrationServiceImpl {
	
	boolean status;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	MongoOperations operations ;
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		operations = (MongoOperations) mongoTemplate;
		
	};
	
	
	@Autowired
	UserRegistrationRepository regRepo;
	
	@Autowired
	UserAuthenticationRepository authRepo;
	
	@Autowired
	ForgotPasswordServiceImpl forgotService;
	
	
	public Response saveUser(UserRegistration user) {
		// TODO Auto-generated method stub
		String accessToken=GeneralUtil.generateAccessToken();
		boolean usedMobileNumber=false;
		Response resp=new Response();
		
		Criteria criteriaEmail=new Criteria();
		criteriaEmail.where("emailid").is(user.getEmailid());
		Query queryMobile=new Query(Criteria.where("mobileNumber").is(user.getMobileNumber()));
		Query query=new Query(criteriaEmail);
		UserRegistration reg=operations.findOne(query,UserRegistration.class);
		usedMobileNumber=operations.exists(queryMobile,UserRegistration.class);
		 if(reg.getEmailid()!=null)
		 {
			 resp.setStatus(false);
			 resp.setTocken(accessToken);
			 resp.setUsedMobileNumber(usedMobileNumber);
			 return resp;
			 
		 }else{
			
		regRepo.insert(user);
		
		UserAuthentication auth=new UserAuthentication();
		auth.setAuthCode(accessToken);
		auth.setEmailId(user.getEmailid());
		authRepo.insert(auth);
		
		 
		 
		
		 
		if(accessToken!=null)
		{
			resp.setStatus(true);
			resp.setTocken(accessToken);
			resp.setUsedMobileNumber(usedMobileNumber);
			return resp;
		}
			else
			{
				resp.setStatus(false);
				resp.setTocken(accessToken);
			}
		return resp;
			
		 }

	}
	
	@SuppressWarnings("deprecation")
	public Response getUserLoginInfo(String email,String passw) {
		// TODO Auto-generated method stub
		 status=false;
		 
		 Date dateRegistration,currentDate;
		 dateRegistration=new Date();
		 boolean verifiedMobile=false;
		 String accessToken=GeneralUtil.generateAccessToken();
		Response resp=new Response();
		UserAuthentication userAuth=new UserAuthentication();
		List<UserRegistration> register=regRepo.findByemailid(email);
		//List<UserRegistration> register=regRepo.findAll();
		Iterator<UserRegistration> itr=register.iterator();
		//Date currentDate=new Date();
		
		verifiedMobile=register.get(0).isMobileNumberVarified();
		SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 try {
			 if(register.get(0).getDate()!=null)
			dateRegistration=format.parse(register.get(0).getDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 currentDate=new Date();
		while(itr.hasNext())
		{
			UserRegistration register1=itr.next();
		if(register1.getEmailid().equals(email)&&register1.getPassword().equals(passw))
		{
			status=true;
			userAuth.setAuthCode(accessToken);
			userAuth.setEmailId(email);
			userAuth.setLoginDate( new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate));
			
			authRepo.insert(userAuth);
		}
		}
		
	    
	    //UserAuthentication userAuth=authRepo.findByEmailId(email);
		resp.setTocken(accessToken);
	    resp.setStatus(status);
	    resp.setVerifiedMobileNumber(verifiedMobile);
	    if(dateRegistration!=null)
	    resp.setUserRegisterDate( new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(dateRegistration));
	   // resp.setTocken(userAuth.getAuthCode());
		
		
			return resp;
		
	}
	
public	boolean authentication(String authcode)
	{
	String auth2=null;
	 boolean state=false;
	/*List<UserAuthentication> auth=authRepo.findByAuthCode(authcode);
	Iterator<UserAuthentication> itr=auth.iterator();
	while(itr.hasNext())
	{
		UserAuthentication auth1=itr.next();
		if(auth1.getAuthCode().equals(authcode))
		{
			auth2=auth1.getAuthCode();
		}
	}*/
	 if(authcode!=null)
	 {
	 UserAuthentication user=authRepo.findByAuthCode(authcode);
	 if(user!=null)
	 {
	 if(user.getAuthCode().equals(authcode))
		{
			auth2=user.getAuthCode();
		}
	 }
	 }
	 
	 if(auth2!=null)
	 {
		 state=true;
	 }
		return state;
	}
public boolean updatePassword(String passToUpdate,String varCode)
{
	Query updateQuery=new Query(Criteria.where("varificationCode").is(varCode));
	ForgotPassword forgot=operations.findOne(updateQuery,ForgotPassword.class);
	if(forgot!=null && forgot.getVarificationCode().equals(varCode))
	{
		Query updatePass=new Query(Criteria.where("emailid").is(forgot.getEmailid()));
		Update update = new Update();
		update.set("password", passToUpdate);
    	operations.updateFirst(updatePass, update, UserRegistration.class);
		

		forgotService.deleteVarificationCode(forgot.getEmailid());
		return true;	
	}
	return false;
	
}

public boolean updateProfile(UserUpdate userUpdate)
{
	boolean existMobile=false;
	Query userExist=new Query(Criteria.where("mobileNumber").is(userUpdate.getMobile()));
	if(operations.exists(userExist, UserRegistration.class))
	{
		return existMobile=true;
	}else
	{
		
	
	Query updateUser=new Query(Criteria.where("emailid").is(userUpdate.getEmail()));
	Update update = new Update();
	update.set("fullName",userUpdate.getName());
	update.set("mobileNumber",userUpdate.getMobile() );
	operations.updateFirst(updateUser, update, UserRegistration.class);
	return existMobile;
	}
	
}
	
public UserRegistration getProfile(String userEmail)
{
	Query findUser=new Query(Criteria.where("emailid").is(userEmail));
	return operations.findOne(findUser, UserRegistration.class);
	
}
 public String saveUserAddress(UserAddressModel userAddress)
 {
	 Query searchUserQuery = new Query();
	 LastAddressId init = operations.findOne(searchUserQuery, LastAddressId.class);
		if (init == null) {
			init = new LastAddressId();
			init.setLastid(1l);
			operations.insert(init);
		} else {
			init.setLastid(init.getLastid() + 1l);
			operations.updateFirst(searchUserQuery, Update.update("lastid", init.getLastid()), LastAddressId.class);
		}
		UserAddressWithAddressIdModel addressId=new UserAddressWithAddressIdModel();
		addressId.setId(String.valueOf(init.getLastid()));
		addressId.setAddress(userAddress.getAddress());
		addressId.setCity(userAddress.getCity());
		addressId.setPincode(userAddress.getPincode());
		addressId.setName(userAddress.getName());
		addressId.setPhoneNumber(userAddress.getPhoneNumber());
		addressId.setState(userAddress.getState());
		addressId.setLandMark(userAddress.getLandMark());
		addressId.setEmailid(userAddress.getEmailid());
		
		operations.save(addressId);
		
	 return init.toString();
 }

public List<UserAddressWithAddressIdModel> getAllAddress(String email) {
	// TODO Auto-generated method stub
	
	Query query=new Query(Criteria.where("emailid").is(email));
	List<UserAddressWithAddressIdModel> listAddresses=operations.findAll(UserAddressWithAddressIdModel.class);
	return listAddresses;
}
	

}
