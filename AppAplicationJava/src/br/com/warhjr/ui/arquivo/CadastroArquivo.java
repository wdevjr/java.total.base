package br.com.warhjr.ui.arquivo;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

//import com.toedter.calendar.JDateChooser;

import br.com.warhjr.controller.ArquivoController;
import br.com.warhjr.dao.ArquivoDAO;
import br.com.warhjr.dao.UsuarioDAO;
import br.com.warhjr.model.Arquivo;
import br.com.warhjr.ui.ProgressBar;

@SuppressWarnings("serial")
public class CadastroArquivo extends JFrame {

	
	private JPanel contentPane;
	
	private MaskFormatter Formatter = null;
	public File file;
	public ArquivoController AuxMsg = new ArquivoController();

	public int i = 0;

	private JTextField textFieldCodPessoa;



	private int NumTemp = 0;
	private int resposta;
	ArquivoController jds;
	SimpleDateFormat sdt;
	MaskFormatter cpfFormatter;
	public Arquivo auxArquivo = new Arquivo();
	private String NomeFileCaminho;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {
//			try {
//			    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//			        if ("Nimbus".equals(info.getName())) {
//			            UIManager.setLookAndFeel(info.getClassName());
//			            break;
//			        }
//			    }
//			} catch (Exception e) {
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			}
			try {
				// select Look and Feel
				UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
				CadastroArquivo frame = new CadastroArquivo();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public CadastroArquivo() throws Exception {

		setTitle("Cadastro de Arquivos - Treina desktop - java swing");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 814, 454);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// AuxMsg.msgConfima = false;

		final JPanel panelAlerta = new JPanel();
		panelAlerta.setBackground(new Color(238, 232, 170));
		panelAlerta.setVisible(false);
		JLabel IconLabel = new JLabel("Aviso:");
		IconLabel.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/warning_medium.gif")));
		IconLabel.setVisible(false);
		final JLabel labelText_1 = new JLabel("Message:");
		labelText_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelText_1.setForeground(Color.BLUE);
		JTextArea textArea__1 = new JTextArea();

		textArea__1.setEditable(false);
		// textArea__1.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		JLabel LabelNomePessoa = new JLabel("");
		LabelNomePessoa.setForeground(new Color(0, 0, 255));
		JLabel LabelSucess = new JLabel("Sucesso");
		LabelSucess.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/success_medium.gif")));
		LabelSucess.setVisible(false);
		JButton Inserir = new JButton("Inserir");
		Inserir.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/telas/add.png")));
		Inserir.setEnabled(false);
		Inserir.setFont(new Font("Tahoma", Font.BOLD, 11));

		JTextField textFieldexten = new JTextField();
		textFieldexten.setEditable(false);
		textFieldexten.setForeground(Color.BLUE);
		textFieldexten.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldexten.setColumns(10);

		JFormattedTextField textFieldDataAtual = new JFormattedTextField();
		// textFieldDataAtual.setBackground(new Color(255,255,255,255));
		textFieldDataAtual.setForeground(Color.BLUE);
		textFieldDataAtual.setEditable(false);
		textFieldDataAtual.setColumns(10);
		textFieldDataAtual.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		JButton Deletar = new JButton("Deletar");
		Deletar.setFont(new Font("Tahoma", Font.BOLD, 11));
		Deletar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/telas/delete.png")));
		Deletar.setEnabled(false);

		JButton Cancelar = new JButton("Cancelar");
		Cancelar.setIcon(
				new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/AlertsDois/warning_large.gif")));
		Cancelar.setEnabled(false);

		JButton Editar = new JButton("Editar");
		Editar.setFont(new Font("Tahoma", Font.BOLD, 11));
		Editar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/telas/alterar.gif")));
		Editar.setEnabled(false);

		JButton Consultar = new JButton("Consultar");
		Consultar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Procurar2.jpg")));
		Consultar.setEnabled(false);

		JButton Fechar = new JButton("Fechar ...");
		Fechar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Alerts/ip.gif")));

		JButton btnNewButton_Extrair = new JButton("Extrair...");

		JButton btnLocalizarPessoa = new JButton("");

		btnNewButton_1 = new JButton("");

		// DecimalFormat df = new DecimalFormat("#.##");
		// String novonumero = df.format((file.length()));

		MaskFormatter FormatterDecimal = null;
		try {

			FormatterDecimal = new MaskFormatter("###.##");
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

		JLabel LabelCodigo = new JLabel("");

		LabelCodigo.setForeground(Color.RED);

		try {
			Formatter = new MaskFormatter("##/##/####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Formatter.setPlaceholderCharacter('_');

		final JButton Gravar = new JButton("Gravar");
		Gravar.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Alerts/ok_16x10.gif")));
		Gravar.setEnabled(false);
		// Gravar.setEnabled(false);
		Gravar.setFont(new Font("Tahoma", Font.BOLD, 11));

		UsuarioDAO LabelUser = new UsuarioDAO();

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(SystemColor.activeCaption, 1));
		panel_5.setBackground(new Color(191, 205, 219));

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

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 1));
		panel.setBackground(SystemColor.activeCaption);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption, 1));

		panel_1.setBackground(new Color(231, 229, 214));

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(SystemColor.activeCaption, 1));
		panel_2.setBackground(new Color(237, 241, 228, 255));

		btnNewButton_Extrair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {

						ProgressBar bar = new ProgressBar();
						bar.iniciaBar();

						ArquivoController auxContr = new ArquivoController();

						try {
							if (!LabelCodigo.getText().equals("")) {
								auxContr.ExtrairArquivoEx(textArea__1.getText(), LabelCodigo.getText(),
										btnNewButton_Extrair);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}

						bar.paraBar();
						JOptionPane.showMessageDialog(null, "Sucesso!, Salvamento pronto!");
					}
				});
				t.start();

			}
		});
		btnNewButton_Extrair
				.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Alerts/download.gif")));
		Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Arquivo auxArquivo = new Arquivo();

				ArquivoController auxContr = new ArquivoController();

				if (NumTemp == 1) {

					auxArquivo.setId(Integer.parseInt(LabelCodigo.getText()));
					auxArquivo.setNomearquivo(textArea__1.getText());
					auxArquivo.setExtencao(textFieldexten.getText());
					auxArquivo.setTamanho(textFieldTamanho.getText());
					// auxArquivo.setEndereco(textFieldEndereco.getText());
					auxArquivo.setIdPessoa(Integer.parseInt(textFieldCodPessoa.getText()));
					auxArquivo.setData(textFieldDataAtual.getText());

					Inserir.setEnabled(true);
					Gravar.setEnabled(false);
					Editar.setEnabled(false);
					Deletar.setEnabled(true);
					Cancelar.setEnabled(true);
					Consultar.setEnabled(true);

					try {
						auxContr.EditaArq(auxArquivo, file.getAbsolutePath());
					} catch (Exception e1) {

						// labelText.setVisible(true);
						labelText_1.setText(String.valueOf(e1.getMessage()));

						if (AuxMsg.msgConfima == false) {
							labelText_1.setVisible(true);
							labelText_1.setText(String.valueOf(e1.getMessage()));
							IconLabel.setVisible(true);
							panelAlerta.setVisible(true);
						} else {
							labelText_1.setVisible(true);
							labelText_1.setText(String.valueOf(e1.getMessage()));
							IconLabel.setVisible(false);
							panelAlerta.setVisible(true);
							LabelSucess.setVisible(true);
						}
						// e2.printStackTrace();

					}

				} else {

					auxArquivo.setId(Integer.parseInt(LabelCodigo.getText()));
					auxArquivo.setNomearquivo(textArea__1.getText());
					auxArquivo.setExtencao(textFieldexten.getText());
					auxArquivo.setTamanho(textFieldTamanho.getText());
					// auxArquivo.setEndereco(textFieldEndereco.getText());
					auxArquivo.setData(textFieldDataAtual.getText());
					auxArquivo.setIdPessoa(Integer.parseInt(textFieldCodPessoa.getText()));

					Inserir.setEnabled(true);
					Gravar.setEnabled(false);
					Editar.setEnabled(false);
					Deletar.setEnabled(true);
					Cancelar.setEnabled(true);
					Consultar.setEnabled(true);

					try {
						auxContr.EditaArquivoSemFile(auxArquivo);
					} catch (Exception e2) {
						// labelText.setVisible(true);
						labelText_1.setText(String.valueOf(e2.getMessage()));

						if (AuxMsg.msgConfima == false) {
							labelText_1.setVisible(true);
							labelText_1.setText(String.valueOf(e2.getMessage()));
							IconLabel.setVisible(true);
							panelAlerta.setVisible(true);
						} else {
							labelText_1.setVisible(true);
							labelText_1.setText(String.valueOf(e2.getMessage()));
							IconLabel.setVisible(false);
							panelAlerta.setVisible(true);
							LabelSucess.setVisible(true);
						}
						// e2.printStackTrace();

					}
				}
				Inserir.setEnabled(true);
				Gravar.setEnabled(false);
				Editar.setEnabled(false);
				Deletar.setEnabled(true);
				Cancelar.setEnabled(true);
				Consultar.setEnabled(true);

				// JOptionPane.showMessageDialog(null, "Sucesso!, Deditado pronto!");
			}

		});

		// Editar.setEnabled(false);

		Deletar.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Excluir o registro ?",
						"Exclusão de Registro", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {

					ArquivoController auxController = new ArquivoController();

					auxController.deletarArquivo(LabelCodigo.getText());

					textArea__1.setText("");
					textFieldexten.setText("");
					textFieldTamanho.setText("");
					// textFieldEndereco.setText("");
					textFieldCodPessoa.setText("");
					LabelNomePessoa.setText("");
					LabelCodigo.setText("Código!");
					textFieldDataAtual.setText("");
				}

			}

		});
		// Deletar.setEnabled(false);

		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LabelCodigo.setText("Código!");
				Inserir.setEnabled(true);
				Gravar.setEnabled(false);
				Editar.setEnabled(false);
				Deletar.setEnabled(false);
				Cancelar.setEnabled(true);
				Consultar.setEnabled(true);
				textArea__1.setText("");
				textFieldexten.setText("");
				textFieldTamanho.setText("");
				textFieldDataAtual.setText("");

				textFieldCodPessoa.setText("");
				LabelNomePessoa.setText("");
				panelAlerta.setVisible(false);
			}
		});
		Cancelar.setFont(new Font("Tahoma", Font.BOLD, 11));

		Inserir.setEnabled(true);
		Gravar.setEnabled(false);
		Editar.setEnabled(false);
		Deletar.setEnabled(false);
		Cancelar.setEnabled(true);
		Consultar.setEnabled(true);

		Consultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				NumTemp = 0;

				BuscaArquivoDialog cc = new BuscaArquivoDialog();
				//SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
				// BuscaArquivoDialog auxDialog = new BuscaArquivoDialog();
				cc.setVisible(true);
				cc.setLocationRelativeTo(null);
				try {

					cc.consultarArquivo(LabelCodigo, textArea__1, textFieldexten, textFieldTamanho, textFieldCodPessoa,
							LabelNomePessoa, textFieldDataAtual);

				} catch (ParseException e1) {

					e1.printStackTrace();
				}

				Editar.setEnabled(true);
				Deletar.setEnabled(true);

			}
		});
		Consultar.setFont(new Font("Tahoma", Font.BOLD, 11));

		Inserir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				ArquivoDAO auxCodInc = null;
				// auxArquivo = null;
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

				NomeFileCaminho = "";

				// textArea__1.append("");
				textFieldCodPessoa.setText("0");

				textFieldexten.setText("");
				textFieldTamanho.setText((""));
				// textFieldEndereco.setText("");
				LabelNomePessoa.setText("");

				LabelCodigo.setVisible(true);
				Inserir.setEnabled(false);
				Gravar.setEnabled(true);
				Editar.setEnabled(false);
				Deletar.setEnabled(false);
				Cancelar.setEnabled(true);
				Consultar.setEnabled(false);
				panelAlerta.setVisible(false);

				textFieldDataAtual.setText(formatador.format(data));
//				ArquivoController AuxMsg = new ArquivoController();
//				

//				 AuxMsg.msgConfima = false;
				try {
					LabelCodigo.setText(String.valueOf(auxCodInc.incrementa()));
					// auxCodInc.incrementa();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		Gravar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//SimpleDateFormat dstn = new SimpleDateFormat("dd/MM/yyyy");
				ArquivoController auxSave = new ArquivoController();

//				Thread t = new Thread(new Runnable() {
//					@Override
//					public void run() {
//
//						ProgressBar bar = new ProgressBar();
//					    bar.iniciaBar();
			
						try {
							Arquivo auxArquivo = new Arquivo();
							auxArquivo.setNomearquivo(textArea__1.getText());
							auxArquivo.setExtencao(textFieldexten.getText());
							auxArquivo.setTamanho(textFieldTamanho.getText());
							auxArquivo.setData(textFieldDataAtual.getText());
							auxArquivo.setIdPessoa(Integer.parseInt(textFieldCodPessoa.getText()));
							
							auxSave.SalvarArq(auxArquivo, NomeFileCaminho);
							
						} catch (Exception e2) {


							auxArquivo = null;
							
							if (AuxMsg.msgConfima == false) {
								labelText_1.setText("");
								labelText_1.setVisible(true);
								labelText_1.setText(String.valueOf(e2.getMessage()));
								IconLabel.setText("");
								LabelSucess.setVisible(false);
								IconLabel.setVisible(true);
								panelAlerta.setVisible(true);
							} else {
								labelText_1.setText("");
								labelText_1.setVisible(true);
								labelText_1.setText(String.valueOf(e2.getMessage()));
								LabelSucess.setText("");
								IconLabel.setText("");
								IconLabel.setVisible(false);
								LabelSucess.setVisible(true);
								panelAlerta.setVisible(true);

							}
						

						}

						if ((!textArea__1.getText().equals("")) && (!textFieldCodPessoa.getText().equals("0"))
								&& (!NomeFileCaminho.equals(""))) {
							Inserir.setEnabled(true);
							Inserir.setEnabled(true);
							Gravar.setEnabled(false);
							Editar.setEnabled(true);
							Deletar.setEnabled(true);
							Cancelar.setEnabled(true);
							Consultar.setEnabled(true);

							//bar.paraBar();
						}

					}
				//});
				//t.start();
			//}
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

					textArea__1.setText((file.getName()));
					if (!file.getName().equals("")) {

						NomeFileCaminho = file.getAbsolutePath();
					}

					DecimalFormat df = new DecimalFormat("###.##");
					double novonumero = (file.length());
					double numberAux = (novonumero);
					double number = (numberAux / 1024 / 1024);
					textFieldTamanho.setText(df.format(number).toString());
					if (number > 200.00) {
						JOptionPane.showMessageDialog(null, "Ultrapassou 200 MB!");
						Gravar.setEnabled(false);
						Editar.setEnabled(false);
						return;
					} else {

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

						textFieldexten.setText(ArquivoController.getExtensao(file.getName()));
						SimpleDateFormat dns = new SimpleDateFormat("dd/MM/yyyy");

						Date dataatual = new Date(System.currentTimeMillis());
						textFieldDataAtual.setText(dns.format(dataatual));

						// textFieldEndereco.setText(file.getAbsolutePath());
						// Editar.setVisible(true);
						NumTemp = 1;
					}
					// }
				}
			}
		});

		JLabel lblTamanho = new JLabel("Tamanho:");

		JLabel lblNewLabel_2 = new JLabel("Exten\u00E7\u00E3o:");

		JLabel lblNewLabel_3 = new JLabel("C\u00F3digo:");

		JLabel lblNewLabel_4 = new JLabel("Propiet\u00E1rio:");

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.WHITE);
		panel_3.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		panel_3.setForeground(new Color(0, 0, 0));

		btnLocalizarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NumTemp = 0;
				BuscaPessoaDialog ccd = new BuscaPessoaDialog();

				ccd.consultar(textFieldCodPessoa, LabelNomePessoa);
				ccd.setVisible(true);
				ccd.setAlwaysOnTop(true);
				ccd.setLocationRelativeTo(null);
				LabelNomePessoa.setVisible(true);
				Inserir.setEnabled(false);
				if (Gravar.isEnabled() == false) {
					Editar.setEnabled(true);
				}

			}
		});
		btnLocalizarPessoa
				.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/lupa16x16.png")));

		textFieldCodPessoa = new JTextField();
		textFieldCodPessoa.setForeground(Color.BLACK);
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblCadastroDeArquivos)
						.addGap(481).addComponent(lblWarhjr).addGap(45)));
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

		scrollPane_1.setViewportView(textArea__1);

		JLabel lblMb = new JLabel("MB");

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING,
								gl_panel_1.createSequentialGroup().addContainerGap().addComponent(panelAlerta, 0, 0,
										Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup().addGap(10).addGroup(gl_panel_1
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1
										.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 610,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addComponent(LabelCodigo,
														GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
										.addGap(14)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(btnCarregar, GroupLayout.PREFERRED_SIZE, 131,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNewButton_Extrair, GroupLayout.PREFERRED_SIZE, 131,
														GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel_2).addGap(4)
										.addComponent(textFieldexten, GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(lblTamanho).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldTamanho, GroupLayout.PREFERRED_SIZE, 73,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblMb).addGap(40)
										.addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldDataAtual, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel_3).addGap(126)
										.addComponent(lblNewLabel_4))
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE))))
				.addContainerGap(19, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addComponent(panelAlerta, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(12)
								.addComponent(LabelCodigo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(btnCarregar).addGap(11).addComponent(
								btnNewButton_Extrair, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
				.addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(3).addComponent(lblNewLabel_2))
						.addComponent(textFieldexten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup().addGap(3).addComponent(lblTamanho))
						.addGroup(gl_panel_1.createSequentialGroup().addGap(1)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
										.addComponent(textFieldDataAtual, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldTamanho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMb)))
				.addGap(11)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
				.addGap(6)
				.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAlerta.setVisible(false);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/telas/cancel.png")));
		GroupLayout gl_panelAlerta = new GroupLayout(panelAlerta);
		gl_panelAlerta.setHorizontalGroup(gl_panelAlerta.createParallelGroup(Alignment.LEADING).addGroup(gl_panelAlerta
				.createSequentialGroup().addGap(35)
				.addGroup(gl_panelAlerta.createParallelGroup(Alignment.LEADING).addComponent(LabelSucess)
						.addGroup(gl_panelAlerta.createSequentialGroup().addGap(9).addComponent(IconLabel)))
				.addGap(6).addComponent(labelText_1, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 265, Short.MAX_VALUE).addComponent(btnNewButton_1)));
		gl_panelAlerta.setVerticalGroup(gl_panelAlerta.createParallelGroup(Alignment.LEADING).addGroup(gl_panelAlerta
				.createSequentialGroup()
				.addGroup(gl_panelAlerta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlerta.createSequentialGroup().addGap(13)
								.addGroup(gl_panelAlerta.createParallelGroup(Alignment.LEADING)
										.addComponent(LabelSucess).addComponent(IconLabel).addComponent(labelText_1)))
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
				.addContainerGap()));
		panelAlerta.setLayout(gl_panelAlerta);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(6)
						.addComponent(Inserir, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE).addGap(5)
						.addComponent(Gravar).addGap(6)
						.addComponent(Editar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(Deletar).addGap(1).addComponent(Cancelar).addGap(2)
						.addComponent(Consultar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE).addGap(6)
						.addComponent(Fechar, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(12).addComponent(Inserir,
						GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Gravar, GroupLayout.PREFERRED_SIZE,
						34, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Editar, GroupLayout.PREFERRED_SIZE,
						35, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Deletar,
						GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Cancelar,
						GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Consultar,
						GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11).addComponent(Fechar, GroupLayout.PREFERRED_SIZE,
						35, GroupLayout.PREFERRED_SIZE)));
		panel_2.setLayout(gl_panel_2);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_5, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 788, GroupLayout.PREFERRED_SIZE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
				.addGap(1).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE).addGap(12)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE).addGap(11)
				.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		contentPane.setLayout(gl_contentPane);

	}
}
