import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.StringTokenizer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TreinaRedesComJava {

	private JFrame frame;
    private String IP = "";
    private static final String DEFAULT_GATEWAY= "Gateway Padrão";
    private StringBuffer sb = new StringBuffer();
    private JButton btnMacadress;
    private JLabel labelmac = new JLabel("");
    
	/**
	 * Launch the application.
	 * warhjr - Walter Junior
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TreinaRedesComJava window = new TreinaRedesComJava();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	private String getMacAddress() throws IOException {
		String os = System.getProperty("os.name");
		try {
			if (os.startsWith("Windows")) {
				return windowsParseMacAddress(IP);

			} else if (os.startsWith("Linux")) {
				return linuxParseMacAddress(linuxRunIfConfigCommand());
			} else {
				throw new IOException("Sistema operacional desconhecido: " + os);
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
			throw new IOException(ex.getMessage());
		}
	}

	private final static String windowsRunIpConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec("getmac");
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1)
				break;
			buffer.append((char) c);
		}
		String outputText = buffer.toString();
		stdoutStream.close();
		return outputText;
	}



	private final static boolean linuxIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != 17)
			return false;
		return true;
	}

	private final static String linuxRunIfConfigCommand() throws IOException {
		Process p = Runtime.getRuntime().exec("ifconfig");
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());
		StringBuffer buffer = new StringBuffer();
		for (;;) {
			int c = stdoutStream.read();
			if (c == -1)
				break;
			buffer.append((char) c);
		}
		String outputText = buffer.toString();
		stdoutStream.close();
		return outputText;
	}

	private final static String linuxParseMacAddress(String ipConfigResponse) throws ParseException {
		String localHost = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch (java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new ParseException(ex.getMessage(), 0);
		}
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			boolean containsLocalHost = line.indexOf(localHost) >= 0;
			// IP
			if (containsLocalHost && lastMacAddress != null) {
				return lastMacAddress;
			}
			// MAC address
			int macAddressPosition = line.indexOf("HWaddr");
			if (macAddressPosition <= 0)
				continue;
			String macAddressCandidate = line.substring(macAddressPosition + 6).trim();
			if (linuxIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
		ParseException ex = new ParseException(
				"Nao foi poss�vel ler o MAC address para " + localHost + " de [" + ipConfigResponse + "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	/*
	 * Windows
	 */
	private final static String windowsParseMacAddress(String ipConfigResponse) throws ParseException {
		String localHost = null;
		try {
			localHost = InetAddress.getLocalHost().getHostAddress();
		} catch (java.net.UnknownHostException ex) {
			ex.printStackTrace();
			throw new ParseException(ex.getMessage(), 0);
		}
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse, "\n");
		String lastMacAddress = null;
		while (tokenizer.hasMoreTokens()) {
			String line = tokenizer.nextToken().trim();
			// IP
			if (line.endsWith(localHost) && lastMacAddress != null) {
				return lastMacAddress;
			}
			// MAC address
			int macAddressPosition = line.indexOf(":");
			if (macAddressPosition <= 0)
				continue;
			String macAddressCandidate = line.substring(macAddressPosition + 1).trim();
			if (windowsIsMacAddress(macAddressCandidate)) {
				lastMacAddress = macAddressCandidate;
				continue;
			}
		}
		ParseException ex = new ParseException("Nao foi poss�vel ler o MAC address de [" + ipConfigResponse + "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	private final static boolean windowsIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != 17)
			return false;
		return true;
	}
	
	void getLimpaStringBuilder()
	{
		sb.setLength(0); 
	}

	public TreinaRedesComJava() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel labelIPHost = new JLabel("");
		JLabel labelNomeHost = new JLabel("");
		JButton btnNewButton = new JButton("IP do host");
		
		labelmac.setText("");
		JLabel labelGateway = new JLabel("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					InetAddress addr = InetAddress.getLocalHost();
					IP = addr.getHostAddress();
					labelIPHost.setText(IP);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		JButton btnNewButton_1 = new JButton("Nome do Host");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					InetAddress addHost = InetAddress.getLocalHost();
					String nomeHost = addHost.getHostName();
					labelNomeHost.setText(nomeHost);

					addHost = InetAddress.getLocalHost();
				} catch (UnknownHostException e1) {

					e1.printStackTrace();
				}

			}
		});

		btnMacadress = new JButton("MacAdress");
		btnMacadress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String s = "";
			            InetAddress ip = null;
						try {
							ip = InetAddress.getLocalHost();
						} catch (UnknownHostException e1) {
						
							e1.printStackTrace();
						}

			            
			            NetworkInterface network = null;
						try {
							network = NetworkInterface.getByInetAddress(ip);
						} catch (SocketException e1) {
							
							e1.printStackTrace();
						}

			           
			            byte[] mac = null;
						try {
							mac = network.getHardwareAddress();
						} catch (SocketException e1) {
							
							e1.printStackTrace();
						}
						
						getLimpaStringBuilder();
			            for (int i = 0; i < mac.length; i++) {

			                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			            }

			            
			            s = sb.toString();
			           labelmac.setText(s);

			    }	
			      
		});
		
		JButton btnNewButton_2 = new JButton("Gateway");
		btnNewButton_2.addActionListener(new ActionListener() {
			private String gateway;
			private String adapter;

			public void actionPerformed(ActionEvent e) {
								
			    try{
			        String gateway;
			        Process result = Runtime.getRuntime().exec("netstat -rn");

			        BufferedReader output = new BufferedReader(new InputStreamReader(result.getInputStream()));

			        String line = output.readLine();
			        while(line != null){
			            if ( line.trim().startsWith("default") == true || line.trim().startsWith("0.0.0.0") == true )
			                break;      
			            line = output.readLine();
			        }
			        if(line==null) //gateway not found;
			            return;

			        StringTokenizer st = new StringTokenizer( line );
			        st.nextToken();
			        st.nextToken();
			        gateway = st.nextToken();
			        labelGateway.setText(gateway);

			    } catch( Exception t ) { 
			        System.out.println( t.toString() );
			        gateway = new String();
			        adapter = new String();
			    }
			}
		
			
		});
		
		JButton btnNewButton_3 = new JButton("Limpar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelIPHost.setText("");
				labelNomeHost.setText("");
				labelmac.setText("");
				labelGateway.setText("");
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(labelIPHost, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(labelNomeHost, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnMacadress, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(labelmac))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNewButton_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE))
							.addGap(27)
							.addComponent(labelGateway)))
					.addGap(102))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(labelIPHost)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(labelNomeHost)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnMacadress)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(labelmac)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(labelGateway)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_3)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
