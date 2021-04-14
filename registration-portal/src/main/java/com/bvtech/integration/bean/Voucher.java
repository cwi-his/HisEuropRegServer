package com.bvtech.integration.bean;

public class Voucher {
	


		private String code;
	    private String typCode;
	 
	
	    public Voucher() {
			super();
		}


		public Voucher(String code, String typCode) {
	
	        super();
	        this.setCode(code);
	        this.setTypCode(typCode);
	
	    }
	    
	    
		public String getCode() {
			return code;
		}
	
	
		public void setCode(String code) {
			this.code = code;
		}
	
	
		public String getTypCode() {
			return typCode;
		}
	
	
		public void setTypCode(String typCode) {
			this.typCode = typCode;
		}







	

}
