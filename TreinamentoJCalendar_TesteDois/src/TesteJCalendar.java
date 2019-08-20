import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DarkStar;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.toedter.calendar.JCalendar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

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
					//PlasticLookAndFeel.setPlasticTheme(new Green());
					 try {
						 UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
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
		setBounds(100, 100, 532, 358);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JDateChooser dataChooser = new JDateChooser();
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
		calendarPanel.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		calendarPanel.setVisible(false);


		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		JLabel LabelJCalendar = new JLabel("");

		JButton btnNewButton = new JButton("Transferir");
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
					SwingUtilities.updateComponentTreeUI(calendarPanel);
					calendarPanel.updateUI();
				} catch (UnsupportedLookAndFeelException e1) {

				}
				if (calendarPanel.isVisible() == false) {
					calendarPanel.setVisible(true);
				} else {
					calendarPanel.setVisible(false);
				}
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(dataChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)
							.addGap(30))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(chckbxNewCheckBox))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(LabelJCalendar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addGap(62))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(dataChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxNewCheckBox)
						.addComponent(btnNewButton))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addComponent(LabelJCalendar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))
					.addGap(58))
		);
		
		
		panel.add(calendarPanel);
		contentPane.setLayout(gl_contentPane);
	}
}
