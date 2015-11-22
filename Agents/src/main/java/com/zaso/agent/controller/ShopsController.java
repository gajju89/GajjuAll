package com.zaso.agent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zaso.agent.model.Agents;
import com.zaso.agent.model.Shops;
import com.zaso.agent.service.ShopsServiceImpl;

@Controller
@RequestMapping(value="/shop")
public class ShopsController {
	@Autowired
	ShopsServiceImpl shop;
	
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public @ResponseBody List<Shops> getAll()
	{
		return shop.list();
	}
	@RequestMapping(value="/entry", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveOrUpdate(@RequestBody Shops shop1)
	{
		 shop.saveOrUpdate(shop1);
	}
	
	@RequestMapping(value="/delete/{emailid}", method=RequestMethod.GET)
	public @ResponseBody void DeleteShop(@PathVariable String emailid)
	{
		shop.DeleteShop(emailid);
	}
}
