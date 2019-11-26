package br.com.warhjr.ui;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import br.com.warhjr.ui.arquivo.CadastroArquivo;

import br.com.warhjr.ui.usuario.CadastroUsuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MenuPrinc extends JFrame {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JButton buttonNew;
	private JToolBar toolBar;
	private LayoutManager auxPenilUser = null;
	private JFrame MenuPrinc;

	// protected String newline = "\n";
//    static final private String PREVIOUS = "previous";
//    static final private String UP = "up";
//    static final private String NEXT = "next";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MenuPrinc frame = new MenuPrinc();
					frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
					frame.setLocationRelativeTo(null);
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

	public MenuPrinc() {
		requestFocus(true);
		setTitle("Menu Principal - Treinamentos 2015 - V1.0.00");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 457);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(231, 229, 214));

		JSeparator separator = new JSeparator();
		mnArquivo.add(separator);

		JSeparator separator_1 = new JSeparator();
		mnArquivo.add(separator_1);

		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);

		JMenuItem mntmAjudaDoPrograma = new JMenuItem("Ajuda do Programa ...");
		mnSobre.add(mntmAjudaDoPrograma);

		JSeparator separator_2 = new JSeparator();
		mnSobre.add(separator_2);

		JMenuItem mntmEmailParaO = new JMenuItem("Email para o Autor....");
		mnSobre.add(mntmEmailParaO);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(231, 229, 214));

		panel.setBackground(SystemColor.menu);
		panel.setBorder(new LineBorder(Color.BLUE));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(263, Short.MAX_VALUE)));

		JButton ButtonArquivoBar = new JButton("");

		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastro de Cidades");
		mntmNewMenuItem.setBackground(SystemColor.text);
		mnArquivo.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cadastro de Endere\u00E7os");
		mntmNewMenuItem_1.setBackground(SystemColor.text);
		mnArquivo.add(mntmNewMenuItem_1);

		JMenuItem mntmCadastroDePessoas = new JMenuItem("Cadastro de Pessoas");
		mntmCadastroDePessoas
				.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/Menu/User group3232.png")));
		mntmCadastroDePessoas.setHorizontalAlignment(SwingConstants.LEFT);
		mntmCadastroDePessoas.setBackground(SystemColor.text);
		mnArquivo.add(mntmCadastroDePessoas);

		JMenuItem mntmCadastroDeArquivos = new JMenuItem("Cadastro de Arquivos");
		mntmCadastroDeArquivos
				.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/Menu/gravar.jpg")));

		JMenuItem mntmCadastroDeUsurios = new JMenuItem("Cadastro de Usu\u00E1rios");
		mntmCadastroDeUsurios.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/Adm.jpg")));
		mntmCadastroDeUsurios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					CadastroUsuario auxUser = new CadastroUsuario();
					auxUser.setVisible(true);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Problemas com o Cadastro" + ex.getMessage());
				}
			}
		});

		mntmCadastroDeArquivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		mntmCadastroDeUsurios.setBackground(SystemColor.text);
		mnArquivo.add(mntmCadastroDeUsurios);
		mntmCadastroDeArquivos.setBackground(SystemColor.text);
		mnArquivo.add(mntmCadastroDeArquivos);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/sair2.png")));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem menuItem = new JMenuItem("");
		menuItem.setBackground(Color.WHITE);
		mnArquivo.add(menuItem);

		JSeparator separator_3 = new JSeparator();
		mnArquivo.add(separator_3);
		mntmSair.setBackground(SystemColor.text);
		mnArquivo.add(mntmSair);

		ButtonArquivoBar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// ...

					CadastroArquivo aux = new CadastroArquivo();
					aux.setVisible(true);
					// aux.setAlwaysOnTop(true);
					aux.setLocationRelativeTo(null);
					aux.setFocusable(true);

				} catch (Exception ea) {
					JOptionPane.showMessageDialog(null, "Problemas com o Cadastro de Arquivos" + ea.getMessage());
				}
			}
		});

		ButtonArquivoBar.setForeground(Color.BLACK);
		ButtonArquivoBar.setBackground(Color.WHITE);
		ButtonArquivoBar.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/Menu/gravar.jpg")));

		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					CadastroUsuario auxUser = new CadastroUsuario();
					auxUser.setLocationRelativeTo(null);
					auxUser.setVisible(true);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Problemas com o Cadastro" + ex.getMessage());
				}

			}
		});
		btnNewButton.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/Adm.jpg")));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1
				.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/Menu/User group3232.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(ButtonArquivoBar, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(541, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
						.addComponent(ButtonArquivoBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86,
								Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
				.addGap(22)));
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);

//		buttonNew = addToolbarButton( toolBar, false, "New",
//				"new", "Create a new document" );

		// addButtons(toolBar);

	}

	protected void criaFormularioArquivo() throws Exception {
		CadastroArquivo frameArquivo = new CadastroArquivo();
		frameArquivo.setVisible(true);
		desktopPane.add(frameArquivo);

	}
}