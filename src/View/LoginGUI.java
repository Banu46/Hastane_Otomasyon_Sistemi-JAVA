package View;



import java.awt.BorderLayout;


import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;
public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doktorTC;
	private JPasswordField fld_doktorPass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastaPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(10, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("hlogo.png")));
		lblNewLabel.setBounds(161, 0, 161, 97);
		w_pane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hastane Yönetim Otomasyon Sistemine Hoş Geldiniz");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
		lblNewLabel_1.setBounds(35, 94, 425, 35);
		w_pane.add(lblNewLabel_1);
		
		JTabbedPane w_tabPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabPane.setBounds(0, 130, 486, 233);
		w_pane.add(w_tabPane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabPane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. Numaranız :");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblNewLabel_1_1.setBounds(10, 11, 176, 35);
		w_hastaLogin.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Şifre:");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblNewLabel_1_2.setBounds(10, 72, 153, 35);
		w_hastaLogin.add(lblNewLabel_1_2);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		fld_hastaTc.setBounds(212, 11, 250, 35);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btn_kayıt = new JButton("Kayıt Ol");
		btn_kayıt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btn_kayıt.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btn_kayıt.setBounds(21, 132, 191, 53);
		w_hastaLogin.add(btn_kayıt);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length()==0 || fld_hastaPass.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					boolean key = true;
					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_hastaTc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setName(rs.getString("name"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Hatalı giriş bilgileri !");
					}
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		btn_hastaLogin.setBounds(250, 132, 191, 53);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(212, 72, 250, 35);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel w_doktorLogin = new JPanel();
		w_doktorLogin.setBackground(new Color(255, 255, 255));
		w_tabPane.addTab("Doktor Giriş", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("T.C. Numaranız :");
		lblNewLabel_1_1_1.setBounds(10, 11, 176, 35);
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		w_doktorLogin.add(lblNewLabel_1_1_1);
		
		fld_doktorTC = new JTextField();
		fld_doktorTC.setBounds(212, 11, 247, 32);
		fld_doktorTC.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		fld_doktorTC.setColumns(10);
		w_doktorLogin.add(fld_doktorTC);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Şifre:");
		lblNewLabel_1_2_1.setBounds(10, 72, 153, 35);
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		w_doktorLogin.add(lblNewLabel_1_2_1);
		
		JButton btn_doktorLogin = new JButton("Giriş Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doktorTC.getText().length()==0 || fld_doktorPass.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection con = conn.connDB();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doktorTC.getText().equals(rs.getString("tcno")) && fld_doktorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("basHekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setName(rs.getString("name"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("doktor")){
									Doctor doctor  = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setName(rs.getString("name"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();

								}
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_doktorLogin.setBounds(97, 135, 281, 59);
		btn_doktorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		w_doktorLogin.add(btn_doktorLogin);
		
		fld_doktorPass = new JPasswordField();
		fld_doktorPass.setBounds(212, 72, 250, 35);
		w_doktorLogin.add(fld_doktorPass);
		
		
		
	}
}
