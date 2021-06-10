package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SalaryModel;


//getSalary 메서드를 통해 쿼리문 관리하는 mapper.xml에 접근해 
//데이터베이스와 상호 작용하므로 SalaryMapper.xml 파일의 select 태그에서 지정한 id 값과 같아야합니다.

@Repository 
@Mapper 
public interface SalaryMapper { 
	List<SalaryModel> getSalary(); 
}

