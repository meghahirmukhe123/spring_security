package com.StudentResult.service;

import java.util.List;

import com.StudentResult.entity.stuResult;

public interface resultService {
	//create
	stuResult createResult(stuResult StuResult);
	
	//get all
	List<stuResult> getAllResult();
	
	//get by stuId
	List<stuResult> getByStuId(String stuId);
	
	//get by exam id
	stuResult getByExamid(String examId);

}
