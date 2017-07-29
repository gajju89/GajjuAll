package com.zaso.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.model.OrderSpecification;
import com.zaso.agent.model.UserOrder;
import com.zaso.agent.service.OrderService;

@Controller
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	OrderService order;
	
	@RequestMapping(value="/saveOrder/{useremail}/{agentemail}/",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveOrder(@PathVariable("useremail")String userEmail,@PathVariable("agentemail")String agentEmail)
	{
		order.saveOrder(userEmail,agentEmail);
	}
	
	@RequestMapping(value="/getall/orders",method=RequestMethod.GET)
	public @ResponseBody List<UserOrder> getAllOrders()
	{
		return order.getAllOrders();
	}
	
	@RequestMapping(value="/verify/{useremail}/",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody boolean verifyNumber(@PathVariable("useremail")String userEmail)
	{
		return order.verifyNow(userEmail);
	}
	
	@RequestMapping(value="/saveorder/new",method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody String saveOrder(@RequestBody OrderSpecification orderNew )
	{
		return order.createOrder(orderNew);
	}

}
