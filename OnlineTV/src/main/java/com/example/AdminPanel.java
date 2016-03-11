package com.example;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.google.gwt.dom.builder.shared.ButtonBuilder;
import com.google.gwt.resources.css.ast.CssProperty.StringValue;
import com.google.gwt.user.client.ui.Widget;
import com.onlinetv.model.FreeVideosModel;
import com.onlinetv.model.LastMongoId;

import com.onlinetv.services.AdminPanelService;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.Validator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.xuggle.xuggler.IContainer;

@SpringUI(path="")
@SuppressWarnings("serial")
@Theme("valo")
public class AdminPanel extends UI implements Button.ClickListener{
	
	
	public AdminPanelService service = new AdminPanelService();
	
	
	Calendar calendar;
	public AdminPanelService getService() {
		return service;
	}

	public void setService(AdminPanelService service) {
		this.service = service;
	}

	DateField date;
	TextField title;
	 CheckBox checkbox;
	VerticalLayout layout;
	VerticalLayout tab1;
	VerticalLayout tab2;
	private Button submit;
	
	static String url;
	private String uploadedFileName;
	
	private IContainer container = IContainer.make();
	 private MyReceiver receiver = new MyReceiver();
	 private Label status = new Label("Please select a file to upload");
	 private Upload upload = new Upload(null, receiver);
	 private ProgressIndicator pi = new ProgressIndicator();
	 private HorizontalLayout progressLayout = new HorizontalLayout();
	  

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
		 layout=new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		TabSheet tab=new TabSheet();
		tab.setSizeFull();
		
//		tab.setHeight("400px");
//	    tab.setWidth("300px");
		layout.addComponent(tab);
		
		
		 tab1 = new VerticalLayout();
		tab1.setCaption("Sheduled");
		
		tab1.setMargin(true);
		tab1.setSpacing(true);
		
		tab.addTab(tab1);
		
		
		
		tab2 = new VerticalLayout();
		tab2.setCaption("Upload");
		tab2.setSizeFull();
		tab2.setSpacing(true);
		tab2.setMargin(true);
		//tab2.addComponent(new Label("hello get your your shedule here"));
		tab.addTab(tab2);
	
		
		  setContent(layout);
		  
		  uploadLayout();
		  submitButtonListner();
		   sheduledLayout();
		   //deleteSheduledVideo();
		  
	}
	
	@SuppressWarnings("deprecation")
	public void uploadLayout()
	{
		 
		 title=new TextField("Title");
		title.setRequired(true);
		title.addValidator(new StringLengthValidator("please give a title",1,200,true));
		
		 upload.setImmediate(true);
	        upload.setButtonCaption("Select file");
	        
	        progressLayout.setSpacing(true);
	        progressLayout.setVisible(false);
	        progressLayout.addComponent(pi);
	        progressLayout.setComponentAlignment(pi, Alignment.MIDDLE_LEFT);
	        
//	        Date date1= new Date();
	        
	        checkbox = new CheckBox("paid");
			checkbox.setValue(true);
	         
	        
	        
	         date=new DateField();
	         date.setResolution(Resolution.SECOND);
	         
	        
	               
	          submit=new Button("submit");
	        
	        
	        

	     // Set the date and time to present
	        
	        /*HorizontalLayout hori=new HorizontalLayout();
	        hori.setSpacing(true)*/;
	        VerticalLayout uploadFile=new VerticalLayout();
	        uploadFile.addComponent(status);
	        uploadFile.addComponent(progressLayout);
	        uploadFile.addComponent(upload);
	        /*hori.addComponent(uploadFile);
	        hori.addComponent(checkbox);
	        */
	        
	        
	        tab2.addComponent(title);
			/*tab2.addComponent(status);
	        tab2.addComponent(progressLayout);
	        tab2.addComponent(upload);*/
	        tab2.addComponent(uploadFile);
	        tab2.addComponent(date);
	        tab2.addComponent(checkbox);
	        tab2.addComponent(submit);
	        
	        final Button cancelProcessing = new Button("Cancel");
	        cancelProcessing.addListener(new Button.ClickListener() {
	            public void buttonClick(ClickEvent event) {
	                upload.interruptUpload();
	            }
	        });
	        cancelProcessing.setStyleName("small");
	        progressLayout.addComponent(cancelProcessing);

	        /**
	         * =========== Add needed listener for the upload component: start,
	         * progress, finish, success, fail ===========
	         */

	        upload.addListener(new Upload.StartedListener() {
	            public void uploadStarted(StartedEvent event) {
	                // This method gets called immediatedly after upload is started
	                upload.setVisible(false);
	                progressLayout.setVisible(true);
	                pi.setValue(0f);
	                pi.setPollingInterval(500);
	                status.setValue("Uploading file \"" + event.getFilename()
	                        + "\"");
	                
	            }
	        });
	        upload.addSucceededListener(receiver);
	        upload.addProgressListener(new Upload.ProgressListener() {
	            public void updateProgress(long readBytes, long contentLength) {
	                // This method gets called several times during the update
	                pi.setValue(new Float(readBytes / (float) contentLength));
	            }

	        });
	       


	        upload.addFailedListener(new Upload.FailedListener() {
	            public void uploadFailed(FailedEvent event) {
	                // This method gets called when the upload failed
	                status.setValue("Uploading interrupted");
	            }
	        });
	        
	        upload.addFinishedListener(new Upload.FinishedListener() {
	            public void uploadFinished(FinishedEvent event) {
	                // This method gets called always when the upload finished,
	                // either succeeding or failing
	                progressLayout.setVisible(false);
	                upload.setVisible(true);
	                upload.setCaption("upload finished !,Select another file");
	            }
	        });

	    

	       
		
		
	}
	@SuppressWarnings("unchecked")
	public void sheduledLayout()
	{
		VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        tab1.addComponent(layout);
        Table table1 = new Table("Sheduled Videos List");
//        IndexedContainer container1 = new IndexedContainer();
        Table container1 = new Table();
        container1.addContainerProperty("seq.no", String.class, "");
        container1.addContainerProperty("date", String.class, "");
        container1.addContainerProperty("videoTitle", String.class, "");
        container1.addContainerProperty("startTime", String.class, "");
        container1.addContainerProperty("endTime", String.class, "");
        container1.addContainerProperty("duration", String.class, "");
        container1.addContainerProperty("paid", Boolean.class, "");
      container1.addContainerProperty("delete",Button.class,"");
        
        
		List<FreeVideosModel> listFree=service.getAllFreeVideos();
		int count=1;
		for(FreeVideosModel model:listFree)
		{
			
			HorizontalLayout insideLayout=new HorizontalLayout();
			//Table table=new Table();
			final Button button=new Button();
			button.setCaption("delete");
			button.setData(model.getVideoUrl());
			button.addClickListener(this);
			
			
//			model.setDelete(button);
			Item item = container1.addItem(new Object[]{model,button});
			//container1.addItem(button);
			item.getItemProperty("seq.no").setValue(String.valueOf(count));
            item.getItemProperty("date").setValue(model.getDate());
            item.getItemProperty("videoTitle").setValue(model.getVideoTitle());
            item.getItemProperty("startTime").setValue(model.getStartTime());
            item.getItemProperty("endTime").setValue(model.getEndTime());
            item.getItemProperty("duration").setValue(model.getDuration());
            item.getItemProperty("paid").setValue(model.isPaid());
            item.getItemProperty("delete").setValue(button);
           //item.getItemProperty("delete").setValue(model.getDelete());
			
			//button.setData(model.getId().toString());
           /* table.setContainerDataSource(container1);
            insideLayout.addComponent(table);
            insideLayout.addComponent(button);
            layout.addComponent(insideLayout);*/
            
            count++;
		}
	
		//table1.setContainerDataSource(container1);
		layout.addComponent(container1);
		
		
		
		
		
		
	}
	
	public  class MyReceiver implements Receiver,SucceededListener {

        private String fileName;
        
        private String mtype;
       

        public File file;

        public OutputStream receiveUpload(String filename,
                                          String mimeType) {
            // Create upload stream
            FileOutputStream fos = null; // Stream to write to
            try {
            	File tmp =null;
            	if (!( tmp =new File("/tmp")).exists())
            	{
            		tmp.mkdir();
					
            	}
            	if (!(tmp =new File("/tmp/upload")).exists())
				{
					tmp.mkdir();
				}
                // Open the file for writing.
                file = new File("/tmp/upload/" + filename);

               fileName=file.getAbsolutePath();
                fos = new FileOutputStream(file);
            } catch (final java.io.FileNotFoundException e) {
                new Notification("Could not open file<br/>",
                                 e.getMessage(),
                                 Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());
                return null;
            } 
            return fos; // Return the output stream to write to
        }

        public String getFileName() {
            return uploadedFileName;
        }

        public String getMimeType() {
            return mtype;
        }

       

		@Override
		public void uploadSucceeded(SucceededEvent event) {
			// TODO Auto-generated method stub
			 if(uploadedFileName!=null)
			 {
				 File delete=new File(uploadedFileName);
				 delete.delete();
			 }
			 uploadedFileName = fileName;
			
			 
		}
		

    }
	

	@Override
	public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String data=(String)event.getButton().getData();
				//String data=button.getData().toString();
				service.deleteSheduled(data);
				
				
			}
		
	
	
	public void submitButtonListner()
	{
		submit.setImmediate(true);
		submit.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				int day = 0;
				int minute=0;
				int hour=0;
				int month=0;
				int year=0;
				long duration=0;
				int seconds=0;
				Date date1 = null;
				LastMongoId id=service.getLast();
				
				List<FreeVideosModel> list=service.getAllFreeVideos();
				
				
				 try {
					 
					   File uploadFile = new File(uploadedFileName);
						url=service.uploadToAws(uploadFile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage().toString());
					}
					
				
			    
				if(date!=null)
				{
					/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
					sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
					sdf.format(date);
					Locale locale[]=sdf.getAvailableLocales();*/
					
					calendar=Calendar.getInstance();
					calendar.setTime(date.getValue());
					
			
					 hour=calendar.get(Calendar.HOUR_OF_DAY);
					 minute=calendar.get(Calendar.MINUTE);
					 day=calendar.get(Calendar.DAY_OF_MONTH);
					 month=calendar.get(Calendar.MONTH)+1;
					 year=calendar.get(Calendar.YEAR);
					 seconds=calendar.get(Calendar.SECOND);
					 
				} 
					 
					
					 
					 
					 
					 
				
				
				int result = container.open(uploadedFileName, IContainer.Type.READ, null);
				if(result<0)
				{
					throw new RuntimeException("Failed to open media file");

				}else
				{
				duration = container.getDuration();
				
				}
				long durationInMili=duration/1000;
				Date newDate=date.getValue();
				long dateInMili=newDate.getTime();
				long endTimeInMili=dateInMili+durationInMili;
				
				String startDate=null,startTime=null,endTime=null,durationTime=null;
				SimpleDateFormat sdfStartDate=new SimpleDateFormat("dd-MM-yyyy");
				sdfStartDate.setTimeZone(TimeZone.getTimeZone("GMT"));
				SimpleDateFormat sdfStartTime=new SimpleDateFormat("hh:mm:ss");
				sdfStartTime.setTimeZone(TimeZone.getTimeZone("GMT"));
				SimpleDateFormat sdfduration=new SimpleDateFormat("hh:mm:ss");
				sdfduration.setTimeZone(TimeZone.getTimeZone("GMT"));
				//sdfduration.setTimeZone(TimeZone.);
				 startDate=sdfStartDate.format(newDate);
				 startTime=sdfStartTime.format(newDate);
				 endTime=sdfStartTime.format(new Date(endTimeInMili));
				 durationTime=sdfduration.format(new Date(durationInMili));
				 
				 
				 
				 
				 for(int i=0;i<list.size();i++)
					{
					 try {
							
						SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
						Date validateStartTime;
						
							validateStartTime = sdf.parse(list.get(i).getStartTime());
					
						Date validateEndTime=sdf.parse(list.get(i).getEndTime());
						long validateStartTimeInMili=validateStartTime.getTime();
						long validateEndTimeInMili=validateEndTime.getTime();
						
						if(dateInMili<=validateEndTimeInMili && dateInMili >=validateStartTimeInMili)
						{
							new Notification("This is a warning",
				                    "<br/>This is the <i>last</i> warning",
				                    Notification.Type.WARNING_MESSAGE, true)
				                    .show(Page.getCurrent());
						}
					 }catch(Exception e){
						 e.printStackTrace();
					 }
						
						
					}
				 
				 
				
				
				if(checkbox.getValue()==false)
				{
				FreeVideosModel model=new FreeVideosModel();
				model.setId(String.valueOf(day)+","+id.getLastid());
				
				/*if((hour<10||minute<10||seconds<10))
				{
				if(hour<10)
				{
					model.setStartTime("0"+String.valueOf(hour)+":"+String.valueOf(minute)+":"+seconds);
				}
				if(minute<10)
				{
					model.setStartTime(String.valueOf(hour)+":0"+String.valueOf(minute)+":"+seconds);	
				}
				if(seconds<10)
				{
					model.setStartTime(String.valueOf(hour)+":"+String.valueOf(minute)+":0"+seconds);
				}
				if(hour<10 && minute<10)
				{
					model.setStartTime("0"+String.valueOf(hour)+":0"+String.valueOf(minute)+":"+seconds);
				}
				if(minute<10 && seconds<10)
				{
					model.setStartTime(String.valueOf(hour)+":0"+String.valueOf(minute)+":0"+seconds);
				}
				if(hour<10 && seconds<10)
				{
					model.setStartTime("0"+String.valueOf(hour)+":"+String.valueOf(minute)+":0"+seconds);
				}
				if(hour<10 && minute<10 && seconds<10)
				{
					model.setStartTime("0"+String.valueOf(hour)+":0"+String.valueOf(minute)+":0"+seconds);
				}
				
				}
			else
			{
				model.setStartTime(String.valueOf(hour)+":"+String.valueOf(minute)+":"+seconds);
			}*/
				
//				model.setStartTime(String.valueOf(hour)+":"+String.valueOf(minute)+":"+seconds);
				model.setStartTime(startTime);
				model.setVideoTitle(title.getValue());
				
				/*int endTimeValue=(int) (((duration/1000)/1000)/60);
				int videohour = endTimeValue/60;
				int videomin = endTimeValue%60;
				int videoSecond=(int)(((duration/1000)/1000)%60);
				
				int videoEndTimeMinute=minute+videomin;
				int videoEndTimeHour=hour+videohour;
				int videoEndTimeSeconds=videoSecond+seconds;*/
				
				
				
				/*if(videoEndTimeMinute>=60 || videoEndTimeHour>=24 || videoEndTimeSeconds>=60)
				{
					
					if(videoEndTimeMinute>=60)
					{
					videoEndTimeMinute=videoEndTimeMinute%60;
					videoEndTimeHour=videoEndTimeHour+1;
					}
					if(videoEndTimeHour>=24)
					{
						videoEndTimeHour=videoEndTimeHour/24;
					}
					if(videoEndTimeSeconds>=60)
					{
						videoEndTimeSeconds=videoEndTimeSeconds%60;
						videoEndTimeMinute=videoEndTimeMinute+1;
					}
				}
				if((videoEndTimeHour<10||videoEndTimeMinute<10||videoEndTimeSeconds<10))
						{
					if(videoEndTimeHour<10)
					{
						model.setEndTime(String.valueOf("0"+videoEndTimeHour)+":"+String.valueOf(videoEndTimeMinute)+":"+String.valueOf(videoEndTimeSeconds));
					}
					if(videoEndTimeMinute<10)
					{
						model.setEndTime(String.valueOf(videoEndTimeHour)+":0"+String.valueOf(videoEndTimeMinute)+":"+String.valueOf(videoEndTimeSeconds));	
					}
					if(videoEndTimeSeconds<10)
					{
						model.setEndTime(String.valueOf(videoEndTimeHour)+":"+String.valueOf(videoEndTimeMinute)+":0"+String.valueOf(videoEndTimeSeconds));
					}
					if(videoEndTimeHour<10 && videoEndTimeMinute<10)
					{
						model.setEndTime(String.valueOf("0"+videoEndTimeHour)+":0"+String.valueOf(videoEndTimeMinute)+":"+String.valueOf(videoEndTimeSeconds));
					}
					if(videoEndTimeMinute<10 && videoEndTimeSeconds<10)
					{
						model.setEndTime(String.valueOf(""+videoEndTimeHour)+":0"+String.valueOf(videoEndTimeMinute)+":0"+String.valueOf(videoEndTimeSeconds));
					}
					if(videoEndTimeHour<10 && videoEndTimeSeconds<10)
					{
						model.setEndTime(String.valueOf("0"+videoEndTimeHour)+":"+String.valueOf(videoEndTimeMinute)+":0"+String.valueOf(videoEndTimeSeconds));
					}
					if(videoEndTimeHour<10 && videoEndTimeMinute<10 && videoEndTimeSeconds<10)
					{
						model.setEndTime(String.valueOf("0"+videoEndTimeHour)+":0"+String.valueOf(videoEndTimeMinute)+":0"+String.valueOf(videoEndTimeSeconds));
					}
					
					}
				else
				{
				model.setEndTime(String.valueOf(videoEndTimeHour)+":"+String.valueOf(videoEndTimeMinute)+":"+String.valueOf(videoEndTimeSeconds));
				}*/
				
//				model.setEndTime(String.valueOf(videoEndTimeHour)+":"+String.valueOf(videoEndTimeMinute)+":"+String.valueOf(videoEndTimeSeconds));
				model.setEndTime(endTime);
			/*if((videohour<10||videomin<10||videoSecond<10))
			{
			if(videohour<10)
			{
				model.setDuration("0"+String.valueOf(videohour)+":"+String.valueOf(videomin)+":"+String.valueOf(videoSecond));
			}
			if(videomin<10)
			{
				model.setDuration(String.valueOf(videohour)+":0"+String.valueOf(videomin)+":"+String.valueOf(videoSecond));	
			}
			if(videoSecond<10)
			{
				model.setDuration(String.valueOf(videohour)+":"+String.valueOf(videomin)+":0"+String.valueOf(videoSecond));
			}
			if(videohour<10 && videomin<10)
			{
				model.setDuration("0"+String.valueOf(videohour)+":0"+String.valueOf(videomin)+":"+String.valueOf(videoSecond));
			}
			if(videomin<10 && videoSecond<10)
			{
				model.setDuration(String.valueOf(videohour)+":0"+String.valueOf(videomin)+":0"+String.valueOf(videoSecond));
			}
			if(videohour<10 && videoSecond<10)
			{
				model.setDuration("0"+String.valueOf(videohour)+":"+String.valueOf(videomin)+":0"+String.valueOf(videoSecond));
			}
			if(videohour<10 && videomin<10 && videoSecond<10)
			{
				model.setDuration("0"+String.valueOf(videohour)+":0"+String.valueOf(videomin)+":0"+String.valueOf(videoSecond));
			}
			
			}
		else
		{
			model.setDuration(String.valueOf(videohour)+":"+String.valueOf(videomin)+":"+String.valueOf(videoSecond));
		}*/
//				model.setDuration(String.valueOf(videohour)+":"+String.valueOf(videomin)+":"+String.valueOf(videoSecond));
				model.setDuration(durationTime);
				model.setVideoUrl(url);
				model.setPaid(false);
				//model.setDate(day+"-"+month+"-"+year);
				model.setDate(startDate);
				service.saveVideo(model);
				}else
				{
					FreeVideosModel model=new FreeVideosModel();
					model.setId(String.valueOf(day)+","+id.getLastid());
					model.setVideoTitle(title.getValue());
					model.setDuration(new SimpleDateFormat("hh:mm:ss").format(new Date(duration/1000)));
					model.setVideoUrl(url);
					model.setPaid(true);
					service.saveVideo(model);
					
				}

				
				
			}
		});
	}
}
