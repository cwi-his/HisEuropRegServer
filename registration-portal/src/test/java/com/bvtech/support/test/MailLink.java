package com.bvtech.support.test;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class MailLink {
	
	private static String genQueryStringEmail(Integer value) {
		
		StringBuilder b = new StringBuilder();
		Random r = new Random();
		String subset = "012345678901234567890123456789acde"; // no 'b', 't', 'h'
		// we use two subset of digit 0123456789 to increase the number inside the string

		for (int i = 0; i < 40; i++) {
			int index = r.nextInt(subset.length());
			char c = subset.charAt(index);
			b.append(c);
		}

		String specialCharSubset = "bht";
		
		String code = b.substring(20) + value + specialCharSubset.charAt(r.nextInt(specialCharSubset.length())) + b.substring(21);
		return new String(Base64.encodeBase64(code.getBytes()));
//		return code;
	}
	

	private static String getProInfFromEmail(String link) {
		String value = new String(Base64.decodeBase64(link.getBytes()));
//		String value = link;
		Integer from = 20;
		int positionSpecialChar=value.lastIndexOf("b");
		if(positionSpecialChar==-1){
			positionSpecialChar=value.lastIndexOf("h");
			if(positionSpecialChar==-1)
				positionSpecialChar=value.lastIndexOf("t");
		}
		
		Integer to = from + (positionSpecialChar - from);

		return value.substring(from, to);

	}
	
	
	public static void main(String[] args) {
		Integer value = 4565;
		System.out.println("VALUE ["+value+"]");
		String link = genQueryStringEmail(value);
		System.out.println("LINK [" + link+"]");
		String pid = getProInfFromEmail(link);
		System.out.println("Value from link ["+pid+"]");
	}
}
