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
import com.zaso.agent.model.Category;
import com.zaso.agent.service.CategoryService;
import com.zaso.agent.service.CategoryServiceImpl;

@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	CategoryServiceImpl cate;
	
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public @ResponseBody List<Category> getAll()
	{
		return cate.list();
	}
	@RequestMapping(value="/entry", method=RequestMethod.POST,headers="Accept=application/json")
	public @ResponseBody void saveOrUpdate(@RequestBody Category category)
	{
		 cate.saveOrUpdate(category);
	}
	
	@RequestMapping(value="/delete/{emailid}", method=RequestMethod.GET)
	public @ResponseBody void DeleteCategory(@PathVariable String emailid)
	{
		cate.DeleteCategory(emailid);
	}

}
