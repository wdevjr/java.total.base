package br.com.warhjr.ui.cidade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.warhjr.ui.usuario.CadastroUsuario;

@SuppressWarnings("serial")
public class CadastroCidade extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				try {
					CadastroUsuario cli = new CadastroUsuario();
					CadastroCidade frame = new CadastroCidade();

					// frame.add(cli.panel_super);
					// cli.setVisible(true);
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
	 * @param CadastroUsuario
	 * @throws ParseException
	 */
	@SuppressWarnings("rawtypes")
	public CadastroCidade() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 357);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelPrinc = new JPanel();
		contentPane.add(panelPrinc, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(Color.WHITE);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);

		JLabel label = new JLabel("warhjr - 2015");
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));

		JPanel panel_2 = new JPanel();

		JButton button = new JButton("Inserir");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_1 = new JButton("Gravar");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_2 = new JButton("Editar");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_3 = new JButton("Deletar");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_4 = new JButton("Cancelar");
		button_4.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_5 = new JButton("Consultar");
		button_5.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton button_6 = new JButton("Fechar ...");
		button_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 688, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addGap(320)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING).addGap(0, 55, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(11)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
								.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addComponent(button_3, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addComponent(button_4, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addComponent(button_5, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
								.addComponent(button_6, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
						.addGap(9)));
		panel_2.setLayout(gl_panel_2);

		JPanel panel_3 = new JPanel();

		JLabel label_1 = new JLabel("xxxxx");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_2 = new JLabel("Usuario:");

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);

		JLabel label_3 = new JLabel("Login:");

		JLabel label_4 = new JLabel("xxxxxxx");
		label_4.setForeground(new Color(0, 128, 0));
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.GRAY);

		JLabel label_5 = new JLabel("Tipo:");

		JLabel label_6 = new JLabel("xxxxx");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(0, 688, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup()
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup().addGap(22).addComponent(label_1,
										GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup().addGap(107).addComponent(label_2)))
						.addGap(26)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup().addGap(55).addComponent(label_3))
								.addGroup(gl_panel_3.createSequentialGroup().addGap(46).addComponent(label_4)))
						.addGap(56)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup().addGap(70).addComponent(label_5))
								.addGroup(gl_panel_3.createSequentialGroup().addGap(34).addComponent(label_6,
										GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(107, Short.MAX_VALUE)));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addGap(0, 67, Short.MAX_VALUE)
				.addGroup(gl_panel_3.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_3.createSequentialGroup().addComponent(label_5)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_6))
								.addGroup(gl_panel_3.createSequentialGroup().addComponent(label_3)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_4,
												GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup().addComponent(label_2)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(label_1)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3.setLayout(gl_panel_3);

		JPanel panel_4 = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE))
								.addGap(10))
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 688, Short.MAX_VALUE)
								.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
								.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup()
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGap(137)));

		JLabel labelCodigoCidade = new JLabel("xxxxxxxxxx");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Nome Cidade:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_2 = new JLabel("UF:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		JComboBox comboBoxUF = new JComboBox();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup().addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup().addGap(26)
								.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_4.createSequentialGroup().addComponent(lblNewLabel_1)
												.addGap(282).addComponent(lblNewLabel_2))
										.addGroup(gl_panel_4.createSequentialGroup()
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, 346,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(comboBoxUF,
														GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_4.createSequentialGroup().addContainerGap().addComponent(labelCodigoCidade)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup().addContainerGap().addComponent(labelCodigoCidade).addGap(7)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxUF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(167, Short.MAX_VALUE)));
		panel_4.setLayout(gl_panel_4);

		JLabel lblNewLabel = new JLabel("Cadastro de Cidades");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel_1.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
						.addPreferredGap(ComponentPlacement.RELATED, 546, Short.MAX_VALUE).addComponent(label)
						.addContainerGap()));
		gl_panel_1
				.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addGroup(gl_panel_1
								.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(lblNewLabel))
								.addContainerGap(23, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		GroupLayout gl_panelPrinc = new GroupLayout(panelPrinc);
		gl_panelPrinc.setHorizontalGroup(gl_panelPrinc.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrinc.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 718, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(174, Short.MAX_VALUE)));
		gl_panelPrinc.setVerticalGroup(gl_panelPrinc.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrinc.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 442, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panelPrinc.setLayout(gl_panelPrinc);
//		CadastroCidade frame = new CadastroCidade();
//		CadastroUsuario cli;
//		try {
//			cli = new CadastroUsuario();
//			panelPrinc.add(cli.panel_super);
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//	        .setVisible(true);
	}
}
