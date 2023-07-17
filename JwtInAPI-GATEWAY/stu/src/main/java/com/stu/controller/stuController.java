package com.stu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stu.entity.student;
import com.stu.service.stuService;

@RestController
public class stuController {
	
	@Autowired
	private stuService Stuservice;
	
	@PostMapping("/stu")
	public student saveall(@RequestBody student Stu)
	{
		return Stuservice.createStudent(Stu);
	}
	
	@GetMapping("/stu")
	public List<student> getall()
	{
		return Stuservice.getAllStudent();
	}
	
	@GetMapping("/stu/{stuId}")
	public student getbyid(@PathVariable String stuId)
	{
		return Stuservice.getByid(stuId);
	}
	

}
