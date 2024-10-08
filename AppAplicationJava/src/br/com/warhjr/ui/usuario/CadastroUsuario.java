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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.warhjr.controller.UsuarioController;
import br.com.warhjr.dao.ConectionDataBase;
import br.com.warhjr.model.Usuario;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


@SuppressWarnings("serial")
public class CadastroUsuario extends JFrame {
	

	private JPanel contentPane;
	public static JPanel panelAlert;
	public static JPanel panel_super;
	public JLabel LabelStatus;
	UsuarioController auxControllerUser = new UsuarioController();;
	SimpleDateFormat sdt;
	private JTextField textConsulta;
	private JTable tableUsers;
	private JTextField textFieldID;
	private JPanel panel;
	private JTextField textFieldNome;
	private JTextField textFieldLogin;
	private JPasswordField textFieldSenha;
	private JTextField textFieldData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

//      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//        if ("Nimbus".equals(info.getName())) {
//          try {
//			UIManager.setLookAndFeel(info.getClassName());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//         break;
//		   }
//		} 


		
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
//					try {
//					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//					        if ("Nimbus".equals(info.getName())) {
//					            UIManager.setLookAndFeel(info.getClassName());
//					            break;
//					        }
//					    }
//					} catch (Exception e) {
//						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					}
					
			        try {
			            // select Look and Feel
			            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
						CadastroUsuario frame = new CadastroUsuario();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			        }
			        catch (Exception ex) {
			            ex.printStackTrace();
			        }
		

					
					


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
		
		if (textConsulta.getText().length() != 0) {
			Object[] tableColumes = new Object[6];

			tableColumes[0] = "Código";
			tableColumes[1] = "Nome";
			tableColumes[2] = "Login";
			tableColumes[3] = "Senha";
			tableColumes[4] = "Tipo Usuário";
			tableColumes[5] = "Data Cadastro";

			model.setColumnIdentifiers(tableColumes);
			tableUsers.setModel(model);

			try {
				Object[] objects = new Object[6];

				ListIterator<Usuario> lstg = userTable.buscaUsuarios(textConsulta.getText()).listIterator();

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
	
	
	public void getPopularTableComParametro() {
		UsuarioController userTable = new UsuarioController();
		DefaultTableModel model = new DefaultTableModel();
		
		if (textConsulta.getText().length() != 0) {
			Object[] tableColumes = new Object[6];

			tableColumes[0] = "Código";
			tableColumes[1] = "Nome";
			tableColumes[2] = "Login";
			tableColumes[3] = "Senha";
			tableColumes[4] = "Tipo Usuário";
			tableColumes[5] = "Data Cadastro";

			model.setColumnIdentifiers(tableColumes);
			tableUsers.setModel(model);

			try {
				Object[] objects = new Object[6];

				ListIterator<Usuario> lstg = userTable.buscaUserId(Long.parseLong(textFieldID.getText())).listIterator();

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
	
//	
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
//    {  
//      Component comp = CellRe.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  
//      
//      if(isSelected)
//          comp.setForeground(Color.red);
//      return comp;  
//   }  



	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public CadastroUsuario()  throws Exception {
		setTitle("Treina swing java Contriole de Usu\u00E1rios- v10.0");

		// setAlwaysOnTop(true);
		// requestFocus(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 867, 526);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
		panel_3.setBackground(new Color(237,241,228,255));
		

		JButton btnFechar = new JButton("Fechar");
		btnFechar.setBounds(689, 11, 107, 25);
		
		JButton btGravar = new JButton("Gravar");
		btGravar.setEnabled(false);
		JButton button = new JButton("Inserir");
		JButton btEditar = new JButton("Editar");
		JButton btnNewButton = new JButton("Pesquisar");
		tableUsers = new JTable();
		tableUsers.setBackground(new Color(204, 255, 255));
		tableUsers.setShowGrid(true);
		tableUsers.setSelectionBackground(new Color(57, 105, 138));
		tableUsers.setSelectionForeground(Color.WHITE);

		JButton btDeletar = new JButton("Deletar");

		
		textConsulta = new JTextField();
		textConsulta.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textConsulta.setColumns(10);
		
		btDeletar.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unused" })
			private JComboBox textFieldTipo;

			@Override
			public void actionPerformed(ActionEvent e) {

				int resposta;

				resposta = JOptionPane.showConfirmDialog(null, "Deseja mesmo Excluir? ...", "Erro na Exclusão...",
						JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					auxControllerUser.deleteUser(textFieldID.getText());
					getPopularTable();
					textFieldID.setText(null);
					textFieldNome.setText(null);
					textFieldLogin.setText(null);
					textFieldSenha.setText(null);
					//textFieldTipo.setSelectedItem(null);
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
				//btnNewButton_1.setEnabled(false);

			}
		});
		btNovo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btNovo.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/telas/add.png")));
		btNovo.setBounds(10, 11, 94, 25);
		panel_3.add(btNovo);


		JLabel label_6 = new JLabel("Nome:");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnImprimir = new JButton("Imprimir");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldNome.setBackground(Color.WHITE);
		
		JLabel label_7 = new JLabel("Login:");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		textFieldLogin.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldLogin.setBackground(Color.WHITE);
		
		JLabel label_8 = new JLabel("Senha:");
		label_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldSenha = new JPasswordField();
		textFieldSenha.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		
		JLabel label_9 = new JLabel("Tipo:");
		label_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JComboBox textFieldTipo = new JComboBox();
		textFieldTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textFieldTipo.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		textFieldTipo.addItem("Administrador");
		textFieldTipo.addItem("Usuário");
		textFieldTipo.addItem("Super");
		
		JLabel lblNewLabel_2 = new JLabel("Data Cadastro:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		textFieldData = new JTextField();
		textFieldData.setEditable(false);
		textFieldData.setColumns(10);
		textFieldData.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
		
		btEditar.setBounds(198, 11, 94, 25);
		btEditar.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/telas/alterar.gif")));
		btEditar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btEditar.setEnabled(false);

		JButton btCancelar = new JButton("Cancelar");

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

					try {
						userAux.setNome(textFieldNome.getText());
						userAux.setLogin(textFieldLogin.getText());
						userAux.setSenha(new String(textFieldSenha.getPassword()));
						userAux.setTipo(textFieldTipo.getSelectedItem().toString());
						userAux.setData(textFieldData.getText());

						auxControllerUser.SalvaUsuarios(userAux);
//						if (textConsulta.getText().length() != 0) {
//							getPopularTable();
//							requestFocus();
//						}
						//JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso!");

					} catch (Exception e1) {

						e1.printStackTrace();
					}
					
					if (!userAux.getNome().equals("") && !userAux.getLogin().equals("") && !userAux.getSenha().equals(""))
					{
					btNovo.setEnabled(true);
					btGravar.setEnabled(false);
					btDeletar.setEnabled(true);
					btEditar.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso!");
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
					JOptionPane.showMessageDialog(null, "O nome do Usuário deve ser informado!", "Atenção!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldLogin.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "O nome de login deve ser informado!", "Atenção!",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (textFieldSenha.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "A senha também deve ser informada!", "Atenção!",
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
						//getPopularTable();
						getPopularTableComParametro();
      				    tableUsers.selectAll();
						

						btEditar.setEnabled(false);
						btNovo.setEnabled(true);
						btDeletar.setEnabled(true);
						btGravar.setEnabled(false);

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage().toString(), "Atenção Possivel Erro!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}

		});


		btCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btNovo.setEnabled(true);
				btDeletar.setEnabled(false);
				btGravar.setEnabled(false);
				btEditar.setEnabled(false);
				//btnNewButton_1.setEnabled(true);
			}
		});
		btCancelar.setBounds(392, 11, 124, 25);
		btCancelar.setIcon(
				new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/AlertsDois/warning_large.gif")));
		btCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));

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

		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		textFieldID.setBorder(new LineBorder(SystemColor.BLUE, 1, false));




		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(231, 229, 214));
		panel_4.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(38))
		);
		
		JPanel panel_5 = new JPanel();
		panel_5.setForeground(Color.BLUE);
		panel_5.setBorder(new TitledBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Dados do Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Dados  do Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)), "Dados do Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		

		btnImprimir.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				HashMap parameterMap;
				if (textFieldID.getText().length() > 0) {
					ConectionDataBase con = new ConectionDataBase();

					parameterMap = new HashMap();
					String imagemLogo = "br/com/warhjr/report/img/Adm.jpg";
					parameterMap.put("logoUser", imagemLogo);
					parameterMap.put("iduser", new Integer(textFieldID.getText()));


					try {
						InputStream arquivo = getClass().getResourceAsStream("/br/com/warhjr/report/reportUsuarios.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(arquivo);

						JasperPrint relatorio = JasperFillManager.fillReport(report, parameterMap, con.getConnection());
						JasperViewer viewer = new JasperViewer(relatorio, false);

						viewer.setTitle("Relatório de Usuários");
						viewer.setVisible(true);

					} catch (Exception e1) {

						e1.printStackTrace();

					}

				} else {

					JOptionPane.showMessageDialog(null, "Consulte um Usuário e Selecione no Grid (Tabela) Abaixo !");
				}
			}
			
		});
		btnImprimir.setBackground(Color.WHITE);
		btnImprimir.setIcon(new ImageIcon(CadastroUsuario.class.getResource("/br/com/warhjr/img/Alerts/website_development.png")));
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
								.addComponent(label_6)
								.addGroup(gl_panel_5.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textFieldTipo, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_8)
							.addGap(159)
							.addComponent(label_9)
							.addGap(158)
							.addComponent(label_7)))
					.addGap(17))
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_5.createSequentialGroup()
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(label_7))
					.addGap(6)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_5.createSequentialGroup()
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_9)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_5.createSequentialGroup()
							.addComponent(label_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(26))
		);
		panel_5.setLayout(gl_panel_5);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(56)
					.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(btnImprimir, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGap(12))
		);
		panel.setLayout(gl_panel);

		btnNewButton.addActionListener(new ActionListener() {
			private Object[] objects;
			private UsuarioController userTable;

			@Override
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {


				userTable = new UsuarioController();
				DefaultTableModel model = new DefaultTableModel();
				UsuarioController cc = new UsuarioController();
				if (textConsulta.getText().length() != 0) 
				{
					Object[] tableColumes = new Object[6];

					tableColumes[0] = "Código";
					tableColumes[1] = "Nome";
					tableColumes[2] = "Login";
				    tableColumes[3] = "Senha";
					tableColumes[4] = "Tipo Usuário";
					tableColumes[5] = "Data Cadastro";

					model.setColumnIdentifiers(tableColumes);
					tableUsers.setModel(model);

					try {

						objects = new Object[6];

						ListIterator<Usuario> lstg = userTable.buscaUsuarios(textConsulta.getText()).listIterator();

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
						//}
					}

				}

			
		});



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
										.addComponent(textConsulta, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_4.createSequentialGroup().addContainerGap(47, Short.MAX_VALUE)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton)
								.addComponent(textConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addGap(16)));

		tableUsers.addMouseListener(new MouseAdapter() {
	

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
