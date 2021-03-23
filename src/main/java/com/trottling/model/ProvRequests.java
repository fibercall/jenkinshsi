package com.trottling.model;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "olprovrequests")
public class ProvRequests {

	public ProvRequests() {
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "requestid")
	private BigInteger requestId;
	
	@Column(name = "dpndrequestid", nullable = true)
	private Long dpndrequestid;
	
	@Column(name = "acctcafno")
	private long cafNo;
	
	@Column(name = "customerid")
	private long customerId;
	
	@Column(name = "prodcafno")
	private long prodCafNo;
	
	@Column(name = "tenantcode")
	private String tenantCode;
	
	@Column(name = "prodcode")
	private String prodCode;
	
	@Column(name = "srvccodeaddl")
	private String serviceCodeAddl;
	
	@Column(name = "srvccodeprov")
	private String serviceCodeProv;
	
	@Column(name = "provparams")
	private String provParams;
	
	@Column(name = "ossmsgfmt")
	private String ossMsgFmt;
	
	@Column(name = "requestaction")
	private int requestAction;
	
	@Column(name = "status")
	private int status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "executeddate", nullable = false)
	private Date executedDate;
	
	@Column(name = "lmoid")
	private long lmoId;
	
	@Column(name = "mspid")
	private long mspId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdon", nullable = false)
	private Date createdOn;
	
	@Column(name = "createdby")
	private String createdBy;
	
	@Column(name = "createdipaddr")
	private String createdIpAddr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifiedon", nullable = false)
	private Date modifiedOn;
	
	@Column(name = "modifiedby")
	private String modifiedBy;
	
	@Column(name = "modifiedipaddr")
	private String modifiedIpAddr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deactivatedon", nullable = false)
	private Date deactivatedOn;
	
	@Column(name = "deactivatedby")
	private String deactivatedBy;
	
	@Column(name = "deactivatedipaddr")
	private String deactivatedIpAddr;
	
	@Column(name = "stbcafno")
	private long stbCafNo;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name = "requestId" ,referencedColumnName ="requestId")
	private List<ProvJsons> provJsons;
	
	@Column(name= "nwsubscode")
	private String nwSubsCode;

	public String getNwSubsCode() {
		return nwSubsCode;
	}

	public void setNwSubsCode(String nwSubsCode) {
		this.nwSubsCode = nwSubsCode;
	}

	public Long getDpndrequestid() {
		return dpndrequestid;
	}

	public void setDpndrequestid(Long dpndrequestid) {
		this.dpndrequestid = dpndrequestid;
	}

	/**
	 * @return the requestId
	 */
	public BigInteger getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(BigInteger requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the cafNo
	 */
	public long getCafNo() {
		return cafNo;
	}

	/**
	 * @param cafNo the cafNo to set
	 */
	public void setCafNo(long cafNo) {
		this.cafNo = cafNo;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the prodCafNo
	 */
	public long getProdCafNo() {
		return prodCafNo;
	}

	/**
	 * @param prodCafNo the prodCafNo to set
	 */
	public void setProdCafNo(long prodCafNo) {
		this.prodCafNo = prodCafNo;
	}

	/**
	 * @return the tenantCode
	 */
	public String getTenantCode() {
		return tenantCode;
	}

	/**
	 * @param tenantCode the tenantCode to set
	 */
	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	/**
	 * @return the prodCode
	 */
	public String getProdCode() {
		return prodCode;
	}

	/**
	 * @param prodCode the prodCode to set
	 */
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	/**
	 * @return the serviceCodeAddl
	 */
	public String getServiceCodeAddl() {
		return serviceCodeAddl;
	}

	/**
	 * @param serviceCodeAddl the serviceCodeAddl to set
	 */
	public void setServiceCodeAddl(String serviceCodeAddl) {
		this.serviceCodeAddl = serviceCodeAddl;
	}

	/**
	 * @return the serviceCodeProv
	 */
	public String getServiceCodeProv() {
		return serviceCodeProv;
	}

	/**
	 * @param serviceCodeProv the serviceCodeProv to set
	 */
	public void setServiceCodeProv(String serviceCodeProv) {
		this.serviceCodeProv = serviceCodeProv;
	}

	/**
	 * @return the provParams
	 */
	public String getProvParams() {
		return provParams;
	}

	/**
	 * @param provParams the provParams to set
	 */
	public void setProvParams(String provParams) {
		this.provParams = provParams;
	}

	/**
	 * @return the ossMsgFmt
	 */
	public String getOssMsgFmt() {
		return ossMsgFmt;
	}

	/**
	 * @param ossMsgFmt the ossMsgFmt to set
	 */
	public void setOssMsgFmt(String ossMsgFmt) {
		this.ossMsgFmt = ossMsgFmt;
	}

	/**
	 * @return the requestAction
	 */
	public int getRequestAction() {
		return requestAction;
	}

	/**
	 * @param requestAction the requestAction to set
	 */
	public void setRequestAction(int requestAction) {
		this.requestAction = requestAction;
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
	 * @return the lmoId
	 */
	public long getLmoId() {
		return lmoId;
	}

	/**
	 * @param lmoId the lmoId to set
	 */
	public void setLmoId(long lmoId) {
		this.lmoId = lmoId;
	}

	/**
	 * @return the mspId
	 */
	public long getMspId() {
		return mspId;
	}

	/**
	 * @param mspId the mspId to set
	 */
	public void setMspId(long mspId) {
		this.mspId = mspId;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdIpAddr
	 */
	public String getCreatedIpAddr() {
		return createdIpAddr;
	}

	/**
	 * @param createdIpAddr the createdIpAddr to set
	 */
	public void setCreatedIpAddr(String createdIpAddr) {
		this.createdIpAddr = createdIpAddr;
	}

	/**
	 * @return the modifiedOn
	 */
	public Date getModifiedOn() {
		return modifiedOn;
	}

	/**
	 * @param modifiedOn the modifiedOn to set
	 */
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedIpAddr
	 */
	public String getModifiedIpAddr() {
		return modifiedIpAddr;
	}

	/**
	 * @param modifiedIpAddr the modifiedIpAddr to set
	 */
	public void setModifiedIpAddr(String modifiedIpAddr) {
		this.modifiedIpAddr = modifiedIpAddr;
	}

	/**
	 * @return the deactivatedOn
	 */
	public Date getDeactivatedOn() {
		return deactivatedOn;
	}

	/**
	 * @param deactivatedOn the deactivatedOn to set
	 */
	public void setDeactivatedOn(Date deactivatedOn) {
		this.deactivatedOn = deactivatedOn;
	}

	/**
	 * @return the deactivatedBy
	 */
	public String getDeactivatedBy() {
		return deactivatedBy;
	}

	/**
	 * @param deactivatedBy the deactivatedBy to set
	 */
	public void setDeactivatedBy(String deactivatedBy) {
		this.deactivatedBy = deactivatedBy;
	}

	/**
	 * @return the deactivatedIpAddr
	 */
	public String getDeactivatedIpAddr() {
		return deactivatedIpAddr;
	}

	/**
	 * @param deactivatedIpAddr the deactivatedIpAddr to set
	 */
	public void setDeactivatedIpAddr(String deactivatedIpAddr) {
		this.deactivatedIpAddr = deactivatedIpAddr;
	}

	/**
	 * @return the provJsons
	 */
	public List<ProvJsons> getProvJsons() {
		return provJsons;
	}

	/**
	 * @param provJsons the provJsons to set
	 */
	public void setProvJsons(List<ProvJsons> provJsons) {
		this.provJsons = provJsons;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getStbCafNo() {
		return stbCafNo;
	}

	public void setStbCafNo(long stbCafNo) {
		this.stbCafNo = stbCafNo;
	}
}