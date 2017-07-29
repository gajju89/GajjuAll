package com.zaso.agent.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.zaso.agent.model.LastMongoId;
import com.zaso.agent.model.LastOrderId;
import com.zaso.agent.model.OrderSpecification;
import com.zaso.agent.model.UserOrder;

import com.zaso.agent.model.UserRegistration;
import com.zaso.agent.utils.GeneralUtil;

@Repository
public class OrderService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	MongoOperations operations ;
	@Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
		operations = (MongoOperations) mongoTemplate;
		
	}
	
	public void saveOrder(String userEmail,String agentEmail)
	{
		UserOrder userOrder=new UserOrder();
		operations.save(userOrder);
		
	}
	
	public List<UserOrder> getAllOrders(){
		
		return operations.findAll(UserOrder.class);
		
	}
	
public boolean	verifyNow(String userEmail)
	{
	boolean state=false;
	Query query=new Query(Criteria.where("emailid").is(userEmail));
	
	UserRegistration registerUser=operations.findOne(query,UserRegistration.class);
	if(registerUser.isMobileNumberVarified())
	{
		state=true;
		return state;
	}else{
	
	return state;
	}
		
	}


public String createOrder(OrderSpecification orderNew) {
	// TODO Auto-generated method stub
	Query searchUserQuery = new Query();
	LastOrderId init = operations.findOne(searchUserQuery, LastOrderId.class);
	if (init == null) {
		init = new LastOrderId();
		init.setLastid(1l);
		operations.insert(init);
	} else {
		init.setLastid(init.getLastid() + 1l);
		operations.updateFirst(searchUserQuery, Update.update("lastid", init.getLastid()), LastOrderId.class);
	}
	String orderExternalId=GeneralUtil.generateOrderId();
	String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	UserOrder newUserOrder=new UserOrder();
	newUserOrder.setAddressId(orderNew.getAddressId());
	newUserOrder.setAgentAsign(orderNew.getAgentAsign());
	newUserOrder.setCategory(orderNew.getCategory());
	newUserOrder.setDateOfOrder(date);
	newUserOrder.setLat(orderNew.getLat());
	newUserOrder.setLon(orderNew.getLon());
	newUserOrder.setOrderid(String.valueOf(init.getLastid()));
	newUserOrder.setOrderNumber(orderExternalId);
	newUserOrder.setOrderStatus("created");
	newUserOrder.setSubCategory(orderNew.getSubCategory());
	newUserOrder.setUserName(orderNew.getUserName());
	newUserOrder.setUserEmail(orderNew.getUserEmail());
	newUserOrder.setUserNumber(orderNew.getUserMobileNumber());

	operations.save(newUserOrder);
	return orderExternalId;
}

}
