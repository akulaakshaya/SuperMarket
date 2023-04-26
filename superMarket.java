package Akshaya;


	

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
			ImageIcon background_img = new ImageIcon("C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\smLogin.jpg");
			JLabel background = new JLabel("", background_img, JLabel.CENTER);
			Image img = background_img.getImage();
			Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
			background_img = new ImageIcon(temp_img);
			background.setBounds(0, 0, 960, 470);
			mf.add(background);

			ImageIcon buttonImg = new ImageIcon("button.jpg");
			Image scaledButtonImg = buttonImg.getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH);
			ImageIcon scaledButtonIcon = new ImageIcon(scaledButtonImg);

			// Button Creation
			JButton mb1 = new JButton("OWNER");
			mb1.setIcon(scaledButtonIcon);
			mb1.setBounds(650, 200, 120, 40);
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
					ta.setBounds(50, 100, 250, 250);

					// Label Creation
					Label ll = new Label("Super Market");
					ll.setBounds(370, 40, 200, 40);
					ll.setFont(new Font("Bold", 70, 30));
					// ll.setForeground(Color.LIGHT_GRAY);
					ll.setBackground(Color.lightGray);
					f.add(ll);

					// Button Creation
					// Button b1 = new Button("Sales");
					// b1.setBounds(450, 360, 70, 30);
					JButton b2 = new JButton("Purchase");
					b2.setBounds(400, 110, 150, 50);
					JButton b3 = new JButton("Master_Entry");
					b3.setBounds(400, 190, 150, 50);
//					JButton b4 = new JButton("Stocks");
//					b4.setBounds(400, 270, 150, 50);
					JButton b5 = new JButton("Reports");
					b5.setBounds(400, 270, 150, 50);
					
					
					// MasterEntry Table
					b3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							Frame me = new Frame();
							
							// Label Creation
							Label ll = new Label("Super Market");
							ll.setBounds(520, 40, 350, 40);
							ll.setFont(new Font("Bold", 70, 30));
							// ll.setForeground(Color.LIGHT_GRAY);
							ll.setBackground(Color.lightGray);
							me.add(ll);

							TextArea tar = new TextArea();
							tar.setBounds(50, 100, 400, 150);
							me.add(tar);
							TextArea tar1 = new TextArea();
							tar1.setBounds(50, 290, 400, 150);
							me.add(tar1);
							TextArea tar2 = new TextArea();
							tar2.setBounds(530, 170, 400, 150);
							me.add(tar2);

							query = "select * from akproducts ";
							String query1 = "select * from akStocks ";
							String query2 = "select * from akVendors ";
							String y = "PRODUCTS TABLE \n\n\n"
									+ "PRODUCT_ID    PRODUCT_NAME    CATEGORY    GST      UNIT_PRICE  \n";
							try {

								Class.forName("org.postgresql.Driver");
								con = DriverManager.getConnection(
										"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
								Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_UPDATABLE);
								// PRoducts TAble setting
								rs = cs.executeQuery(query);
								rs.absolute(0);
								while (rs.next()) {
									y += rs.getInt(1) + "                           " + rs.getString(2)
											+ "                  " + rs.getString(3) + "             " + rs.getDouble(4)
											+ "               " + rs.getDouble(5) + "\n";
								}
								tar.setText(y);

								// Stocks Table Setting
								y = "STOCKS TABLE \n\n\n" + "PRODUCT_ID    VENDOR_ID   STOCK_COUNT  \n";
								rs = cs.executeQuery(query1);
								rs.absolute(0);
								while (rs.next())  {
									y += rs.getInt(1) + "                             " + rs.getInt(2)
											+ "                      " + rs.getInt(3) + " \n";
								}
								tar1.setText(y);

								// Vendors Table Setting
								y = "VENDORS TABLE \n\n\n"
										+ "PRODUCT_ID    VENDOR_ID       NAME       AVAILABLE         GST       UNIT_PRICE  \n";
								rs = cs.executeQuery(query2);
								rs.absolute(0);
								while (rs.next()) {
									y += rs.getInt(1) + "                           " + rs.getInt(2)
											+ "                           " + rs.getString(3) + "           " + rs.getInt(4)
											+ "            " + rs.getDouble(5) + "                 " + rs.getDouble(6)
											+ "\n";
								}
								tar2.setText(y);
							} catch (Exception exe) {
								System.out.println(exe);
							}

							// Background Image
							ImageIcon background_img = new ImageIcon("C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\masterEntryFrame.jpg");
							JLabel background = new JLabel("", background_img, JLabel.CENTER);
							Image img = background_img.getImage();
							Image temp_img = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
							background_img = new ImageIcon(temp_img);
							background.setBounds(0, 0, 960, 470);
							me.add(background);
							me.setLayout(null);
							me.setSize(960, 470);
							me.setTitle("SuperMarket");
							me.setVisible(true);
							me.setBackground(Color.lightGray);
							me.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) {
									me.dispose();

								}
							});
						}
					});
					
					
					// Reports BUtton Functionality in OWNER
					b5.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							Frame fr = new Frame();

							// Label Creation
							Label ll = new Label("Super Market");
							ll.setBounds(350, 40, 270, 40);
							ll.setFont(new Font("Bold", 70, 30));
							// ll.setForeground(Color.LIGHT_GRAY);
							ll.setBackground(Color.lightGray);
							fr.add(ll);

							// Buttons In Reports
							JButton b1 = new JButton("Items With Reorder Level");
							b1.setBounds(350, 100, 230, 50);
							JButton b2 = new JButton("Daily Sales");
							b2.setBounds(350, 170, 230, 50);
							JButton b3 = new JButton("Sales Between Two dates");
							b3.setBounds(350, 240, 230, 50);
							JButton b4 = new JButton("GST Report");
							b4.setBounds(350, 310, 230, 50);
							JButton b5 = new JButton("Purchases Between Two dates");
							b5.setBounds(350, 380, 230, 50);

							// Items With Reorder Level button Funtionality
							b1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									Frame fr1 = new Frame();

									// Label Creation
									Label ll = new Label("Super Market");
									ll.setBounds(350, 40, 270, 40);
									ll.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll.setBackground(Color.lightGray);
									fr1.add(ll);

									TextArea t1 = new TextArea();
									t1.setBounds(200, 100, 550, 350);
									fr1.add(t1);
									// Result set of Reports
									query = "select * from akStocks where stockCount<5";
									String y = "ITEMS WHICH ARE LESS THAN COUNT 5 \n\n\n"
											+ "PRODUCT_ID  |  VENDOR_ID   |  AVAILABLE  \n";
									try {

										Class.forName("org.postgresql.Driver");
										con = DriverManager.getConnection(
												"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
										Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
												ResultSet.CONCUR_UPDATABLE);
										rs = cs.executeQuery(query);
										while (rs.next()) {
											y += rs.getInt(1) + "                          | " + rs.getInt(2)
													+ "                         | " + rs.getInt(3) + "\n";
										}
										t1.setText(y);
									} catch (Exception exe) {
										System.out.println(exe);
									}

									// Background Image
									ImageIcon background_img = new ImageIcon(
											"C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\reportsFrame.jpg");
									JLabel background = new JLabel("", background_img, JLabel.CENTER);
									Image img = background_img.getImage();
									Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
									background_img = new ImageIcon(temp_img);
									background.setBounds(0, 0, 960, 470);
									fr1.add(background);
									fr1.setLayout(null);
									fr1.setSize(960, 470);
									fr1.setTitle("SuperMarket");
									fr1.setVisible(true);
									fr1.setBackground(Color.lightGray);
									fr1.addWindowListener(new WindowAdapter() {
										public void windowClosing(WindowEvent we) {
											fr1.dispose();

										}
									});

								}
							});

							// Daily Sales button Funtionality
							b2.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									Frame fr1 = new Frame();

									// Label Creation
									Label ll = new Label("Super Market");
									ll.setBounds(350, 40, 270, 40);
									ll.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll.setBackground(Color.lightGray);
									fr1.add(ll);

									TextArea t1 = new TextArea();
									t1.setBounds(200, 100, 550, 350);
									fr1.add(t1);
									// Result set of Reports
									query = "Select * from akReports where  extract(year from s_date)=extract(year from current_date)\r\n"
											+ "and extract(month from s_date)=extract(month from current_date)\r\n"
											+ "and extract(day from s_date)=extract(day from current_date);";
									String y = "  PRODUCT_ID    |  VENDOR_ID     |      TYPE      | QUANTITY   \n";
									try {

										Class.forName("org.postgresql.Driver");
										con = DriverManager.getConnection(
												"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
										Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
												ResultSet.CONCUR_UPDATABLE);
										rs = cs.executeQuery(query);
										while (rs.next()) {
											y += rs.getInt(1) + "                              |    " + rs.getInt(2)
													+ "          |       " + rs.getString(3) + "   |   " + rs.getDouble(5)
													+ "\n";
										}
										t1.setText(y);
									} catch (Exception exe) {
										System.out.println(exe);
									}

									// Background Image
									ImageIcon background_img = new ImageIcon(
											"C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\reportsFrame.jpg");
									JLabel background = new JLabel("", background_img, JLabel.CENTER);
									Image img = background_img.getImage();
									Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
									background_img = new ImageIcon(temp_img);
									background.setBounds(0, 0, 960, 470);

									fr1.add(background);
									fr1.setLayout(null);
									fr1.setSize(960, 470);
									fr1.setTitle("SuperMarket");
									fr1.setVisible(true);
									fr1.setBackground(Color.lightGray);
									fr1.addWindowListener(new WindowAdapter() {
										public void windowClosing(WindowEvent we) {
											fr1.dispose();

										}
									});
								}
							});

							// Sales Between Two dates button Funtionality
							b3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {

									Frame fr1 = new Frame();

									// Label Creation
									Label ll = new Label("Date-1");
									ll.setBounds(70, 120, 70, 30);
									// ll.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll.setBackground(Color.lightGray);
									fr1.add(ll);

									TextField trx1 = new TextField();
									trx1.setBounds(170, 120, 70, 30);
									fr1.add(trx1);

									// two Labels Two Buttons
									Label ll1 = new Label("Date-2");
									ll1.setBounds(70, 160, 70, 30);
									// ll1.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll1.setBackground(Color.lightGray);
									fr1.add(ll1);
									TextField trx2 = new TextField();
									trx2.setBounds(170, 160, 70, 30);
									fr1.add(trx2);

									Label ll2 = new Label("Super Market");
									ll2.setBounds(350, 40, 270, 40);
									ll2.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll2.setBackground(Color.lightGray);
									fr1.add(ll2);

									JButton rb = new JButton("ENTER");
									rb.setBounds(130, 200, 50, 30);
									fr1.add(rb);
									TextArea t1x = new TextArea();
									t1x.setBounds(280, 100, 600, 340);
									fr1.add(t1x);
									rb.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent ae) {
											String a = trx1.getText();
											String b = trx2.getText();
											String[] arrOfStr = a.split("/");
											int p = Integer.parseInt(arrOfStr[0]);
											int q = Integer.parseInt(arrOfStr[1]);
											int r = Integer.parseInt(arrOfStr[2]);
											String[] arrOfStr1 = b.split("/");
											int x = Integer.parseInt(arrOfStr1[0]);
											int y = Integer.parseInt(arrOfStr1[1]);
											int z = Integer.parseInt(arrOfStr1[2]);

											query = "select * from akReports where S_type='Sales' and extract(year from S_date)>="
													+ r + "  and extract(day from S_date)>=" + p
													+ " and extract(month from S_date)>=" + q + " \r\n"
													+ "and extract(year from S_date)<=" + z
													+ "  and extract(day from S_date)<=" + x
													+ " and extract(month from S_date)<=" + y + ";";
											// Result set of Reports

											String yx = "  PRODUCT_ID    |  VENDOR_ID     |      TYPE    |      PRICE     |      QUANTITY   \n";
											try {

												Class.forName("org.postgresql.Driver");
												con = DriverManager.getConnection(
														"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
												Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
												rs = cs.executeQuery(query);
												while (rs.next()) {
													yx += rs.getInt(1) + "                              |    "
															+ rs.getInt(2) + "                 |       " + rs.getString(3)
															+ "                    |   " + rs.getDouble(5)
															+ "               |     " + rs.getInt(6) + "\n";
												}
												t1x.setText(yx);
											} catch (Exception exe) {
												System.out.println(exe);
											}

										}
									});

									// Background Image
									ImageIcon background_img = new ImageIcon(
											"C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\reportsFrame.jpg");
									JLabel background = new JLabel("", background_img, JLabel.CENTER);
									Image img = background_img.getImage();
									Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
									background_img = new ImageIcon(temp_img);
									background.setBounds(0, 0, 960, 470);

									fr1.add(background);
									fr1.setLayout(null);
									fr1.setSize(960, 470);
									fr1.setTitle("SuperMarket");
									fr1.setVisible(true);
									fr1.setBackground(Color.lightGray);
									fr1.addWindowListener(new WindowAdapter() {
										public void windowClosing(WindowEvent we) {
											fr1.dispose();

										}
									});
								}
							});

							// GST Report button Funtionality
							b4.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									Frame fr1 = new Frame();

									// Label Creation
									Label ll = new Label("Super Market");
									ll.setBounds(350, 40, 250, 40);
									ll.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll.setBackground(Color.lightGray);
									fr1.add(ll);
									String purchaseGST = "\r\n"
											+ "select sum(r.quantity*v.v_gst) from akvendors v,akReports r where r.S_type='Purchase'"
											+ " and v.prod_id=r.pid and v.Vendor_id=r.vid group by r.S_type='Purchase';";
									String salesGST = "select sum(r.quantity*v.gst) from akproducts v,akReports r where r.S_type='Sales'\r\n"
											+ "and v.prod_id=r.pid  group by r.S_type='Sales';\r\n" + "\r\n" + "";

									
									Label llx1 = new Label("Sales GST Report:");
									llx1.setBounds(340, 200, 150, 40);
									fr1.add(llx1);
									TextField tax1 = new TextField();
									tax1.setBounds(520, 200, 90, 40);
									Label llx2 = new Label("Purchase GST Report:");
									llx2.setBounds(340, 260, 150, 40);
									fr1.add(llx2);
									TextField tax2 = new TextField();
									tax2.setBounds(520, 260, 90, 40);

									String yxt = "";
									try {

										Class.forName("org.postgresql.Driver");
										con = DriverManager.getConnection(
												"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
										Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
												ResultSet.CONCUR_UPDATABLE);
										rs = cs.executeQuery(purchaseGST);
										rs.absolute(1);
										tax2.setText(rs.getString(1));
										rs = cs.executeQuery(salesGST);
										rs.absolute(1);
										tax1.setText(rs.getString(1));
									} catch (Exception exe) {
										System.out.println(exe);
									}

									fr1.add(tax2);
									fr1.add(tax1);

									// Background Image
									ImageIcon background_img = new ImageIcon(
											"C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\reportsFrame.jpg");
									JLabel background = new JLabel("", background_img, JLabel.CENTER);
									Image img = background_img.getImage();
									Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
									background_img = new ImageIcon(temp_img);
									background.setBounds(0, 0, 960, 470);
									fr1.add(background);
									fr1.setLayout(null);
									fr1.setSize(960, 470);
									fr1.setTitle("SuperMarket");
									fr1.setVisible(true);
									fr1.setBackground(Color.lightGray);
									fr1.addWindowListener(new WindowAdapter() {
										public void windowClosing(WindowEvent we) {
											fr1.dispose();

										}
									});
								}
							});

							// Purchases Between Two dates button Functionality
							b5.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									Frame fr1 = new Frame();

									
									
									// Label Creation
									Label ll = new Label("Date-1");
									ll.setBounds(70, 120, 70, 30);
									// ll.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll.setBackground(Color.lightGray);
									fr1.add(ll);

									TextField trx1 = new TextField();
									trx1.setBounds(170, 120, 70, 30);
									fr1.add(trx1);

									// two Labels Two Buttons
									Label ll1 = new Label("Date-2");
									ll1.setBounds(70, 160, 70, 30);
									// ll1.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll1.setBackground(Color.lightGray);
									fr1.add(ll1);
									TextField trx2 = new TextField();
									trx2.setBounds(170, 160, 70, 30);
									fr1.add(trx2);

									Label ll2 = new Label("Super Market");
									ll2.setBounds(350, 40, 270, 40);
									ll2.setFont(new Font("Bold", 70, 30));
									// ll.setForeground(Color.LIGHT_GRAY);
									ll2.setBackground(Color.lightGray);
									fr1.add(ll2);

									JButton rb = new JButton("ENTER");
									rb.setBounds(130, 200, 50, 30);
									fr1.add(rb);
									TextArea t1x = new TextArea();
									t1x.setBounds(280, 100, 600, 340);
									fr1.add(t1x);
									
									
									rb.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent ae) {
											String a = trx1.getText();
											String b = trx2.getText();
											String[] arrOfStr = a.split("/");
											int p = Integer.parseInt(arrOfStr[0]);
											int q = Integer.parseInt(arrOfStr[1]);
											int r = Integer.parseInt(arrOfStr[2]);
											String[] arrOfStr1 = b.split("/");
											int x = Integer.parseInt(arrOfStr1[0]);
											int y = Integer.parseInt(arrOfStr1[1]);
											int z = Integer.parseInt(arrOfStr1[2]);

											query = "select * from akReports where S_type='Purchase' and extract(year from S_date)>="
													+ r + "  and extract(day from S_date)>=" + p
													+ " and extract(month from S_date)>=" + q + " \r\n"
													+ "and extract(year from S_date)<=" + z
													+ "  and extract(day from S_date)<=" + x
													+ " and extract(month from S_date)<=" + y + ";";
											// Result set of Reports

											String yx = "  PRODUCT_ID    |  VENDOR_ID     |      TYPE    |      PRICE     |      QUANTITY   \n";
											try {

												Class.forName("org.postgresql.Driver");
												con = DriverManager.getConnection(
														"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
												Statement cs = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
												rs = cs.executeQuery(query);
												while (rs.next()) {
													yx += rs.getInt(1) + "                              |    "
															+ rs.getInt(2) + "                 |       " + rs.getString(3)
															+ "                    |   " + rs.getDouble(5)
															+ "               |     " + rs.getInt(6) + "\n";
												}
												t1x.setText(yx);
											} catch (Exception exe) {
												System.out.println(exe);
											}

										}
									});

									// Background Image
									ImageIcon background_img = new ImageIcon(
											"C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\reportsFrame.jpg");
									JLabel background = new JLabel("", background_img, JLabel.CENTER);
									Image img = background_img.getImage();
									Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
									background_img = new ImageIcon(temp_img);
									background.setBounds(0, 0, 960, 470);

									fr1.add(background);
									fr1.setLayout(null);
									fr1.setSize(960, 470);
									fr1.setTitle("SuperMarket");
									fr1.setVisible(true);
									fr1.setBackground(Color.lightGray);
									fr1.addWindowListener(new WindowAdapter() {
										public void windowClosing(WindowEvent we) {
											fr1.dispose();

										}
									});
								}
							});

							fr.add(b1);
							fr.add(b2);
							fr.add(b3);
							fr.add(b4);
							fr.add(b5);
							// Background Image
							ImageIcon background_img = new ImageIcon("C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\reportsFrame.jpg");
							JLabel background = new JLabel("", background_img, JLabel.CENTER);
							Image img = background_img.getImage();
							Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
							background_img = new ImageIcon(temp_img);
							background.setBounds(0, 0, 960, 470);
							fr.add(background);
							fr.setLayout(null);
							fr.setSize(960, 470);
							fr.setTitle("SuperMarket");
							fr.setVisible(true);
							fr.setBackground(Color.lightGray);
							fr.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) {
									fr.dispose();

								}
							});

						}
					});
					
					
					
					
					
					
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
										"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
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
							
							// Label Creation
							Label ll = new Label("Super Market");
							ll.setBounds(370, 40, 200, 40);
							ll.setFont(new Font("Bold", 70, 30));
							// ll.setForeground(Color.LIGHT_GRAY);
							ll.setBackground(Color.lightGray);
							f1.add(ll);
							
							Label l1 = new Label("Enter Vendor_Id");
							l1.setBounds(350, 150, 120, 30);
							l1.setBackground(Color.LIGHT_GRAY);
							TextField tf1 = new TextField("0");
							tf1.setBounds(500, 150, 100, 30);

							Label l2 = new Label("Enter Product_Id");
							l2.setBounds(350, 200, 120, 30);
							l2.setBackground(Color.LIGHT_GRAY);
							TextField tf2 = new TextField("0");
							tf2.setBounds(500, 200, 100, 30);

							Label l3 = new Label("Count");
							l3.setBounds(350, 250, 120, 30);
							l3.setBackground(Color.LIGHT_GRAY);
							TextField tf3 = new TextField("0");
							tf3.setBounds(500, 250, 100, 30);

							// Enter Button
							JButton b11 = new JButton("BUY");
							b11.setBounds(440, 320, 70, 30);
							b11.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									try {
										int asked = Integer.parseInt(tf3.getText());
										System.out.println("Asked is" + asked);

										Class.forName("org.postgresql.Driver");
										Connection con = DriverManager.getConnection(
												"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
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
															"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
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
															+ ",'Purchase','" + ts + "'," + totalPrice + ","+asked+")";
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
							
							
							ImageIcon background_img = new ImageIcon("C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\adminPurchase.jpg");
							JLabel background = new JLabel("", background_img, JLabel.CENTER);
							Image img = background_img.getImage();
							Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
							background_img = new ImageIcon(temp_img);
							background.setBounds(0, 0, 960, 470);
							f1.add(background);
							f1.setLayout(null);
							f1.setSize(960, 470);
							f1.setTitle("SuperMarket");
							f1.setVisible(true);
							//f1.setBackground(Color.lightGray);
							f1.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) {
									f1.dispose();

								}
							});

						}
					});

					// Stocks Button

					// Frame Settings to be Visible

					// f.add(b1);
					f.add(b2);
					f.add(b3);
					//f.add(b4);
					f.add(b5);

					ImageIcon background_img = new ImageIcon("C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\ownerFirstPage.jpg");
					JLabel background = new JLabel("", background_img, JLabel.CENTER);
					Image img = background_img.getImage();
					Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
					background_img = new ImageIcon(temp_img);
					background.setBounds(0, 0, 960, 470);
					f.add(background);
					
					f.setLayout(null);
					f.setSize(960, 470);
					f.setTitle("SuperMarket");
					f.setVisible(true);
					// f.setBackground(Color.lightGray);
					f.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							f.dispose();
						}

					});

				}
			});

			// Customers Site

			JButton mb2 = new JButton("CUSTOMER");
			mb2.setIcon(scaledButtonIcon);
			mb2.setBounds(650, 300, 120, 40);
			mf.add(mb2);
			mb2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					// Label Creation
					Label llc = new Label("Super Market");
					llc.setBounds(420,40,200,40);
					llc.setFont(new Font("Bold", 70, 30));
					llc.setBackground(Color.lightGray);

					Frame fc = new Frame();
					fc.add(llc);
					// Button Creation
					JButton b2c = new JButton("Purchase");
					b2c.setBounds(470, 230, 100, 40);
					b2c.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							Frame fcp = new Frame();
							TextArea ta = new TextArea();
							ta.setBounds(50, 100, 400, 250);
							fcp.add(ta);
							query = "Select akproducts.prod_id,akStocks.vid ,akproducts.prod_name,akproducts.gst,akproducts.unit_price,akStocks.stockCount from akproducts,akStocks where akproducts.prod_id=akStocks.pid";
							String y = "  PRODUCT_ID   |    VENDOR_ID    |   PRODUCT_NAME   |  GST    |  UNIT_PRICE  |  AVAILABILITY   \n ";
							try {

								Class.forName("org.postgresql.Driver");
								con = DriverManager.getConnection(
										"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
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

							
							
							
							// Label Creation
							Label ll = new Label("Super Market");
							ll.setBounds(350, 40, 270, 40);
							ll.setFont(new Font("Bold", 70, 30));
							// ll.setForeground(Color.LIGHT_GRAY);
							ll.setBackground(Color.lightGray);
							fcp.add(ll);
							
							Label l1 = new Label("Enter Vendor_Id");
							l1.setBounds(540, 150, 120, 30);
							l1.setBackground(Color.pink);
							TextField tf1 = new TextField("0");
							tf1.setBounds(690, 150, 100, 30);

							Label l2 = new Label("Enter Product_Id");
							l2.setBounds(540, 200, 120, 30);
							l2.setBackground(Color.pink);
							TextField tf2 = new TextField("0");
							tf2.setBounds(690, 200, 100, 30);

							Label l3 = new Label("Count");
							l3.setBounds(540, 250, 120, 30);
							l3.setBackground(Color.pink);
							TextField tf3 = new TextField("0");
							tf3.setBounds(690, 250, 100, 30);

							// Enter Button
							JButton b11 = new JButton("BUY");
							b11.setBounds(620, 320, 70, 30);
							
							
							b11.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent ae) {
									try {
										int asked = Integer.parseInt(tf3.getText());
										System.out.println("Asked is" + asked);

										Class.forName("org.postgresql.Driver");
										Connection con = DriverManager.getConnection(
												"jdbc:postgresql://LocalHost:5432/postgres?user=postgres&password=Akshaya@2001");
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
															+ ",'Sales','" + ts + "'," + totalPrice + ","+asked+")";
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
							ImageIcon background_img = new ImageIcon("C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\customerPurchase.jpg");
							JLabel background = new JLabel("", background_img, JLabel.CENTER);
							Image img = background_img.getImage();
							Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
							background_img = new ImageIcon(temp_img);
							background.setBounds(0, 0, 960, 470);
							fcp.add(background);
							fcp.setLayout(null);
							fcp.setSize(960, 470);
							fcp.setTitle("SuperMarket");
							fcp.setVisible(true);
							fcp.setBackground(Color.lightGray);
							fcp.addWindowListener(new WindowAdapter() {
								public void windowClosing(WindowEvent we) {
									fcp.dispose();

								}
							});

						}
					});
					fc.add(b2c);
					ImageIcon background_img = new ImageIcon("C:\\Users\\akshaya varma\\Desktop\\tcw java\\Akshaya\\src\\Akshaya\\adminPurchase.jpg");
					JLabel background = new JLabel("", background_img, JLabel.CENTER);
					Image img = background_img.getImage();
					Image temp_img = img.getScaledInstance(1024, 683, Image.SCALE_SMOOTH);
					background_img = new ImageIcon(temp_img);
					background.setBounds(0, 0, 960, 470);
					fc.add(background);
					fc.setLayout(null);
					fc.setSize(960, 470);
					fc.setTitle("SuperMarket");
					fc.setVisible(true);
					
					fc.setBackground(Color.lightGray);
					fc.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent we) {
							fc.dispose();
							//System.exit(0);
						}

					});
				}
			});

			mf.setLayout(null);
			mf.setSize(960, 470);
			mf.setTitle("SuperMarket");
			mf.setVisible(true);

			mf.add(mb2);
			//mf.setBackground(Color.lightGray);
			mf.addWindowListener(new WindowAdapter() {

				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}

			});

		}

	}

	public class superMarket {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			superMarketApplication sma = new superMarketApplication();
			// sma.paint();
		}

	}
