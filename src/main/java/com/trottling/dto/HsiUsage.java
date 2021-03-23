package com.trottling.dto;

public class HsiUsage {

	private long  cafno;;
	private String client_id;
	private String AaaDownloadThreshold;
	private String AaaaUploadThreshold;
	private int status;
	private String mobileNumber;
	private String cpeMacAddress;
	
	public long getCafno() {
		return cafno;
	}
	public void setCafno(long cafno) {
		this.cafno = cafno;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getAaaDownloadThreshold() {
		return AaaDownloadThreshold;
	}
	public void setAaaDownloadThreshold(String aaaDownloadThreshold) {
		AaaDownloadThreshold = aaaDownloadThreshold;
	}
	public String getAaaaUploadThreshold() {
		return AaaaUploadThreshold;
	}
	public void setAaaaUploadThreshold(String aaaaUploadThreshold) {
		AaaaUploadThreshold = aaaaUploadThreshold;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
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
	@Override
	public String toString() {
		return "HsiUsage [cafno=" + cafno + ", client_id=" + client_id + ", AaaDownloadThreshold="
				+ AaaDownloadThreshold + ", AaaaUploadThreshold=" + AaaaUploadThreshold + ", status=" + status
				+ ", mobileNumber=" + mobileNumber + ", cpeMacAddress=" + cpeMacAddress + "]";
	}
	
	
	
	
	
}
