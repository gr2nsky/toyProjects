package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.SalaryModel;
import com.example.demo.service.SalaryService;

@Controller 
public class HomeController { 
	@Autowired 
	SalaryService salaryService; 
	@RequestMapping(value = "/home") 
	public List<SalaryModel> goHome() { 
		List<SalaryModel> salaryList = salaryService.getSalary(); 
		
		return salaryList; 
	} 
	
	
    @RequestMapping(value = "/")
    public String home(){
        return "Hello World!";
    }
}
