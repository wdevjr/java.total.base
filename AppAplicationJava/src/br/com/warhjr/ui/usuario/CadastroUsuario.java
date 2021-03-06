package br.com.warhjr.ui.usuario;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.com.warhjr.controller.UsuarioController;
import br.com.warhjr.dao.ConectionDataBase;
import br.com.warhjr.dao.UsuarioDAO;
import br.com.warhjr.model.Usuario;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

@SuppressWarnings("serial")
public class CadastroUsuario extends JFrame {
	

	private JPanel contentPane;
	private JPanel panel_2;
	public static JPanel panelAlert;
	public static JPanel panel_super;
	public JLabel LabelStatus;
	private JButton btnInserir;
	private JButton btnBusca;
	private JButton btnGravar;
	private JButton btnEditar;
	private JButton btnDeletar;
	private JButton btnCancelar;
	private JButton btGravar;
	private JButton clique;
	private JLabel lblNewLabel;
	private JTextField labelCodigo;
	private JLabel txtLlblCidade;
	private JLabel LabelUser;
	private JLabel LabelLogin;
	private JButton btnConsultar;
	// protected AbstractButton btnEditar;
	UsuarioController auxControllerUser = new UsuarioController();;
	private JLabel msg;
	private JTextField textFieldNome;
	private JTextField textFieldLogin;
	private JLabel datalabel;
	private Date data;
	SimpleDateFormat sdt;
	private JTextField textFieldnome;
	private JTable tableUsers;
	private JTextField textFieldID;
	private JPasswordField textFieldSenha;
	private JTextField datatext;
	private JTextField textFieldData;
	private JPanel panel;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 * @throws Exception
	 */

	public void getPopularTable() {
		UsuarioController userTable = new UsuarioController();
		DefaultTableModel model = new DefaultTableModel();
		UsuarioController cc = new UsuarioController();
		
		if (textFieldnome.getText().length() != 0) {
			Object[] tableColumes = new Object[6];

			tableColumes[0] = "C�digo";
			tableColumes[1] = "Nome";
			tableColumes[2] = "Login";
			tableColumes[3] = "Senha";
			tableColumes[4] = "Tipo Usu�rio";
			tableColumes[5] = "Data Cadastro";

			model.setColumnIdentifiers(tableColumes);
			tableUsers.setModel(model);

			try {
				Object[] objects = new Object[6];

				ListIterator<Usuario> lstg = userTable.buscaUsuarios(textFieldnome.getText()).listIterator();

				while (lstg.hasNext()) {
					Usuario userAux = lstg.next();

					objects[0] = userAux.getId();
					objects[1] = userAux.getNome();
					objects[2] = userAux.getLogin();
					objects[3] = userAux.getSenha();
					objects[4] = userAux.getTipo();
					objects[5] = userAux.getData();

					model.addRow(objects);
				}

				tableUsers.setModel(model);
				tableUsers.setRowHeight(20);
				tableUsers.getColumnModel().getColumn(0).setPreferredWidth(2);
				tableUsers.getColumnModel().getColumn(1).setPreferredWidth(200);

				tableUsers.getColumnModel().getColumn(3).setMaxWidth(0);
				tableUsers.getColumnModel().getColumn(3).setMinWidth(0);
				tableUsers.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
				tableUsers.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);

			} catch (Exception eq) {
				JOptionPane.showInternalMessageDialog(null, eq.getMessage().toString());
			}
		}
		// return null;
	}

	@SuppressWarnings("deprecation")
	public CadastroUsuario() throws Exception {
		setTitle("Treina swing java Contriole de Usu\u00E1rios- v10.0");

		// setAlwaysOnTop(true);
		// requestFocus(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 832, 522);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
		panel_3.setBackground(new Color(237,241,228,255));
		
		JButton btGravar = new JButton("Gravar");
		btGravar.setEnabled(false);
		JButton button = new JButton("Inserir");
		JButton btEditar = new JButton("Editar");
		JButton btnNewButton = new JButton("Pesquisar");
		JComboBox textFieldTipo = new JComboBox();
		textFieldTipo.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldTipo.addItem("Administrador");
		textFieldTipo.addItem("Usu�rio");
		textFieldTipo.addItem("Super");
		tableUsers = new JTable();
		JButton btDeletar = new JButton("Deletar");
		JButton btnNewButton_1 = new JButton("Imprimir Relat\u00F3rio");
		btDeletar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int resposta;

				resposta = JOptionPane.showConfirmDialog(null, "Deseja mesmo Excluir? ...", "Erro na Exclus�o...",
						JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					auxControllerUser.deleteUser(textFieldID.getText());
					getPopularTable();
					textFieldID.setText(null);
					textFieldNome.setText(null);
					textFieldLogin.setText(null);
					textFieldSenha.setText(null);
					textFieldTipo.setSelectedItem(null);
					textFieldData.setText(null);
					JOptionPane.showMessageDialog(null, "Dados Excluidos com Sucesso", "Sucesso!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btDeletar.setBounds(294, 11, 95, 25);
		btDeletar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/telas/delete.png")));
		btDeletar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btDeletar.setEnabled(false);

		JButton btInserir = new JButton("Novo");

		JButton btNovo = new JButton("Novo");
		btNovo.addActionListener(new ActionListener() {

			

			@Override
			public void actionPerformed(ActionEvent e) {

				Calendar c = Calendar.getInstance();
				Date data = c.getTime();

				// DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
				// //Data COmpleta

				SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

				textFieldNome.setText("");
				textFieldLogin.setText("");
				textFieldSenha.setText("");
				textFieldData.setText(dt.format(data));

				btGravar.setEnabled(true);
				btNovo.setEnabled(false);
				btnNewButton_1.setEnabled(false);

			}
		});
		btNovo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btNovo.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/telas/add.png")));
		btNovo.setBounds(10, 11, 94, 25);
		panel_3.add(btNovo);

		UsuarioDAO user = new UsuarioDAO();
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaption, 1));
		panel_1.setBackground(new Color(191, 205, 219));

		JLabel label = new JLabel("xxxxx");
		label.setText(UsuarioDAO.getNomeUser());
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_1 = new JLabel("Usuario:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel label_2 = new JLabel("Login:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel label_3 = new JLabel("xxxxxxx");
		label_3.setForeground(new Color(0, 128, 0));
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));

		label_3.setText(UsuarioDAO.getLoginUser());

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.GRAY);

		JLabel label_4 = new JLabel("Tipo:");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel label_5 = new JLabel("xxxxx");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 11));

		label_5.setText(UsuarioDAO.getTipo());
		
		

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				btGravar.setEnabled(true);

			}
		});

		btGravar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Usuario userAux = new Usuario();

				if (textFieldNome.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "O Nome do Usu�rio deve ser informado!", "Aten��o!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldLogin.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "O nome de login deve ser informado!", "Aten��o!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldSenha.getPassword().toString().length() == 0) {
					JOptionPane.showMessageDialog(null, "A Senha tamb�m deve ser informada!", "Aten��o!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					try {
						userAux.setNome(textFieldNome.getText());
						userAux.setLogin(textFieldLogin.getText());
						userAux.setSenha(new String(textFieldSenha.getPassword()));
						userAux.setTipo(textFieldTipo.getSelectedItem().toString());
						userAux.setData(textFieldData.getText());

						auxControllerUser.SalvaUsuarios(userAux);
						if (textFieldnome.getText().length() != 0) {
							getPopularTable();
						}
						JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso!");
					} catch (Exception e1) {

						e1.printStackTrace();
					}

					// } catch (Exception ex) {
					// msg.setVisible(true);
					// msg.setText(ex.getMessage());
					// }

					btNovo.setEnabled(true);
					btGravar.setEnabled(false);
					btDeletar.setEnabled(true);
					btEditar.setEnabled(false);
					btnNewButton_1.setEnabled(true);

					// } else {
					// JOptionPane.showMessageDialog(null, "Dados
					// Incompletos!","Aten��o!",JOptionPane.INFORMATION_MESSAGE);
					// }
				}
			}

		});
		btGravar.setBounds(107, 11, 94, 25);
		btGravar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/Alerts/ok_16x10.gif")));
		btGravar.setFont(new Font("Tahoma", Font.BOLD, 11));

		btEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (textFieldNome.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "O nome do Usu�rio deve ser informado!", "Aten��o!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldLogin.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "O nome de login deve ser informado!", "Aten��o!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldSenha.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "A senha tamb�m deve ser informada!", "Aten��o!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					try {

						Usuario usuarios = new Usuario();

						usuarios.setId(new Integer(textFieldID.getText()));
						usuarios.setNome(textFieldNome.getText());
						usuarios.setLogin(textFieldLogin.getText());
						usuarios.setSenha(new String(textFieldSenha.getPassword()));
						usuarios.setTipo(textFieldTipo.getSelectedItem().toString());
						usuarios.setData(textFieldData.getText());

						auxControllerUser.UpdateUsuarios(usuarios);
						getPopularTable();

						btEditar.setEnabled(false);
						btNovo.setEnabled(true);
						btDeletar.setEnabled(true);
						btGravar.setEnabled(false);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage().toString(), "Aten��o Possivel Erro!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

		});

		btEditar.setBounds(198, 11, 94, 25);
		btEditar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/telas/alterar.gif")));
		btEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btEditar.setEnabled(false);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btNovo.setEnabled(true);
				btDeletar.setEnabled(false);
				btGravar.setEnabled(false);
				btEditar.setEnabled(false);
				btnNewButton_1.setEnabled(true);
			}
		});
		btCancelar.setBounds(392, 11, 124, 25);
		btCancelar.setIcon(
				new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/AlertsDois/warning_large.gif")));
		btCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(689, 11, 107, 25);
		btnFechar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		btnFechar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/Alerts/ip.gif")));
		btnFechar.setFont(new Font("Tahoma", Font.BOLD, 11));

		panel = new JPanel();
		panel.setBackground(new Color(231, 229, 214));
		panel.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));

		JLabel label_6 = new JLabel("Nome:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_7 = new JLabel("Login:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBackground(SystemColor.text);
		textFieldNome.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		textFieldLogin.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldLogin.setBackground(SystemColor.text);

		JLabel label_8 = new JLabel("Senha:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_9 = new JLabel("Tipo:");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_2 = new JLabel("Data Cadastro:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		textFieldID.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		textFieldSenha = new JPasswordField();
		textFieldSenha.setBorder(new LineBorder(SystemColor.BLUE, 1, false));

		
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (textFieldID.getText().length() > 0) {
					ConectionDataBase con = new ConectionDataBase();

					HashMap parameterMap = new HashMap();
					String imagemLogo = "br/com/warhjr/report/img/Adm.jpg";
					parameterMap.put("logoUser", imagemLogo);
					parameterMap.put("iduser", new Integer(textFieldID.getText()));

					try {
						InputStream arquivo = getClass()
								.getResourceAsStream("/br/com/warhjr/report/reportUsuarios.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(arquivo);

						JasperPrint relatorio = JasperFillManager.fillReport(report, parameterMap, con.getConnection());
						JasperViewer viewer = new JasperViewer(relatorio, false);

						viewer.setTitle("Relat�rio de Usu�rios");
						viewer.setVisible(true);

					} catch (Exception e1) {

						e1.printStackTrace();

					}

				} else {

					JOptionPane.showMessageDialog(null, "Consulte um Usu�rio e Selecione no Grid (Tabela) Abaixo !");
				}
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setIcon(
				new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/Alerts/website_development.png")));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(231, 229, 214));
		panel_4.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
				.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
				.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
				.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 193, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)));

		textFieldData = new JTextField();
		textFieldData.setEditable(false);
		textFieldData.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldData.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(510)
									.addComponent(btnNewButton_1))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_6)
									.addGap(347)
									.addComponent(label_7))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
											.addGap(10)
											.addComponent(textFieldTipo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(label_8)
											.addGap(152)
											.addComponent(label_9)))
									.addGap(69)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_6)
						.addComponent(label_7))
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(label_8)
							.addComponent(label_9))
						.addComponent(lblNewLabel_2))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(27))
		);
		panel.setLayout(gl_panel);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				// getPopularTable();

				UsuarioController userTable = new UsuarioController();
				DefaultTableModel model = new DefaultTableModel();
				UsuarioController cc = new UsuarioController();
				if (textFieldnome.getText().length() != 0) {
					Object[] tableColumes = new Object[6];

					tableColumes[0] = "C�digo";
					tableColumes[1] = "Nome";
					tableColumes[2] = "Login";
					tableColumes[3] = "Senha";
					tableColumes[4] = "Tipo Usu�rio";
					tableColumes[5] = "Data Cadastro";

					model.setColumnIdentifiers(tableColumes);
					tableUsers.setModel(model);

					try {
						Object[] objects = new Object[6];

						ListIterator<Usuario> lstg = userTable.buscaUsuarios(textFieldnome.getText()).listIterator();

						while (lstg.hasNext()) {
							Usuario userAux = lstg.next();

							objects[0] = userAux.getId();
							objects[1] = userAux.getNome();
							objects[2] = userAux.getLogin();
							objects[3] = userAux.getSenha();
							objects[4] = userAux.getTipo();
							objects[5] = userAux.getData();

							model.addRow(objects);
						}

						tableUsers.setModel(model);
						tableUsers.setRowHeight(20);
						tableUsers.getColumnModel().getColumn(0).setPreferredWidth(2);
						tableUsers.getColumnModel().getColumn(1).setPreferredWidth(200);

						tableUsers.getColumnModel().getColumn(3).setMaxWidth(0);
						tableUsers.getColumnModel().getColumn(3).setMinWidth(0);
						tableUsers.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
						tableUsers.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);

					} catch (Exception eq) {
						JOptionPane.showInternalMessageDialog(null, eq.getMessage().toString());
					}
				}

			}
		});

		textFieldnome = new JTextField();
		textFieldnome.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldnome.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
								.addGroup(gl_panel_4.createSequentialGroup().addComponent(btnNewButton)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldnome, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_4.createSequentialGroup().addContainerGap(47, Short.MAX_VALUE)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(textFieldnome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addGap(16)));

		tableUsers.addMouseListener(new MouseAdapter() {
			private AbstractButton datatext;

			@Override
			public void mousePressed(MouseEvent e) {

				int linha = tableUsers.getSelectedRow();

				textFieldID.setText(tableUsers.getValueAt(linha, 0).toString());
				textFieldNome.setText(tableUsers.getValueAt(linha, 1).toString());
				textFieldLogin.setText(tableUsers.getValueAt(linha, 2).toString());
				textFieldSenha.setText(tableUsers.getValueAt(linha, 3).toString());
				textFieldTipo.setSelectedItem(tableUsers.getValueAt(linha, 4).toString());
				textFieldData.setText(tableUsers.getValueAt(linha, 5).toString());

				btNovo.setEnabled(true);
				btGravar.setEnabled(false);
				btEditar.setEnabled(true);
				btDeletar.setEnabled(true);

			}
		});
		scrollPane.setViewportView(tableUsers);
		panel_4.setLayout(gl_panel_4);
		panel_3.setLayout(null);
		panel_3.add(button);
		panel_3.add(btGravar);
		panel_3.add(btEditar);
		panel_3.add(btDeletar);
		panel_3.add(btCancelar);
		panel_3.add(btnFechar);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(6).addComponent(label_1)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE).addGap(10)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(label_2).addGap(10)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE).addGap(29)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(4).addComponent(label_4).addGap(6)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
				.addComponent(label_2).addComponent(label_3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
				.addComponent(label_4)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(2).addComponent(label_5, GroupLayout.PREFERRED_SIZE,
						14, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createSequentialGroup().addGap(2)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(label_1)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		// setLocationRelativeTo(null);

	}

	public JTable getTable() {
		return tableUsers;
	}

	public Color getPanelBackground() {
		return panel.getBackground();
	}

	public void setPanelBackground(Color background) {
		panel.setBackground(background);
	}
}
