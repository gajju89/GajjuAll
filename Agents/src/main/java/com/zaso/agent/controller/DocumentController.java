package com.zaso.agent.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zaso.agent.model.DocumentAvailable;
import com.zaso.agent.model.TaskRelatedDocument;
import com.zaso.agent.service.DocumentAvailableServiceImpl;
import com.zaso.agent.service.DocumentServiceImpl;
import com.zaso.agent.service.TaskRelatedDocumentServiceImpl;

@Controller
@RequestMapping(value="/Document")
public class DocumentController {
	
	@Autowired
	DocumentServiceImpl servImpl;
	
	@Autowired
	TaskRelatedDocumentServiceImpl taskImpl;
	
	@Autowired
	DocumentAvailableServiceImpl docAvService;
	
	 @RequestMapping(value="/docStorage/{userid}", method=RequestMethod.POST,headers="Accept=multipart/form-data")
	    public @ResponseBody void singleSave(@RequestParam("file") MultipartFile[] file,@PathVariable("userid")String userid)throws Exception{
		 servImpl.saveDocument(file,userid);
	 }
		 
		 
		 @RequestMapping(value="/task/doc", method=RequestMethod.POST,headers="Accept=application/json")
		 public @ResponseBody void saveTaskDocument(@RequestBody TaskRelatedDocument task)
		 {
			 taskImpl.saveTaskRelatedDocument(task);
		 }
		 
		 @RequestMapping(value="/doc/available", method=RequestMethod.POST,headers="Accept=application/json")
		public @ResponseBody void saveAvailableDocument(@RequestBody DocumentAvailable docAvailable)
		{
		
			 docAvService.saveDocumnet(docAvailable);
		}
		 
		 @RequestMapping(value="/doc/getAvailable", method=RequestMethod.GET)
		 public @ResponseBody List<DocumentAvailable>  getDocument()
		 {
			 return docAvService.findDocument();
		 }
		 
		 @RequestMapping(value="/doc/getAvailable/SingleDoc/{docNumber}", method=RequestMethod.GET)
		 public @ResponseBody List<DocumentAvailable>  getDocument(@PathVariable("docNumber")String docNumber)
		 {
			 return docAvService.findDocument(docNumber);
		 }
		 
		 @RequestMapping(value="/task/getAll", method=RequestMethod.GET)
		 public @ResponseBody List<TaskRelatedDocument> getAllTask()
		 {
			 return taskImpl.getAllTask();
		 }
		 
		 @RequestMapping(value="/task/relatedDoc/{task}", method=RequestMethod.GET)
		 public @ResponseBody HashMap<String, List<TaskRelatedDocument>> getTaskDocument(@PathVariable("task")String task)
		 {
			return taskImpl.getTaskDocument(task);
		 }

		 @RequestMapping(value="/get/awsbucket/url", method=RequestMethod.GET)
		 public @ResponseBody JSONObject getObjectFromBucket()
		 {
			 JSONObject link=new JSONObject();
			 link.put("url",servImpl.getObjectFromBucket());
			return link; 
		 }
}


