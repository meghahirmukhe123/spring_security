package com.stu.service;

import java.util.List;

import com.stu.entity.student;

public interface stuService {
	//create
	student createStudent(student Student);
	
	//get all
	List<student> getAllStudent();
	
	//get by id
	student getByid(String stuId);

}
