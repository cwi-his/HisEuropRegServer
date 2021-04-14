package com.bvtech.integration.bean;

import java.io.Serializable;
import java.util.Date;

public class LogisticDeliveryInformation  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	// Raffaele - 05.01.2017 - Christian W. asks me to add this two field (skype on 05.01.2017 at 15:50)
	private String  voucherCode;
	private String  typeCode;
	
	private String	deliveryAdressLastName;
	private String	deliveryAdressFirstName;
	private String	deliveryAdressStreetName;
	private String	deliveryAdressHouseNumber;
	private String	deliveryAdressAdditions;
	private String	deliveryAdressZipCode;
	private String	deliveryAdressCityName;
	private String	deliveryAdressAdditionalHints;
	private String	deliveryAdressPatientId;
	
	// Raffaele - 12.01.2017 - Christian W. asks me to add this one other field
	private String  registrationCompletedDateTime;
		

	public String getDeliveryAdressLastName() {
		return deliveryAdressLastName;
	}
	
	public void setDeliveryAdressLastName(String deliveryAdressLastName) {
		this.deliveryAdressLastName = deliveryAdressLastName;
	}
	
	public String getDeliveryAdressFirstName() {
		return deliveryAdressFirstName;
	}
	
	public void setDeliveryAdressFirstName(String deliveryAdressFirstName) {
		this.deliveryAdressFirstName = deliveryAdressFirstName;
	}
	
	public String getDeliveryAdressStreetName() {
		return deliveryAdressStreetName;
	}
	
	public void setDeliveryAdressStreetName(String deliveryAdressStreetName) {
		this.deliveryAdressStreetName = deliveryAdressStreetName;
	}
	
	public String getDeliveryAdressHouseNumber() {
		return deliveryAdressHouseNumber;
	}
	
	public void setDeliveryAdressHouseNumber(String deliveryAdressHouseNumber) {
		this.deliveryAdressHouseNumber = deliveryAdressHouseNumber;
	}
	
	public String getDeliveryAdressAdditions() {
		return deliveryAdressAdditions;
	}
	
	public void setDeliveryAdressAdditions(String deliveryAdressAdditions) {
		this.deliveryAdressAdditions = deliveryAdressAdditions;
	}
	
	public String getDeliveryAdressZipCode() {
		return deliveryAdressZipCode;
	}
	
	public void setDeliveryAdressZipCode(String deliveryAdressZipCode) {
		this.deliveryAdressZipCode = deliveryAdressZipCode;
	}
	
	public String getDeliveryAdressCityName() {
		return deliveryAdressCityName;
	}
	
	public void setDeliveryAdressCityName(String deliveryAdressCityName) {
		this.deliveryAdressCityName = deliveryAdressCityName;
	}
	
	public String getDeliveryAdressAdditionalHints() {
		return deliveryAdressAdditionalHints;
	}
	
	public void setDeliveryAdressAdditionalHints(String deliveryAdressAdditionalHints) {
		this.deliveryAdressAdditionalHints = deliveryAdressAdditionalHints;
	}
	
	public String getDeliveryAdressPatientId() {
		return deliveryAdressPatientId;
	}

	public void setDeliveryAdressPatientId(String deliveryAdressPatientId) {
		this.deliveryAdressPatientId = deliveryAdressPatientId;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getRegistrationCompletedDateTime() {
		return registrationCompletedDateTime;
	}

	public void setRegistrationCompletedDateTime(String registrationCompletedDateTime) {
		this.registrationCompletedDateTime = registrationCompletedDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deliveryAdressAdditionalHints == null) ? 0 : deliveryAdressAdditionalHints.hashCode());
		result = prime * result + ((deliveryAdressAdditions == null) ? 0 : deliveryAdressAdditions.hashCode());
		result = prime * result + ((deliveryAdressCityName == null) ? 0 : deliveryAdressCityName.hashCode());
		result = prime * result + ((deliveryAdressFirstName == null) ? 0 : deliveryAdressFirstName.hashCode());
		result = prime * result + ((deliveryAdressHouseNumber == null) ? 0 : deliveryAdressHouseNumber.hashCode());
		result = prime * result + ((deliveryAdressLastName == null) ? 0 : deliveryAdressLastName.hashCode());
		result = prime * result + ((deliveryAdressPatientId == null) ? 0 : deliveryAdressPatientId.hashCode());
		result = prime * result + ((deliveryAdressStreetName == null) ? 0 : deliveryAdressStreetName.hashCode());
		result = prime * result + ((deliveryAdressZipCode == null) ? 0 : deliveryAdressZipCode.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		result = prime * result + ((voucherCode == null) ? 0 : voucherCode.hashCode());
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
		LogisticDeliveryInformation other = (LogisticDeliveryInformation) obj;
		if (deliveryAdressAdditionalHints == null) {
			if (other.deliveryAdressAdditionalHints != null)
				return false;
		} else if (!deliveryAdressAdditionalHints.equals(other.deliveryAdressAdditionalHints))
			return false;
		if (deliveryAdressAdditions == null) {
			if (other.deliveryAdressAdditions != null)
				return false;
		} else if (!deliveryAdressAdditions.equals(other.deliveryAdressAdditions))
			return false;
		if (deliveryAdressCityName == null) {
			if (other.deliveryAdressCityName != null)
				return false;
		} else if (!deliveryAdressCityName.equals(other.deliveryAdressCityName))
			return false;
		if (deliveryAdressFirstName == null) {
			if (other.deliveryAdressFirstName != null)
				return false;
		} else if (!deliveryAdressFirstName.equals(other.deliveryAdressFirstName))
			return false;
		if (deliveryAdressHouseNumber == null) {
			if (other.deliveryAdressHouseNumber != null)
				return false;
		} else if (!deliveryAdressHouseNumber.equals(other.deliveryAdressHouseNumber))
			return false;
		if (deliveryAdressLastName == null) {
			if (other.deliveryAdressLastName != null)
				return false;
		} else if (!deliveryAdressLastName.equals(other.deliveryAdressLastName))
			return false;
		if (deliveryAdressPatientId == null) {
			if (other.deliveryAdressPatientId != null)
				return false;
		} else if (!deliveryAdressPatientId.equals(other.deliveryAdressPatientId))
			return false;
		if (deliveryAdressStreetName == null) {
			if (other.deliveryAdressStreetName != null)
				return false;
		} else if (!deliveryAdressStreetName.equals(other.deliveryAdressStreetName))
			return false;
		if (deliveryAdressZipCode == null) {
			if (other.deliveryAdressZipCode != null)
				return false;
		} else if (!deliveryAdressZipCode.equals(other.deliveryAdressZipCode))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		if (voucherCode == null) {
			if (other.voucherCode != null)
				return false;
		} else if (!voucherCode.equals(other.voucherCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogisticDeliveryInformation [voucherCode=" + voucherCode + ", typeCode=" + typeCode
				+ ", deliveryAdressLastName=" + deliveryAdressLastName + ", deliveryAdressFirstName="
				+ deliveryAdressFirstName + ", deliveryAdressStreetName=" + deliveryAdressStreetName
				+ ", deliveryAdressHouseNumber=" + deliveryAdressHouseNumber + ", deliveryAdressAdditions="
				+ deliveryAdressAdditions + ", deliveryAdressZipCode=" + deliveryAdressZipCode
				+ ", deliveryAdressCityName=" + deliveryAdressCityName + ", deliveryAdressAdditionalHints="
				+ deliveryAdressAdditionalHints + ", deliveryAdressPatientId=" + deliveryAdressPatientId + "]";
	}
	
}
