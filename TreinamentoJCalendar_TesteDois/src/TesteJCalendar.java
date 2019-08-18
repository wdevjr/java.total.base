import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.toedter.calendar.JCalendar;
import javax.swing.LayoutStyle.ComponentPlacement;
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JDateChooser dateChooser = new JDateChooser();
		
		JPanel panel = new JPanel();
		JLabel LabelJCalendar = new JLabel("");
		JCalendar calendarPanel = new JCalendar();
		calendarPanel.getDayChooser().getDayPanel().setBorder(new LineBorder(new Color(0, 0, 255)));
		GridLayout gridLayout = (GridLayout) calendarPanel.getDayChooser().getDayPanel().getLayout();
		calendarPanel.getYearChooser().getSpinner().setBackground(SystemColor.info);
		calendarPanel.getDayChooser().getDayPanel().setBackground(Color.WHITE);
		calendarPanel.setVisible(false);
		
		JButton btnNewButton = new JButton("Transferir");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				String Campo = null;
				String dataAtual = null;
				SimpleDateFormat dnsDate = new SimpleDateFormat("dd/MM/yyyy");

			if (calendarPanel.isVisible() == false)
			    {
			    	if (dateChooser.getDate() != null)
			    	{
			    	dataAtual = dnsDate.format(dateChooser.getDate());
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
				if (calendarPanel.isVisible() == false)
				{
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
					.addContainerGap(72, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(chckbxNewCheckBox))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(LabelJCalendar, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addGap(79))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxNewCheckBox)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNewButton)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(68)
							.addComponent(LabelJCalendar, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(66))
		);
		
		
		panel.add(calendarPanel);
		contentPane.setLayout(gl_contentPane);
	}
}
