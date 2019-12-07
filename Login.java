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
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Rectangle;

public class Login extends JFrame {
	GradientPanel p1;
	JPanel p2;
	JLabel l1, l2, title, l3;
	JTextField tf1, tf2;

	JButton b1, b2, b3;
	Font f1, f2;
	int height, width;
	private JTextField textField;

	public Login(int height, int width) {
		super("E-Commerce Admin Login");
		this.height = height;
		this.width = width;
		height = height / 2;
		width = width / 2;

		p1 = new GradientPanel(600, 200);
		p1.setBackground(SystemColor.inactiveCaption);
		p1.setOpaque(false);
		p1.setLayout(null);

		f1 = new Font("Calisto MT", Font.BOLD, 18);
		f2 = new Font("Calisto MT", Font.BOLD, 14);
		p2 = new TitlePanel(400, 90);
		// p2.setBackground(new Color(204, 110, 155));
		title = new JLabel(
				"<HTML><BR><BODY><CENTER>\r\nANALYSIS OF USERS BEHAVIOUR IN STRUCTURED<BR/>E-COMMERCE WEBSITES</CENTER></BODY></HTML>");
		title.setBackground(new Color(0, 153, 153));
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Calisto MT", Font.BOLD, 20));
		p2.add(title);

		l3 = new JLabel("E-Commerce! Administration Login");
		l3.setForeground(SystemColor.textHighlight);
		l3.setFont(new Font("Arial", Font.BOLD, 20));
		l3.setBounds(884, 224, 520, 30);
		p1.add(l3);

		l1 = new JLabel("Username");
		l1.setFont(new Font("Calisto MT", Font.ITALIC, 18));
		l1.setBounds(884, 391, 100, 30);
		p1.add(l1);
		tf1 = new JTextField(15);
		tf1.setFont(f1);
		tf1.setBounds(1007, 393, 206, 30);
		p1.add(tf1);

		l2 = new JLabel("Password");
		l2.setFont(new Font("Calisto MT", Font.ITALIC, 18));
		l2.setBounds(884, 448, 100, 30);
		p1.add(l2);
		tf2 = new JPasswordField(15);
		tf2.setFont(f1);		
		tf2.setBounds(1007, 450, 206, 30);
		p1.add(tf2);

		JPanel pan3 = new JPanel();
		b1 = new JButton("Login");
		b1.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		// Image okIcon = new
		// ImageIcon(this.getClass().getResource("/ok-con.png")).getImage();
		// b1.setIcon(new ImageIcon(okIcon));
		b1.setFont(new Font("Arial", Font.ITALIC, 14));
		b1.setBounds(940, 506, 100, 30);
		p1.add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				login();
			}
		});
		b2 = new JButton("Reset");
		b2.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));
		b2.setBackground(SystemColor.activeCaptionBorder);
		b2.setFont(new Font("Arial", Font.ITALIC, 14));
		b2.setBounds(1097, 506, 100, 30);
		p1.add(b2);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tf1.setText("");
				tf2.setText("");
			}
		});

		getContentPane().add(p1, BorderLayout.CENTER);

		textField = new JTextField();
		textField.setSelectedTextColor(Color.BLACK);
		textField.setDisabledTextColor(Color.GRAY);
		textField.setForeground(Color.WHITE);
		textField.setOpaque(false);
		textField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(180, 180, 180),
				SystemColor.activeCaptionBorder, SystemColor.activeCaptionBorder, SystemColor.activeCaptionBorder));
		textField.setBounds(664, 335, 674, 255);
		p1.add(textField);
		textField.setColumns(10);

		JLabel Text = new JLabel(
				"<html>Use a valid username and password to gain access to the adminstrator backend.</html>");
		Text.setVerticalAlignment(SwingConstants.TOP);
		Text.setVerticalTextPosition(SwingConstants.BOTTOM);
		Text.setFont(new Font("Arial", Font.PLAIN, 14));
		Text.setBounds(884, 265, 294, 49);
		p1.add(Text);

		JLabel PadlockImage = new JLabel("");
		Image lock = new ImageIcon(this.getClass().getResource("/PadLock.png")).getImage();
		PadlockImage.setIcon(new ImageIcon(lock));
		PadlockImage.setBounds(685, 335, 143, 255);
		p1.add(PadlockImage);

		JLabel dollar = new JLabel("");
		Image dollarImage = new ImageIcon(this.getClass().getResource("/dollar.png")).getImage();
		dollar.setIcon(new ImageIcon(dollarImage));
	 	dollar.setBounds(1319, 31, 150, 145);
		p1.add(dollar);

		JLabel cart = new JLabel("");
		Image cartImage = new ImageIcon(this.getClass().getResource("/cart-icon.png")).getImage();
		cart.setIcon(new ImageIcon(cartImage));
		cart.setBounds(1191, 31, 119, 145);
		p1.add(cart);

		JLabel eCommerceWebsite = new JLabel("");
		Image eCommerceImage = new ImageIcon(this.getClass().getResource("/eCommerce.jpg")).getImage();
		eCommerceWebsite.setIcon(new ImageIcon(eCommerceImage));
		eCommerceWebsite.setBounds(22, 0, 614, 395);
		p1.add(eCommerceWebsite);

		JLabel googleWallet = new JLabel("");
		Image googleImage = new ImageIcon(this.getClass().getResource("/google-wallet.png")).getImage();
		googleWallet.setIcon(new ImageIcon(googleImage));
		googleWallet.setBounds(1007, 31, 171, 134);
		p1.add(googleWallet);

		JLabel admin = new JLabel("");
		Image adminImage = new ImageIcon(this.getClass().getResource("/admin.png")).getImage();
		admin.setIcon(new ImageIcon(adminImage));
		admin.setBounds(773, 202, 108, 153);
		p1.add(admin);

		getContentPane().add(p2, BorderLayout.NORTH);

	}

	public static void main(String a[]) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) d.getHeight();
		int width = (int) d.getWidth();
		Login login = new Login(height, width);
		login.setVisible(true);
		login.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void clear() {
		tf1.setText("");
		tf2.setText("");
	}

	public void login() {
		String user = tf1.getText();
		String pass = tf2.getText();

		if (user == null || user.trim().length() <= 0) {
			JOptionPane.showMessageDialog(this, "Please enter a Username");
			tf1.requestFocus();
			return;
		}
		if (pass == null || pass.trim().length() <= 0) {
			JOptionPane.showMessageDialog(this, "Please enter a Password");
			tf2.requestFocus();
			return;
		}
		boolean flag = false;
		try {
			if (user.equals("admin") && pass.equals("ondemand")) {
				setVisible(false);
				this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Process pr = new Process(this);
				pr.setVisible(true);
				pr.setExtendedState(JFrame.MAXIMIZED_BOTH);

			} else {
				JOptionPane.showMessageDialog(this, "Invalid user");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
