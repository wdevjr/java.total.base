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


public class ProgressBar extends Thread {
	
	private JDialog dialog = null;


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
		lblAguardeProcedimentoSendo.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(dialog.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(bar, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(150)
							.addComponent(lblAguardeProcedimentoSendo)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(lblAguardeProcedimentoSendo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(bar, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(32))
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
