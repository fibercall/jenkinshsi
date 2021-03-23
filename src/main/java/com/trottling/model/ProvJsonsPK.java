package com.trottling.model;

import java.io.Serializable;
import java.math.BigInteger;

public class ProvJsonsPK  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger requestId;
	
	private int seqNum;
	
	public BigInteger getRequestId() {
		return requestId;
	}
	public void setRequestId(BigInteger requestId) {
		this.requestId = requestId;
	}
	public int getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (requestId.intValue() ^ (requestId.intValue() >>> 32));
		result = prime * result + seqNum;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProvJsonsPK other = (ProvJsonsPK) obj;
		if (requestId != other.requestId)
			return false;
		if (seqNum != other.seqNum)
			return false;
		return true;
	}
	
	

}
