
//javac -cp .;mysql-connector-j-9.3.0.jar acc.java
//java -cp .;mysql-connector-j-9.3.0.jar acc

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class acc extends JFrame implements ActionListener{
//declarations
	JButton s,l;
	JLabel i,p,w,c;
	JTextField id_num;
	JPasswordField pass_val;
	String msg;
	acc(String wel){
		this.msg = wel;
		getContentPane().setBackground(Color.WHITE);
//input
		id_num = new JTextField();
		id_num.setBounds(195,80,200,30);
		id_num.setFont(new Font("Mongolian Baiti",Font.BOLD,20));
		add(id_num);

		pass_val = new JPasswordField();
		pass_val.setBounds(195,160,200,30);
		pass_val.setFont(new Font("Mongolian Baiti",Font.BOLD,20));
		add(pass_val);
//text
		i =new JLabel("Enter ID");
		i.setBounds(195,50,200,30);
		i.setFont(new Font("Mongolian Baiti", Font.BOLD,20));
		i.setForeground(Color.BLACK);
		add(i);

		p =new JLabel("Enter PASSWORD");
		p.setBounds(195,130,300,30);
		p.setFont(new Font("Mongolian Baiti", Font.BOLD,20));
		p.setForeground(Color.BLACK);
		add(p);

		w =new JLabel(msg);
		w.setBounds(0, 10, 600, 40);
		w.setFont(new Font("Mongolian Baiti", Font.BOLD,40));
		w.setForeground(Color.RED);
		w.setHorizontalAlignment(SwingConstants.CENTER);
		add(w);		

		c =new JLabel("Don't have account?");
		c.setBounds(220,260,200,30);
		c.setFont(new Font("Mongolian Baiti", Font.PLAIN,20));
		c.setForeground(Color.BLUE);
		add(c);

//buttons
		s = new JButton("SIGN UP");
		s.setBounds(235,290,120,35);
		s.setBackground(new Color(33, 150, 243));       
		s.setForeground(Color.BLACK);
		s.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		s.setFocusPainted(false);  
		s.setBorderPainted(false);
		s.setOpaque(true); 
		s.addActionListener(this);
		add(s);

		l = new JButton("LOGIN");
		l.setBounds(235,210,120,35);
		l.setBackground(new Color(33, 150, 243));       
		l.setForeground(Color.BLACK);
		l.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		l.setFocusPainted(false);  
		l.setBorderPainted(false);
		l.setOpaque(true); 
		l.addActionListener(this);
		add(l);
//frame

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("home page");
		setSize(600,400);
		setLocation(400,200);
		setVisible(true);
	}
	


	public void actionPerformed(ActionEvent ae) {
		try{
        		if (ae.getSource() == l){
            			int id = Integer.parseInt(id_num.getText().trim());
            			String pass = new String(pass_val.getPassword());

//connection
				Class.forName("com.mysql.cj.jdbc.Driver");
           			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","hk117");
            			String query = "SELECT password FROM customers WHERE id = ?";
            			PreparedStatement ps = con.prepareStatement(query);
            			ps.setInt(1, id);
            			ResultSet rs = ps.executeQuery();

	            		if (rs.next()) {
					String ap=rs.getString("password");
					if (ap.equals(pass)){
						setVisible(false);
						new login(String.valueOf(id),"");
					} else {
						setVisible(false);
                				new acc("WRONG PASSWORD!");
           	 			}
		
            				rs.close();
            				ps.close();
            				con.close();
				}else{
					setVisible(false);
					new acc("USER NOT FOUND");
				}
			}else if (ae.getSource() == s){
				setVisible(false);
				new sign();
			}	
		}catch(Exception e){
			System.out.println(e);
		}

    	}
	public static void main(String[] args){
		new acc("WELCOME");			
	}
}
