package com;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Time;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.text.DecimalFormat; 
public class ReadDataset{
	static DecimalFormat df = new DecimalFormat("#.####");
	static ArrayList<String[]> dataset = new ArrayList<String[]>();
	static ArrayList<String> ip = new ArrayList<String>();
	static long default_time = 0;//5*60*60*1000;
	static LinkedHashMap<String,ArrayList<Log>> log = new LinkedHashMap<String,ArrayList<Log>>();
	static ArrayList<Session> session = new ArrayList<Session>();
	static ArrayList<String> chart = new ArrayList<String>(); 
public static void read(File file){
	dataset.clear();
	ip.clear();
	log.clear();
	session.clear();
	try{
		default_time+= 0;//30*60*1000;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while((line = br.readLine())!=null){
			String arr[] = line.trim().split("\\|");
			dataset.add(arr);
			if(!ip.contains(arr[1]))
				ip.add(arr[1]);
		}
		br.close();
	}catch(IOException io){
		io.printStackTrace();
	}
}
public static void clean(){
	for(int i=0;i<dataset.size();i++){
		String arr[] = dataset.get(i);
		if(arr.length > 5){
			String type[] = arr[2].trim().split("\\s+");
			String ip = arr[1].trim();
			String request = type[0].trim();
			String page = type[1].trim();
			String status = arr[4].trim();
			String second = "-";
			String event = "";
			if(arr[2].trim().indexOf("robots.txt") == -1 && page.length() > 1 && status.equals("200")){
				String main = getMain(page);
				if(main.indexOf("/") != -1){
					second = getSecond(page);
					event = "Visit Secondary L2";
				}else{
					second = "-";
					event = "Visit Main L2";
				}
				String date_time = arr[5].trim();
				date_time = date_time.replace("-"," ");
				date_time = date_time.replaceAll("\\/","-");
				Timestamp time = Timestamp.valueOf(date_time.trim());
				Log lg = new Log();
				lg.setIP(ip);
				lg.setPage(page);
				lg.setType(request);
				lg.setMain(main);
				lg.setSecond(second);
				lg.setTime(time);
				lg.setStatusCode(Integer.parseInt(status));
				lg.setEvent(event);
				if(log.containsKey(ip)){
					ArrayList<Log> temp = log.get(ip);
					lg.setRecord(temp.get(0).getRecord());
					log.get(ip).add(lg);
				}else{
					lg.setRecord((log.size()+1));
					ArrayList<Log> temp = new ArrayList<Log>();
					temp.add(lg);
					log.put(ip,temp);
				}
			}
		}
	}
}
public static String getMain(String page){
	int index = 0;
	if(page.startsWith("/"))
		page = page.substring(1,page.length());
	if(page.endsWith("/")){
		page = page.substring(0,(page.length()-1));
	}
	if(page.indexOf("/") != -1)
		index = page.lastIndexOf("/");
	else
		index = page.length();
	
	return page.substring(0,index);
}
public static String getSecond(String page){
	int index = 0;
	if(page.startsWith("/"))
		page = page.substring(1,page.length());
	if(page.endsWith("/")){
		page = page.substring(0,(page.length()-1));
	}
	index = page.lastIndexOf("/");
	
	return page.substring((index+1),page.length());
}
public static void viewCleanLog(){
	int index = 1;
	ViewCleanLog vcl = new ViewCleanLog();
	for(Map.Entry<String,ArrayList<Log>> me : log.entrySet()){
		String key = me.getKey();
		ArrayList<Log> value = me.getValue();
		for(int i=0;i<value.size();i++){
			Log lg = value.get(i);
			Object row[] = {index,lg.getRecord(),lg.getIP(),lg.getTime().toString(),lg.getEvent(),lg.getPage(),lg.getType(),lg.getStatusCode(),lg.getMain(),lg.getSecond()};
			vcl.dtm.addRow(row);
			index = index + 1;
		}
	}
	vcl.setSize(900,600);
	vcl.setVisible(true);
}

public static void userBehaviour(){
	chart.clear();
	long unused = 6*60*60*1000;
	long session_time = 5*60*1000;
	long total_time;
		/*
		 * ArrayList<String> event = new ArrayList<String>(); event.add("yamaha");
		 * event.add("roland"); event.add("electro-harmonix"); event.add("casio");
		 */
	ViewBehaviour vb = new ViewBehaviour();
	for(Map.Entry<String,ArrayList<Log>> me : log.entrySet()){
		String key = me.getKey();
		ArrayList<Log> value = me.getValue();
		total_time = 0;
		for(int i=0;i<value.size();i++){
			Log lg1 = value.get(i);
			String second1 = lg1.getSecond().toLowerCase();
			String event1 = lg1.getEvent().toLowerCase();
			double count = 0;
			for(int j=i+1;j<value.size();j++){
				Log lg2 = value.get(j);
				long time = lg2.getTime().getTime() - lg1.getTime().getTime();
				total_time = total_time + time;
				String second2 = lg2.getSecond().toLowerCase();
				String event2 = lg2.getEvent().toLowerCase();
				if(second1.equals(second2) || event1.equals(event2)){
					if(time > session_time){
						count = count + 1;
					}
				}
			}
			if(count > 0){
				double events = count/(double)log.size();
				double session = count/(double)value.size();
				Object row[] = {lg1.getPage(),lg1.getSecond(),df.format(events),df.format(session),count,new Time(unused-total_time)};
				vb.dtm.addRow(row);
				String str = lg1.getSecond().trim().toLowerCase().replaceAll("[^a-zA-Z\\s+]", "");
				if(chart.size() < 10 && str.trim().length() > 0){
					chart.add(str.trim()+","+session);
				}
			}
		}
	}
	vb.setSize(900,600);
	vb.setVisible(true);
}

public static void process(String ip){	
	session.clear();
	int index = 0;
	Timestamp start_time = null;
	Timestamp end_time = null;
	StringBuilder session_data = new StringBuilder();
	StringBuilder time_data = new StringBuilder();
	String first_arr[] = null;
	String second_arr[] = null;
	for(int i=0;i<dataset.size();i++){
		String arr[] = dataset.get(i);
		if(arr[1].equals(ip)){
			String date = arr[5].replace("-"," ");
			date = date.replaceAll("\\/","-");
			Timestamp time = Timestamp.valueOf(date.trim());
			if(index == 0){
				first_arr = arr;
				start_time = time;
				index = index + 1;
			}
			else if(index > 0){
				second_arr = arr;
				end_time = time;
				Time remain = new Time((end_time.getTime() - start_time.getTime())-default_time);
				session_data.append(first_arr[2]+","+second_arr[2]+"\n");
				time_data.append(remain.toString()+"\n");
				first_arr = second_arr;
				start_time = end_time;
			}
		}
	}
	if(session_data.length() > 0)
		session_data.deleteCharAt(session_data.length()-1);
	if(time_data.length() > 0)
		time_data.deleteCharAt(time_data.length()-1);
	String split_time[] = time_data.toString().split("\n");
	Time time_arr[] = new Time[split_time.length];
	for(int i=0;i<split_time.length;i++){
		time_arr[i] = Time.valueOf(split_time[i]);
	}
	Session obj = new Session();
	obj.setIP(ip);
	obj.setSession(session_data.toString().split("\n"));
	obj.setTime(time_arr);
	session.add(obj);
}
public static void showResult(DefaultTableModel dtm){
	for(int i=0;i<session.size();i++){
		Session obj = session.get(i);
		String session_data[] = obj.getSession();
		Time time[] = obj.getTime();
		for(int j=0;j<time.length;j++){
			String arr[] = session_data[j].split(",");
			Object row[] = {obj.getIP(),arr[0],arr[1],time[j]};
			dtm.addRow(row);
		}
	}
}
public static void showResult(DefaultTableModel dtm,int time_th,int path_th){
	long create_time = time_th*60*1000;
	Time time_thr = new Time(create_time-default_time);
	boolean flag = false;			
	for(int i=0;i<session.size();i++){
		Session obj = session.get(i);
		String session_data[] = obj.getSession();
		Time time[] = obj.getTime();
		int path_length = 1;
		String target_page = "none";
		Time spent = null;
		int improve_link = 0;
	//	int new_link = 0;
		StringBuilder intermediate = new StringBuilder();
		for(int k=0;k<session_data.length;k++){
			Time spent_time = time[k];
			if(spent_time.after(time_thr)){
				target_page = session_data[k];
				intermediate.append(session_data[k]+"\n");
				spent = spent_time;
				if(path_length > path_th){
					improve_link = path_length - path_th;
		//			new_link = improve_link+improve_link;
				}else{
					improve_link = 0;
		//			new_link = 0;
				}
				String inter[] = intermediate.toString().split(",");
				String tar[] = target_page.split(",");
				Object row[]={obj.getIP(),time_th,path_th,improve_link,spent,inter[0],tar[1],intermediate.toString()};
				dtm.addRow(row);
				path_length = 1;
				intermediate.delete(0,intermediate.length());
				flag = true;
			}else{
				path_length = path_length + 1;
				intermediate.append(session_data[k]+"\n");
			}
		}
	}
	if(!flag){
		JOptionPane.showMessageDialog(null,"No links to improve");
	}
}
}
