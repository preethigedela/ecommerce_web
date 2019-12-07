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
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class NavigationImprovements extends JFrame
{
	JButton b1;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
	Font f1;
	JPanel p1,p2;
	String columns[]={"User IP","Time Thr","Path Thr","Improved Links","Spent Time","Source Page","Target Page","Intermediate Pages"};
public NavigationImprovements(){
	super("View Navigation Improvements Result");
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
	  
	table.setRowHeight(30);
	dtm.addColumn(columns[0]);
	dtm.addColumn(columns[1]);
	dtm.addColumn(columns[2]);
	dtm.addColumn(columns[3]);
	dtm.addColumn(columns[4]);
	dtm.addColumn(columns[5]);
	dtm.addColumn(columns[6]);
	dtm.addColumn(columns[7]);
//	dtm.addColumn(columns[8]);
	p1.add(jsp,BorderLayout.CENTER);
	
	p2 = new JPanel();
	p2.setBackground(Color.white);
	b1 = new JButton("View Intermediate Pages");
	b1.setFont(f1);
	p2.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			ArrayList<String> list = new ArrayList<String>();
			int row = table.getSelectedRow();
			if(row != -1) {
				String source = dtm.getValueAt(row,6).toString().trim();
				String dest = dtm.getValueAt(row,7).toString().trim();
				String value = dtm.getValueAt(row,8).toString().trim();
				String val = value.replaceAll("\n",",");
				String inter[] = val.split(",");
				StringBuilder sb = new StringBuilder();
				for(int i=1;i<inter.length-1;i++){
					String in = inter[i].trim();
					//System.out.println(in+" " +i);
					if(!list.contains(in)){
						list.add(in);
						sb.append(in+"\n");
					}
				}
				ViewSession vs = new ViewSession();
				vs.setVisible(true);
				vs.setSize(900,700);
				vs.area.setText("");
				vs.area.append("Source Page	: "+source+"\n");
				vs.area.append("Destination Page : "+dest+"\n\n");
				if(sb.toString().length() > 0)
					vs.area.append("\nIntermediate Pages\n\n"+sb.toString()+"\n");
				else
					vs.area.append("\nNo Intermediate Pages\n");
			} else {
				JOptionPane.showMessageDialog(null,"First select row from the table then click button");
			}
		}
	});
	add(p1,BorderLayout.CENTER);
	add(p2,BorderLayout.SOUTH);
}
public void clear(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
}