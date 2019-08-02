package br.com.warhjr.ui.arquivo;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

//import com.toedter.calendar.JDateChooser;

import br.com.warhjr.controller.ArquivoController;
import br.com.warhjr.dao.ArquivoDAO;
import br.com.warhjr.dao.UsuarioDAO;
import br.com.warhjr.model.Arquivo;
import javax.swing.JLayeredPane;

public class CadastroArquivo extends JFrame {

	private JLabel LabelCodigo;
	private JLabel labelpessoa;
	private JPanel contentPane;
	private JButton btnCarregar;
	private JLabel labelExtencao;
	private JTextField textFieldTamanho;
	private JTextField textFieldexten;
	private JTextField linha;
	private JTextArea textArea_;
	private Container contentor;
	private JTextField textFieldEndereco;
	private InputStream inputStrean;
	public File file;
	private JPanel panel_sucesso;
	private JLabel lbLabelGif;
	private ArquivoController auxSave;
	// private JDateChooser dateChooser_1;

	private Timer t;
	int i = 0;
	private JLabel LabelSucesso;
	private JLabel LabelLoadinAjax;
	private JTextField textFieldCodPessoa;
	private JButton Inserir;
	private JButton Gravar;
	private JButton Editar;
	private JButton Deletar;
	private JButton Cancelar;
	private JButton Consultar;
	private JLabel labelText;
	private JLabel Alert;
	private JButton btnNewButton;
	private JFileChooser fileChooser;
	private Arquivo auxArquivo = new Arquivo();
	private JScrollPane scrollPane;
	private int NumTemp = 0;
	private int resposta;
	private UsuarioDAO LabelUser;
	ArquivoController jds;
	SimpleDateFormat sdt;
	private String data;
	private JFormattedTextField dataNum;
	private JTextField textFieldData;
	private JTextField textFieldDatas;
	private JTextField numdata;
	MaskFormatter cpfFormatter;
	// private JFormattedTextField dataAtual;
	private SimpleDateFormat sdft;
	private JFormattedTextField dataAtual_1;
	private Date Date;
	private static JProgressBar progressBar = new JProgressBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CadastroArquivo frame = new CadastroArquivo();
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
	 * 
	 * @throws Exception
	 */

	public CadastroArquivo() throws Exception {

		setTitle("Cadastro de Arquivos - Treina desktop - java swing");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 813, 448);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		final JTextArea textArea__1 = new JTextArea();
		// textArea__1.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		final JLabel LabelNomePessoa = new JLabel("");
		LabelNomePessoa.setForeground(new Color(0, 0, 255));

		final JButton Inserir = new JButton("Inserir");
		Inserir.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/telas/add.png")));
		Inserir.setEnabled(false);
		Inserir.setFont(new Font("Tahoma", Font.BOLD, 11));

		final JTextField textFieldexten = new JTextField();
		textFieldexten.setEditable(false);
		textFieldexten.setForeground(Color.BLUE);
		textFieldexten.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldexten.setColumns(10);

		final JButton Deletar = new JButton("Deletar");
		Deletar.setFont(new Font("Tahoma", Font.BOLD, 11));
		Deletar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/telas/delete.png")));
		Deletar.setEnabled(false);

		final JButton Cancelar = new JButton("Cancelar");
		Cancelar.setIcon(
				new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/AlertsDois/warning_large.gif")));
		Cancelar.setEnabled(false);

		final JButton Editar = new JButton("Editar");
		Editar.setFont(new Font("Tahoma", Font.BOLD, 11));
		Editar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/telas/alterar.gif")));

		final JButton Consultar = new JButton("Consultar");
		Consultar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Procurar2.jpg")));
		Consultar.setEnabled(false);

		JButton Fechar = new JButton("Fechar ...");
		Fechar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Alerts/ip.gif")));

		final JFormattedTextField dataAtual = new JFormattedTextField();

		final JButton btnNewButton_Extrair = new JButton("Extrair...");

		// DecimalFormat df = new DecimalFormat("#.##");
		// String novonumero = df.format((file.length()));

		MaskFormatter FormatterDecimal = null;
		try {

			FormatterDecimal = new MaskFormatter("###.###");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FormatterDecimal.setPlaceholderCharacter('_');

		// JTextField textFieldTamanho = new JFormattedTextField(FormatterDecimal);
		JTextField textFieldTamanho = new JTextField();
		textFieldTamanho.setEditable(false);
		textFieldTamanho.setForeground(Color.BLUE);
		textFieldTamanho.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldTamanho.setColumns(10);

		final JLabel LabelCodigo = new JLabel("");

		LabelCodigo.setForeground(Color.RED);

		MaskFormatter Formatter = null;
		try {
			Formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Formatter.setPlaceholderCharacter('_');
		dataAtual_1 = new JFormattedTextField(Formatter);
		dataAtual_1.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		final JButton Gravar = new JButton("Gravar");
		Gravar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Alerts/ok_16x10.gif")));
		Gravar.setEnabled(false);
		// Gravar.setEnabled(false);
		Gravar.setFont(new Font("Tahoma", Font.BOLD, 11));

		UsuarioDAO LabelUser = new UsuarioDAO();

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(SystemColor.activeCaption, 1));
		panel_5.setBackground(new Color(231, 229, 214));

		JLabel label = new JLabel("Usuario:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel label_user = new JLabel(LabelUser.getNomeUser());
		label_user.setForeground(Color.BLUE);
		label_user.setFont(new Font("Tahoma", Font.BOLD, 11));

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);

		JLabel label_2 = new JLabel("Login:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel label_login = new JLabel(LabelUser.getLoginUser());
		label_login.setForeground(new Color(0, 128, 0));
		label_login.setFont(new Font("Tahoma", Font.BOLD, 11));

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.GRAY);

		JLabel label_4 = new JLabel("Tipo:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));

		btnNewButton_Extrair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArquivoController auxContr = new ArquivoController();

				try {
					if (!LabelCodigo.getText().equals("")) {
						auxContr.ExtrairArquivoEx(textArea__1.getText(), LabelCodigo.getText(), btnNewButton_Extrair);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_Extrair
				.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Alerts/download.gif")));

		final JLabel Alert = new JLabel("xxxx");
		Alert.setHorizontalAlignment(SwingConstants.LEFT);
		Alert.setVisible(false);

		final JLabel labelText = new JLabel("New label");
		labelText.setForeground(Color.BLUE);
		labelText.setVisible(false);

		Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat dns = new SimpleDateFormat("dd/MM/yyyy");

				if ((!textArea__1.getText().equals("")) && (!textFieldCodPessoa.getText().equals(""))
						&& (!LabelCodigo.getText().equals(""))) {
					// Arquivo auxArquivo = new Arquivo();
					ArquivoController auxContr = new ArquivoController();

					if (NumTemp == 1) {
						if ((!textArea__1.getText().equals("")) && (!textFieldCodPessoa.getText().equals(""))
								&& (!LabelCodigo.getText().equals(""))) {
							auxArquivo.setId(Integer.parseInt(LabelCodigo.getText()));
							auxArquivo.setNomearquivo(textArea__1.getText());
							auxArquivo.setExtencao(textFieldexten.getText());
							auxArquivo.setTamanho(textFieldTamanho.getText());
							// auxArquivo.setEndereco(textFieldEndereco.getText());
							auxArquivo.setId_pessoa(Integer.parseInt(textFieldCodPessoa.getText()));
							auxArquivo.setData(dataAtual_1.getText());

							Inserir.setEnabled(true);
							Gravar.setEnabled(false);
							Editar.setEnabled(false);
							Deletar.setEnabled(true);
							Cancelar.setEnabled(true);
							Consultar.setEnabled(true);

							try {
								auxContr.EditaArq(auxArquivo, file.getAbsolutePath());
							} catch (Exception e1) {

								e1.printStackTrace();
							}
						} else
							JOptionPane.showMessageDialog(null, "Insira um Arquivo e Seleciocione um propriet�rio!");
					} else

					{
						if ((!textArea__1.getText().equals("")) && (!textFieldCodPessoa.getText().equals(""))
								&& (!LabelCodigo.getText().equals(""))) {
							auxArquivo.setId(Integer.parseInt(LabelCodigo.getText()));
							auxArquivo.setNomearquivo(textArea__1.getText());
							auxArquivo.setExtencao(textFieldexten.getText());
							auxArquivo.setTamanho(textFieldTamanho.getText());
							// auxArquivo.setEndereco(textFieldEndereco.getText());
							auxArquivo.setData(dataAtual_1.getText());
							auxArquivo.setId_pessoa(Integer.parseInt(textFieldCodPessoa.getText()));

							Inserir.setEnabled(true);
							Gravar.setEnabled(false);
							Editar.setEnabled(false);
							Deletar.setEnabled(true);
							Cancelar.setEnabled(true);
							Consultar.setEnabled(true);

							try {
								auxContr.EditaArquivoSemFile(auxArquivo);
							} catch (Exception e1) {

								e1.printStackTrace();
							}
						} else
							JOptionPane.showMessageDialog(null, "Insira um Arquivo e Selecione um propriet�rio!");
						Inserir.setEnabled(true);
						Gravar.setEnabled(false);
						Editar.setEnabled(false);
						Deletar.setEnabled(true);
						Cancelar.setEnabled(true);
						Consultar.setEnabled(true);
					}
				} else
					JOptionPane.showMessageDialog(null, "Insira um Arquivo e Seleciocione um propriet�rio!");

			}

		});
		// Editar.setEnabled(false);

		Deletar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Excluir o registro ?",
						"Exclus�o de Registro", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {

					ArquivoController auxController = new ArquivoController();

					auxController.deletarArquivo(LabelCodigo.getText());

					textArea__1.setText("");
					textFieldexten.setText("");
					textFieldTamanho.setText("");
					// textFieldEndereco.setText("");
					textFieldCodPessoa.setText("");
					LabelNomePessoa.setText("");
					LabelCodigo.setText("C�digo!");
					dataAtual_1.setText("");
					JOptionPane.showMessageDialog(null, "Dados Excluidos!", "Situa��o dos Dados",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		// Deletar.setEnabled(false);

		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LabelCodigo.setText("C�digo!");
				Inserir.setEnabled(true);
				Gravar.setEnabled(false);
				Editar.setEnabled(false);
				Deletar.setEnabled(false);
				Cancelar.setEnabled(true);
				Consultar.setEnabled(true);

				dataAtual_1.setText("");
			}
		});
		Cancelar.setFont(new Font("Tahoma", Font.BOLD, 11));

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 1));
		panel.setBackground(SystemColor.activeCaption);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption, 1));

		panel_1.setBackground(new Color(231, 229, 214));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.activeCaption, 1));
		panel_2.setBackground(new Color(231, 229, 214));

		Inserir.setEnabled(true);
		Gravar.setEnabled(false);
		Editar.setEnabled(false);
		Deletar.setEnabled(false);
		Cancelar.setEnabled(true);
		Consultar.setEnabled(true);

		Consultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				NumTemp = 0;
				ArquivoController auC = new ArquivoController();
				BuscaArquivoDialog cc = new BuscaArquivoDialog();
				SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
				// BuscaArquivoDialog auxDialog = new BuscaArquivoDialog();
				cc.setVisible(true);
				cc.setLocationRelativeTo(null);
				try {

					cc.consultarArquivo(LabelCodigo, textArea__1, textFieldexten, textFieldTamanho, textFieldCodPessoa,
							LabelNomePessoa, dataAtual_1);

					// String numdata=auxDialog.dataNum;
					// dateChooser_1.setDate((auC.StringADate(cc.dataNum)));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// cc.setVisible(true);
				// cc.setAlwaysOnTop(true);
				// cc.setLocationRelativeTo(null);
				Editar.setEnabled(true);
				Deletar.setEnabled(true);
				// if ((numdata) != null)
				// {
				//
				// dateChooser_1.setDate((auC.StringADate(cc.dataNum)));
				//
				// }

			}
		});
		Consultar.setFont(new Font("Tahoma", Font.BOLD, 11));

		final JLayeredPane panelAlerta = new JLayeredPane();
		// panelAlerta.setBorder(new LineBorder(new Color(0, 0, 255), 2));
		panelAlerta.setBackground(SystemColor.menu);

		//
		Inserir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ArquivoDAO auxCodInc = null;
				try {
					auxCodInc = new ArquivoDAO();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Date data = new Date();
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

				LabelCodigo.setText("");
				textArea__1.setText("");
				textFieldexten.setText("");
				textFieldTamanho.setText((""));
				// textFieldEndereco.setText("");
				textFieldCodPessoa.setText("");
				LabelNomePessoa.setText("");

				LabelCodigo.setVisible(true);
				Inserir.setEnabled(false);
				Gravar.setEnabled(true);
				Editar.setEnabled(false);
				Deletar.setEnabled(false);
				Cancelar.setEnabled(true);
				Consultar.setEnabled(false);

				dataAtual_1.setText(formatador.format(data));
				try {
					LabelCodigo.setText(String.valueOf(auxCodInc.incrementa()));
					//auxCodInc.incrementa();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}

			}
		});
		Gravar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				SimpleDateFormat dstn = new SimpleDateFormat("dd/MM/yyyy");

				if ((!textArea__1.getText().equals("")) && (!textFieldCodPessoa.getText().equals(""))) {
					// statusSplash auxPonto = new statusSplash();

					// progressBar.setIndeterminate(true);
					// carrega(10, progressBar);

					ArquivoController auxSave = new ArquivoController();

					// LabelCodigo.setText();
					// auxArquivo.setId(Integer.parseInt(LabelCodigo.getText()));
					// carrega(20, progressBar);
					auxArquivo.setNomearquivo(textArea__1.getText());

					auxArquivo.setExtencao(textFieldexten.getText());
					// carrega( 30, progressBar);
					auxArquivo.setTamanho(textFieldTamanho.getText());

					// auxArquivo.setEndereco(textFieldEndereco.getText());

					// carrega(40, progressBar);

					auxArquivo.setData(dataAtual_1.getText());

					// carrega(50, progressBar);

					auxArquivo.setCod_pessoa(Integer.parseInt(textFieldCodPessoa.getText()));

					// carrega(70, progressBar);

					try {

						// carrega(90, progressBar);
						auxSave.SalvarArq(auxArquivo, file.getAbsolutePath());

						// carrega(100, progressBar);
					} catch (Exception e1) {
//						ArquivoDAO auxCadCodigo = new ArquivoDAO();
//						try {
//							LabelCodigo.setText(String.valueOf(auxCadCodigo.incrementa()));
//						} catch (SQLException e2) {
//
//							e2.printStackTrace();
//						}
						labelText.setVisible(true);
						labelText.setText(String.valueOf(e1.getMessage()));

					}
					/*
					 * auxPonto.setVisible(true); auxPonto.setLocationRelativeTo(null);
					 * auxPonto.carrega();
					 */

					Inserir.setEnabled(true);
					Inserir.setEnabled(true);
					Gravar.setEnabled(false);
					Editar.setEnabled(true);
					Deletar.setEnabled(true);
					Cancelar.setEnabled(true);
					Consultar.setEnabled(true);
					// auxPonto.setVisible(false);

					// Icon icone =
					Alert.setIcon(new ImageIcon(
							CadastroArquivo.class.getResource("/br/com/warhjr/img/AlertsDois/success_medium.gif")));
					Alert.setVisible(true);
					labelText.setVisible(true);
					labelText.setText("Dados Inseridos com Sucesso!");
				} else {
					JOptionPane.showMessageDialog(null,
							"Insira um Arquivo ou Selecione um Propriet�rio para esse Arquivo!");

				}
			}

		});

		Fechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.exit(0);
				dispose();
			}
		});
		Fechar.setFont(new Font("Tahoma", Font.BOLD, 11));

		final JButton btnCarregar = new JButton("Carregar...");
		btnCarregar.setIcon(
				new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/GNOME_SESSION_32x32.PNG")));
		btnCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(btnCarregar) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					ArquivoController auxController = new ArquivoController();

					textArea__1.setText((file.getName()));

					// if (file.length() > 262144000) {

					// JOptionPane.showMessageDialog(null, "O Arquivo, ultapassou o tamanho
					// permitido de 250 MB!");
					// dataAtual_1.setText("");
					// LabelCodigo.setText("C�digo!");
					Inserir.setEnabled(false);
					Gravar.setEnabled(true);
					if (LabelCodigo.getText() != null) {
						Editar.setEnabled(false);
					} else {
						Editar.setEnabled(true);
					}
					Deletar.setEnabled(false);
					Cancelar.setEnabled(true);
					Consultar.setEnabled(true);

					// } else {

					DecimalFormat df = new DecimalFormat("###.###");
					double novonumero = (file.length());
					double numberAux = (novonumero);
					double number = (numberAux / 1024 / 1024);
					textFieldTamanho.setText(df.format(number).toString());

					textFieldexten.setText(ArquivoController.getExtensao(file.getName()));
					// textFieldEndereco.setText(file.getAbsolutePath());
					// Editar.setVisible(true);
					NumTemp = 1;
					// }
				}
			}
		});

		JLabel lblTamanho = new JLabel("Tamanho:");

		JLabel lblNewLabel_2 = new JLabel("Exten\u00E7\u00E3o:");

		JLabel lblNewLabel_3 = new JLabel("C\u00F3digo:");

		JLabel lblNewLabel_4 = new JLabel("Propiet\u00E1rio:");

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		panel_3.setForeground(new Color(0, 0, 0));

		JButton btnLocalizarPessoa = new JButton("");
		btnLocalizarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumTemp = 0;
				BuscaPessoaDialog ccd = new BuscaPessoaDialog();

				ccd.consultar(textFieldCodPessoa, LabelNomePessoa);
				ccd.setVisible(true);
				ccd.setAlwaysOnTop(true);
				ccd.setLocationRelativeTo(null);
				LabelNomePessoa.setVisible(true);
				if (Gravar.isEnabled() == false) {
					Editar.setEnabled(true);
				} else
					Editar.setEnabled(false);
			}
		});
		btnLocalizarPessoa
				.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/lupa16x16.png")));

		textFieldCodPessoa = new JTextField();
		textFieldCodPessoa.setForeground(Color.RED);
		textFieldCodPessoa.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldCodPessoa.setEditable(false);
		textFieldCodPessoa.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
						.addComponent(textFieldCodPessoa, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnLocalizarPessoa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(LabelNomePessoa, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(LabelNomePessoa, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
								.addComponent(btnLocalizarPessoa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(textFieldCodPessoa, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
						.addContainerGap()));
		panel_3.setLayout(gl_panel_3);

		JLabel lblCadastroDeArquivos = new JLabel("Cadastro de Arquivos");
		lblCadastroDeArquivos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCadastroDeArquivos.setForeground(SystemColor.text);

		JLabel lblWarhjr = new JLabel("warhjr - 2015");
		lblWarhjr.setHorizontalAlignment(SwingConstants.LEFT);
		lblWarhjr.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblWarhjr.setForeground(Color.BLUE);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE).addComponent(panel,
										Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(11, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addGap(1).addComponent(panel_2, 0, 0, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addGap(1)
						.addComponent(panel_5, 0, 0, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
				.addGap(1).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblCadastroDeArquivos)
						.addGap(516).addComponent(lblWarhjr).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addGap(5).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCadastroDeArquivos).addComponent(lblWarhjr))));
		panel.setLayout(gl_panel);

		JLabel label_tipo = new JLabel(LabelUser.getTipo());
		label_tipo.setForeground(Color.BLUE);
		label_tipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_5
				.createSequentialGroup().addGap(12).addComponent(label).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(label_user, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE).addGap(22)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(18).addComponent(label_2).addGap(12)
				.addComponent(label_login, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE).addGap(29)
				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addGap(5).addComponent(label_4).addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(label_tipo, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(206, Short.MAX_VALUE)));
		gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_5
				.createSequentialGroup().addGap(0)
				.addGroup(gl_panel_5.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(separator, Alignment.LEADING).addComponent(label_2, Alignment.LEADING)
						.addComponent(label_login, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 18,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,
								gl_panel_5.createParallelGroup(Alignment.BASELINE).addComponent(label_4).addComponent(
										label_tipo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING,
								gl_panel_5
										.createParallelGroup(Alignment.TRAILING).addComponent(label_user,
												GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
										.addComponent(label)))
				.addContainerGap()));
		panel_5.setLayout(gl_panel_5);

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Data Cadastro:");

		dataAtual_1.setBackground(new Color(250, 250, 210));
		dataAtual_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		dataAtual_1.setForeground(new Color(0, 128, 0));
		dataAtual_1.setBorder(new LineBorder(SystemColor.WHITE, 0, false));
		GroupLayout gl_panelAlerta = new GroupLayout(panelAlerta);
		gl_panelAlerta
				.setHorizontalGroup(
						gl_panelAlerta.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAlerta.createSequentialGroup().addGap(12)
										.addComponent(Alert, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(labelText,
												GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(185, Short.MAX_VALUE)));
		gl_panelAlerta
				.setVerticalGroup(
						gl_panelAlerta.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAlerta.createSequentialGroup()
										.addGroup(gl_panelAlerta.createParallelGroup(Alignment.BASELINE)
												.addComponent(Alert, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(labelText))
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelAlerta.setLayout(gl_panelAlerta);

		scrollPane_1.setViewportView(textArea__1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(10)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panelAlerta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(LabelCodigo, GroupLayout.PREFERRED_SIZE, 107,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 610,
												GroupLayout.PREFERRED_SIZE))
								.addGap(14)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCarregar, GroupLayout.PREFERRED_SIZE, 131,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_Extrair, GroupLayout.PREFERRED_SIZE, 131,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(
								gl_panel_1.createSequentialGroup().addComponent(lblNewLabel_2).addGap(4)
										.addComponent(textFieldexten, GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(lblTamanho).addGap(4)
										.addComponent(textFieldTamanho, GroupLayout.PREFERRED_SIZE, 106,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10).addComponent(lblNewLabel).addGap(4).addComponent(dataAtual_1,
												GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel_3).addGap(126)
								.addComponent(lblNewLabel_4))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(6)
				.addComponent(panelAlerta, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE).addGap(6)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(LabelCodigo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE).addGap(6)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(btnCarregar).addGap(11).addComponent(
								btnNewButton_Extrair, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(3).addComponent(lblNewLabel_2))
						.addComponent(textFieldexten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(3).addComponent(lblTamanho))
						.addComponent(textFieldTamanho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(1).addComponent(lblNewLabel))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(1).addComponent(dataAtual_1,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(11)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
				.addGap(6)
				.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(46, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(6)
						.addComponent(Inserir, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(Gravar).addGap(5)
						.addComponent(Editar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(Deletar).addGap(1).addComponent(Cancelar).addGap(2)
						.addComponent(Consultar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(Fechar, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE).addGap(31)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Editar,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Deletar,
								GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addGroup(gl_panel_2
								.createParallelGroup(Alignment.BASELINE)
								.addComponent(Cancelar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(Consultar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(Fechar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup().addGap(12)
								.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(Gravar, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(Inserir, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(16, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);

	}
}