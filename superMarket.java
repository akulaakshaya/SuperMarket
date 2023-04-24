package Akshaya;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class superMarketApplication{
	ResultSet rs;
	Connection con;
	PreparedStatement ps;
	String query="";
	
	public superMarketApplication() {
		Frame f=new Frame();
		//TextArea For Purchase
		TextArea ta = new TextArea();
		ta.setBounds(340, 550, 700, 250);
		
		
		//Label Creation
		Label ll = new Label("Super Market");
		ll.setBounds(520, 40, 350, 40);
		ll.setFont(new Font("Bold", 70, 30));
		ll.setBackground(Color.lightGray);
		f.add(ll);
		
		
		//Button Creation
		Button b1 = new Button("Sales");
		b1.setBounds(450, 360, 70, 30);
		Button b2 = new Button("Purchase");
		b2.setBounds(540, 270, 70, 30);
		Button b3 = new Button("Master_Entry");
		b3.setBounds(630, 360, 90, 30);
		Button b4 = new Button("Stocks");
		b4.setBounds(740, 270, 70, 30);
		Button b5 = new Button("Reports");
		b5.setBounds(830, 360, 70, 30);
		
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				//Result set of VEndors list
				query="Select * from vendors";
				String y="";
				try {
					
					Class.forName("org.postgresql.Driver");
					con = DriverManager.getConnection(
							"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
					Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = cs.executeQuery(query);
					while (rs.next()) {
						y += rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3) + " " + rs.getInt(4) + "\n";
					}
					ta.setText(y);
				} catch (Exception exe) {
					System.out.println(exe);
				}
				
				
				Frame f1=new Frame();
				Label l1=new Label("Enter Vendor_Id");
				l1.setBounds(450,200,120,30);
				l1.setBackground(Color.lightGray);
				TextField tf1=new TextField("0");
				tf1.setBounds(600,200,100,30);
				
				Label l2=new Label("Enter Product_Id");
				l2.setBounds(450,250,120,30);
				l2.setBackground(Color.lightGray);
				TextField tf2=new TextField("0");
				tf2.setBounds(600,250,100,30);
				
				
				Label l3=new Label("Count");
				l3.setBounds(450,300,120,30);
				l3.setBackground(Color.lightGray);
				TextField tf3=new TextField("0");
				tf3.setBounds(600,300,100,30);
				
				

				
				//Enter Button
				Button b11=new Button("Enter");
				b11.setBounds(540,370,70,30);
				b11.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						try {
							String y = " ";
							int asked=Integer.parseInt(tf3.getText());
							System.out.println("Asked is"+ asked);	
						
							Class.forName("org.postgresql.Driver");
							Connection con = DriverManager.getConnection(
									"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
							Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
							int pid=Integer.parseInt(tf2.getText());
							int vid=Integer.parseInt(tf1.getText());
							String query1="";
							//query1="Select available from vendors where prod_id="+pid+" and Vendor_id="+vid+"";
//							System.out.println(pid);
//							System.out.println(pid);
//							
//							 
//							rs=cs.executeQuery(query1);
//							System.out.println(cs.executeQuery(query1));	
//							if(rs==asked) {	
//							}
							rs.absolute(0);
							while(rs.next()) {
								if(rs.getInt(1)==vid && rs.getInt(3)==pid) {
									if(rs.getInt(4)>asked) {
										int x=rs.getInt(4)-asked;
										query1="update vendors set available="+x+" where prod_id="+pid+" and Vendor_id="+vid+"";
										cs.executeUpdate(query1);
										break;
									}
									else {
										System.out.println("Under-Stock");
									}
								}
							}
							
						} catch (Exception exe) {
							
						}
						
						
				
					}
				});
				
				
				f1.add(b11);
				f1.add(l1);
				f1.add(tf1);
				f1.add(l3);
				f1.add(tf3);
				f1.add(l2);
				f1.add(tf2);
				f1.add(ta);
				
				
				
				
				
				
				
				
				
				
				f1.setLayout(null);
				f1.setSize(1300, 1300);
				f1.setTitle("SuperMarket");
				f1.setVisible(true);
				f1.setBackground(Color.lightGray);
				f1.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}

				});
			}
		});
		
		
		
		//Frame Settings to be Visible 
		f.setLayout(null);
		f.setSize(1300, 1300);
		f.setTitle("SuperMarket");
		f.setVisible(true);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.setBackground(Color.lightGray);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

		});
	}
}
public class superMarket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		superMarketApplication sma=new superMarketApplication();
	}

}
