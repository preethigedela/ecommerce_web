package com;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class ViewNavigation extends JFrame{
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Font f1;
public ViewNavigation(){
	setTitle("View Session");
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int r,int c){
			return false;
		}
	};
	f1 = new Font("Arial",Font.PLAIN,16);
	table = new JTable(dtm);
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	jsp = new JScrollPane(table);
	
	// set font for headers
    Font f = new Font("Calisto MT", Font.BOLD, 18);
    JTableHeader header = table.getTableHeader();
    //header.setBackground(Color.red);
	  header.setFont(f);
	  
	table.setFont(f1);
	table.setRowHeight(30);
	dtm.addColumn("IP");
	dtm.addColumn("Source Page");
	dtm.addColumn("Target Page");
	dtm.addColumn("Spent Time");
	table.getColumnModel().getColumn(0).setPreferredWidth(250);
	table.getColumnModel().getColumn(1).setPreferredWidth(270);
	table.getColumnModel().getColumn(2).setPreferredWidth(270);
	table.getColumnModel().getColumn(3).setPreferredWidth(150);
	jsp = new JScrollPane(table);
	getContentPane().add(jsp);
}

}