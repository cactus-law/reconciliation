package com.froad.recon.importfile.model;

/**
 * IImpStatusDetailId entity. @author MyEclipse Persistence Tools
 */

public class IimpStatusDetailId implements java.io.Serializable {

	// Fields

	private String platformDetailNo;
	private String impDate;

	// Constructors

	/** default constructor */
	public IimpStatusDetailId() {
	}

	/** full constructor */
	public IimpStatusDetailId(String platformDetailNo, String impDate) {
		this.platformDetailNo = platformDetailNo;
		this.impDate = impDate;
	}

	// Property accessors

	public String getPlatformDetailNo() {
		return this.platformDetailNo;
	}

	public void setPlatformDetailNo(String platformDetailNo) {
		this.platformDetailNo = platformDetailNo;
	}

	public String getImpDate() {
		return this.impDate;
	}

	public void setImpDate(String impDate) {
		this.impDate = impDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IimpStatusDetailId))
			return false;
		IimpStatusDetailId castOther = (IimpStatusDetailId) other;

		return ((this.getPlatformDetailNo() == castOther.getPlatformDetailNo()) || (this
				.getPlatformDetailNo() != null
				&& castOther.getPlatformDetailNo() != null && this
				.getPlatformDetailNo().equals(castOther.getPlatformDetailNo())))
				&& ((this.getImpDate() == castOther.getImpDate()) || (this
						.getImpDate() != null
						&& castOther.getImpDate() != null && this.getImpDate()
						.equals(castOther.getImpDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPlatformDetailNo() == null ? 0 : this
						.getPlatformDetailNo().hashCode());
		result = 37 * result
				+ (getImpDate() == null ? 0 : this.getImpDate().hashCode());
		return result;
	}

}