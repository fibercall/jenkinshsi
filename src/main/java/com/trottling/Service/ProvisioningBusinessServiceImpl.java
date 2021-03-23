package com.trottling.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.trottling.Dao.CommonDao;
import com.trottling.Utils.COMSConstants;
import com.trottling.Utils.SMSCallApi;
import com.trottling.dto.AaaPocDTO;
import com.trottling.dto.HsiUsage;
import com.trottling.model.ProvJsons;
import com.trottling.model.ProvRequests;

@Service
@PropertySource("classpath:provisionservicesproperties.properties")
public class ProvisioningBusinessServiceImpl {


	@Autowired
	CommonDao commonDAO;

	 @Value("${aaa.server.ip.poc.COA}")
	 private String pocCOAAAAUrl;
	 
	 @Value("${aaa.server.ip.poc.Update}")
	 private String pocUpdateAAAUrl;
	 
	 @Value("${aaa.server.ip.poc.reverse.COA}")
	 private String pocCOAAAAUrlReverse;
	 
	 @Value("${aaa.server.ip.poc.reverse.Delete}")
	 private String pocCOAAAAUrlDelete;
	 
	 @Value("${aaa.server.ip.poc.reverse.Update}")
	 private String pocUpdateAAAUrlReverse;
	

 	@Autowired
	private com.trottling.Dao.provRequestsDAO provRequestsDAO;
	
	public static final String PROV_REQUEST_SEQUENCE = "PROVREQUESTID";

	/*
	 * //trottling urls private String pocCOAAAAUrl =
	 * "http://172.16.4.103:8080/radiusISU?code=Change-Filter-Request&Client=nwsubscode&cisco-avpair=subscriber:sa=newupdown";
	 * private String pocUpdateAAAUrl =
	 * "http://172.16.4.107/prov4serv/prov_if?oper=update&client=nwsubscode&avp=Filter-Id<>nonblock<>check&avp=accessId<>cpeMacAddress<>reply&avp=Profile<>pV4<>reply&avp=cisco-avpair<>subscriber:sa=newupdown<>reply&avp=cisco-avpair<>subscriber:accounting-list=default<>reply";
	 * 
	 * //reverse trottling urls private String pocCOAAAAUrlReverse =
	 * "http://172.16.4.103:8080/radiusISU?code=Change-Filter-Request&Client=nwsubscode&cisco-avpair=subscriber:sa=oldupdown";
	 * private String pocCOAAAAUrlDelete =
	 * "http://172.16.4.103:8080/radiusISU?code=Change-Filter-Request&Client=nwsubscode&cisco-avpair=subscriber:sd=newupdown";
	 * private String pocUpdateAAAUrlReverse =
	 * "http://172.16.4.107/prov4serv/prov_if?oper=update&client=nwsubscode&avp=Filter-Id<>nonblock<>check&avp=accessId<>cpeMacAddress<>reply&avp=Profile<>pV4<>reply&avp=cisco-avpair<>subscriber:sa=oldupdown<>reply&avp=cisco-avpair<>subscriber:accounting-list=default<>reply";
	 */
	
	public List<AaaPocDTO> processPOCUpdationRequests(String YearAndMonth) {

		List<AaaPocDTO> listOfProvisioningRequests = new ArrayList<>();
		
		String status ="";

		try {

			listOfProvisioningRequests = commonDAO.getPOCProvisioningRequests(YearAndMonth);

			// status =fillPOCUpdateTables(listOfProvisioningRequests, "A", hsiUsage);

		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;

		} 
		
		return listOfProvisioningRequests;

	}
	
	
	
	@Transactional

	public String fillPOCUpdateTables(List<AaaPocDTO> listOfProvisioningRequests, String type  ) {


	String status = "";
	String response = "" ;
	int cafUpdatedStatus = 0;
	
	

	try {
		int i = 1;
		TimeZone tz = TimeZone.getTimeZone("GMT");
		Calendar now = Calendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
	
	if(listOfProvisioningRequests.size()>0 && listOfProvisioningRequests!=null) {

	for (AaaPocDTO dto : listOfProvisioningRequests) {

		List<ProvJsons> provJsonList1 = new ArrayList<ProvJsons>();	
		List<ProvJsons> provJsonList2 = new ArrayList<ProvJsons>();	
//	long requestId = commonDAO.generateSequence("PROVREQUESTID");
		
		try {
			
			//String LagAndDate = "";
			String LagAndDate = sdf.format(new Date());
			
			
			System.out.println("seqnumber   "+i);
			
			
		
		if(LagAndDate!=null) {
		LagAndDate = dto.getClient_id() + LagAndDate ;
		if(LagAndDate!=null && !LagAndDate.equals("")) {
		LagAndDate = LagAndDate.replaceAll(":", "");
		if(LagAndDate!=null && !LagAndDate.equals("")) {
		LagAndDate = LagAndDate.replace("lag", "");
		if(LagAndDate!=null && !LagAndDate.equals("")) {
		LagAndDate = LagAndDate.trim();
	//	if(LagAndDate!=null && !LagAndDate.equals("")) {
		
	//	long requestId11 = Long.valueOf("11"+LagAndDate);
	//	long requestId12 = Long.valueOf("12"+LagAndDate);
		
		BigInteger requestId11 = new BigInteger("11"+LagAndDate);
		BigInteger requestId12 = new BigInteger("12"+LagAndDate);
		
		
		
	// Define Provisioning Request Table

		ProvRequests provRequest11 = createProvisionRequest(dto, requestId11, now, type);
		ProvRequests provRequest12 = createProvisionRequest(dto, requestId12, now, type);

		//String oldupAndDownProfileName = updateDTO.getOldupdown();
		//String newupAndDownProfileName = updateDTO.getNewupdown();
		
		String oldupAndDownProfileName = dto.getOldupdown();
		String newupAndDownProfileName = dto.getNewupdown();
		String clientName= dto.getClient_id();
		
		
		//String macAddress = commonDAO.getAcessId(Long.valueOf(dto.getProdcafno())).replaceAll(":", "").replaceAll("(.{4})(?!$)", "$1.");
		
		String macAddress = "";
		
		if(dto.getCpeMacAddress()!=null && !dto.getCpeMacAddress().equals("")) {
			macAddress = dto.getCpeMacAddress().replaceAll(":", "").replaceAll("(.{4})(?!$)", "$1.");
		}else {
			macAddress = "0000.0000.0000";
		}

	//COA Trottling
	String url = pocCOAAAAUrl.replace("nwsubscode", clientName).replace("newupdown",newupAndDownProfileName);
	ProvJsons aaaCOAProvReq = COAAAAServiceActivation(requestId11, now ,url,1);
	provJsonList1.add(aaaCOAProvReq);
	provRequest11.setProvJsons(provJsonList1);
	ProvRequests proReq1 = provRequestsDAO.saveOrUpdate(provRequest11);
	
	//Update Trottling
	String updateUrl = pocUpdateAAAUrl.replace("nwsubscode", clientName).replace("newupdown", newupAndDownProfileName).replace("cpeMacAddress",macAddress);
	ProvJsons aaaUpdationProvReq = UpdateAAAServiceActivation(requestId12, now ,updateUrl,2);
	provJsonList2.add(aaaUpdationProvReq);
	provRequest12.setProvJsons(provJsonList2);
	ProvRequests proReq2 = provRequestsDAO.saveOrUpdate(provRequest12);

	provRequest11 = null;
	provJsonList1 = null;
	provRequest12 = null;
	provJsonList2= null;
	
	
	 commonDAO.UpdateMonthlyUsage(1 ,BigInteger.valueOf(Long.valueOf(dto.getProdcafno())));
	

										/*
										 * if(proReq1.getRequestAction()!=0) { response =
										 * smsCallApi.sendSMS(dto.getMobileNumber(), "Your PhoneUsageLimit Crossed");
										 * 
										 * if(response!=null && !response.equals("")) { cafUpdatedStatus =
										 * commonDAO.UpdateMonthlyUsage(1
										 * ,BigInteger.valueOf(Long.valueOf(dto.getProdcafno()))); } }
										 */
	
	
		}}}}
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
	
		i++;
		
	}
	
	}

	status = "success";
	now = null;
	tz = null;
	} catch (Exception e) {

	status = "fail";
	throw e;
	}
	return status;

	}
	
	
	
	public ProvRequests createProvisionRequest(AaaPocDTO dto, BigInteger requestId, Calendar now, String type) {
		ProvRequests provRequest = new ProvRequests();
		try {
			// Define Provisioning Request Table
			provRequest.setRequestId(requestId);
			provRequest.setDpndrequestid(dto.getDependencyRequestId());
			provRequest.setCafNo(Long.parseLong(dto.getAcctcafno()));
			provRequest.setCustomerId(Long.parseLong(dto.getCustid()));
			provRequest.setProdCafNo(Long.parseLong(dto.getProdcafno()));
			provRequest.setStbCafNo(0);
			provRequest.setTenantCode("APSFL");
			provRequest.setProdCode(dto.getProdcode());
			provRequest.setServiceCodeAddl(dto.getSrvccodeaddl());
			provRequest.setServiceCodeProv(dto.getSrvccodeprov());
			if (type.equalsIgnoreCase("A"))
				provRequest.setRequestAction(COMSConstants.ACTIVATION);
			else if (type.equalsIgnoreCase("S"))
				provRequest.setRequestAction(COMSConstants.SUSPEND);
			else if (type.equalsIgnoreCase("R"))
				provRequest.setRequestAction(COMSConstants.RESUME);
			else if (type.equalsIgnoreCase("D"))
				provRequest.setRequestAction(COMSConstants.DEACTIVATE);
			provRequest.setStatus(COMSConstants.TO_BE_PROCESSED);
			provRequest.setCreatedOn(now.getTime());
			provRequest.setCreatedBy(COMSConstants.SYSTEM);
			provRequest.setModifiedOn(now.getTime());
			provRequest.setModifiedBy(COMSConstants.SYSTEM);
			provRequest.setNwSubsCode(dto.getNwSubscode());
		} catch (Exception ex) {
		}
		return provRequest;
	}
	
	public ProvJsons COAAAAServiceActivation(BigInteger requestId, Calendar now , String url ,int seqNum) {
	ProvJsons aaaCOAProvReq = new ProvJsons();
	try{
		aaaCOAProvReq.setRequestId(requestId);
		aaaCOAProvReq.setReq("NULL");
		aaaCOAProvReq.setServiceType(COMSConstants.AAA_SERVICE_CODE);
		aaaCOAProvReq.setCallType("GET");
		aaaCOAProvReq.setUrl(url);
		aaaCOAProvReq.setStatus(COMSConstants.TO_BE_PROCESSED);
		aaaCOAProvReq.setSeqNum(seqNum);
		aaaCOAProvReq.setCreatedDate(now.getTime());
	} catch (Exception ex) {
	}
	return aaaCOAProvReq;
 } 
	
	
	public ProvJsons UpdateAAAServiceActivation(BigInteger requestId, Calendar now , String updateUrl,int seqNum) {
		ProvJsons aaaUpdationProvReq = new ProvJsons();
		try{
			aaaUpdationProvReq.setRequestId(requestId);
			aaaUpdationProvReq.setReq("NULL");
			aaaUpdationProvReq.setServiceType(COMSConstants.AAA_SERVICE_CODE);
			aaaUpdationProvReq.setCallType("GET");
			aaaUpdationProvReq.setUrl(updateUrl);
			aaaUpdationProvReq.setStatus(COMSConstants.TO_BE_PROCESSED);
			aaaUpdationProvReq.setSeqNum(seqNum);
			aaaUpdationProvReq.setCreatedDate(now.getTime());
		} catch (Exception ex) {
		}
		return aaaUpdationProvReq;
	 } 
	

	public List<AaaPocDTO> processReversePOCUpdationRequests(String YearAndMonth) {

		List<AaaPocDTO> listOfProvisioningRequests = new ArrayList<>();
		
		String status ="";

		try {

			listOfProvisioningRequests = commonDAO.getPOCProvisioningRequestsForReverse(YearAndMonth);

		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;

		} 
		
		return listOfProvisioningRequests;

	}
	
	@Transactional

	public String fillPOCUpdateTablesReverse(List<AaaPocDTO> listOfProvisioningRequests, String type  ) {


	String status = "";
	String response = "" ;
	
	try {

	TimeZone tz = TimeZone.getTimeZone("GMT");
	Calendar now = Calendar.getInstance(tz);
	SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
	
	String LagAndDate = sdf.format(new Date());
	
	if(listOfProvisioningRequests.size()>0 && listOfProvisioningRequests!=null) {

	for (AaaPocDTO dto : listOfProvisioningRequests) {

		List<ProvJsons> provJsonList3 = new ArrayList<ProvJsons>();	
		List<ProvJsons> provJsonList4 = new ArrayList<ProvJsons>();	
		List<ProvJsons> provJsonList5 = new ArrayList<ProvJsons>();	
//	long requestId = commonDAO.generateSequence("PROVREQUESTID");
		
		try {
		
		if(LagAndDate!=null && !LagAndDate.equals("")) {
		LagAndDate = dto.getClient_id() + LagAndDate ;
		if(LagAndDate!=null && !LagAndDate.equals("")) {
		LagAndDate = LagAndDate.replaceAll(":", "");
		if(LagAndDate!=null && !LagAndDate.equals("")) {
		LagAndDate = LagAndDate.replace("lag", "");
		if(LagAndDate!=null && !LagAndDate.equals("")) {
		LagAndDate = LagAndDate.trim();
	//	if(LagAndDate!=null && !LagAndDate.equals("")) {
		
	//	long requestId13 = Long.valueOf("13"+LagAndDate);
	//	long requestId14 = Long.valueOf("14"+LagAndDate);
	//	long requestId15 = Long.valueOf("15"+LagAndDate);
		
		BigInteger requestId13 = new BigInteger("13"+LagAndDate);
		BigInteger requestId14 = new BigInteger("14"+LagAndDate);
		BigInteger requestId15 = new BigInteger("15"+LagAndDate);
		
	// Define Provisioning Request Table

		ProvRequests provRequest3 = createProvisionRequest(dto, requestId13, now, type);
		ProvRequests provRequest4 = createProvisionRequest(dto, requestId14, now, type);
		ProvRequests provRequest5 = createProvisionRequest(dto, requestId15, now, type);

		//String oldupAndDownProfileName = updateDTO.getOldupdown();
		//String newupAndDownProfileName = updateDTO.getNewupdown();
	
		String oldupAndDownProfileName = dto.getOldupdown();
		String newupAndDownProfileName = dto.getNewupdown();
		String clientName= dto.getClient_id();
		
		
		
		String macAddress = "";
		
		if(dto.getCpeMacAddress()!=null && !dto.getCpeMacAddress().equals("")) {
			macAddress = dto.getCpeMacAddress().replaceAll(":", "").replaceAll("(.{4})(?!$)", "$1.");
		}else {
			macAddress = "0000.0000.0000";
		}

	//COA Reverse Trottling
	String url = pocCOAAAAUrlReverse.replace("nwsubscode", clientName).replace("oldupdown",oldupAndDownProfileName);
	ProvJsons aaaCOAProvReq = COAAAAServiceActivation(requestId13, now ,url,1);
	provJsonList3.add(aaaCOAProvReq);
	provRequest3.setProvJsons(provJsonList3);
	ProvRequests provReq3 = provRequestsDAO.saveOrUpdate(provRequest3);
	
	//COA Reverse Trottling Delete
		String urlDelete = pocCOAAAAUrlDelete.replace("nwsubscode", clientName).replace("newupdown",newupAndDownProfileName);
		ProvJsons aaaCOAProvReqDelete = COAAAAServiceActivation(requestId14, now ,urlDelete,2);
		provJsonList4.add(aaaCOAProvReqDelete);
		provRequest4.setProvJsons(provJsonList4);
		ProvRequests provReq4 = provRequestsDAO.saveOrUpdate(provRequest4);
	
	//Update Reverse Trottling
	String updateUrl = pocUpdateAAAUrlReverse.replace("nwsubscode", clientName).replace("oldupdown", oldupAndDownProfileName).replace("cpeMacAddress", macAddress);
	ProvJsons aaaUpdationProvReq = UpdateAAAServiceActivation(requestId15, now ,updateUrl,3);
	provJsonList5.add(aaaUpdationProvReq);
	provRequest5.setProvJsons(provJsonList5);
	ProvRequests provReq5 = provRequestsDAO.saveOrUpdate(provRequest5);

										/*
										 * if(provReq.getProdCafNo()!=0) commonDAO.UpdateMonthlyUsage(6
										 * ,BigInteger.valueOf(Long.valueOf(dto.getProdcafno())));
										 */
	
	provRequest3 = null;
	provJsonList3 = null;
	
	provRequest4 = null;
	provJsonList4 = null;
	
	provRequest5 = null;
	provJsonList5 = null;

	
		}}}}
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
	
	}
	
	}

	status = "success";
	now = null;
	tz = null;
	} catch (Exception e) {

	status = "fail";
	throw e;
	}
	return status;

	}
	
	
	
	public String ProvisioningPreocessAndStatusUpdate() {
		String message="";
		List<Object[]> requestObj = new ArrayList<Object[]>();
		try {
			
			requestObj = commonDAO.getCafsListWithStatus(1);
			
			
			if(requestObj.size()>0 && requestObj!=null) {
			for(Object[] obj : requestObj){
				
				BigInteger requestId = (BigInteger) obj[0];
				byte status = (byte) obj[2];
				BigInteger cafNumber = (BigInteger) obj[1];
				
				
				String changeStatus = "";
				int cafUpdatedStatus = 0;
				
				if(status == 1){
					try {
					changeStatus = commonDAO.InsertIntoProJsonsTables(requestId);
					if(changeStatus.equals("success")){
						
						cafUpdatedStatus = commonDAO.UpdateMonthlyUsage(9 , cafNumber);
						
					}
					if(cafUpdatedStatus!=0){
						message = "Success";
							}
					}catch (Exception e) {
						// TODO: handle exception
						
						e.printStackTrace();
					}
				}

			}
		}
			/*
			 * if(message.equals("")){ message="Failed"; }
			 */
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	public String ProvisioningPreocessAndStatusUpdateReverse() {
		String message="";
		List<Object[]> requestObj = new ArrayList<Object[]>();
		try {
			
			requestObj = commonDAO.getCafsListWithStatus(6);
			
			
			if(requestObj.size()>0 && requestObj!=null) {
			for(Object[] obj : requestObj){
				
				BigInteger requestId = (BigInteger) obj[0];
				byte status = (byte) obj[2];
				BigInteger cafNumber = (BigInteger) obj[1];
				
				
				String changeStatus = "";
				int cafUpdatedStatus = 0;
				
				if(status == 1){
					
					
					try {
					
					changeStatus = commonDAO.InsertIntoProJsonsTables(requestId);
					if(changeStatus.equals("success")){
						
						cafUpdatedStatus = commonDAO.UpdateMonthlyUsage(0 , cafNumber);
						
					}
					if(cafUpdatedStatus!=0){
						message = "Success";
							}
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

			}
		}
			/*
			 * if(message.equals("")){ message="Failed"; }
			 */
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
}
