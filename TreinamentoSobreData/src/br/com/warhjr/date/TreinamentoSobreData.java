package br.com.warhjr.date;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TreinamentoSobreData {

	private JFrame frame;
	private JTextField textFieldDateDois;
	private JLabel labelRespDate;
	private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreinamentoSobreData window = new TreinamentoSobreData();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TreinamentoSobreData() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextField textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		
		JButton btnDate = new JButton("ok");
		btnDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GregorianCalendar cal1 = new GregorianCalendar();
				textFieldDate.setText(String.valueOf(formato.format(cal1.getTime())));
			}
		});
		
		textFieldDateDois = new JTextField();
		textFieldDateDois.setColumns(10);
		
		JButton btnDateDois = new JButton("ok");
		btnDateDois.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GregorianCalendar cal2 = new GregorianCalendar();
					if (!textFieldDateDois.getText().equals("")) {
						cal2.add(GregorianCalendar.DATE, Integer.parseInt(textFieldDateDois.getText()));
						labelRespDate.setText(formato.format(cal2.getTime()));
						cal2.clear();
					}
					return;
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.toString());
				}
			}
		});
		
		labelRespDate = new JLabel("New label");
		
		JButton btnNewButton = new JButton("Limpar Campos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDateDois.setText("");
				labelRespDate.setText("");
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textFieldDateDois, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnDateDois))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textFieldDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDate)))
							.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(labelRespDate)
							.addContainerGap(362, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDate))
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldDateDois, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDateDois))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(labelRespDate))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(btnNewButton)))
					.addContainerGap(125, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
