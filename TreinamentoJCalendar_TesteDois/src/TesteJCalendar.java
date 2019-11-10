import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.Silver;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class TesteJCalendar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteJCalendar frame = new TesteJCalendar();
					frame.setVisible(true);

//					PlasticLookAndFeel.setPlasticTheme(new Silver());
					 try {
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
						// UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
						// UIManager.setLookAndFeel(new WindowsLookAndFeel());
					 } catch (UnsupportedLookAndFeelException ex1) {
						 ex1.printStackTrace();
					 
					 }
	

					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TesteJCalendar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			SwingUtilities.updateComponentTreeUI(contentPane);
			contentPane.updateUI();
		} catch (UnsupportedLookAndFeelException e1) {

		}
	
		JDateChooser dataChooser = new JDateChooser();
		dataChooser.setBackground(Color.WHITE);

		dataChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
					SwingUtilities.updateComponentTreeUI(dataChooser);
					dataChooser.updateUI();
				} catch (UnsupportedLookAndFeelException e1) {

				}
			}
		});
		JCalendar calendarPanel = new JCalendar();
//		try {
//			UIManager.setLookAndFeel(new WindowsLookAndFeel());
//			SwingUtilities.updateComponentTreeUI(calendarPanel);
//			dataChooser.updateUI();
//		} catch (UnsupportedLookAndFeelException e1) {
//
//		}
		calendarPanel.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		calendarPanel.setVisible(false);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 220));
		JLabel LabelJCalendar = new JLabel("");

		JButton btnNewButton = new JButton("Transferir");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setToolTipText("");
		btnNewButton.setIcon(new ImageIcon(TesteJCalendar.class.getResource("/br/com/warhjr/icon/lupa24x24.png")));
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				String dataAtual = null;
				SimpleDateFormat dnsDate = new SimpleDateFormat("dd/MM/yyyy");

				if (calendarPanel.isVisible() == false) {
					if (dataChooser.getDate() != null) {
						dataAtual = dnsDate.format(dataChooser.getDate());
						LabelJCalendar.setText(dataAtual);

					} else {
						JOptionPane.showMessageDialog(null, "Preencha qualquer um dos campos!");
					}
				} else {
					String dataAtualJCalendar = dnsDate.format(calendarPanel.getDate());
					LabelJCalendar.setText(dataAtualJCalendar);
				}

			}

		});

		LabelJCalendar.setFont(new Font("Tahoma", Font.BOLD, 12));

		JCheckBox chckbxNewCheckBox = new JCheckBox("Check box JCalendar");
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					UIManager.setLookAndFeel(new WindowsLookAndFeel());
					SwingUtilities.updateComponentTreeUI(dataChooser);
					dataChooser.updateUI();
				} catch (UnsupportedLookAndFeelException e1) {

				}
				if (calendarPanel.isVisible() == false) {
					calendarPanel.setVisible(true);
				} else {
					calendarPanel.setVisible(false);
				}
			}
		});

		panel.add(calendarPanel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(179)
							.addComponent(dataChooser, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chckbxNewCheckBox))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(133)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(LabelJCalendar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(dataChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(6))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxNewCheckBox)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(54)
							.addComponent(LabelJCalendar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
					.addGap(171))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
