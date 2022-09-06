package br.com.warhjr.ui.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import br.com.warhjr.controller.UsuarioController;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.ui.MenuPrinc;
import br.com.warhjr.ui.arquivo.CadastroArquivo;

public class LoginUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblMsg;
	private JLabel lblNewLabel_3;
	private Timer t;
	private JPasswordField senhaText;
	private Usuario usuario;
	private String[] listaLogin;
	private ArrayList<Usuario> listaCombox;
	private JComboBox logintext1;
	private JComboBox loginTextComboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginUser frame = new LoginUser();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			carrega();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @throws Exception
	 */
	public LoginUser() throws Exception {

		setBounds(100, 100, 418, 391);
		getContentPane().setLayout(new BorderLayout());

		UsuarioController auxController = new UsuarioController();
		int tam = auxController.ListaddCombo().size();

		UsuarioController usercombo = new UsuarioController();
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.CENTER);
			contentPanel.setBackground(Color.WHITE);
			
			contentPanel.setForeground(Color.WHITE);
			contentPanel.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
			senhaText = new JPasswordField();
			senhaText.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
			JLabel lblNewLabel = new JLabel("Usu\u00E1rio:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setForeground(new Color(0, 128, 0));

			JLabel lblNewLabel_1 = new JLabel("Senha:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_1.setForeground(new Color(0, 128, 0));

			final JLabel lblMsg_1 = new JLabel("Login ou Senha Incorretos !");
			lblMsg_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblMsg_1.setForeground(new Color(255, 0, 0));
			lblMsg_1.setVisible(false);
			loginTextComboBox = new JComboBox();
			loginTextComboBox.setMaximumRowCount(3);
			auxController.addComboBox(loginTextComboBox);
			loginTextComboBox.setToolTipText("");
			loginTextComboBox.setSelectedIndex(0);
			loginTextComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
			loginTextComboBox.setBackground(new Color(238, 232, 170));
			loginTextComboBox.setForeground(Color.BLUE);
			loginTextComboBox.setBorder(new LineBorder(SystemColor.BLUE, 1, false));
			// loginText1.setFont(new Font("Tahoma", Font.PLAIN, 17));

			senhaText.setFont(new Font("Tahoma", Font.BOLD, 20));
			senhaText.setForeground(new Color(0, 128, 0));
			senhaText.addMouseListener(new MouseAdapter() {

			});
			senhaText.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					okButton.doClick();

				}
			});
			// usercombo.addComboBox(loginText);

			JLabel lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(
					LoginUser.class.getResource("/br/com/warhjr/img/Crystal_Clear_app_Login_Manager.png")));

			JLabel lblNewLabel_4 = new JLabel("warhjr - 2016");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel_4.setForeground(Color.BLUE);

			JLabel lblNewLabel_5 = new JLabel("");
			lblNewLabel_5.setIcon(new ImageIcon(LoginUser.class.getResource("/br/com/warhjr/img/telas/duke-plug.png")));

			JLabel lblNewLabel_6 = new JLabel("");
			lblNewLabel_6.setIcon(
					new ImageIcon(LoginUser.class.getResource("/br/com/warhjr/img/SqlServerEJava128x128.png")));
			{
				okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(LoginUser.class.getResource("/br/com/warhjr/img/chave.jpg")));
				okButton.addPropertyChangeListener(new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
					}
				});
				okButton.addActionListener(new ActionListener() {
					@Override
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent e) {
						UsuarioController objclic = new UsuarioController();

						try {
							if (objclic.LogarEx(loginTextComboBox.getSelectedItem(),
									new String(senhaText.getPassword())) == true) {

								CadastroArquivo frame = new CadastroArquivo();
								MenuPrinc auxMenu = new MenuPrinc();
								auxMenu.setVisible(true);
								auxMenu.setLocationRelativeTo(null);
								auxMenu.setExtendedState(auxMenu.getExtendedState() | Frame.MAXIMIZED_BOTH);
								dispose();

							} else {

								t = new javax.swing.Timer(400, new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										if (lblMsg_1.isVisible() == true) {
											lblMsg_1.setVisible(false);
											try {
												Thread.sleep(4000);
												t.stop();
												senhaText.setText(null);
											} catch (InterruptedException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
										} else {
											lblMsg_1.setVisible(true);

										}
									}
								});
								t.start();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}

					}
				});

				okButton.setActionCommand("");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setIcon(null);
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						System.exit(0);

					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addContainerGap()
							.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
			gl_buttonPane.setVerticalGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup().addContainerGap()
							.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
							.addContainerGap()));
			GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
			gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup().addGap(127).addComponent(lblMsg_1,
							GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createSequentialGroup().addGap(10)
							.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createSequentialGroup().addGap(10).addGroup(gl_contentPanel
							.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel).addComponent(loginTextComboBox,
													GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
									.addGap(6))
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 54,
											GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPanel.createSequentialGroup().addGap(60)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPanel.createSequentialGroup().addGap(18)
															.addComponent(lblNewLabel_4))
													.addGroup(gl_contentPanel.createSequentialGroup().addGap(15)
															.addComponent(lblNewLabel_5))
													.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 94,
															GroupLayout.PREFERRED_SIZE)))
									.addComponent(senhaText, GroupLayout.PREFERRED_SIZE, 178,
											GroupLayout.PREFERRED_SIZE))));
			gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPanel.createSequentialGroup().addGap(11).addComponent(lblNewLabel_4).addGap(6)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblNewLabel_5)
											.addGap(6).addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 80,
													GroupLayout.PREFERRED_SIZE))
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 115,
											GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
									.addComponent(lblNewLabel_1))
							.addGap(6)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(loginTextComboBox, GroupLayout.PREFERRED_SIZE,
											GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(senhaText, GroupLayout.PREFERRED_SIZE, 31,
											GroupLayout.PREFERRED_SIZE))
							.addGap(11).addComponent(lblMsg_1).addGap(11)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 31,
											GroupLayout.PREFERRED_SIZE))));
			contentPanel.setLayout(gl_contentPanel);
			buttonPane.setLayout(gl_buttonPane);
		}
		// okButton.requestFocusInWindow();
	}

	public static void carrega() {
		for (int i = 0; i < 101; i++) {
			try {
				Thread.sleep(50);
				//progressBar.setValue(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Não foi possivel carregar a barra" + e.getMessage());
			}

		}
	}
}
