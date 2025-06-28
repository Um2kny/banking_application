
//javac -cp .;mysql-connector-j-9.3.0.jar login.java
//java -cp .;mysql-connector-j-9.3.0.jar login

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;

public class login extends JFrame implements ActionListener{
//declarations
	JButton d,t,b,out;
	JLabel w,i,n,ph,m,r,f;
	JPanel dial;
	JTextField amt;
	JTextArea a;
	String user,feed;
	login(String id,String back){
		this.user = id;
		this.feed = back;
		getContentPane().setBackground(Color.WHITE);

// connnection
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
     			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","hk117");


			String pr = "select * from customers where id = ?";
	      		PreparedStatement p = con1.prepareStatement(pr);
			p.setString(1,user);
			ResultSet rs = p.executeQuery();
			while(rs.next()){
				int you = rs.getInt("id");
				String name =rs.getString("name");
				String address =rs.getString("address");
				String num =rs.getString("phone_number");
				String email =rs.getString("email");
				int bal=rs.getInt("balance");


				i =new JLabel("ID: " + you );
				i.setBounds(10, 60, 280, 30);
				i.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
				i.setForeground(Color.BLACK);
				add(i);	
			
				n =new JLabel("NAME: " + name );
				n.setBounds(10, 100, 280, 30);
				n.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
				n.setForeground(Color.BLACK);
				add(n);	


				a =new JTextArea("ADDRESS: "+ address);
				a.setBounds(10, 140, 280, 60);
				a.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
				a.setForeground(Color.BLACK);
				a.setLineWrap(true);
				a.setWrapStyleWord(true);
				a.setEditable(false);        
				a.setOpaque(false);            
				a.setFocusable(false);          
				add(a);	

				ph =new JLabel("PHONE NUMBER: " + num);
				ph.setBounds(10, 200, 280, 30);
				ph.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
				ph.setForeground(Color.BLACK);
				add(ph);	
		
				m =new JLabel("EMAIL: " + email);
				m.setBounds(10, 240, 280, 30);
				m.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
				m.setForeground(Color.BLACK);
				add(m);	

				r =new JLabel("BALANCE: " + bal);
				r.setBounds(10, 280, 280, 30);
				r.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
				r.setForeground(Color.BLACK);
				add(r);	

			}
			p.close();
			con1.close();
		}catch (Exception e){
			System.out.println(e);
		}
//text
		w =new JLabel("ACCOUNT DETAILS");
		w.setBounds(10, 0, 600, 40);
		w.setFont(new Font("Mongolian Baiti", Font.BOLD,25));
		w.setForeground(Color.BLACK);
		add(w);	

		f =new JLabel(feed);
		f.setBounds(10, 25, 600, 40);
		f.setFont(new Font("Mongolian Baiti", Font.BOLD,20));
		f.setForeground(Color.RED);
		add(f);	

//input
		amt = new JTextField("0");
		amt.setBounds(320,10,250,40);
		amt.setFont(new Font("Mongolian Baiti",Font.BOLD,20));
		amt.setHorizontalAlignment(JTextField.RIGHT);
		amt.setEditable(false);
		add(amt);


//dial
		dial = new JPanel();
		dial.setLayout(new GridLayout(4, 3, 5, 5)); // 4 rows × 3 cols
		dial.setBounds(330, 60, 240, 180); // Position under the amt field
		add(dial);

// Buttons for digits and control
		String[] buttons = {
    			"1", "2", "3",
    			"4", "5", "6",
    			"7", "8", "9",
    			"AC", "0", "←"
		};	
		for (String label : buttons) {
    			b = new JButton(label);
   			b.setFont(new Font("Arial", Font.PLAIN, 18));
    			b.setFocusPainted(false);
    			b.setCursor(new Cursor(Cursor.HAND_CURSOR));
    			b.setForeground(Color.BLACK);
    			dial.add(b);

    			b.addActionListener(e -> {
        			String current = amt.getText().trim();
				switch (label) {
					case "AC":
                			amt.setText("0");
                			break;
            				case "←":
                			if (current.length() > 1)
                    				amt.setText(current.substring(0, current.length() - 1));
                				else
                    				amt.setText("0");
                				break;
            				default: // digits
                			if (current.equals("0"))
                    				amt.setText(label); // replace leading zero
                			else
                    				amt.setText(current + label); // append digit
        			}
    			});
		}
//buttons
		d = new JButton("DEPOSIT");
		d.setBounds(350,260,200,35);
		d.setBackground(new Color(33, 150, 243));       
		d.setForeground(Color.BLACK);
		d.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		d.setFocusPainted(false);  
		d.setBorderPainted(false);
		d.setOpaque(true); 
		d.addActionListener(this);
		add(d);

		t = new JButton("WITHDRAW");
		t.setBounds(350,310,200,35);
		t.setBackground(new Color(33, 150, 243));       
		t.setForeground(Color.BLACK);
		t.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		t.setFocusPainted(false);  
		t.setBorderPainted(false);
		t.setOpaque(true); 
		t.addActionListener(this);
		add(t);


		out = new JButton("LOG OUT");
		out.setBounds(10,310,200,35);
		out.setBackground(Color.RED);       
		out.setForeground(Color.WHITE);
		out.setFont(new Font("Mongolian Baiti",Font.PLAIN,20));
		out.setFocusPainted(false);  
		out.setBorderPainted(false);
		out.setOpaque(true); 
		out.addActionListener(this);
		add(out);
//frame

		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Dashboard");
		setSize(600,400);
		setLocation(400,200);
		setVisible(true);
	}
	

//actions
	public void actionPerformed(ActionEvent ae) {
		try{
        		if (ae.getSource() == d){
				int paisa = Integer.parseInt(amt.getText().trim());
//connection
				Class.forName("com.mysql.cj.jdbc.Driver");
           			Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","hk117");


				PreparedStatement dep = con2.prepareStatement("UPDATE customers SET balance = balance + ? WHERE id = ?");   
				dep.setInt(1, paisa); 
				dep.setString(2, user); 
				dep.executeUpdate();
				dep.close();
				con2.close();

				setVisible(false);
				new login(String.valueOf(user),"DEPOSIT SUCCESSFUL");						
			}else if (ae.getSource() == t){
				int paisa = Integer.parseInt(amt.getText().trim());
//connection
				Class.forName("com.mysql.cj.jdbc.Driver");
           			Connection con3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","hk117");
				PreparedStatement st = con3.prepareStatement("SELECT balance FROM customers WHERE id = ?");
				st.setString(1, user);
				ResultSet rs = st.executeQuery();
				int cb;
				if (rs.next()) {
					cb = rs.getInt("balance");
					if (paisa <= cb){

						PreparedStatement dep = con3.prepareStatement("UPDATE customers SET balance = balance - ? WHERE id = ?");   
						dep.setInt(1, paisa); 
						dep.setString(2, user); 
						dep.executeUpdate();
						dep.close();					
						setVisible(false);
						new login(String.valueOf(user),"WITHDRAW SUCCESSFUL");
					}else{
						setVisible(false);
						new login(String.valueOf(user),"INSUFFICIANT BALANCE");
					}
				}else{
					new login(String.valueOf(user),"WITHDRAWAL FAILED");
				}
				con3.close();

			}else if(ae.getSource()== out){
				setVisible(false);
				new acc("WELCOME");
			}	
		}catch(Exception e){
			System.out.println(e);
		}
		

    	}
	public static void main(String[] args){
		new login("11700","NOTE:");			
	}
}
