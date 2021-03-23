package com.trottling.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trottling.Service.ProvisioningBusinessServiceImpl;
import com.trottling.Service.TrottlingService;
import com.trottling.Utils.SMSCallApi;
import com.trottling.dto.AaaPocDTO;
import com.trottling.dto.HsiUsage;

@RestController
public class TrottlingController {

	@Autowired
	TrottlingService trottlingService;
	
	@Autowired
	ProvisioningBusinessServiceImpl provisioningBusinessServiceImpl;
	
	
	
	@GetMapping("/TrottlingMonthly")
	public List<AaaPocDTO> TrottlingMonthlyApi() {
		
		List<AaaPocDTO> listOfProvisioningRequests = new ArrayList<AaaPocDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String YearAndMonth = sdf.format(new Date());
		
	//	String YearAndMonth = "2018-10";
		
		/* String YearAndMonth = year + "-" + month ; */
		//String response = "" ;
		//SMSCallApi smsCallApi = new SMSCallApi();
		
		try {
			
			listOfProvisioningRequests = provisioningBusinessServiceImpl.processPOCUpdationRequests(YearAndMonth);
			
			try {
			if(listOfProvisioningRequests.size() > 0 && listOfProvisioningRequests!=null) {
				
					String returnValue =  provisioningBusinessServiceImpl.fillPOCUpdateTables(listOfProvisioningRequests,"A"); 
				//  response = smsCallApi.sendSMS(aaaPocDTO.getMobileNumber(), "Your PhoneUsageLimit Crossed");
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listOfProvisioningRequests;
	}
	
	
	@GetMapping("/ReverseTrottlingMonthly")
	public List<AaaPocDTO> ReverseTrottlingMonthlyApi() {
		
		List<AaaPocDTO> listOfProvisioningRequests = new ArrayList<AaaPocDTO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String YearAndMonth = sdf.format(new Date());
		
	//	String YearAndMonth = "2018-11";
		try {
			
			listOfProvisioningRequests = provisioningBusinessServiceImpl.processReversePOCUpdationRequests(YearAndMonth);

			try {
				
			if(listOfProvisioningRequests.size() > 0 && listOfProvisioningRequests!=null) {
				
					String returnValue =  provisioningBusinessServiceImpl.fillPOCUpdateTablesReverse(listOfProvisioningRequests,"A"); 
			}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return listOfProvisioningRequests;
	}
	
	
	
}
