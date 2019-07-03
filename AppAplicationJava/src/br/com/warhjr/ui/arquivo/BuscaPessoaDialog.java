package br.com.warhjr.ui.arquivo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import br.com.warhjr.controller.PessoaController;
import br.com.warhjr.controller.UsuarioController;
import br.com.warhjr.model.Pessoa;
import br.com.warhjr.model.Usuario;
import br.com.warhjr.ui.MeuRenderer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ListIterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.SystemColor;

public class BuscaPessoaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldPessoa;
	private JTable dsTablePessoa;
	private JTextField codigo = null;
	private JLabel nomepessoa = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaPessoaDialog dialog = new BuscaPessoaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void consultar(JTextField tfcodigo, JLabel tfnomepessoa) {
		this.codigo = tfcodigo;
		this.nomepessoa = tfnomepessoa;

		// setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public BuscaPessoaDialog() {
		setBounds(100, 100, 882, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setViewportBorder(new LineBorder(Color.BLUE));

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);

		textFieldPessoa = new JTextField();
		textFieldPessoa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				if ((e.getKeyCode() != 8) && (!textFieldPessoa.getText().equals(""))) {
					DefaultTableModel aModel = new DefaultTableModel();
					Object[] tableColumnNames = new Object[9];
					tableColumnNames[0] = "Código";
					tableColumnNames[1] = "Código Endereço";
					tableColumnNames[2] = "Nome";
					tableColumnNames[3] = "Idade";
					tableColumnNames[4] = "Sexo";
					tableColumnNames[5] = "Endereço";
					tableColumnNames[6] = "Bairro";
					tableColumnNames[7] = "Nome Cidade";
					tableColumnNames[8] = "UF";

					aModel.setColumnIdentifiers(tableColumnNames);
					dsTablePessoa.setModel(aModel);

					Object[] objects = new Object[9];
					PessoaController pessoa = new PessoaController();
					try {
						ListIterator<Pessoa> lstrg = pessoa.BuscaPessoas(textFieldPessoa.getText()).listIterator();
						while (lstrg.hasNext()) {
							Pessoa newfocus = lstrg.next();

							objects[0] = newfocus.getId_pessoa();
							objects[1] = newfocus.getCod_endereco();
							objects[2] = newfocus.getNomePessoa();
							objects[3] = newfocus.getIdade();
							objects[4] = newfocus.getSexo();
							objects[5] = newfocus.getEndereco();
							objects[6] = newfocus.getBairro();
							objects[7] = newfocus.getNomecidade();
							objects[8] = newfocus.getUf();

							aModel.addRow(objects);

						}

						dsTablePessoa.setModel(aModel);
						// dsTablePessoa.getColumnModel().getColumn(0).setPreferredWidth(320);
						dsTablePessoa.getColumnModel().getColumn(1).setPreferredWidth(200);
						dsTablePessoa.getColumnModel().getColumn(2).setPreferredWidth(470);
						dsTablePessoa.getColumnModel().getColumn(7).setPreferredWidth(370);
						dsTablePessoa.getColumnModel().getColumn(5).setPreferredWidth(410);
						// dsTableUser.getColumnModel().getColumn(9).setPreferredWidth(200);
//					
//					
//					dsTablePessoa.getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//					dsTablePessoa.getColumnModel().getColumn( 1 ).setMinWidth( 0 );  
//					dsTablePessoa.getTableHeader().getColumnModel().getColumn( 1 ).setMaxWidth( 0 );  
//					dsTablePessoa.getTableHeader().getColumnModel().getColumn( 1 ).setMinWidth( 0 );
//					
//					dsTablePessoa.getColumnModel().getColumn( 4 ).setMaxWidth( 0 );  
//					dsTablePessoa.getColumnModel().getColumn( 4 ).setMinWidth( 0 );  
//					dsTablePessoa.getTableHeader().getColumnModel().getColumn( 4 ).setMaxWidth( 0 );  
//					dsTablePessoa.getTableHeader().getColumnModel().getColumn( 4 ).setMinWidth( 0 );
//					
//					
//					dsTablePessoa.getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
//					dsTablePessoa.getColumnModel().getColumn( 6 ).setMinWidth( 0 );  
//					dsTablePessoa.getTableHeader().getColumnModel().getColumn( 6 ).setMaxWidth( 0 );  
//					dsTablePessoa.getTableHeader().getColumnModel().getColumn( 6 ).setMinWidth( 0 );
//					
						dsTablePessoa.setRowHeight(25);

						MeuRenderer auxrend = new MeuRenderer();
						TableCellRenderer renderer = new MeuRenderer();
						dsTablePessoa.setDefaultRenderer(Object.class, renderer);

					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}

				}
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		textFieldPessoa.setForeground(Color.BLUE);
		textFieldPessoa.setColumns(10);

		JLabel lblDigiteUmaPessoa = new JLabel("Digite uma Pessoa:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap().addComponent(lblDigiteUmaPessoa)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textFieldPessoa, GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE).addGap(31)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldPessoa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDigiteUmaPessoa))
						.addContainerGap(20, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(5)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)));

		dsTablePessoa = new JTable();
		scrollPane.setViewportView(dsTablePessoa);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						int linha = dsTablePessoa.getSelectedRow();

						codigo.setText("" + dsTablePessoa.getValueAt(linha, 0));
						nomepessoa.setText("" + dsTablePessoa.getValueAt(linha, 2));

						// dispose();
						setVisible(false);
					}
				});

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
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
