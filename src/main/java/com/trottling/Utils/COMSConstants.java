package com.trottling.Utils;

public class COMSConstants {

	public static final String DATE_TIME_STRING_PATTERN = "yyyy-MM-dd hh:mm:ss";
	public static final String DATE_STRING_PATTERN = "yyyy-MM-dd";
	public static final String DATE_STRING_WITH_DAY_NAME_IN_SHORT_FORMAT = "EEE, dd MMM yyyy";
	

	public static final int ACTIVATION = 1;
	public static final int SUSPEND = 2;
	public static final int RESUME = 3;
	public static final int DEACTIVATE = 4;
	
	public static final int SUCCESS = 1;
	public static final int FAILED = 2;
	public static final int TO_BE_PROCESSED = 0;
	public static final int TO_BE_REPLACED_AND_PROCESSED = 3;
	public static final int REPLACE_AND_PROCESS = 6;
	
	public static final String SYSTEM = "System";
	public static final String PROV_REQUEST_SEQUENCE = "PROVREQUESTID";
	
	public static final String INTERNET_SERVICE_CODE = "HSI";
	public static final String IPTV_SERVICE_CODE = "IPTV";
	public static final String TELEPHONY_SERVICE_CODE = "VOIP";
	public static final String VPN_SERVICE_CODE = "VPN";
	public static final String AAA_SERVICE_CODE= "AAA";
	public static final String VOD_SERVICE_CODE = "VOD";//added by sagar 18_01_2019
	
	public static final String IPTV_EXPIRY_DATE = "29991231";
	
	public static final String DEVICE_CATEGORY = "OTHER_DEVICE";
	
	//PROVISIONING SERVICE PARAMETER DEFINITION FOR CORE SERVICES
	public static final String UPSTREAM_TRAFFIC_PROFILE_NAME = "UPSTREAMTRAFFICPROFILENAME";
	public static final String DOWN_STREAM_TRAFFIC_PROFILE_NAME = "DOWNSTREAMTRAFFICPROFILENAME";
	public static final String AAA_IPADDRESS = "AAAIPADDRESS";
	public static final String DOWNLOAD_LIMIT = "DOWNLOADLIMIT";
	public static final String DOWNLOAD_LIMIT_UNIT = "DOWNLOADLIMITUNIT";
	public static final String UPLOAD_LIMIT = "UPLOADLIMIT";
	public static final String UPLOAD_LIMIT_UNIT = "UPLOADLIMITUNIT";
	
	
	//PROVISIONING SERVICE PARAMETER DEFINITION FOR CreatePVI TELEPHONY (ZTE SERVER)
	public static final String SECVER = "SECVER";
	public static final String PECFN = "PECFN";
	public static final String PCCFN = "PCCFN";
	public static final String SECFN = "SECFN";
	public static final String SCCFN = "SCCFN";
	
	//CreatePUI TELEPHONY (ZTE SERVER)
	public static final String BARFLAG = "BARFLAG";
	public static final String SPID = "SPID";
	public static final String ROAMSCHEMEID = "ROAMSCHEMEID";
	public static final String SCSCFNAMELIST = "SCSCFNAMELIST";
	
	//Set Alias Group TELEPHONY (ZTE SERVER)
	public static final String ALIASGROUPID = "ALIASGROUPID";
	
	//Add Enum TELEPHONY (ZTE SERVER)
	public static final String ZONENAME = "ZONENAME";
	public static final String ORDER = "ORDER";
	public static final String PREFERENCE = "PREFERENCE";
	public static final String FLAGS = "FLAGS";
	public static final String SERVICE = "SERVICE";
	public static final String REGEXP = "REGEXP";
	
	
	//Add TP TELEPHONY (ZTE SERVER)
	public static final String TEMPLATEIDX = "TEMPLATEIDX";
	public static final String UTYPE = "UTYPE";
	public static final String NSCFU = "NSCFU";
	public static final String NSRIO = "NSRIO";
	public static final String NSCW = "NSCW";
	public static final String NSNPTY = "NSNPTY";
	public static final String NSFAX = "NSFAX";
	public static final String NSABBR = "NSABBR";
	public static final String NSVT = "NSVT";
	
	//Add for AAA server	
	public static final String DOWNSPEED = "AAA-DownloadSpeed-Normal";
	public static final String UPSPEED = "AAA-UploadSpeed-Normal";
	

}
