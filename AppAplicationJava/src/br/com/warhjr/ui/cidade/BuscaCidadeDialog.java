package br.com.warhjr.ui.cidade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import br.com.warhjr.controller.CidadeController;
import br.com.warhjr.model.Cidade;
import br.com.warhjr.ui.MeuRenderer;

@SuppressWarnings("serial")
public class BuscaCidadeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JTable dsTableCidades;
	private JTextField textCidade;
	private JTextField codigo = null;
	private JTextField nomecidade = null;
	private JTextField nomeuf = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscaCidadeDialog dialog = new BuscaCidadeDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void consultar(JTextField tfcodigo, JTextField tfnomecidade, JTextField tfnomeuf) {
		this.codigo = tfcodigo;
		this.nomecidade = tfnomecidade;
		this.nomeuf = tfnomeuf;
		// setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public BuscaCidadeDialog() {
		setTitle("Busca Cidades - v1.0");
		setBounds(100, 100, 797, 437);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			panel = new JPanel();
			panel.setBackground(new Color(245, 245, 220));
		}

		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 751,
										Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 217, Short.MAX_VALUE).addGap(16)));

		textCidade = new JTextField();
		textCidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				// Grid ...

			}
		});
		textCidade.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome Cidade:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		JButton btnNewButton = new JButton("Busca");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Grid ...
				DefaultTableModel aModel = new DefaultTableModel();
				Object[] tableColumnNames = new Object[3];
				tableColumnNames[0] = "C�digo";
				tableColumnNames[1] = "Nome Cidade";
				tableColumnNames[2] = "UF";

				aModel.setColumnIdentifiers(tableColumnNames);
				dsTableCidades.setModel(aModel);

				Object[] objects = new Object[3];
				CidadeController cid = new CidadeController();
				ListIterator<Cidade> lstrg = null;
				try {
					lstrg = cid.buscaCidadePorNome(textCidade.getText()).listIterator();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				while (lstrg.hasNext()) {
					Cidade newfocus = lstrg.next();

					objects[0] = newfocus.getId_cidade();
					objects[1] = newfocus.getNomecidade();
					objects[2] = newfocus.getUf();

					aModel.addRow(objects);
				}

				dsTableCidades.setModel(aModel);

			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(42).addComponent(lblNewLabel).addGap(18)
						.addComponent(textCidade, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE).addGap(18)
						.addComponent(btnNewButton).addGap(138)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(22)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel).addComponent(btnNewButton))
				.addContainerGap(20, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE));

		dsTableCidades = new JTable();
		dsTableCidades.setColumnSelectionAllowed(true);
		dsTableCidades.setCellSelectionEnabled(true);
		dsTableCidades.setFillsViewportHeight(true);
		dsTableCidades.setSurrendersFocusOnKeystroke(true);
		dsTableCidades.setBackground(new Color(248, 248, 255));
		dsTableCidades.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		dsTableCidades.setForeground(new Color(230, 230, 250));
		dsTableCidades.setBorder(UIManager.getBorder("DesktopIcon.border"));
		dsTableCidades.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		dsTableCidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(dsTableCidades);
		TableCellRenderer renderer = new MeuRenderer();
		dsTableCidades.setDefaultRenderer(Object.class, renderer);

		dsTableCidades.setRowHeight(25);
		// dsTableCidades.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panel_1.setLayout(gl_panel_1);

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
						int linha = dsTableCidades.getSelectedRow();

						codigo.setText("" + dsTableCidades.getValueAt(linha, 0));
						nomecidade.setText("" + dsTableCidades.getValueAt(linha, 1));
						nomeuf.setText("" + dsTableCidades.getValueAt(linha, 2));
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
