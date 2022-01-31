package com.spring.boot.dto;

public class FaqDTO {
/*
CREATE TABLE FAQ (
f_num number nut null, 
ques varchar2(200), 
ans varchar2(3000),
FAQ_cat varchar2(50) 
)
*/
	
	private int f_num;
	private String ques, ans, faq_cat;
	
	public int getF_num() {
		return f_num;
	}
	public void setF_num(int f_num) {
		this.f_num = f_num;
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public String getAns() {
		return ans;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
	public String getFaq_cat() {
		return faq_cat;
	}
	public void setFaq_cat(String faq_cat) {
		this.faq_cat = faq_cat;
	}
	
	
	
}
