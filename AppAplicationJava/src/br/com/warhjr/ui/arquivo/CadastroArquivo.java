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

import javax.swing.AbstractButton;
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
import br.com.warhjr.ui.ProgressBar;

public class CadastroArquivo extends JFrame{

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
	private MaskFormatter Formatter = null;
	public File file;
	private JPanel panel_sucesso;
	private JLabel lbLabelGif;
	private ArquivoController auxSave = new ArquivoController();

	public Thread t1;
	public Timer t2;
	public int i = 0;
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
	private Date Date;
	private static JProgressBar progressBar = new JProgressBar();
	private JTextField textFieldDataAtual;
	public Arquivo auxArquivo = new Arquivo();
	private JButton textArea__1;
	private JButton LabelNomePessoa;
    private String NomeFileCaminho;
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
		setBounds(100, 100, 826, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panelAlerta = new JPanel();
		panelAlerta.setVisible(false);
		panelAlerta.setBackground(new Color(238, 232, 170));
		final JLabel Alert_1 = new JLabel("");
		Alert_1.setIcon(new ImageIcon(CadastroArquivo.class.getResource("/br/com/warhjr/img/Alerts/ok_16x10.gif")));
		
		Alert_1.setHorizontalAlignment(SwingConstants.LEFT);
		
				final JLabel labelText_1 = new JLabel("New label");
				labelText_1.setForeground(Color.BLUE);
		JTextArea textArea__1 = new JTextArea();
		textArea__1.setEditable(false);
		// textArea__1.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		JLabel LabelNomePessoa = new JLabel("");
		LabelNomePessoa.setForeground(new Color(0, 0, 255));

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
		//textFieldDataAtual.setBackground(new Color(255,255,255,255));
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

		btnNewButton_Extrair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						//DialogProgress bar = new DialogProgress();
						ProgressBar bar = new ProgressBar();
						bar.iniciaBar();

				ArquivoController auxContr = new ArquivoController();

				try {
					if (!LabelCodigo.getText().equals("")) {
						auxContr.ExtrairArquivoEx(textArea__1.getText(), LabelCodigo.getText(), btnNewButton_Extrair);
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

//				if (textArea__1.getText().equals("")) {
//					JOptionPane.showMessageDialog(null, "Insira um  Arquivo!");
//					return;
//				}
//				if  (textFieldCodPessoa.getText().equals("")) {
//					JOptionPane.showMessageDialog(null, "Informe uma Pessoa!");
//					return;
//				}
					
					// Arquivo auxArquivo = new Arquivo();
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

								//labelText.setVisible(true);
								labelText_1.setText(String.valueOf(e1.getMessage()));
							}
					
							t2 = new javax.swing.Timer(600, new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if (Alert_1.isVisible() == true) {
										Alert_1.setVisible(false);
										labelText_1.setVisible(false);
										Alert_1.setVisible(false);
										panelAlerta.setVisible(false);
										try {
											Thread.sleep(4000);
											t2.stop();
										} catch (InterruptedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									} else {
										panelAlerta.setVisible(true);
										Alert_1.setVisible(true);
										labelText_1.setVisible(true);
										

									}
								}
							});
							t2.start();
						
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
							} catch (Exception e1) {
								//labelText.setVisible(true);
								labelText_1.setText(String.valueOf(e1.getMessage()));
							}
							

						Inserir.setEnabled(true);
						Gravar.setEnabled(false);
						Editar.setEnabled(false);
						Deletar.setEnabled(true);
						Cancelar.setEnabled(true);
						Consultar.setEnabled(true);
					}
					t2 = new javax.swing.Timer(600, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (Alert_1.isVisible() == true) {
								Alert_1.setVisible(false);
								labelText_1.setVisible(false);
								Alert_1.setVisible(false);
								panelAlerta.setVisible(false);
								try {
									Thread.sleep(4000);
									t2.stop();
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								panelAlerta.setVisible(true);
								Alert_1.setVisible(true);
								labelText_1.setVisible(true);
								
							}
						
							}
						
					});
					t2.start();
		

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
		panel_2.setBackground(new Color(237,241,228,255));

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
				//auxArquivo = null;
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
                
				//textArea__1.append("");
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

				textFieldDataAtual.setText(formatador.format(data));
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

				SimpleDateFormat dstn = new SimpleDateFormat("dd/MM/yyyy");
                 
					t1 = new Thread(new Runnable() {
						
						@Override
						public void run() {
							ProgressBar bar = new ProgressBar();
							bar.iniciaBar();

							//Gravar.setBackground(Color.blue);
				try {

							
							Arquivo auxArquivo = new  Arquivo();
							auxArquivo.setNomearquivo(textArea__1.getText());
							
							auxArquivo.setExtencao(textFieldexten.getText());

							auxArquivo.setTamanho(textFieldTamanho.getText());

							auxArquivo.setData(textFieldDataAtual.getText());

							auxArquivo.setIdPessoa(Integer.parseInt(textFieldCodPessoa.getText()));		

							auxSave.SalvarArq(auxArquivo, NomeFileCaminho);

						} catch (Exception e2) {
                           //e2.printStackTrace();
						  // labelText.setVisible(true);

							labelText_1.setText(String.valueOf(e2.getMessage()));
                          //  auxArquivo = null;
							//labelText_1.setVisible(true);
//							

						}

                      if ((!textArea__1.getText().equals("")) && (!textFieldCodPessoa.getText().equals("0")) && (!NomeFileCaminho.equals("")))
                      {
							Inserir.setEnabled(true);
							Inserir.setEnabled(true);
							Gravar.setEnabled(false);
							Editar.setEnabled(true);
							Deletar.setEnabled(true);
							Cancelar.setEnabled(true);
							Consultar.setEnabled(true);
					 }
                      	
			
//							t2 = new javax.swing.Timer(600, new ActionListener() {
//								@Override
//								public void actionPerformed(ActionEvent e) {
//									if (Alert_1.isVisible() == true) {
//										Alert_1.setVisible(false);
//										labelText_1.setVisible(false);
//										Alert_1.setVisible(false);
//										panelAlerta.setVisible(false);
//										try {
//											Thread.sleep(4000);
//											t2.stop();
//										} catch (InterruptedException e1) {
//											// TODO Auto-generated catch block
//											e1.printStackTrace();
//											
//										}
//									} else {
//										panelAlerta.setVisible(true);
//										labelText_1.setVisible(true);
//										Alert_1.setVisible(true);
//										
//
//									}
//								}
//							});
//							t2.start();


							//Gravar.setBackground(new Color(240, 240, 240));
							bar.paraBar();
							
						}
					});
					t1.start();
					
					
			}
//
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
					

					// if (file.length() > 262144000) {

					// JOptionPane.showMessageDialog(null, "O Arquivo, ultapassou o tamanho
					// permitido de 250 MB!");
					// dataAtual_1.setText("");
					// LabelCodigo.setText("Código!");
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

					DecimalFormat df = new DecimalFormat("###.##");
					double novonumero = (file.length());
					double numberAux = (novonumero);
					double number = (numberAux / 1024 / 1024);
					textFieldTamanho.setText(df.format(number).toString());

					textFieldexten.setText(ArquivoController.getExtensao(file.getName()));
					SimpleDateFormat dns = new SimpleDateFormat("dd/MM/yyyy");

						Date dataatual = new Date(System.currentTimeMillis());
						textFieldDataAtual.setText(dns.format(dataatual));
					
				  
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
		textFieldCodPessoa.setEnabled(false);
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
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 790, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblCadastroDeArquivos)
						.addGap(516).addComponent(lblWarhjr).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup().addGap(5).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCadastroDeArquivos).addComponent(lblWarhjr))));
		panel.setLayout(gl_panel);

		JScrollPane scrollPane_1 = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Data Cadastro:");



		scrollPane_1.setViewportView(textArea__1);
		
		JLabel lblMb = new JLabel("MB");
		

		

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 610, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(LabelCodigo, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
									.addGap(14)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(btnCarregar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_Extrair, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(4)
									.addComponent(textFieldexten, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblTamanho)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldTamanho, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblMb)
									.addGap(40)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldDataAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addGap(126)
									.addComponent(lblNewLabel_4))
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelAlerta, GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(panelAlerta, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(LabelCodigo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnCarregar)
							.addGap(11)
							.addComponent(btnNewButton_Extrair, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_2))
						.addComponent(textFieldexten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(3)
							.addComponent(lblTamanho))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textFieldDataAtual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldTamanho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMb)))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addGap(6)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		

				GroupLayout gl_panelAlerta = new GroupLayout(panelAlerta);
				gl_panelAlerta.setHorizontalGroup(
					gl_panelAlerta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAlerta.createSequentialGroup()
							.addContainerGap()
							.addComponent(Alert_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelText_1, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(355, Short.MAX_VALUE))
				);
				gl_panelAlerta.setVerticalGroup(
					gl_panelAlerta.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelAlerta.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panelAlerta.createParallelGroup(Alignment.LEADING)
								.addComponent(labelText_1)
								.addComponent(Alert_1, Alignment.TRAILING))
							.addContainerGap())
				);
				panelAlerta.setLayout(gl_panelAlerta);
				Alert_1.setVisible(false);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(6)
					.addComponent(Inserir, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(Gravar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Editar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(Deletar)
					.addGap(1)
					.addComponent(Cancelar)
					.addGap(2)
					.addComponent(Consultar, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Fechar, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(Deletar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(Cancelar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(Consultar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addComponent(Fechar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addComponent(Editar, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(Gravar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
								.addGap(1)
								.addComponent(Inserir, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);

	}
}
