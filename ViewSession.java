package com;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import java.awt.Font;
public class ViewSession extends JFrame
{
	JTextArea area;
	JScrollPane jsp;
public ViewSession(){
	setTitle("View Session");
	area = new JTextArea();
	jsp = new JScrollPane(area);
	getContentPane().add(jsp);
	area.setEditable(false);
	area.setFont(new Font("Monospaced",Font.BOLD,16));
}

}