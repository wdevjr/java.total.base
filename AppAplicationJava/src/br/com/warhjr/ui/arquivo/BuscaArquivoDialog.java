package br.com.warhjr.ui.arquivo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import br.com.warhjr.controller.ArquivoController;
import br.com.warhjr.model.Arquivo;
import br.com.warhjr.ui.MeuRenderer;
import java.awt.SystemColor;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

public class BuscaArquivoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private static final long serialVersionUID = 1L;
	private JLabel labelcodigo = null;
	private JLabel nomePessoa = null;
	private JTextArea nomearquivo = null;
	// private JTextField endereco=null;
	public JFormattedTextField data = null;
	public String dataNum;
	private JTextField extencao = null;
	private JTextField tamanho = null;
	private JTextField codigoPessoa = null;
	private JTable dstableArquivo;
	private JTextField textField;
	private JPanel buttonPane;
	SimpleDateFormat ds;

	public static void main(String[] args) {
		try {
			BuscaArquivoDialog dialog = new BuscaArquivoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void consultarArquivo(JLabel tflabelcodigo, JTextArea tftextFieldArquivoNome, JTextField tfextencao,
			JTextField tftamanho, JTextField tfcodigoPessoa, JLabel labelNomePessoa, JFormattedTextField object)
			throws ParseException {

		this.labelcodigo = tflabelcodigo;
		this.nomearquivo = tftextFieldArquivoNome;
		// this.endereco=tfendereco;
		this.extencao = tfextencao;
		this.tamanho = tftamanho;
		this.nomePessoa = labelNomePessoa;
		this.codigoPessoa = tfcodigoPessoa;
		this.data = object;

	}

	/**
	 * Create the dialog.
	 */
	public BuscaArquivoDialog() {
		setBounds(100, 100, 830, 438);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		JPanel panel_1 = new JPanel();
		{
			buttonPane = new JPanel();
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(buttonPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
					.addGap(4))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5))
		);

		JButton btnNewButton_1 = new JButton("Ok");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat ds = new SimpleDateFormat("dd/MM/yyyy");
				int linha = dstableArquivo.getSelectedRow();

				labelcodigo.setText("" + dstableArquivo.getValueAt(linha, 0));
				codigoPessoa.setText("" + dstableArquivo.getValueAt(linha, 1));

				nomePessoa.setText("" + dstableArquivo.getValueAt(linha, 2));
				nomearquivo.setText("" + dstableArquivo.getValueAt(linha, 3));
				// endereco.setText(""+dstableArquivo.getValueAt(linha, 4));
				extencao.setText("" + dstableArquivo.getValueAt(linha, 4));

				tamanho.setText("" + dstableArquivo.getValueAt(linha, 5));

				data.setText(("" + dstableArquivo.getValueAt(linha, 6).toString()));
				// dataNum=data;
				// data = "16/09/2016";
				// dispose();
				setVisible(false);
			}

		});

		JButton btnNewButton_2 = new JButton("Cancelar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(gl_buttonPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_buttonPane.createSequentialGroup().addContainerGap(491, Short.MAX_VALUE)
						.addComponent(btnNewButton_1).addGap(18).addComponent(btnNewButton_2).addContainerGap()));
		gl_buttonPane
				.setVerticalGroup(
						gl_buttonPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
								gl_buttonPane.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnNewButton_2).addComponent(btnNewButton_1))
										.addContainerGap()));
		buttonPane.setLayout(gl_buttonPane);

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.setIcon(
				new ImageIcon(BuscaArquivoDialog.class.getResource("/br/com/warhjr/img/Alerts/icon_find.gif")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel aModel = new DefaultTableModel();
				Object[] tableColumnNames = new Object[7];
				tableColumnNames[0] = "Código";
				tableColumnNames[1] = "Código Pessoa";
				tableColumnNames[2] = "Nome Pessoa";
				tableColumnNames[3] = "Nome Arquivo";
				// tableColumnNames[4] = "Nome Endereço";
				tableColumnNames[4] = "Extenção";
				tableColumnNames[5] = "Tamanho";
				tableColumnNames[6] = "Data Cadastro";

				aModel.setColumnIdentifiers(tableColumnNames);
				dstableArquivo.setModel(aModel);

				Object[] objects = new Object[7];
				ArquivoController arquivo = new ArquivoController();
				// try {
				ListIterator<Arquivo> lstrg = null;
				try {
					lstrg = arquivo.BuscaArquivos(textField.getText(), textField.getText()).listIterator();
				} catch (Exception e1) {

					e1.printStackTrace();
				}
				while (lstrg.hasNext()) {
					Arquivo newfocus = lstrg.next();

					objects[0] = newfocus.getId();
					objects[1] = newfocus.getIdPessoa();
					objects[2] = newfocus.getNomePessoa();
					objects[3] = newfocus.getNomearquivo();

					// objects[4] = newfocus.getEndereco();

					objects[4] = newfocus.getExtencao();
					objects[5] = newfocus.getTamanho();
					objects[6] = (newfocus.getData().toString());

					aModel.addRow(objects);

				}
				dstableArquivo.getColumnModel().getColumn(2).setPreferredWidth(320);
				dstableArquivo.getColumnModel().getColumn(3).setPreferredWidth(320);
				// dstableArquivo.getColumnModel().getColumn(5).setPreferredWidth(200);

				dstableArquivo.setRowHeight(25);

				MeuRenderer auxrend = new MeuRenderer();
				TableCellRenderer renderer = new MeuRenderer();
				dstableArquivo.setDefaultRenderer(Object.class, renderer);
			}

			// }
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_panel.createSequentialGroup().addGap(26)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton).addGap(33)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnNewButton).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(17, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
		);

		dstableArquivo = new JTable();
		dstableArquivo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dstableArquivo.setSurrendersFocusOnKeystroke(true);
		dstableArquivo.setBorder(new LineBorder(Color.BLUE));
		dstableArquivo.setBackground(new Color(255, 255, 204));
		dstableArquivo.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane.setViewportView(dstableArquivo);
		panel_1.setLayout(gl_panel_1);
		contentPanel.setLayout(gl_contentPanel);
	}

}
