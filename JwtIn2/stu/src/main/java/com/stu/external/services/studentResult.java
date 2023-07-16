package com.stu.external.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.stu.entity.stuResult;

@FeignClient(name = "STUDENTRESULT-SERVICE")
public interface studentResult 
{
	
	//getting by student id
	@GetMapping("/result/stuid/{stuId}")
    List<stuResult> getStudent(@PathVariable String stuId);
    
    //getting all student
    @GetMapping("/result/stuid/{stuId}")
    List<stuResult> getAllResultsAlso(@PathVariable String stuId);

}
