package br.com.warhjr.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.warhjr.dao.ArquivoDAO;

import javax.swing.JProgressBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.sql.SQLException;

public class DialogProgress extends Thread {

   
	private JDialog dialog = null;

    


	/**
	 * Create the dialog.
	 * @wbp.parser.entryPoint
	 */
	public void run(){
		super.run();
		dialog = new JDialog();
		dialog.getContentPane().setBackground(SystemColor.info);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		
		JLabel lblNewLabel = new JLabel("Aguarde a leitura do Procedimento!");
		lblNewLabel.setForeground(Color.BLUE);
		GroupLayout groupLayout = new GroupLayout(dialog.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 487, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(33, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(205, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(167))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(38))
		);
		dialog.getContentPane().setLayout(groupLayout);
		dialog.setUndecorated(true);
		dialog.setModal(true);
		dialog.setSize(new Dimension(542, 118));
		dialog.setLocationRelativeTo(null);

        try { // try da Thread
         int value = progressBar.getValue();
         ArquivoDAO auxDAO = new ArquivoDAO();
         progressBar.setMinimum(0);
        // progressBar.setMaximum(auxDAO.tamanho);
		 	     	     		
        // progressBar.setValue(auxDAO.contador);  		
         sleep(500);      
	       } catch (InterruptedException ignoredException) {
	        }      
	
        progressBar.setValue(100);
	    }
	        
	    
		

	


		  
	

	public void iniciaBar() {
		start();
	}
	
	
	public void paraBar() {
		dialog.setVisible(false);
		dialog.dispose();
	}
}
