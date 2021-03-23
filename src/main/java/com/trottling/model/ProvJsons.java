package com.trottling.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "olprovjsons")
@IdClass(ProvJsonsPK.class)
public class ProvJsons implements Serializable{

	public ProvJsons() {
	}

	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @Column(name = "ID") private long id;
	 */

	
	  @Id
	 // @GeneratedValue(strategy = GenerationType.SEQUENCE)
	  @Column(name = "requestid") 
	  private BigInteger requestId;
	 

	@Id
	@Column(name = "seqnum")
	private int seqNum;

	@Column(name = "req")
	private String req;

	@Column(name = "resp")
	private String resp;

	@Column(name = "servicetype")
	private String serviceType;

	@Column(name = "calltype")
	private String callType;

	@Column(name = "url")
	private String url;

	@Column(name = "status")
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "executed_date", nullable = false)
	private Date executedDate;

	
	public BigInteger getRequestId() {
		return requestId;
	}

	public void setRequestId(BigInteger requestId) {
		this.requestId = requestId;
	}

	public String getReq() {
		return req;
	}

	/**
	 * @param req the req to set
	 */
	public void setReq(String req) {
		this.req = req;
	}

	/**
	 * @return the resp
	 */
	public String getResp() {
		return resp;
	}

	/**
	 * @param resp the resp to set
	 */
	public void setResp(String resp) {
		this.resp = resp;
	}

	/**
	 * @return the serviceType
	 */
	public String getServiceType() {
		return serviceType;
	}

	/**
	 * @param serviceType the serviceType to set
	 */
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/**
	 * @return the callType
	 */
	public String getCallType() {
		return callType;
	}

	/**
	 * @param callType the callType to set
	 */
	public void setCallType(String callType) {
		this.callType = callType;
	}

	/**
	 * @param delAAAUrl
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the seqNum
	 */
	public int getSeqNum() {
		return seqNum;
	}

	/**
	 * @param seqNum the seqNum to set
	 */
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the executedDate
	 */
	public Date getExecutedDate() {
		return executedDate;
	}

	/**
	 * @param executedDate the executedDate to set
	 */
	public void setExecutedDate(Date executedDate) {
		this.executedDate = executedDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}