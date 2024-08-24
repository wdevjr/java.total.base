package br.com.warhjr.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import br.com.warhjr.dao.UsuarioDAO;
import br.com.warhjr.ui.arquivo.CadastroArquivo;
import br.com.warhjr.ui.usuario.CadastroUsuario;

@SuppressWarnings("serial")
public class MenuPrinc extends JFrame {

	/**
	 * 
	 */
	// private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane desktopPane;

	private JPanel panelStatusBar = new JPanel();

	private JLabel labelTempGlobo = new JLabel();
	private JLabel labelTempChave = new JLabel();
	private JLabel labelTempLogin = new JLabel();
	private JLabel labelTempHora = new JLabel();

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
			        try {
			            // select Look and Feel
			        UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					MenuPrinc frame = new MenuPrinc();
					//frame.setExtendedState(frame.getExtendedState() | Frame.NORMAL);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public static String DiaDaSemana(int i, int tipo) {
		String diasem[] = { "domingo", "segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira",
				"sábado" };
		if (tipo == 0)
			return (diasem[i - 1]);
		else
			return (diasem[i - 1].substring(0, 6));
	}

	public static String NomeDoMes(int i, int tipo) {
		String mes[] = { "janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro",
				"outubro", "novembro", "dezembro" };
		if (tipo == 0)
			return (mes[i - 1]);
		else
			return (mes[i - 1].substring(0, 7));
	}

	public static String DataPorExtenso() {
		
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat FormatoDia = new SimpleDateFormat("dd");
		SimpleDateFormat FormatoMes = new SimpleDateFormat("MM");
		SimpleDateFormat FormatoAno = new SimpleDateFormat("yyyy");
		
		int d = Integer.parseInt(FormatoDia.format(calendar.getTime()));
		int m = Integer.parseInt(FormatoMes.format(calendar.getTime()));
		int a = Integer.parseInt(FormatoAno.format(calendar.getTime()));

		Calendar data = new GregorianCalendar(a, m - 1, d);
		int ds = data.get(Calendar.DAY_OF_WEEK);

		return (" " + d + " de " + NomeDoMes(m, 0) + " de " + a + "  " + DiaDaSemana(ds, 0) + ".  ");
	}

	@SuppressWarnings("unused")
	private String hora() {
		SimpleDateFormat ds = new SimpleDateFormat("HH");
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		// DateFormat f = DateFormat.getTimeInstance();
		return ds.format(data);
	}

	public String Saudacao() {
		SimpleDateFormat ds = new SimpleDateFormat("HH");
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		Integer horas = new Integer(ds.format(data));

		if (horas >= 0 && horas <= 5) { // entre meia noite (0h) e 5 da madrugada
			return "Boa madrugada!";
		} else {
			if (horas >= 6 && horas < 12) { // entre 6 e 11 da manh�
				return "Bom dia!";
			} else {
				if (horas >= 12 && horas < 18) { // entre meio dia (12h) e 17 (5h) da tarde
					return "Boa tarde!";
				} else {
					if (horas >= 18 && horas <= 23) { // entre 18 (6h) e 23 (11h) da noite
						return "Boa noite!";
					}
				}
			}
		}
		return null;

	}

	
	public MenuPrinc() {
		requestFocus(true);
		setTitle("Menu Principal - Treinamentos 2015 - V1.0.00");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 616);
		panelStatusBar.setBackground(Color.WHITE);

		// panelStatusBar.setPreferredSize(new Dimension(frame.getWidth(), 16));
		panelStatusBar.setLayout(new BoxLayout(panelStatusBar, BoxLayout.X_AXIS));
		JLabel statusLabel = new JLabel("status");
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		// panelStatusBar.add(statusLabel);
		// panelStatusBar.setLayout(new BoxLayout(south, 1));
		this.labelTempGlobo.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/terraPrnc.png")));
		this.labelTempChave.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/Msgina 002.png")));
		this.labelTempLogin.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/login_16x16.png")));
		this.labelTempHora.setIcon(new ImageIcon(MenuPrinc.class.getResource("/br/com/warhjr/img/clock.png")));
		panelStatusBar.add(new JLabel("       "));
		panelStatusBar.add(this.labelTempGlobo);
		panelStatusBar.add(new JLabel("   " + Saudacao() + "  Hoje é" + " " + DataPorExtenso()));
		panelStatusBar.add(new JLabel("   "));
		panelStatusBar.add(new JSeparator(1));
		panelStatusBar.add(this.labelTempLogin);
		panelStatusBar.add(new JLabel(" Nome: " + UsuarioDAO.getNomeUser()));
		panelStatusBar.add(new JLabel("     "));
		panelStatusBar.add(new JSeparator(1));
		panelStatusBar.add(this.labelTempChave);
		panelStatusBar.add(new JLabel(" Login: " + UsuarioDAO.getLoginUser() + "    "));
		panelStatusBar.add(new JSeparator(1));
		panelStatusBar.add(new JLabel("    "));
		panelStatusBar.add(this.labelTempHora);

		panelStatusBar.add(new JLabel("Hora Atual: "));
		ClockPane relogio = new ClockPane();
		relogio = new ClockPane();
		panelStatusBar.add(relogio);
		panelStatusBar.add(new JSeparator(1));
		panelStatusBar.add(new JLabel("Post Meridiem: " + "PM             "));
		panelStatusBar.add(new JSeparator(1));

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

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelStatusBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGap(0)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 409, Short.MAX_VALUE).addComponent(panelStatusBar,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
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