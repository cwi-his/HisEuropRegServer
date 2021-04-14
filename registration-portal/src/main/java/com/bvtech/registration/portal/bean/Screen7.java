package com.bvtech.registration.portal.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class Screen7 {
	

	Integer typesSection1a;
	Integer typesSection1b;
	
	
	public Integer getTypesSection1a() {
		return typesSection1a;
	}
	public void setTypesSection1a(Integer typesSection1a) {
		this.typesSection1a = typesSection1a;
	}
	
	public Integer getTypesSection1b() {
		return typesSection1b;
	}
	public void setTypesSection1b(Integer typesSection1b) {
		this.typesSection1b = typesSection1b;
	}
	
	public static void initSection1aList(Model model) {
        List<String> section1aList = new ArrayList<String>();
        section1aList.add("Yes");
        section1aList.add("No");
        section1aList.add("I don't know");
  
        model.addAttribute("typesSection1a", section1aList);
    }
	
	public static void initSection1bList(Model model) {
        List<String> section1bList = new ArrayList<String>();
        section1bList.add("Yes");
        section1bList.add("No");
        section1bList.add("I don't know");
  
        model.addAttribute("typesSection1b", section1bList);
    }
}
