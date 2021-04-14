package com.bvtech.registration.portal.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class Screen10 {
	
	
	private Integer questionInsuline;
	private Integer questionInsulineB=0;

	public Integer getQuestionInsuline() {
		return questionInsuline;
	}


	public void setQuestionInsuline(Integer questionInsuline) {
		this.questionInsuline = questionInsuline;
	}

	public Integer getQuestionInsulineB() {
		return questionInsulineB;
	}


	public void setQuestionInsulineB(Integer questionInsulineB) {
		this.questionInsulineB = questionInsulineB;
	}


	public static void initInsulineList(Model model) {
        List<String> insulineList = new ArrayList<String>();
        insulineList.add("Yes");
        insulineList.add("No");
    
  
        model.addAttribute("typesInsuline", insulineList);
    }
	
	public static void initInsulineBList(Model model) {
        List<String> insulineBList = new ArrayList<String>();
        insulineBList.add("less than 6 months");
        insulineBList.add("6-12 months");
        insulineBList.add("one order year");
    
  
        model.addAttribute("typesInsulineB", insulineBList);
    }
	



}
