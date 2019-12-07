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
import java.awt.Dimension;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ViewBehaviour extends JFrame{
	
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Font f1;
	JPanel p1;
	String columns[]={"Event Name","Product Name","Total Events","Total Sessions"};
//			"Number Of Hits","Spent Time"};
public ViewBehaviour(){
	super("View Behaviour");
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
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
	// set font for headers
    Font f = new Font("Calisto MT", Font.BOLD, 18);
    JTableHeader header = table.getTableHeader();
    //header.setBackground(Color.red);
	  header.setFont(f);
	  
	jsp = new JScrollPane(table);
	table.setFont(f1);
	table.setRowHeight(30);
	dtm.addColumn(columns[0]);
	dtm.addColumn(columns[1]);
	dtm.addColumn(columns[2]);
	dtm.addColumn(columns[3]);
		/*
		 * dtm.addColumn(columns[4]); dtm.addColumn(columns[5]);
		 */
	table.getColumnModel().getColumn(0).setPreferredWidth(300);
	table.getColumnModel().getColumn(1).setPreferredWidth(200);
	table.getColumnModel().getColumn(2).setPreferredWidth(150);
	table.getColumnModel().getColumn(3).setPreferredWidth(150);
		/*
		 * table.getColumnModel().getColumn(4).setPreferredWidth(150);
		 * table.getColumnModel().getColumn(5).setPreferredWidth(150);
		 */
	p1.add(jsp,BorderLayout.CENTER);
	p1.setPreferredSize(new Dimension(1800,900));

	add(p1,BorderLayout.CENTER);
}
public void clear(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
}