package br.com.warhjr.ui.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ListIterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import br.com.warhjr.controller.UsuarioController;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.ui.MeuRenderer;

@SuppressWarnings("serial")
public class BuscaUsuarioDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JTextField tfNome;
	public JTable dsTableUser;
	public static JLabel id = null;
	private JTextField nome = null;
	private JTextField login = null;
	private JTextField senha = null;
	private JFormattedTextField cpf = null;
	private JTextField endereco = null;
	private JTextField bairro = null;
	private JTextField cep = null;
	private JTextField codigo = null;
	private JTextField nomecidade = null;
	private JTextField nomeuf = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaUsuarioDialog dialog = new BuscaUsuarioDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(dialog);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public void consultarUser(JLabel tfcodigo, JTextField tfnome, JTextField tflogin, JTextField tfsenha) {

		BuscaUsuarioDialog.id = tfcodigo;
		this.nome = tfnome;
		this.login = tflogin;
		this.senha = tfsenha;
		// this.cpf=tfcpf;
		// this.endereco=tfendereco;
		// this.bairro=tfbairro;
		// this.cep=tfcep;
		// this.codigo=tfCodcidade;
		// this.nomecidade=tfnomecidade;
		// this.nomeuf=tfUf;
		// setVisible(true);
	}

	public BuscaUsuarioDialog() {

		setTitle("Busca Usu\u00E1rios - v1.0");
		setBounds(100, 100, 565, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			panel = new JPanel();
			panel.setBackground(new Color(245, 245, 220));
		}

		JPanel panel_1 = new JPanel();

		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				DefaultTableModel aModel = new DefaultTableModel();
				Object[] tableColumnNames = new Object[5];
				tableColumnNames[0] = "Código";
				tableColumnNames[1] = "Nome do Usuário";
				tableColumnNames[2] = "Login";
				tableColumnNames[3] = "Senha";
				tableColumnNames[4] = "Nivel";

				aModel.setColumnIdentifiers(tableColumnNames);
				dsTableUser.setModel(aModel);

				Object[] objects = new Object[5];
				UsuarioController cid = new UsuarioController();
				try {
					if ((!tfNome.getText().equals("")) && (e.getKeyCode() != 8)) {
						ListIterator<Usuario> lstrg = cid.buscaUsuarios(tfNome.getText()).listIterator();
						while (lstrg.hasNext()) {
							Usuario newfocus = lstrg.next();

							objects[0] = newfocus.getId();
							objects[1] = newfocus.getNome();
							objects[2] = newfocus.getLogin();
							objects[3] = newfocus.getSenha();
							objects[4] = newfocus.getTipo();

							aModel.addRow(objects);

						}

						dsTableUser.setModel(aModel);
						dsTableUser.getColumnModel().getColumn(1).setPreferredWidth(320);
						dsTableUser.getColumnModel().getColumn(0).setPreferredWidth(90);
						// dsTableUser.getColumnModel().getColumn(9).setPreferredWidth(200);

						dsTableUser.getColumnModel().getColumn(3).setMaxWidth(0);
						dsTableUser.getColumnModel().getColumn(3).setMinWidth(0);
						dsTableUser.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
						dsTableUser.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);

						dsTableUser.getColumnModel().getColumn(4).setMaxWidth(0);
						dsTableUser.getColumnModel().getColumn(4).setMinWidth(0);
						dsTableUser.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
						dsTableUser.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);

//					dsTableUser.getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
//					dsTableUser.getColumnModel().getColumn( 8 ).setMinWidth( 0 );  
//					dsTableUser.getTableHeader().getColumnModel().getColumn( 8 ).setMaxWidth( 0 );  
//					dsTableUser.getTableHeader().getColumnModel().getColumn( 8 ).setMinWidth( 0 );
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			}

		});
		tfNome.setForeground(new Color(0, 0, 255));
		tfNome.setColumns(10);
		JLabel lblNewLabel = new JLabel("Digite o Usu\u00E1rio:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
						.addComponent(tfNome, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE).addGap(48)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
						.addContainerGap(18, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JScrollPane scrollPanesnow = new JScrollPane();
		scrollPanesnow.setViewportBorder(new LineBorder(new Color(0, 0, 255)));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(scrollPanesnow, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(scrollPanesnow, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
						.addContainerGap()));
		{
			dsTableUser = new JTable();
			dsTableUser.setFillsViewportHeight(true);
			dsTableUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dsTableUser.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
			scrollPanesnow.setViewportView(dsTableUser);
			TableCellRenderer renderer = new MeuRenderer();
			dsTableUser.setDefaultRenderer(Object.class, renderer);
			dsTableUser.setRowHeight(30); // tamanho do campo .. Fundura ...
			dsTableUser.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE).addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						int linha = dsTableUser.getSelectedRow();

						if (dsTableUser.getRowCount() > 0) {
							id.setText("" + dsTableUser.getValueAt(linha, 0));
							nome.setText("" + dsTableUser.getValueAt(linha, 1));
							login.setText("" + dsTableUser.getValueAt(linha, 2));
							senha.setText("" + dsTableUser.getValueAt(linha, 3));
							cpf.setText("" + dsTableUser.getValueAt(linha, 4));
							endereco.setText("" + dsTableUser.getValueAt(linha, 5));
							bairro.setText("" + dsTableUser.getValueAt(linha, 6));
							cep.setText("" + dsTableUser.getValueAt(linha, 7));
							codigo.setText("" + dsTableUser.getValueAt(linha, 8));
							nomecidade.setText("" + dsTableUser.getValueAt(linha, 9));
							nomeuf.setText("" + dsTableUser.getValueAt(linha, 10));
							setVisible(false);
						}

//						if (dsTableUser.getRowCount() > 0)
//						{
//						CadastroUsuario auxtela;
//						try {
//							auxtela = new CadastroUsuario();
//							auxtela.getPanelAlert().setVisible(true);
//							auxtela.LabelStatus.setVisible(true);
//							auxtela.LabelStatus.setText("Modo de Editar !");
//						} catch (ParseException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//
//						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
