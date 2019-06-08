package br.com.warhjr.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;

public class DialogProgress extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DialogProgress dialog = new DialogProgress();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public DialogProgress() {

		setUndecorated(true);
		setBounds(100, 100, 730, 118);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setForeground(new Color(250, 250, 210));
		}

		JLabel lblNewLabel = new JLabel("Algum Segundos ...");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(progressBar,
								GroupLayout.PREFERRED_SIZE, 691, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(312).addComponent(lblNewLabel)))
						.addContainerGap(19, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(22).addComponent(lblNewLabel).addGap(26)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE).addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);

	}
}
