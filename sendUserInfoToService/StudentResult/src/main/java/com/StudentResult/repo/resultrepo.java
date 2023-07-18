package com.StudentResult.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StudentResult.entity.stuResult;

public interface resultrepo extends JpaRepository<stuResult, String> {
	
	List<stuResult> getBystuId(String stuId);

}
