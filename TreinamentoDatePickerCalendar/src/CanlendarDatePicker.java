

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import org.jdatepicker.JDatePicker;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import org.jdatepicker.JDatePanel;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanlendarDatePicker extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private JDatePicker datePicker;
	private Component datePanelPlacar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CanlendarDatePicker dialog = new CanlendarDatePicker();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CanlendarDatePicker() {
		setBounds(100, 100, 607, 393);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();

		JButton btnTransferir = new JButton("Transferir");
		JLabel LabelData = new JLabel("xxxxxxxx");
		LabelData.setFont(new Font("Tahoma", Font.BOLD, 12));
		LabelData.setForeground(Color.BLUE);

		JDatePicker datePicker_1 = new JDatePicker();

		JCheckBox CheckBoxDate = new JCheckBox("Habilitar DatePanel");
		JPanel panelDatePlacar = new JPanel();
		panelDatePlacar.setVisible(false);
		JDatePanel datePanelMostra = new JDatePanel();
		CheckBoxDate.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (panelDatePlacar.isVisible() == true) {
					panelDatePlacar.setVisible(false);
				} else {
					panelDatePlacar.setVisible(true);
				}
			}
		});
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (panelDatePlacar.isVisible() == false) {
					if (datePicker_1.getFormattedTextField().getText().length() > 0) {
						SimpleDateFormat dns = new SimpleDateFormat("dd/MM/yyyy");
						Calendar selectedValue = (Calendar) datePicker_1.getModel().getValue();
						Date selectedDate = selectedValue.getTime();
						String dataAtual = dns.format(selectedDate);
						LabelData.setText(dataAtual);
					}
					} else {
						
							SimpleDateFormat dns = new SimpleDateFormat("dd/MM/yyyy");
							Calendar selectedValue = (Calendar) datePanelMostra.getModel().getValue();
							Date selectedDate = selectedValue.getTime();
							String dataAtual = dns.format(selectedDate);
							LabelData.setText(dataAtual);
						}
					
				
			}
		});

	GroupLayout gl_contentPanel = new GroupLayout(
			contentPanel);gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addGap(37).addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(panel,GroupLayout.PREFERRED_SIZE,213,GroupLayout.PREFERRED_SIZE).addComponent(panelDatePlacar,GroupLayout.PREFERRED_SIZE,225,GroupLayout.PREFERRED_SIZE)).addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addGap(29).addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(btnTransferir).addComponent(CheckBoxDate))).addGroup(gl_contentPanel.createSequentialGroup().addGap(53).addComponent(LabelData,GroupLayout.PREFERRED_SIZE,119,GroupLayout.PREFERRED_SIZE))).addContainerGap(147,Short.MAX_VALUE)));gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addGap(26).addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED).addComponent(CheckBoxDate).addPreferredGap(ComponentPlacement.RELATED).addComponent(btnTransferir).addGap(86).addComponent(LabelData)).addGroup(gl_contentPanel.createSequentialGroup().addComponent(panel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addGap(18).addComponent(panelDatePlacar,GroupLayout.PREFERRED_SIZE,205,GroupLayout.PREFERRED_SIZE))).addContainerGap(31,Short.MAX_VALUE)));

	GroupLayout gl_panelDatePlacar = new GroupLayout(
			panelDatePlacar);gl_panelDatePlacar.setHorizontalGroup(gl_panelDatePlacar.createParallelGroup(Alignment.LEADING).addGroup(gl_panelDatePlacar.createSequentialGroup().addContainerGap().addComponent(datePanelMostra,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addContainerGap(68,Short.MAX_VALUE)));gl_panelDatePlacar.setVerticalGroup(gl_panelDatePlacar.createParallelGroup(Alignment.LEADING).addGroup(gl_panelDatePlacar.createSequentialGroup().addContainerGap().addComponent(datePanelMostra,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE).addContainerGap(14,Short.MAX_VALUE)));panelDatePlacar.setLayout(gl_panelDatePlacar);

	panel.add(datePicker_1);contentPanel.setLayout(gl_contentPanel);
	{

	}

	{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
	}
}}
