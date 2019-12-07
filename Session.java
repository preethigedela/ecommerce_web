package com;
import java.sql.Time;
public class Session{
	String ip;
	String session[];
	Time time[];
public void setIP(String ip){
	this.ip = ip;
}
public String getIP(){
	return ip;
}
public void setSession(String session[]){
	this.session = session;
}
public String[] getSession(){
	return session;
}
public void setTime(Time[] time){
	this.time = time;
}
public Time[] getTime(){
	return time;
}
}
