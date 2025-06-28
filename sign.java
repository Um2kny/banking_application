
//javac -cp .;mysql-connector-j-9.3.0.jar sign.java
//java -cp .;mysql-connector-j-9.3.0.jar sign

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class sign extends JFrame implements ActionListener{
//declarations	
	JTextField name,pnum,email;
	JTextArea address;
	JPasswordField password;
	JLabel n,p,e,a,d;
	JButton s,b;
	sign(){
		getContentPane().setBackground(Color.WHITE);
//input
		name = new JTextField();
		name.setBounds(10,40,250,30);
		name.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		name.setBorder(BorderFactory.createCompoundBorder(
   			BorderFactory.createLineBorder(Color.GRAY),
    			BorderFactory.createEmptyBorder(5, 5, 5, 5) 
		));
		add(name);

		address = new JTextArea();
		address.setBounds(10,140,250,120);
		address.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		address.setLineWrap(true);
		address.setWrapStyleWord(true);
		address.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		address.setBorder(BorderFactory.createCompoundBorder(
   			BorderFactory.createLineBorder(Color.GRAY),
    			BorderFactory.createEmptyBorder(5, 5, 5, 5) 
		));
		add(address);

		pnum = new JTextField();
		pnum.setBounds(320,40,250,30);
		pnum.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		pnum.setBorder(BorderFactory.createCompoundBorder(
   			BorderFactory.createLineBorder(Color.GRAY),
    			BorderFactory.createEmptyBorder(5, 5, 5, 5) 
		));
		add(pnum);

		email = new JTextField();
		email.setBounds(320,140,250,30);
		email.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		email.setBorder(BorderFactory.createCompoundBorder(
   			BorderFactory.createLineBorder(Color.GRAY),
    			BorderFactory.createEmptyBorder(5, 5, 5, 5) 
		));
		add(email);


		password = new JPasswordField();
		password.setBounds(320,230,250,30);
		password.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		password.setBorder(BorderFactory.createCompoundBorder(
   			BorderFactory.createLineBorder(Color.GRAY),
    			BorderFactory.createEmptyBorder(5, 5, 5, 5) 
		));
		add(password);
//text
		n =new JLabel("Enter NAME:");
		n.setBounds(10,10,200,30);
		n.setFont(new Font("Mongolian Baiti", Font.PLAIN,20));
		n.setForeground(Color.BLACK);
		add(n);

		a =new JLabel("Enter ADDRESS:");
		a.setBounds(10,100,300,30);
		a.setFont(new Font("Mongolian Baiti", Font.PLAIN,20));
		a.setForeground(Color.BLACK);
		add(a);

		p =new JLabel("Enter PHONE NUMBER:");
		p.setBounds(320,10,400,30);
		p.setFont(new Font("Mongolian Baiti", Font.PLAIN,20));
		p.setForeground(Color.BLACK);
		add(p);

		e =new JLabel("Enter EMAIL ID:");
		e.setBounds(320,100,400,30);
		e.setFont(new Font("Mongolian Baiti", Font.PLAIN,20));
		e.setForeground(Color.BLACK);
		add(e);

		d =new JLabel("Enter PASSWORD:");
		d.setBounds(320,190,400,30);
		d.setFont(new Font("Mongolian Baiti", Font.PLAIN,20));
		d.setForeground(Color.BLACK);
		add(d);

//buttons
		s = new JButton("SUBMIT > ");
		s.setBounds(420,290,150,35);
		s.setBackground(new Color(33, 150, 243));       
		s.setForeground(Color.BLACK);
		s.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		s.setFocusPainted(false);  
		s.setBorderPainted(false);
		s.setOpaque(true); 
		s.addActionListener(this);
		add(s);

		b = new JButton(" < BACK ");
		b.setBounds(10,290,150,35);
		b.setBackground(new Color(33, 150, 243));       
		b.setForeground(Color.BLACK);
		b.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		b.setFocusPainted(false);  
		b.setBorderPainted(false);
		b.setOpaque(true); 
		b.addActionListener(this);
		add(b);


//frame

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Sign up page");
		setSize(600,400);
		setLocation(400,200);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		try{
        		if (ae.getSource() == s){
            			String naam = name.getText().trim();
            			String pata = address.getText().trim();
            			String number = pnum.getText().trim();
            			String mail = email.getText().trim();
            			String pass = new String(password.getPassword());

//connection
				Class.forName("com.mysql.cj.jdbc.Driver");
           			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","hk117");
            			String query = "INSERT INTO customers (name, address, phone_number, email, password) VALUES (?, ?, ?, ?, ?)";
            			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            			ps.setString(1, naam);
            			ps.setString(2, pata);
            			ps.setString(3, number);
            			ps.setString(4, mail);
            			ps.setString(5, pass);
            			int rows = ps.executeUpdate();
				if (rows>0){
					ResultSet keys = ps.getGeneratedKeys();
					if(keys.next()){
						int newid =keys.getInt(1);
						setVisible(false);
						String naya = String.valueOf(newid);
						new acc("SIGNED UP, YOUR ID :" + naya);
					}else{
						setVisible(false);
						new acc("failed to generate key");
					}
				ps.close();
				}else{
					setVisible(false);
					new acc("SIGN UP FAILED");
				}


			}else if (ae.getSource() == b){
				setVisible(false);
				new acc("WELCOME");
			}	
		}catch(Exception e){
			System.out.println(e);
		}

    	}

	public static void main(String[] args){
		new sign();			
	}
}