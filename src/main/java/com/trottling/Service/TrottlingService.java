package com.trottling.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trottling.Dao.TrottlingDao;
import com.trottling.dto.HsiUsage;

@Service
public class TrottlingService {

	@Autowired
	TrottlingDao trottlingDao;
	
	public List<HsiUsage> getDataUsagePhoneNumbers(String YearAndMonth){
		return trottlingDao.getDataUsagePhoneNumbers(YearAndMonth);
	}
}
