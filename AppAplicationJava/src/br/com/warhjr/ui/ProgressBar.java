package br.com.warhjr.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;


public class ProgressBar extends Thread {
	
	private JDialog dialog = null;


	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void run() {
		super.run();
		JProgressBar bar = new JProgressBar();
		bar.setIndeterminate(true);
		dialog = new JDialog();
		dialog.getContentPane().setBackground(SystemColor.info);
		dialog.setUndecorated(true);
		dialog.setModal(true);
		dialog.setSize(new Dimension(542, 118));
		bar.setForeground(new Color(176,196,222));
		dialog.setLocationRelativeTo(null);
		
		JLabel lblAguardeProcedimentoSendo = new JLabel("Aguarde Procedimento sendo Feito!");
		lblAguardeProcedimentoSendo.setForeground(Color.BLUE);
		lblAguardeProcedimentoSendo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ProgressBar.class.getResource("/br/com/warhjr/img/ajax-loader -transparente.gif")));
		GroupLayout groupLayout = new GroupLayout(dialog.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(168)
							.addComponent(lblAguardeProcedimentoSendo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(bar, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(241)
							.addComponent(lblNewLabel)))
					.addContainerGap(50, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblAguardeProcedimentoSendo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(bar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		dialog.getContentPane().setLayout(groupLayout);
		dialog.setVisible(true);		
	}

	public void iniciaBar() {
		start();
	}
	
	public void paraBar() {
		dialog.setVisible(false);
		dialog.dispose();
	}	
}
