package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.SalaryMapper;
import com.example.demo.model.SalaryModel;


@Service
public class SalaryService { 
	@Autowired 
	public SalaryMapper mapper; 
	public List<SalaryModel> getSalary(){ 
		return mapper.getSalary(); 
		} 
	}
