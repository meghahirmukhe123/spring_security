package com.StudentResult.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StudentResult.entity.stuResult;
import com.StudentResult.repo.resultrepo;
import com.StudentResult.service.resultService;

@Service
public class resultImpl implements resultService {
	
	@Autowired
	private resultrepo Resultrepo;

	@Override
	public stuResult createResult(stuResult StuResult) {
		// TODO Auto-generated method stub
		return Resultrepo.save(StuResult);
	}

	@Override
	public List<stuResult> getAllResult() {
		// TODO Auto-generated method stub
		return Resultrepo.findAll();
	}

	@Override
	public List<stuResult> getByStuId(String stuId) {
		// TODO Auto-generated method stub
		return Resultrepo.getBystuId(stuId);
	}

	@Override
	public stuResult getByExamid(String examId) {
		// TODO Auto-generated method stub
		return Resultrepo.findById(examId).orElseThrow(null);
	}

}
