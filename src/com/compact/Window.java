package com.compact;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Window extends JFrame implements SerialPortEventListener {

	static int port = 3;
	static int txtport;
	static int width = 600;
	static int height = 450;
	static int currentPort;
	String read;
	BufferedReader reader;
	static String inputLine;
	String[] pieces;
	static boolean errFile = false;
	static boolean errData = false;
	static boolean errCom = false;
	static boolean errImg = false;
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static SerialPort serialPort;
	private static BufferedReader input;
	@SuppressWarnings("unused")
	private static OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	static JButton button0;
	static JButton button1;
	static JButton button2;
	static JButton button3;
	static JTextPane txtpnA;
	static JTextPane txtpnC;
	static JTextField txtpnB;
	static JTextField txtpnD;
	static Date datefinal;
	static String datefinalfinal;
	String string = "99";
	ImageIcon icon;

	public Window() throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Utility");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(width, height);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Font f = new Font(Font.SANS_SERIF, 0, 15);
		icon = new ImageIcon("data/bg.png");

		txtpnA = new JTextPane();
		txtpnA.setEditable(false);
		txtpnA.setFont(f);
		txtpnA.setText("Állapot:");
		txtpnA.setBounds(20, 370, 80, 30);
		txtpnA.setOpaque(false);
		contentPane.add(txtpnA);

		txtpnC = new JTextPane();
		txtpnC.setEditable(false);
		txtpnC.setFont(f);
		txtpnC.setText("Port:");
		txtpnC.setBounds(420, 370, 80, 30);
		txtpnC.setOpaque(false);
		contentPane.add(txtpnC);

		txtpnB = new JTextField();
		txtpnB.setEditable(false);
		txtpnB.setBackground(Color.WHITE);
		txtpnB.setText(" Várakozás utasításra...");
		txtpnB.setBounds(80, 372, 200, 24);
		txtpnB.setOpaque(false);
		contentPane.add(txtpnB);

		try {
			reader = new BufferedReader(new FileReader("data/port.txt"));
		} catch (FileNotFoundException a) {
			a.printStackTrace();
			errFile = true;
		}
		try {
			for (int i = 1; i < 0; ++i)
				reader.readLine();
			read = reader.readLine();
			txtport = Integer.parseInt(read);
		} catch (NullPointerException c) {
			c.printStackTrace();
			errData = true;
		}

		txtpnD = new JTextField();
		txtpnD.setEditable(false);
		txtpnD.setText(" " + txtport);
		txtpnD.setBounds(460, 372, 21, 24);
		txtpnD.setOpaque(false);
		contentPane.add(txtpnD);

		button0 = new JButton();
		button0.setText("Hidden");
		button0.setBounds(20, 20, 0, 0);
		contentPane.add(button0);

		button1 = new JButton();
		button1.setText("Dátum szinkronizálása");
		button1.setBounds(20, 20, 150, 30);
		button1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		button1.setVisible(true);
		contentPane.add(button1);

		button2 = new JButton();
		button2.setText("Új port");
		button2.setBounds(493, 372, 73, 25);
		button2.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Port();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		button2.setVisible(true);
		contentPane.add(button2);

		button3 = new JButton();
		button3.setText("Olvasás");
		button3.setBounds(20, 70, 150, 30);
		button3.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Extract();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		button3.setVisible(true);
		contentPane.add(button3);

		JLabel label = new JLabel(icon);
		label.setBounds(0, -35, 600, 450);
		contentPane.add(label);
	}

	public static void main(String[] args) throws Exception {
		Thread t = new Thread() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					Thread.sleep(10000);
				} catch (InterruptedException ie) {
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	public void Read() throws IOException {
		System.out.println("******Start Write******");
		txtpnB.setText(" Folyamatban");
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		if (txtport == 0) {
			currentPort = port;
		} else {
			currentPort = txtport;
		}
		final String PORT_NAMES[] = { "COM" + currentPort };
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			txtpnB.setForeground(Color.RED);
			txtpnB.setText(" A megadott port nem található!");
			errCom = true;
			return;
		}
		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// input = new BufferedReader(new
			// InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// serialPort.addEventListener(this);
			// serialPort.notifyOnDataAvailable(true);
			writeData();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static void writeData() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy MM dd HH mm ss");
			datefinal = new Date();
			datefinalfinal = dateFormat.format(datefinal);
			System.out.println(datefinalfinal);
			output.write(datefinalfinal.getBytes());
			output.flush();
			serialPort.close();
			// contentPane.removeAll();
			// contentPane.repaint();
			// txtpnB.setText("Done!");
			txtpnB.setForeground(Color.BLUE);
			txtpnB.setText(" Dátum szinkronizálása sikeres!");
			System.out.println("*******End Write*******");
			return;
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void Extract() throws IOException {
		System.out.println("******Start Read******");
		txtpnB.setForeground(Color.BLACK);
		txtpnB.setText(" Folyamatban");
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		if (txtport == 0) {
			currentPort = port;
		} else {
			currentPort = txtport;
		}
		final String PORT_NAMES[] = { "COM" + currentPort };

		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			txtpnB.setForeground(Color.RED);
			txtpnB.setText(" A megadott port nem található!");
			errCom = true;
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// input = new BufferedReader(new
			// InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);

			try {
				// int sent = 99;
				output.write(string.getBytes());
				output.flush();
				serialPort.close();
				System.out.println("Sent value: " + string);
				ExtractInitialize();
			} catch (Exception e) {
				System.err.println(e.toString() + " dikk");
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void ExtractInitialize() throws IOException {
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		if (txtport == 0) {
			currentPort = port;
		} else {
			currentPort = txtport;
		}
		final String PORT_NAMES[] = { "COM" + currentPort };

		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			txtpnB.setForeground(Color.RED);
			txtpnB.setText(" A megadott port nem található!");
			errCom = true;
			return;
		}

		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					txtpnB.setForeground(Color.BLUE);
					txtpnB.setText(" Adatok kiolvasása sikeres!");
					System.out.println("*******End Read*******");
					serialPort.close();
					serialPort.removeEventListener();
				}
			};
			javax.swing.Timer t = new javax.swing.Timer(10000, taskPerformer);
			t.setRepeats(false);
			t.start();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				inputLine = null;
				if (input.ready()) {
					inputLine = input.readLine();
					System.out.println(inputLine);
					BufferedWriter output = null;
					try {
						output = new BufferedWriter(new FileWriter("log.txt", true));
						output.write(inputLine + System.lineSeparator());
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (output != null) {
							output.close();
						}
					}
				}
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
}
