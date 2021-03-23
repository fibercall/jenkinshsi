package com.trottling.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trottling.Service.ProvisioningBusinessServiceImpl;

@RestController
@PropertySource("classpath:config.properties")
public class SchedulerController {

	@Autowired
	ProvisioningBusinessServiceImpl provisioningBusinessServiceImpl;
	
	@Scheduled(cron = "${provJob}") // Trigger run on every 1 Minute.
	//@GetMapping("/postProvisioningForTrottling")
	public String postProvisioningForTrottling() {
	 
	  try {
		  
		  provisioningBusinessServiceImpl.ProvisioningPreocessAndStatusUpdate();
	     
	  } catch (Exception e) {
	      e.printStackTrace();
	  }
	  return "success";
	}
	
	
	@Scheduled(cron = "${provJob}") // Trigger run on every 1 Minute.
	//	@GetMapping("/postProvisioningForTrottlingReverse")
		public String postProvisioningForTrottlingReverse() {
		 
		  try {
			  
			  provisioningBusinessServiceImpl.ProvisioningPreocessAndStatusUpdateReverse();
		     
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
		  return "success";
		}

}
