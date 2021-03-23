package com.trottling.dto;

import java.beans.Transient;

public class AaaPocDTO {

	private String cafno;

	private String client_id;

	private String oldupdown;

	private String newupdown;

	private Long dependencyRequestId;

	private String nwSubscode;

	private String srvccodeprov;

	private String coresrvccode;

	private String prodcafno;

	private String custid;

	private String srvccodeaddl;

	private long stbCafNo;
	
	private String prodcode;

	private String acctcafno;
	
	private String mobileNumber;
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCpeMacAddress() {
		return cpeMacAddress;
	}

	public void setCpeMacAddress(String cpeMacAddress) {
		this.cpeMacAddress = cpeMacAddress;
	}

	private String cpeMacAddress;
	
	

	public String getCafno() {

	return cafno;

	}

	public void setCafno(String cafno) {

	this.cafno = cafno;

	}

	public String getClient_id() {

	return client_id;

	}

	public void setClient_id(String client_id) {

	this.client_id = client_id;

	}

	public String getOldupdown() {

	return oldupdown;

	}

	public void setOldupdown(String oldupdown) {

	this.oldupdown = oldupdown;

	}

	public String getNewupdown() {

	return newupdown;

	}

	public void setNewupdown(String newupdown) {

	this.newupdown = newupdown;

	}


	public long getStbCafNo() {

	return stbCafNo;

	}

	public void setStbCafNo(long stbCafNo) {

	this.stbCafNo = stbCafNo;

	}

	public Long getDependencyRequestId() {

	return dependencyRequestId;

	}

	public void setDependencyRequestId(Long dependencyRequestId) {

	this.dependencyRequestId = dependencyRequestId;

	}

	public String getNwSubscode() {

	return nwSubscode;

	}

	public void setNwSubscode(String nwSubscode) {

	this.nwSubscode = nwSubscode;

	}

	public String getSrvccodeprov() {

	return srvccodeprov;

	}

	public void setSrvccodeprov(String srvccodeprov) {

	this.srvccodeprov = srvccodeprov;

	}

	public String getCoresrvccode() {

	return coresrvccode;

	}

	public void setCoresrvccode(String coresrvccode) {

	this.coresrvccode = coresrvccode;

	}

	public String getProdcafno() {

	return prodcafno;

	}

	public void setProdcafno(String prodcafno) {

	this.prodcafno = prodcafno;

	}

	public String getCustid() {

	return custid;

	}

	public void setCustid(String custid) {

	this.custid = custid;

	}

	public String getSrvccodeaddl() {

	return srvccodeaddl;

	}

	public void setSrvccodeaddl(String srvccodeaddl) {

	this.srvccodeaddl = srvccodeaddl;

	}

	public String getProdcode() {

	return prodcode;

	}

	public void setProdcode(String prodcode) {

	this.prodcode = prodcode;

	}

	public String getAcctcafno() {

	return acctcafno;

	}

	public void setAcctcafno(String acctcafno) {

	this.acctcafno = acctcafno;

	}

	@Override
	public String toString() {
		return "AaaPocDTO [cafno=" + cafno + ", client_id=" + client_id + ", oldupdown=" + oldupdown + ", newupdown="
				+ newupdown + ", dependencyRequestId=" + dependencyRequestId + ", nwSubscode=" + nwSubscode
				+ ", srvccodeprov=" + srvccodeprov + ", coresrvccode=" + coresrvccode + ", prodcafno=" + prodcafno
				+ ", custid=" + custid + ", srvccodeaddl=" + srvccodeaddl + ", stbCafNo=" + stbCafNo + ", prodcode="
				+ prodcode + ", acctcafno=" + acctcafno + ", mobileNumber=" + mobileNumber + ", cpeMacAddress="
				+ cpeMacAddress + "]";
	}



	

}
