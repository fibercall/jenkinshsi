package com.trottling.Dao;

import java.math.BigInteger;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.trottling.dto.AaaPocDTO;

@Repository
@PropertySource("classpath:application.properties")
@Transactional
public class CommonDao {
	
	/*
	 * private DataSource dataSource; private JdbcTemplate jdbcTemplate;
	 * 
	 * public DataSource getDataSource() { return dataSource; }
	 * 
	 * @Autowired public void setDataSource(DataSource dataSource) { this.dataSource
	 * = dataSource; this.jdbcTemplate = new JdbcTemplate(dataSource); }
	 */

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	

	@SuppressWarnings("unchecked")

	public List<AaaPocDTO> getPOCProvisioningRequests(String YearAndMonth) {

	List<Object[]> list = new ArrayList<Object[]>();

	List<AaaPocDTO> provisionInfoDTOList = new ArrayList<AaaPocDTO>();

	String qry = null;

	Query query = null;

	StringBuilder builder = new StringBuilder();

	try {


//		builder.append(" SELECT c.cafno,bb.client_id,CONCAT(pm.aaa_up,'_',pm.aaa_down) AS oldupdown, CONCAT(pm.fup_aaa_up,'_',pm.fup_aaa_down) AS newupdown,c.custid,pm.prodname,pm.srvccode,pm.srvcname, c.pocmob1 ,c.cpemacaddr FROM customer_data c JOIN bb_monthly_usage bb ON c.client_id=bb.client_id LEFT JOIN plan_master pm ON c.plan_id=pm.plan_id WHERE bb.agreegatedusage>=bb.month_limit AND bb.month LIKE '"+YearAndMonth+"%' and month_fup_flag = 0 LIMIT 1");
		
			//builder.append("SELECT c.cafno,bb.client_id,CONCAT(pm.aaa_up,'_',pm.aaa_down) AS oldupdown, CONCAT(pm.fup_aaa_up,'_',pm.fup_aaa_down) AS newupdown,c.custid,pm.prodname,pm.srvccode,pm.srvcname, c.pocmob1 ,c.cpemacaddr FROM customer_data c JOIN bb_monthly_usage bb ON c.client_id=bb.client_id LEFT JOIN plan_master pm ON c.plan_id=pm.plan_id WHERE  bb.agreegatedusage>=bb.month_limit AND bb.month LIKE '2019-03%'  AND bb.client_id = 'lag:240041:1:1:8' LIMIT 1");
		
		builder.append("SELECT c.cafno,bb.client_id,CONCAT(pm.aaa_up,'_',pm.aaa_down) AS oldupdown, CONCAT(pm.fup_aaa_up,'_',pm.fup_aaa_down) AS newupdown,c.custid,pm.prodname,pm.srvccode,pm.srvcname, c.pocmob1 ,c.cpemacaddr FROM customer_data c JOIN bb_monthly_usage bb ON c.client_id=bb.client_id LEFT JOIN plan_master pm ON c.plan_id=pm.plan_id WHERE  bb.agreegatedusage>=bb.month_limit and month_fup_flag = 0 limit 1000");
		
	qry = builder.toString();

	query = getEm().createNativeQuery(qry);

	//query.setParameter("cafno", cafNo);

	list = (List<Object[]>) query.getResultList();

	if(list.size()>0 && list!=null) {
	
	for (Object[] object : list) {

	AaaPocDTO provisionInfoDTO = new AaaPocDTO();//object[0] == null ? "" : object[0].toString()

	provisionInfoDTO.setCafno(object[0] == null ? "" : object[0].toString());

	provisionInfoDTO.setClient_id(object[1] == null ? "" : object[1].toString());

	provisionInfoDTO.setOldupdown(object[2] == null ? "" : object[2].toString());

	provisionInfoDTO.setNewupdown(object[3] == null ? "" : object[3].toString());

	provisionInfoDTO.setAcctcafno(object[0] == null ? "" : object[0].toString());

	provisionInfoDTO.setCustid(object[4] == null ? "" : object[4].toString());

	provisionInfoDTO.setProdcafno(object[0] == null ? "" : object[0].toString());

	provisionInfoDTO.setStbCafNo(0);

	provisionInfoDTO.setProdcode(object[5] == null ? "" : object[5].toString());

	provisionInfoDTO.setSrvccodeaddl(object[6] == null ? "" : object[6].toString());

	provisionInfoDTO.setSrvccodeprov(object[7] == null ? "" : object[7].toString());
	
	provisionInfoDTO.setMobileNumber(object[8] == null ? "" : object[8].toString());
	
	provisionInfoDTO.setCpeMacAddress(object[9] == null ? "" : object[9].toString());

	provisionInfoDTOList.add(provisionInfoDTO);

	}
	}

	}

	catch(Exception ex) {

		ex.printStackTrace();
	}

	finally {

	query = null;

	qry = null;

	list = null;

	builder = null;

	}

	return provisionInfoDTOList;

	}
	
	
	@SuppressWarnings("unchecked")

	public List<AaaPocDTO> getPOCProvisioningRequestsForReverse(String YearAndMonth) {

	List<Object[]> list = null;

	List<AaaPocDTO> provisionInfoDTOList = new ArrayList<>();

	String qry = null;

	Query query = null;

	StringBuilder builder = new StringBuilder();

	try {


		//builder.append(" SELECT c.cafno,bb.client_id,CONCAT(pm.aaa_up,'_',pm.aaa_down) AS oldupdown, CONCAT(pm.fup_aaa_up,'_',pm.fup_aaa_down) AS newupdown,c.custid,pm.prodname,pm.srvccode,pm.srvcname, c.pocmob1 ,c.cpemacaddr FROM customer_data c JOIN bb_monthly_usage bb ON c.client_id=bb.client_id LEFT JOIN plan_master pm ON c.plan_id=pm.plan_id WHERE bb.month LIKE '"+YearAndMonth+"%' LIMIT 1");	
		
				builder.append("SELECT c.cafno,bb.client_id,CONCAT(pm.aaa_up,'_',pm.aaa_down) AS oldupdown, CONCAT(pm.fup_aaa_up,'_',pm.fup_aaa_down) AS newupdown,c.custid,pm.prodname,pm.srvccode,pm.srvcname, c.pocmob1 ,c.cpemacaddr FROM customer_data c JOIN bb_monthly_usage bb ON c.client_id=bb.client_id LEFT JOIN plan_master pm ON c.plan_id=pm.plan_id WHERE bb.month LIKE '2019-03%' AND bb.client_id = 'lag:240041:1:1:8' LIMIT 1");
		
	qry = builder.toString();

	query = getEm().createNativeQuery(qry);

	//query.setParameter("cafno", cafNo);

	list = (List<Object[]>) query.getResultList();
	
	if(list.size()>0 && list!=null) {

	for (Object[] object : list) {

	AaaPocDTO provisionInfoDTO = new AaaPocDTO();

	provisionInfoDTO.setCafno(object[0] == null ? "" : object[0].toString());

	provisionInfoDTO.setClient_id(object[1] == null ? "" : object[1].toString());

	provisionInfoDTO.setOldupdown(object[2] == null ? "" : object[2].toString());

	provisionInfoDTO.setNewupdown(object[3] == null ? "" : object[3].toString());

	provisionInfoDTO.setAcctcafno(object[0] == null ? "" : object[0].toString());

	provisionInfoDTO.setCustid(object[4] == null ? "" : object[4].toString());

	provisionInfoDTO.setProdcafno(object[0] == null ? "" : object[0].toString());

	provisionInfoDTO.setStbCafNo(0);

	provisionInfoDTO.setProdcode(object[5] == null ? "" : object[5].toString());

	provisionInfoDTO.setSrvccodeaddl(object[6] == null ? "" : object[6].toString());

	provisionInfoDTO.setSrvccodeprov(object[7] == null ? "" : object[7].toString());
	
	provisionInfoDTO.setMobileNumber(object[8] == null ? "" : object[8].toString());
	
	provisionInfoDTO.setCpeMacAddress(object[9] == null ? "" : object[9].toString());

	provisionInfoDTOList.add(provisionInfoDTO);

	}

	}
	}

	catch(Exception ex) {

		ex.printStackTrace();
	}

	finally {

	query = null;

	qry = null;

	list = null;

	builder = null;

	}

	return provisionInfoDTOList;

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCafsListWithStatus(int status){
		StringBuilder queryString = new StringBuilder();
		Query query = null;
		queryString.append(" SELECT ol.requestid,ol.acctcafno,ol.status  FROM olprovrequests ol,olprovjsons olj,customer_data c ,bb_monthly_usage bb ");
        queryString.append(" WHERE ol.acctcafno=c.cafno AND c.client_id=bb.client_id AND ol.status=1 AND bb.month_fup_flag=:status AND ol.requestid=olj.requestid AND olj.servicetype='AAA' ORDER BY ol.acctcafno");
        List<Object[]> requestObj = new ArrayList<>();
		
		try {
			query = getEm().createNativeQuery(queryString.toString());
			query.setParameter("status", status);
			requestObj = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestObj;
	}
	
	
	
	
	@Transactional
	public String InsertIntoProJsonsTables(BigInteger requestId) {//added by sagar
		StringBuilder queryInsertProRequests = new StringBuilder();		
		StringBuilder queryInsertProJsons = new StringBuilder();
		StringBuilder queryDelOlProRequests = new StringBuilder();		
		StringBuilder queryDelOlProJsons = new StringBuilder();	
		
		Query InsertProRequests = null;
		Query InsertProJsons = null;
		Query DelOlProRequests = null;
		Query DelOlProJsons = null;
		
			
		String result = "";			
					
		try{
			queryInsertProRequests.append(" INSERT INTO provrequests(requestid,dpndrequestid,acctcafno,customerid,prodcafno,stbcafno,tenantcode,prodcode,srvccodeaddl,srvccodeprov,nwsubscode,provparams,ossmsgfmt,requestaction,STATUS,executeddate,lmoid,mspid,createdon,createdby,createdipaddr,modifiedon,modifiedby,modifiedipaddr,deactivatedon,deactivatedby,deactivatedipaddr) ");
			queryInsertProRequests.append(" SELECT requestid,dpndrequestid,acctcafno,customerid,prodcafno,stbcafno,tenantcode,prodcode,srvccodeaddl,srvccodeprov,nwsubscode,provparams,ossmsgfmt,requestaction,STATUS,executeddate,lmoid,mspid,createdon,createdby,createdipaddr,modifiedon,modifiedby,modifiedipaddr,deactivatedon,deactivatedby,deactivatedipaddr ");
			queryInsertProRequests.append(" FROM olprovrequests WHERE requestid = :requestId ");
			
			InsertProRequests =	getEm().createNativeQuery(queryInsertProRequests.toString());
			InsertProRequests.setParameter("requestId", requestId);
			int value1 = InsertProRequests.executeUpdate();
			
			if(value1!=0) {
			queryInsertProJsons.append(" INSERT INTO provjsons (requestid,seqnum,req,resp,servicetype,calltype,url,nwsubscode,STATUS,created_date,executed_date) ");
			queryInsertProJsons.append(" SELECT requestid,seqnum,req,resp,servicetype,calltype,url,nwsubscode,STATUS,created_date,executed_date ");
			queryInsertProJsons.append(" FROM olprovjsons WHERE requestid = :requestId ");
			
			InsertProJsons =	getEm().createNativeQuery(queryInsertProJsons.toString());
			InsertProJsons.setParameter("requestId", requestId);
			int value2 = InsertProJsons.executeUpdate();
			
			if(value2!=0) {
			queryDelOlProRequests.append(" DELETE FROM olprovjsons WHERE requestid = :requestId ");
			
			DelOlProRequests =	getEm().createNativeQuery(queryDelOlProRequests.toString());
			DelOlProRequests.setParameter("requestId", requestId);
			int value3 = DelOlProRequests.executeUpdate();
			
			if(value3!=0) {
			queryDelOlProJsons.append(" DELETE FROM olprovrequests WHERE requestid = :requestId ");
			
			DelOlProJsons =	getEm().createNativeQuery(queryDelOlProJsons.toString());
			DelOlProJsons.setParameter("requestId", requestId);
			int value4 = DelOlProJsons.executeUpdate();
			
			result = "success";	
			
			}
			}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	@Transactional
	public int UpdateMonthlyUsage(int status , BigInteger cafNumber){
		int value = 0;			
		StringBuilder queryString = new StringBuilder();
        Query query = null;
		try{
			// queryString.append("UPDATE bb_monthly_usage SET month_fup_flag = "+status+" WHERE client_id IN ( SELECT client_id FROM customer_data WHERE cafno ="+cafNumber+")");
			
			queryString.append("UPDATE bb_monthly_usage bb SET bb.month_fup_flag  = "+status+" WHERE  bb.month BETWEEN '2019-04-01' AND '2019-04-30' AND bb.client_id IN ( SELECT client_id FROM customer_data WHERE cafno ="+cafNumber+")");
			query = getEm().createNativeQuery(queryString.toString());
			value = query.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			query = null;
			queryString = null;
			
		}
		return value;
	}
	
}
