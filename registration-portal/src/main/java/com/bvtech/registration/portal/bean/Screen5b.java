package com.bvtech.registration.portal.bean;

public class Screen5b {
	private String telephoneNumber2;

	private Integer availability;
	private Integer availability2;
	private Integer availability3;
	private Integer availability4;
	private Boolean contactNow;
	private String fromDate;
	
	public String getTelephoneNumber2() {
		return telephoneNumber2;
	}
	public void setTelephoneNumber2(String telephoneNumber2) {
		this.telephoneNumber2 = telephoneNumber2;
	}
	
	public Integer getAvailability() {
		return availability;
	}
	public void setAvailability(Integer availability) {
		this.availability = (availability==null?0:availability);
	}
	
	public Integer getAvailability2() {
		return availability2;
	}
	public void setAvailability2(Integer availability2) {
		this.availability2 = (availability2==null?0:availability2);
	}
	public Integer getAvailability3() {
		return availability3;
	}
	public void setAvailability3(Integer availability3) {
		this.availability3 = (availability3==null?0:availability3);
	}
	public Integer getAvailability4() {
		return availability4;
	}
	public void setAvailability4(Integer availability4) {
		this.availability4 = (availability4==null?0:availability4);
	}
	
	public Boolean getContactNow() {
		return contactNow;
	}
	public void setContactNow(Boolean contactNow) {
		this.contactNow = contactNow;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
//	public static void initAvailabilityList(Model model) {
//        List<String> availabilityList = new ArrayList<String>();
//        availabilityList.add("morning (7:30-9:30)");
//        availabilityList.add("morning (9:30-12:30)");
//        availabilityList.add("afternoon (12:30-16:30)");
//        availabilityList.add("evening (16:30-18:00)");
//        
//        model.addAttribute("availabilitys", availabilityList);
//    }

}
