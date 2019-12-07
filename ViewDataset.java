package com;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JPanel;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
  
public class ViewDataset extends JFrame
{
	
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Font f1;
	JPanel p1;
	String columns[]={"Record No","User IP","Request Type","Page Visited","Date & Time","Status Code"};
public ViewDataset(){
	super("View Dataset");
	setLayout(new BorderLayout());
	f1 = new Font("Arial",Font.PLAIN,16);
	p1 = new JPanel();
	p1.setBackground(Color.white);
	p1.setLayout(new BorderLayout());
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int r,int c){
			return false;
		}
	};
	table = new JTable(dtm);
	jsp = new JScrollPane(table);
	table.setFont(f1);
	// set font for headers
      Font f = new Font("Calisto MT", Font.BOLD, 18);
      JTableHeader header = table.getTableHeader();
      //header.setBackground(Color.red);
	  header.setFont(f);
	  
	  //header.setForeground(Color.white);
	 // font for table 
	table.setRowHeight(30);
	dtm.addColumn(columns[0]);
	dtm.addColumn(columns[1]);
	dtm.addColumn(columns[2]);
	dtm.addColumn(columns[3]);
	dtm.addColumn(columns[4]);
	dtm.addColumn(columns[5]);
	p1.add(jsp,BorderLayout.CENTER);

	add(p1,BorderLayout.CENTER);
}
public void clear(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
public void viewDataset(ArrayList<String[]> list){
	clear();
	for(int i=0;i<list.size();i++){
		String arr[] = list.get(i);
		if(arr.length > 5){
			String type[] = arr[2].trim().split("\\s+");
			Object row[] = {(i+1),arr[1],type[0],type[1],arr[5],arr[4]};
			dtm.addRow(row);
		}
	}
}
}