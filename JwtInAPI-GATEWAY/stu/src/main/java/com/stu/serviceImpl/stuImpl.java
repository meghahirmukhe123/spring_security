package com.stu.serviceImpl;


import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stu.entity.stuResult;
import com.stu.entity.student;
import com.stu.external.services.studentResult;
import com.stu.repo.sturepo;
import com.stu.service.stuService;

@Service
public class stuImpl implements stuService {

	
	@Autowired
	private sturepo Sturepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private studentResult StudentResult;
	
	private Logger logger= LoggerFactory.getLogger(stuImpl.class);
	
	@Override
	public student createStudent(student Student) {
		String randomId=UUID.randomUUID().toString();
		Student.setStuId(randomId);
		return Sturepo.save(Student);
	}

	
	//getting student data and their result at a time of all students using resttemplate
//	@Override
//	public List<student> getAllStudent() {
//		List<student> allstudents=Sturepo.findAll();
//		
//		for(student s1:allstudents)
//		{
//			ArrayList<stuResult> allresult= restTemplate.getForObject("http://localhost:3002/result/stuid/"+s1.getStuId(), ArrayList.class);
//			logger.info("{} ",allresult);
//			s1.setStuResult(allresult);
//		}
//		return allstudents;
//	}
	
	
	
	//getting student data and their result at a time of all students using feign client
	@Override
	public List<student> getAllStudent() {
		List<student> allstudents=Sturepo.findAll();
		
		for(student s1:allstudents)
		{
			
			List<stuResult> allresult=StudentResult.getAllResultsAlso(s1.getStuId());
			s1.setStuResult(allresult);
		}
		return allstudents;
	}

	
	//communicating student service and student result service
	@Override
	public student getByid(String stuId) {
		student s=Sturepo.findById(stuId).orElseThrow(null);
		
		//http://localhost:3002/result/stuid/e4869a4d-1305-4408-94fb-7399cf3e8aef  -->student result by student id
		//using rest template
//		ArrayList<stuResult> result=restTemplate.getForObject("http://localhost:3002/result/stuid/"+s.getStuId(), ArrayList.class);
//		logger.info("{} ",result);
//		s.setStuResult(result);
		
		
		//using feign client
		//ArrayList<stuResult> result=restTemplate.getForObject("http://localhost:3002/result/stuid/"+s.getStuId(), ArrayList.class);
		//logger.info("{} ",result);
		List<stuResult> stu=StudentResult.getStudent(stuId);
		s.setStuResult(stu);
		
		
		return s;
	}

}
