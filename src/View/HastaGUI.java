package View;

import java.awt.EventQueue;

import Model.Whour;
import Model.Hasta;
import Model.Clinic;
import Model.Appointment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class HastaGUI extends JFrame {

	private JPanel w_Pane;
	private static Hasta hasta = new Hasta();
	private static Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doktorModel;
	private Object[] doktorData = null;
	private JTable tableWhour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {

		doktorModel = new DefaultTableModel();
		Object[] colDoktorName = new Object[2];
		colDoktorName[0] = "ID";
		colDoktorName[1] = "Ad Soyad";
		doktorModel.setColumnIdentifiers(colDoktorName);
		doktorData = new Object[2];

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for(int i=0; i<appoint.getHastalist(hasta.getId()).size(); i++) {
			appointData[0] = appoint.getHastalist(hasta.getId()).get(i).getId();
			appointData[1] = appoint.getHastalist(hasta.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getHastalist(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}

		setTitle("Hastane Sistemi");
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_Pane = new JPanel();
		w_Pane.setBackground(new Color(255, 255, 255));
		w_Pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_Pane);
		w_Pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoşgeldiniz, Sayın " + hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 357, 35);
		w_Pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		btnNewButton.setBounds(642, 13, 84, 33);
		w_Pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 57, 716, 395);
		w_Pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 255, 313);
		w_appointment.add(scrollPane);

		table_doctor = new JTable(doktorModel);
		scrollPane.setViewportView(table_doctor);
		table_doctor.getColumnModel().getColumn(0).setPreferredWidth(5);

		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 3, 175, 29);
		w_appointment.add(lblNewLabel_1);

		JComboBox<Object> select_clinic = new JComboBox<Object>();
		select_clinic.setBackground(new Color(233, 233, 233));
		select_clinic.setBounds(275, 34, 150, 35);
		select_clinic.addItem("--Polikinlik Seç--");
		for (int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < clinic.getClinicDoktorlist(item.getKey()).size(); i++) {
							doktorData[0] = clinic.getClinicDoktorlist(item.getKey()).get(i).getId();
							doktorData[1] = clinic.getClinicDoktorlist(item.getKey()).get(i).getName();
							doktorModel.addRow(doktorData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}

			}
		});
		w_appointment.add(select_clinic);

		JLabel lblNewLabel_1_1 = new JLabel("Polikinlik Adı");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(263, 3, 175, 29);
		w_appointment.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Doktor Seç");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(263, 98, 175, 29);
		w_appointment.add(lblNewLabel_1_1_2);

		JButton btn_selDoctor = new JButton("Seç");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tableWhour.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableWhour.setModel(whourModel);
					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();

				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz!");
				}
			}
		});
		btn_selDoctor.setBackground(new Color(233, 233, 233));
		btn_selDoctor.setBounds(275, 126, 150, 35);
		w_appointment.add(btn_selDoctor);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(444, 43, 255, 313);
		w_appointment.add(w_scrollWhour);

		tableWhour = new JTable(whourModel);
		w_scrollWhour.setViewportView(tableWhour);
		tableWhour.getColumnModel().getColumn(0).setPreferredWidth(5);

		JLabel lblNewLabel_1_2 = new JLabel("Doktor Listesi");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(461, 3, 175, 29);
		w_appointment.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Randevu");
		lblNewLabel_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_1_1_2_1.setBounds(263, 197, 175, 29);
		w_appointment.add(lblNewLabel_1_1_2_1);

		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableWhour.getSelectedRow();
				if (selRow >= 0) {
					String date = tableWhour.getModel().getValueAt(selRow, 1).toString();
						try {
							boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(), selectDoctorName,
									hasta.getName(), date);
							if (control) {
								Helper.showMsg("success");
								hasta.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
							}else {
								Helper.showMsg("error");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
				} else {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz!");
				}
			}
		});
		btn_addAppoint.setBackground(new Color(233, 233, 233));
		btn_addAppoint.setBounds(275, 225, 150, 35);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 11, 691, 345);
		w_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
	}

	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableWhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}

	}
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<appoint.getHastalist(hasta_id).size(); i++) {
			appointData[0] = appoint.getHastalist(hasta_id).get(i).getAppDate();
			appointData[1] = appoint.getHastalist(hasta_id).get(i).getDoctorName();
			appointData[2] = appoint.getHastalist(hasta_id).get(i).getId();
			appointModel.addRow(appointData);
		}

	}
}
