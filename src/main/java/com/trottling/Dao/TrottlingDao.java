package com.trottling.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.trottling.dto.HsiUsage;

@Repository
@PropertySource("classpath:application.properties")
@Transactional
public class TrottlingDao {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public List<HsiUsage> getDataUsagePhoneNumbers(String YearAndMonth){
		
		List<Object[]> objectList = new ArrayList<Object[]>();
		
		List<HsiUsage> HsiUsageList = new ArrayList<HsiUsage>();
		
		Query query = null;
		
		StringBuilder builder = new StringBuilder("SELECT c.cafno,bb.client_id,CONCAT(pm.fup_aaa_up,'_',pm.fup_aaa_down) AS down,CONCAT(pm.aaa_up,'_',pm.aaa_down) AS up , c.pocmob1 , c.cpemacaddr FROM customer_data c \r\n" + 
				"JOIN bb_monthly_usage bb  ON c.client_id=bb.client_id LEFT JOIN plan_master pm ON c.plan_id=pm.plan_id WHERE bb.agreegatedusage >= bb.month_limit AND bb.month LIKE '"+YearAndMonth+"%' LIMIT 1");
		
		try {
			
			query = getEm().createNativeQuery(builder.toString());
			
			objectList = query.getResultList();
			
			for(Object[] obj : objectList) {
				
				HsiUsage hsi = new HsiUsage();
				BigInteger cagInt = (BigInteger) obj[0];
				hsi.setCafno(cagInt.longValue());
				hsi.setClient_id(obj[1] == null ? "" : obj[1].toString());
				hsi.setAaaDownloadThreshold(obj[2] == null ? "" : obj[2].toString());
				hsi.setAaaaUploadThreshold(obj[3] == null ? "" : obj[3].toString());
				hsi.setMobileNumber(obj[4] == null ? "" : obj[4].toString());
				hsi.setCpeMacAddress(obj[5] == null ? "" : obj[5].toString());
				
				
				HsiUsageList.add(hsi);
			}
			
			
			System.out.println("HsiUsageList size..."+HsiUsageList.size());
			
		}catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}finally {
			builder = null;
			query = null;
		}
		
		
		return HsiUsageList;
	}
}
