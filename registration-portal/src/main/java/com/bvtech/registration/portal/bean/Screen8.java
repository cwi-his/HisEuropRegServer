package com.bvtech.registration.portal.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class Screen8 {
	
	Integer typesSection2a;
	Integer typesSection2b;
	
	public Integer getTypesSection2a() {
		return typesSection2a;
	}

	public void setTypesSection2a(Integer typesSection2a) {
		this.typesSection2a = typesSection2a;
	}

	public Integer getTypesSection2b() {
		return typesSection2b;
	}

	public void setTypesSection2b(Integer typesSection2b) {
		this.typesSection2b = typesSection2b;
	}

	
	public static void initSection2aList(Model model) {
        List<String> section2aList = new ArrayList<String>();
        section2aList.add("Yes");
        section2aList.add("No");
        section2aList.add("I don't know");
  
        model.addAttribute("typesSection2a", section2aList);
    }
	
	public static void initSection2bList(Model model) {
        List<String> section2bList = new ArrayList<String>();
        section2bList.add("Yes");
        section2bList.add("No");
        section2bList.add("I don't know");
  
        model.addAttribute("typesSection2b", section2bList);
    }
}
