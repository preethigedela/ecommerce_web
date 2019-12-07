package com;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Cursor;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Cursor;
import org.jfree.ui.RefineryUtilities;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
public class Process extends JFrame{
	GradientPanel p1;
	JPanel p2;
	JLabel l1,l2;
	JButton b1,b2,b3,b4,b5,b6,b7,b8;
	Font f1;
	JFileChooser chooser;
	Login login;
	int height,width;
public Process(Login log){
	super("Process Dataset");
	login = log;

	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	height = (int)d.getHeight();
	width = (int)d.getWidth();
	width = width/2;

	p1 = new GradientPanel(600,200);
	p1.setLayout(null);
	
	f1 = new Font("Calisto MT",Font.BOLD,14);
	p2 = new TitlePanel(600,80);
	p2.setBackground(new Color(204, 110, 155));
	l1 = new JLabel("<HTML><BODY><CENTER><BR>Analysis of users’ behaviour in structured<br/>e-commerce websites</BR></CENTER></BODY></HTML>".toUpperCase());
	l1.setFont(new Font("Calisto MT",Font.BOLD,16));
	p2.add(l1);
	l1.setForeground(Color.white);

/*
	l2 = new JLabel("Upload Ecommerce Dataset");
	l2.setFont(new Font("Calisto MT",Font.BOLD,13));
	l2.setBounds(width-80,10,300,30);
	*/
	l2 = new JLabel("<HTML><CENTER>     Admin Custom Login Dashboard</CENTER></HTML>");	
	l2.setBorder(new MatteBorder(2, 0, 2, 0, (Color) SystemColor.textInactiveText));	
	l2.setForeground(SystemColor.textHighlight);	
	l2.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 18));	
	l2.setBounds(664,36,256,50);
	p1.add(l2);

	b1 = new JButton("Upload Dataset");
	/*b1.setFont(f1);
	b1.setBounds(width-150,60,320,30);
	*/
	b1.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));	
	b1.setBounds(35,496,220,40);
	p1.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			int option = chooser.showOpenDialog(Process.this);
			if(option == chooser.APPROVE_OPTION){
				Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
				setCursor(hourglassCursor);
				ReadDataset.read(chooser.getSelectedFile());
				b5.setEnabled(true);
				b2.setEnabled(true);
				b3.setEnabled(true);
				b4.setEnabled(true);
				b7.setEnabled(true);
				b8.setEnabled(true);
				b6.setEnabled(true);
				JOptionPane.showMessageDialog(Process.this,"Dataset has been uploaded !!");
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				setCursor(normalCursor);
			}
		}
	});

	b5 = new JButton("View Dataset");
	//b5.setFont(f1);
	b5.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));
	//b5.setBounds(width-150,110,320,30);
	b5.setBounds(299,496,220,40);
	p1.add(b5);
	b5.setEnabled(false);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			ViewDataset vd = new ViewDataset();
			vd.setVisible(true);
			vd.setSize(800,600);
			vd.viewDataset(ReadDataset.dataset);
		}
	});

	b2 = new JButton("Log Cleaning & Preparation");
	b2.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));
	b2.setEnabled(false);
	b2.setBounds(728,144,244,40);
	p1.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			ReadDataset.clean();
			ReadDataset.viewCleanLog();
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
		}
	});
	
	
	b3 = new JButton("Identify User Behaviour");
	//b3.setFont(f1);
	b3.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));
	b3.setEnabled(false);
	//b3.setBounds(width-150,210,320,30);
	b3.setBounds(728,232,244,40);
	p1.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			ReadDataset.userBehaviour();
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
		}
	});

	b6 = new JButton("Navigation Patterns");
	//b6.setFont(f1);
	//b6.setBounds(width-150,260,320,30);
	b6.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));	
	b6.setBounds(728,316,244,40);
	p1.add(b6);
	b6.setEnabled(false);
	b6.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			String ip = JOptionPane.showInputDialog(Process.this,"Please enter a valid IP address to view navigation details");
			if(ip != null && ip.trim().length() > 0){
				ReadDataset.process(ip);
				ViewNavigation vs = new ViewNavigation();
				vs.setVisible(true);
				vs.setSize(1000,400);
				ReadDataset.showResult(vs.dtm);
			}else{
				JOptionPane.showMessageDialog(Process.this,"REQUIRED - Please enter a valid IP address");
			}
		}
	});

	b7 = new JButton("Improve Navigation Patterns");
/*	b7.setFont(f1);
	b7.setBounds(width-150,310,320,30);
	*/
	b7.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));	
	b7.setBounds(728,397,244,40);
	p1.add(b7);
	b7.setEnabled(false);
	b7.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		//	String time = JOptionPane.showInputDialog(Process.this,"Please enter a valid Time Threshold value");
			String time = "1";
			String path = JOptionPane.showInputDialog(Process.this,"Please enter a valid Path Threshold value");
			if(time != null && time.length() > 0 && path != null && path.length() > 0){
				NavigationImprovements ni = new NavigationImprovements();
				ni.setVisible(true);
				ni.setSize(1000,400);
				ReadDataset.showResult(ni.dtm,Integer.parseInt(time.trim()),Integer.parseInt(path.trim()));
			}else{
				JOptionPane.showMessageDialog(Process.this,"REQUIRED - Please enter a valid Time & Path Threshold values");
			}
		}
	});

	b8 = new JButton("Session Chart");
	/*b8.setFont(f1);
	b8.setBounds(width-150,360,320,30);
	*/
	b8.setFont(new Font("Calisto MT", Font.BOLD | Font.ITALIC, 16));	
	b8.setBounds(1120,580,220,40);
	p1.add(b8);
	b8.setEnabled(false);
	b8.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Chart chart1 = new Chart("Session Chart");
			chart1.pack();
			RefineryUtilities.centerFrameOnScreen(chart1);
			chart1.setVisible(true);
		}
	});

	b4 = new JButton("Logout");
	//b4.setFont(f1);
	b4.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));	
	b4.setFont(new Font("Arial", Font.BOLD, 16));
	b4.setEnabled(false);
	//b4.setBounds(width-150,410,320,30);
	b4.setBounds(1400,51,96,30);
	p1.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			setVisible(false);
			login.clear();
			login.setVisible(true);
		}
	});
	
	chooser = new JFileChooser(new File("dataset"));
	
	
	// Images
	JLabel main = new JLabel("");
	Image mainImg = new ImageIcon(this.getClass().getResource("/main.jpg")).getImage();
	main.setIcon(new ImageIcon(mainImg));
	main.setBounds(35, 74, 499, 270);
	p1.add(main);
	
	JLabel upload = new JLabel("");
	Image uploadImg = new ImageIcon(this.getClass().getResource("/upload-icon.png")).getImage();
	upload.setIcon(new ImageIcon(uploadImg));
	upload.setBounds(81, 377, 106, 117);
	p1.add(upload);
	
	JLabel chart = new JLabel("");
	Image chartImg = new ImageIcon(this.getClass().getResource("/chart-search-icon.png")).getImage();
	chart.setIcon(new ImageIcon(chartImg));
	chart.setBounds(356, 397, 128, 67);
	p1.add(chart);
	
	JLabel logClean = new JLabel("");
	Image logImg = new ImageIcon(this.getClass().getResource("/log2-icon.png")).getImage();
	logClean.setIcon(new ImageIcon(logImg));
	logClean.setBounds(611, 112, 106, 88);
	p1.add(logClean);
	
	JLabel navigation = new JLabel("");
	Image navigationImg = new ImageIcon(this.getClass().getResource("/map-icon.png")).getImage();
	navigation.setIcon(new ImageIcon(navigationImg));
	navigation.setBounds(611, 320, 112, 117);
	p1.add(navigation);
	
	JLabel userBehavior = new JLabel("");
	Image userBehaviorImg = new ImageIcon(this.getClass().getResource("/user-icon.png")).getImage();
	userBehavior.setIcon(new ImageIcon(userBehaviorImg));
	userBehavior.setBounds(615, 201, 87, 88);
	p1.add(userBehavior);
	
	JLabel logout = new JLabel("");
	Image logoutImg = new ImageIcon(this.getClass().getResource("/logout-icon.png")).getImage();
	logout.setIcon(new ImageIcon(logoutImg));
	logout.setBounds(1320, 36, 83, 50);
	p1.add(logout);
	
	JLabel data = new JLabel("");
	Image dataImg = new ImageIcon(this.getClass().getResource("/data_analytics.png")).getImage();
	data.setIcon(new ImageIcon(dataImg));
	data.setBounds(1006, 222, 500, 349);
	p1.add(data);
	
	
	getContentPane().add(p1,BorderLayout.CENTER);
	getContentPane().add(p2,BorderLayout.NORTH);
}
}