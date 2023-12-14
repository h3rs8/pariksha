package com.exam.model.exam;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.exam.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer rid;
	@Temporal(TemporalType.DATE)
	private Date date;
	private Integer marks;
	private String qname;
	private String uname;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	private Quiz quiz;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getMarks() {
		return marks;
	}
	public void setMarks(Integer marks) {
		this.marks = marks;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public Result(Integer rid, Date date, Integer marks, String qname) {
		super();
		this.rid = rid;
		this.date = date;
		this.marks = marks;
		this.qname = qname;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Result [rid=" + rid + ", date=" + date + ", marks=" + marks + ", qname=" + qname + "]";
	}
	
	
	
}
