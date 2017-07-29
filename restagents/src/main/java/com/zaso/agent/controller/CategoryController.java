package com.zaso.agent.controller;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.fasterxml.jackson.databind.JsonSerializer;
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
		/*try {
			ObjectMapper mapper=new ObjectMapper();
			String serialised=mapper.writeValueAsString(list);
			
			 Cipher c = Cipher.getInstance("AES");
			   byte[] key = "43381A8E10469C42EFBD6EA0DFD9CA71".getBytes("UTF-8");
			   MessageDigest sha = MessageDigest.getInstance("SHA-1");
			   key = sha.digest(key);
			   key = Arrays.copyOf(key, 16);
				SecretKeySpec k =
				  new SecretKeySpec(key, "AES");
				c.init(Cipher.ENCRYPT_MODE, k);
				 return c.doFinal(serialised.toString().getBytes("UTF-8"));
				//serialised.reset();
				
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
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
