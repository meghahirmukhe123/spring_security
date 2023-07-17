package com.stu.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "studentInfo")
public class student {
	
	@jakarta.persistence.Id
	private String stuId;
	private String stuName;
	private String rollNum;
	
	@Transient
	private List<stuResult> StuResult=new ArrayList<>();

	public student(String stuId, String stuName, String rollNum, List<stuResult> stuResult) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.rollNum = rollNum;
		StuResult = stuResult;
	}

	public student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getRollNum() {
		return rollNum;
	}

	public void setRollNum(String rollNum) {
		this.rollNum = rollNum;
	}

	public List<stuResult> getStuResult() {
		return StuResult;
	}

	public void setStuResult(List<stuResult> stuResult) {
		StuResult = stuResult;
	}

	@Override
	public String toString() {
		return "student [stuId=" + stuId + ", stuName=" + stuName + ", rollNum=" + rollNum + ", StuResult=" + StuResult
				+ "]";
	}
	
	
	
}
