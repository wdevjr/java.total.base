import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import sun.nio.ch.Net;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;

public class TreinaRedesComJava {

	private JFrame frame;
	private String IP;
	private StringBuilder sb = new StringBuilder();
	private static final String DEFAULT_GATEWAY = "Default Gateway";

	/**
	 * Launch the application. warhjr - Walter Junior
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
				"Nao foi possível ler o MAC address para " + localHost + " de [" + ipConfigResponse + "]", 0);
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
		ParseException ex = new ParseException("Nao foi possível ler o MAC address de [" + ipConfigResponse + "]", 0);
		ex.printStackTrace();
		throw ex;
	}

	private final static boolean windowsIsMacAddress(String macAddressCandidate) {
		if (macAddressCandidate.length() != 17)
			return false;
		return true;
	}

	public String getDefaultGateway() throws IOException {
		String[] cmd = { "cmd", "/c", "ipconfig | findstr /i \"Gateway\"" };

		ProcessBuilder processBuilder = new ProcessBuilder(cmd);
		Process process = processBuilder.start();

		BufferedReader saida = new BufferedReader(new InputStreamReader(process.getInputStream()));

		String linhaSaida = saida.readLine();
		StringTokenizer st = new StringTokenizer(linhaSaida, ":");
		st.nextToken(); // o endereco esta depois do ":"
		return st.nextToken();
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
		JLabel labelIPHost = new JLabel("xxxxxxxxx");
		JLabel labelNomeHost = new JLabel("xxxxxxxx");
		JButton btnNewButton = new JButton("IP do host");
		JLabel labelmac = new JLabel("xxxxxxxxxx");
		JLabel lblNewLabel = new JLabel("xxxxxxxxxx");
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

		JButton btnMacadress = new JButton("MacAdress");
		btnMacadress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// InetAddress addr = InetAddress.getLocalHost();
				// String ip = addr.getHostAddress();
				// String mac = proc.toString();
				try {
					// Obtém o objeto que representa o endereço da máquina local
					InetAddress ip = InetAddress.getLocalHost();

					// Através do objeto obtido, obtém o objeto que representa a interface de rede
					NetworkInterface network = NetworkInterface.getByInetAddress(ip);

					// Recupera o endereço físico
					byte[] mac = network.getHardwareAddress();
					// System.out.print("Current MAC address : ");

					// Obtém o endereço do byte obtido
					// StringBuilder sb = new StringBuilder();
					for (int i = 0; i < mac.length; i++) {
						// Essa parte, o "mágica" está no format utilizando o formato "%02X%s"
						// O "X" no formato indica que o resultado será formatado como um inteiro
						// hexadecimal
						// (nunca tinha utilizado esse "X" no format, achei na documentação =) )
						// FONTE:
						// http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
					}

					// Imprime o endereço obtido
					labelmac.setText(sb.toString());
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (SocketException e1) {
					e1.printStackTrace();
				}
			}

//				Runtime rt = Runtime.getRuntime();
//				Process proc = null;
//				try {
//					proc = rt.exec("getmac");
//				} catch (IOException e2) {
//
//					e2.printStackTrace();
//				}
//				try {
//					InetAddress addr = InetAddress.getLocalHost();
//					String IP = addr.getHostAddress();
//					//String mac = proc.toString();
//					labelmac.setText(getMacAddress());
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//
//			}

		});

		JButton btnNewButton_2 = new JButton("Gateway");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					try {
						lblNewLabel.setText(getDefaultGateway());
					} catch (IOException e1) {

						e1.printStackTrace();
					}

			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(20).addGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addGap(27)
								.addComponent(labelIPHost, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 113,
										GroupLayout.PREFERRED_SIZE)
								.addGap(27).addComponent(labelNomeHost, GroupLayout.PREFERRED_SIZE, 172,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(btnMacadress, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 113,
												GroupLayout.PREFERRED_SIZE))
								.addGap(27).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel).addComponent(labelmac))))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(48)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton)
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(labelIPHost)))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnNewButton_1)
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(labelNomeHost)))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnMacadress)
								.addGroup(groupLayout.createSequentialGroup().addGap(4).addComponent(labelmac)))
						.addGap(18).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_2).addComponent(lblNewLabel))));
		frame.getContentPane().setLayout(groupLayout);
	}
}
