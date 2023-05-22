package View;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_Pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JTextField fld_pass;
	private Hasta hasta = new Hasta(); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_Pane = new JPanel();
		w_Pane.setBackground(new Color(255, 255, 255));
		w_Pane.setForeground(new Color(255, 255, 255));
		w_Pane.setToolTipText("");
		w_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_Pane);
		w_Pane.setLayout(null);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 36, 266, 29);
		w_Pane.add(fld_name);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 11, 175, 29);
		w_Pane.add(lblNewLabel_1);
		
		fld_tcno = new JTextField();
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(10, 92, 266, 29);
		w_Pane.add(fld_tcno);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 65, 175, 29);
		w_Pane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Şifre");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 121, 175, 29);
		w_Pane.add(lblNewLabel_1_2);
		
		fld_pass = new JTextField();
		fld_pass.setColumns(10);
		fld_pass.setBounds(10, 155, 266, 35);
		w_Pane.add(fld_pass);
		
		JButton btn_backto = new JButton("Kayıt Ol");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0 || fld_pass.getText().length()==0 || fld_name.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_pass.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login = new LoginGUI();
							login.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
			}
		});
		btn_backto.setBounds(10, 201, 266, 35);
		w_Pane.add(btn_backto);
		
		JButton btn_addDoktor_1 = new JButton("Geri Dön");
		btn_addDoktor_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_addDoktor_1.setBounds(10, 247, 266, 35);
		w_Pane.add(btn_addDoktor_1);
	}
}
