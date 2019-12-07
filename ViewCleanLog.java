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
public class ViewCleanLog extends JFrame{
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Font f1;
	JPanel p1;
	String columns[]={"Record No","ID","IP","Timestamp","Event Name","Relative URL","Operation","Status Code","Level-1 section","Level-2 section"};
public ViewCleanLog(){
	super("View Clean Log");
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
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	table.setFont(f1);
	// set font for headers
    Font f = new Font("Calisto MT", Font.BOLD, 18);
    JTableHeader header = table.getTableHeader();
    //header.setBackground(Color.red);
	  header.setFont(f);
	table.setRowHeight(30);
	dtm.addColumn(columns[0]);
	dtm.addColumn(columns[1]);
	dtm.addColumn(columns[2]);
	dtm.addColumn(columns[3]);
	dtm.addColumn(columns[4]);
	dtm.addColumn(columns[5]);
	dtm.addColumn(columns[6]);
	dtm.addColumn(columns[7]);
	dtm.addColumn(columns[8]);
	dtm.addColumn(columns[9]);
	table.getColumnModel().getColumn(2).setPreferredWidth(250);
	table.getColumnModel().getColumn(5).setPreferredWidth(300);
	table.getColumnModel().getColumn(8).setPreferredWidth(300);
	p1.setPreferredSize(new java.awt.Dimension(1500,800));
	p1.add(jsp,BorderLayout.CENTER);

	add(p1,BorderLayout.CENTER);
}
public void clear(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
}