package com.stu.entity;

public class stuResult {

	private String examId;
	private String stuId;
	private String result;
	public stuResult(String examId, String stuId, String result) {
		super();
		this.examId = examId;
		this.stuId = stuId;
		this.result = result;
	}
	public stuResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getExamId() {
		return examId;
	}
	public void setExamId(String examId) {
		this.examId = examId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "stuResult [examId=" + examId + ", stuId=" + stuId + ", result=" + result + "]";
	}
	
}
