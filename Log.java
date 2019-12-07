package com;
import java.sql.Timestamp;
public class Log{
	String ip;
	int record;
	String type,page;
	String main,second;
	Timestamp time;
	int status;
	String event;

public void setEvent(String event){
	this.event = event;
}
public String getEvent(){
	return event;
}
public void setStatusCode(int status){
	this.status = status;
}
public int getStatusCode(){
	return status;
}

public void setRecord(int record){
	this.record = record;
}
public int getRecord(){
	return record;
}
public void setIP(String ip){
	this.ip = ip;
}
public String getIP(){
	return ip;
}

public void setType(String type){
	this.type = type;
}
public String getType(){
	return type;
}

public void setPage(String page){
	this.page = page;
}
public String getPage(){
	return page;
}

public void setMain(String main){
	this.main = main;
}
public String getMain(){
	return main;
}

public void setSecond(String second){
	this.second = second;
}
public String getSecond(){
	return second;
}

public void setTime(Timestamp time){
	this.time = time;
}
public Timestamp getTime(){
	return time;
}
}