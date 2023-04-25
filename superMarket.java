package JavaCore;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
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
import java.sql.Timestamp;
import java.time.Instant;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

class superMarketApplication extends Frame {
	ResultSet rs;
	Connection con;
	PreparedStatement ps;
	String query = "";
	Image img;

	public superMarketApplication() {
		Frame mf = new Frame();
		// Label Creation
		Label ll = new Label("Super Market");
		ll.setBounds(600, 40, 200, 40);
		ll.setFont(new Font("Bold", 70, 30));
		// ll.setForeground(Color.LIGHT_GRAY);
		ll.setBackground(Color.lightGray);
		mf.add(ll);

		// Background Image
		ImageIcon background_img = new ImageIcon("D:\\TEST\\Akshaya\\src\\JavaCore\\background1.JPG");
		JLabel background = new JLabel("", background_img, JLabel.CENTER);
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		background.setBounds(0, 0, 1200, 800);
		mf.add(background);

		ImageIcon buttonImg = new ImageIcon("button.jpg");
		Image scaledButtonImg = buttonImg.getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH);
		ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

		// Button Creation
		JButton mb1 = new JButton("OWNER");
		mb1.setIcon(scaledButtonIcon);
		mb1.setBounds(650, 250, 120, 40);
		mf.add(mb1);
		// Button mb1 = new Button("OWNER");
		// mb1.setBounds(400, 250, 100, 40);
		// mb1.setBackground(Color.LIGHT_GRAY);
		// mf.add(mb1);
		mb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Frame f = new Frame();
				// TextArea For Purchase
				TextArea ta = new TextArea();
				ta.setBounds(340, 550, 700, 250);

				// Label Creation
				Label ll = new Label("Super Market");
				ll.setBounds(520, 40, 350, 40);
				ll.setFont(new Font("Bold", 70, 30));
				// ll.setForeground(Color.LIGHT_GRAY);
				ll.setBackground(Color.lightGray);
				f.add(ll);

				// Button Creation
				// Button b1 = new Button("Sales");
				// b1.setBounds(450, 360, 70, 30);
				Button b2 = new Button("Purchase");
				b2.setBounds(540, 270, 70, 30);
				Button b3 = new Button("Master_Entry");
				b3.setBounds(630, 360, 90, 30);
				Button b4 = new Button("Stocks");
				b4.setBounds(740, 270, 70, 30);
				Button b5 = new Button("Reports");
				b5.setBounds(830, 360, 70, 30);

				// On clicking Purchase Button new Frame opens enter the details from who and how much you want to
				// purchase and
				// Click Enter
				// Here parallelly on purchasing updates Stocks and report
				b2.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent ae) {
						// Result set of Vendors list
						query = "Select * from akvendors";
						String y = "";
						try {

							Class.forName("org.postgresql.Driver");
							con = DriverManager.getConnection(
									"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
							Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_UPDATABLE);
							rs = cs.executeQuery(query);
							while (rs.next()) {
								y += rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getInt(4)
										+ " " + rs.getDouble(5) + " " + rs.getDouble(6) + "\n";
							}
							ta.setText(y);
						} catch (Exception exe) {
							System.out.println(exe);
						}

						Frame f1 = new Frame();
						Label l1 = new Label("Enter Vendor_Id");
						l1.setBounds(450, 200, 120, 30);
						l1.setBackground(Color.lightGray);
						TextField tf1 = new TextField("0");
						tf1.setBounds(600, 200, 100, 30);

						Label l2 = new Label("Enter Product_Id");
						l2.setBounds(450, 250, 120, 30);
						l2.setBackground(Color.lightGray);
						TextField tf2 = new TextField("0");
						tf2.setBounds(600, 250, 100, 30);

						Label l3 = new Label("Count");
						l3.setBounds(450, 300, 120, 30);
						l3.setBackground(Color.lightGray);
						TextField tf3 = new TextField("0");
						tf3.setBounds(600, 300, 100, 30);

						// Enter Button
						Button b11 = new Button("Enter");
						b11.setBounds(540, 370, 70, 30);
						b11.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								try {
									int asked = Integer.parseInt(tf3.getText());
									System.out.println("Asked is" + asked);

									Class.forName("org.postgresql.Driver");
									Connection con = DriverManager.getConnection(
											"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
									Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
											ResultSet.CONCUR_UPDATABLE);
									int pid = Integer.parseInt(tf2.getText());
									int vid = Integer.parseInt(tf1.getText());
									String query1 = ""; // to update vendors quantity on purchasing
									String query2 = "";// to update stocks table on purchasing
									String query3 = "";// to update reports table on purchasing
									rs.absolute(0);
									while (rs.next()) {
										if (rs.getInt(1) == vid && rs.getInt(2) == pid) {
											if (rs.getInt(4) > asked) {
												double vGst = rs.getDouble(5);
												double vUp = rs.getDouble(6);
												int x = rs.getInt(4) - asked;
												query1 = "update akvendors set available=" + x + " where prod_id=" + pid
														+ " and Vendor_id=" + vid + "";
												cs.executeUpdate(query1);

												// for Updating Stocks table
												Connection con1 = DriverManager.getConnection(
														"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
												Statement stmt = con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
												ResultSet rs1 = stmt.executeQuery("select * from akStocks");
												int count = 0;
												while (rs1.next()) {
													if (rs1.getInt(1) == vid && rs1.getInt(2) == pid) {
														count = 1;
													}
												}
												if (count == 1)
													query2 = "update akStocks set count=" + (count + asked)
															+ "where pid=" + pid + "and vid=" + vid;
												else
													query2 = "insert into akStocks values(" + pid + "," + vid + ","
															+ asked + ")";
												stmt.executeUpdate(query2);

												// for updating reports Table
												Timestamp ts = Timestamp.from(Instant.now());
												double totalPrice = (asked * vUp) + (asked * vGst);
												query3 = "insert into akReports values(" + pid + "," + vid
														+ ",'Purchase','" + ts + "'," + totalPrice + ")";
												cs.executeUpdate(query3);
												break;
											} else {
												System.out.println("Under-Stock");
												break;
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

				// Stocks Button

				// Frame Settings to be Visible

				// f.add(b1);
				f.add(b2);
				f.add(b3);
				f.add(b4);
				f.add(b5);

				ImageIcon background_img = new ImageIcon("D:\\TEST\\Akshaya\\src\\JavaCore\\background1.JPG");
				JLabel background = new JLabel("", background_img, JLabel.CENTER);
				Image img = background_img.getImage();
				Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
				background_img = new ImageIcon(temp_img);
				background.setBounds(0, 0, 1200, 800);
				f.add(background);

				f.setLayout(null);
				f.setSize(1300, 1300);
				f.setTitle("SuperMarket");
				f.setVisible(true);
				// f.setBackground(Color.lightGray);
				f.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}

				});

			}
		});

		// Customers Site

		JButton mb2 = new JButton("CUSTOMER");
		mb2.setIcon(scaledButtonIcon);
		mb2.setBounds(650, 350, 120, 40);
		mf.add(mb2);
		mb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				// Label Creation
				Label llc = new Label("Super Market");
				llc.setBounds(520, 40, 350, 40);
				llc.setFont(new Font("Bold", 70, 30));
				llc.setBackground(Color.lightGray);

				Frame fc = new Frame();
				fc.add(llc);
				// Button Creation
				Button b2c = new Button("Purchase");
				b2c.setBounds(560, 350, 100, 40);
				b2c.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						Frame fcp = new Frame();
						TextArea ta = new TextArea();
						ta.setBounds(340, 550, 700, 250);
						fcp.add(ta);
						query = "Select akproducts.prod_id,akStocks.vid ,akproducts.prod_name,akproducts.gst,akproducts.unit_price,akStocks.stockCount from akproducts,akStocks where akproducts.prod_id=akStocks.pid";
						String y = "  PRODUCT_ID   |    VENDOR_ID    |   PRODUCT_NAME   |  GST    |  UNIT_PRICE  |  AVAILABILITY   \n ";
						try {

							Class.forName("org.postgresql.Driver");
							con = DriverManager.getConnection(
									"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
							Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
									ResultSet.CONCUR_UPDATABLE);
							rs = cs.executeQuery(query);
							while (rs.next()) {
								y += " " + rs.getInt(1) + "                         | " + rs.getInt(2)
										+ "                             |" + rs.getString(3) + "                |"
										+ rs.getDouble(4) + "                 | " + rs.getDouble(5)
										+ "                        |" + rs.getDouble(6) + "\n";
							}
							ta.setText(y);
						} catch (Exception exe) {
							System.out.println(exe);
						}

						Label l1 = new Label("Enter Vendor_Id");
						l1.setBounds(450, 200, 120, 30);
						l1.setBackground(Color.lightGray);
						TextField tf1 = new TextField("0");
						tf1.setBounds(600, 200, 100, 30);

						Label l2 = new Label("Enter Product_Id");
						l2.setBounds(450, 250, 120, 30);
						l2.setBackground(Color.lightGray);
						TextField tf2 = new TextField("0");
						tf2.setBounds(600, 250, 100, 30);

						Label l3 = new Label("Count");
						l3.setBounds(450, 300, 120, 30);
						l3.setBackground(Color.lightGray);
						TextField tf3 = new TextField("0");
						tf3.setBounds(600, 300, 100, 30);

						// Enter Button
						Button b11 = new Button("Enter");
						b11.setBounds(540, 370, 70, 30);
						b11.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								try {
									int asked = Integer.parseInt(tf3.getText());
									System.out.println("Asked is" + asked);

									Class.forName("org.postgresql.Driver");
									Connection con = DriverManager.getConnection(
											"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
									Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
											ResultSet.CONCUR_UPDATABLE);
									int vid = Integer.parseInt(tf1.getText());
									int pid = Integer.parseInt(tf2.getText());
									String query1 = ""; // to update stocks quantity on purchasing
									String query2 = "";// to update reports table on purchasing
									rs.absolute(0);
									double Gst;
									double Up;
									while (rs.next()) {
										if (rs.getInt(2) == vid && rs.getInt(1) == pid) {
											if (rs.getInt(6) > asked) {
												Gst = rs.getDouble(4);
												Up = rs.getDouble(5);
												int x = rs.getInt(6) - asked;
												query1 = "update akStocks set stockCount=" + x + " where pid=" + pid
														+ " and vid=" + vid + "";
												cs.executeUpdate(query1);

												// for updating reports Table

												Timestamp ts = Timestamp.from(Instant.now());
												double totalPrice = (asked * Up) + (asked * Gst);
												query2 = "insert into akReports values(" + pid + "," + vid
														+ ",'Sales','" + ts + "'," + totalPrice + ")";
												cs.executeUpdate(query2);

												break;
											} else {
												System.out.println("Under-Stock");
												break;
											}
										}
									}

								}

								catch (Exception exe) {

								}

							}
						});

						fcp.add(b11);
						fcp.add(l1);
						fcp.add(tf1);
						fcp.add(l3);
						fcp.add(tf3);
						fcp.add(l2);
						fcp.add(tf2);
						fcp.setLayout(null);
						fcp.setSize(1300, 1300);
						fcp.setTitle("SuperMarket");
						fcp.setVisible(true);
						fcp.setBackground(Color.lightGray);
						fcp.addWindowListener(new WindowAdapter() {
							public void windowClosing(WindowEvent we) {
								System.exit(0);

							}
						});

					}
				});

				fc.setLayout(null);
				fc.setSize(1300, 1300);
				fc.setTitle("SuperMarket");
				fc.setVisible(true);
				fc.add(b2c);
				fc.setBackground(Color.lightGray);
				fc.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						System.exit(0);
					}

				});
			}
		});

		mf.setLayout(null);
		mf.setSize(1200, 800);
		mf.setTitle("SuperMarket");
		mf.setVisible(true);

		mf.add(mb2);
		// mf.setBackground(Color.lightGray);
		mf.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}

		});

	}

}

public class awtSuperMarket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		superMarketApplication sma = new superMarketApplication();
		// sma.paint();
	}

}
