package com.zaso.agent.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zaso.agent.model.AgentPic;
import com.zaso.agent.service.AgentPicServiceImpl;

@Controller
@RequestMapping("agentpic")
public class AgentPicController {
	@Autowired
	AgentPicServiceImpl ageimpl;
	
	 @RequestMapping(value="/singleupload/{emailid}/{filetype}", method=RequestMethod.POST,headers="Accept=multipart/form-data")
	    public @ResponseBody void singleSave(@PathVariable("emailid") String emailid,@RequestParam("file") MultipartFile file,@PathVariable("filetype") String filetype)throws Exception{
		 ageimpl.saveOrUpdate(emailid,file,filetype);
		

}
	 /*@RequestMapping(value="/save", method=RequestMethod.POST )
	 public @ResponseBody void save(@RequestParam("agentid")String agentid)
	 {
		 
	 }*/
	 @RequestMapping(value="/multiupload/{emailid}/{filetype}", method=RequestMethod.POST,headers="Accept=multipart/form-data" )
	 public @ResponseBody void multiSave(@PathVariable("emailid") String emailid,@RequestParam("multiPartFiles") MultipartFile[] multiPartFiles,String filetype)throws Exception
	  {  
		 //File[] file=null;
		 for(int i=0;i<multiPartFiles.length;i++)
		 {
			// file[i]=ageimpl.singleSave(multiPartFiles[i]);
			// String url=ageimpl.uploadToAws(file[i]);
			 ageimpl.saveOrUpdate(emailid,multiPartFiles[i],filetype);
		 }
	 }
	 
	 @RequestMapping(value="/upload", method=RequestMethod.POST,headers="Accept=multipart/form-data")
	 public @ResponseBody void saveFile(@RequestParam("emailId")String emailId,@RequestParam("photoFile") MultipartFile photoFile)
	 {
		 String email=emailId;
		 MultipartFile file=photoFile;
	 }
}