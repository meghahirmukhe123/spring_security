package com.StudentResult.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.StudentResult.entity.stuResult;
import com.StudentResult.service.resultService;

@RestController
public class resultController {
	
	@Autowired
	private resultService Resultservice;
	
	@PostMapping("/result")
	public stuResult savedata(@RequestBody stuResult result)
	{
		return Resultservice.createResult(result);
	}
	
	@GetMapping("/result")
	public List<stuResult> getall()
	{
		return Resultservice.getAllResult();
	}
	
	@GetMapping("/result/examid/{examId}")
	public stuResult getbyexamid(@PathVariable String examId)
	{
		return Resultservice.getByExamid(examId);
	}
	
	@GetMapping("/result/stuid/{stuId}")
	public List<stuResult> getbystuid(@PathVariable String stuId)
	{
		return Resultservice.getByStuId(stuId);
	}

}
