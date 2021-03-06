package com.compact;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	static int height = 300;
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
	private static OutputStream output;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;
	static JButton button0;
	static JButton button1;
	static JButton buttonRead;
	static JButton buttonPort;
	static JButton buttonExtract;
	static JButton buttonErase;
	static JTextPane txtpnA;
	static JTextPane txtpnC;
	static JTextField txtpnB;
	static JTextField txtpnD;
	static Date datefinal;
	static String datefinalfinal;
	static Date datefinal2;
	static String datefinalfinal2;
	String string = "99";
	static String del = "95";
	ImageIcon icon;

	public Window() throws IOException {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("T�ska Rendszer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		txtpnA.setText("�llapot:");
		txtpnA.setBounds(20, 220, 80, 30);
		txtpnA.setOpaque(false);
		contentPane.add(txtpnA);

		txtpnC = new JTextPane();
		txtpnC.setEditable(false);
		txtpnC.setFont(f);
		txtpnC.setText("Port:");
		txtpnC.setBounds(416, 220, 80, 30);
		txtpnC.setOpaque(false);
		contentPane.add(txtpnC);

		txtpnB = new JTextField();
		txtpnB.setEditable(false);
		txtpnB.setBackground(Color.WHITE);
		txtpnB.setText(" V�rakoz�s utas�t�sra...");
		txtpnB.setBounds(80, 222, 200, 24);
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
		txtpnD.setBackground(Color.WHITE);
		txtpnD.setHorizontalAlignment(JTextField.CENTER);
		txtpnD.setText(String.valueOf(txtport));
		txtpnD.setBounds(458, 222, 25, 24);
		contentPane.add(txtpnD);

		button0 = new JButton();
		button0.setText("Hidden");
		button0.setBounds(20, 20, 0, 0);
		contentPane.add(button0);

		buttonRead = new JButton();
		buttonRead.setText("D�tum szinkroniz�l�sa");
		buttonRead.setBounds(20, 20, 150, 30);
		buttonRead.addMouseListener(new MouseListener() {
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
		buttonRead.setVisible(true);
		contentPane.add(buttonRead);

		buttonPort = new JButton();
		buttonPort.setText("�j port");
		buttonPort.setBounds(493, 222, 73, 24);
		buttonPort.addMouseListener(new MouseListener() {
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
		buttonPort.setVisible(true);
		contentPane.add(buttonPort);

		buttonExtract = new JButton();
		buttonExtract.setText("Olvas�s");
		buttonExtract.setBounds(20, 70, 150, 30);
		buttonExtract.addMouseListener(new MouseListener() {
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
		buttonExtract.setVisible(true);
		contentPane.add(buttonExtract);

		button1 = new JButton();
		button1.setText("Hidden");
		button1.setBounds(20, 100, 0, 0);
		contentPane.add(button1);

		buttonErase = new JButton();
		buttonErase.setText("Mem�ria t�rl�se");
		buttonErase.setBounds(20, 120, 150, 30);
		buttonErase.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Erase();
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
		buttonErase.setVisible(true);
		contentPane.add(buttonErase);

		JLabel label = new JLabel(icon);
		label.setBounds(0, 0, 600, 300);
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
			txtpnD.setBackground(Color.RED);
			txtpnB.setText(" A megadott port nem tal�lhat�!");
			errCom = true;
			return;
		}
		try {
			txtpnD.setBackground(Color.GREEN);
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			output = serialPort.getOutputStream();

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
			txtpnB.setForeground(Color.BLUE);
			txtpnB.setText(" D�tum szinkroniz�l�sa sikeres!");
			System.out.println("*******End Write*******");
			return;
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public void Erase() throws IOException {
		System.out.println("******Start Erase******");
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
			txtpnD.setBackground(Color.RED);
			txtpnB.setText(" A megadott port nem tal�lhat�!");
			errCom = true;
			return;
		}
		try {
			txtpnD.setBackground(Color.GREEN);
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			output = serialPort.getOutputStream();

			writeData2();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static void writeData2() {
		try {
			output.write(del.getBytes());
			output.flush();
			serialPort.close();
			txtpnB.setForeground(Color.BLUE);
			txtpnB.setText(" Mem�ria t�rl�se sikeres!");
			System.out.println("*******End Erase*******");
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
			txtpnD.setBackground(Color.RED);
			txtpnB.setText(" A megadott port nem tal�lhat�!");
			errCom = true;
			return;
		}

		try {
			txtpnD.setBackground(Color.GREEN);
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);

			try {
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
			txtpnD.setBackground(Color.RED);
			txtpnB.setText(" A megadott port nem tal�lhat�!");
			errCom = true;
			return;
		}

		try {
			txtpnD.setBackground(Color.GREEN);
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			buttonExtract.setEnabled(false);

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					txtpnB.setForeground(Color.BLUE);
					txtpnB.setText(" Adatok kiolvas�sa sikeres!");
					System.out.println("*******End Read*******");
					buttonExtract.setEnabled(true);
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
					BufferedWriter output = null;

					pieces = inputLine.split("\\s");

					DateFormat dateFormat2 = new SimpleDateFormat("MMdd HHmmss");
					datefinal2 = new Date();
					datefinalfinal2 = dateFormat2.format(datefinal2);

					String filenamenumber = pieces[4] + ". t�ska ";
					String filename = filenamenumber + datefinalfinal2;

					try {
						String myDocumentPath = System.getProperty("user.home") + "/" + "Documents";
						output = new BufferedWriter(new FileWriter(myDocumentPath + "/" + filename + ".txt", true));
						int chunk = 10;
						String nulltab = null;
						for (int i = 10; i < pieces.length; i += chunk) {
							if (pieces[i].equals("255")) {
								nulltab = "";
							} else {
								nulltab = "\t";
							}
							String status = null;
							if (pieces[i+5].equals("1")) {
								status = "Hat�stalan�t�s";
							} else if (pieces[i+5].equals("2")) {
								status = "T�ska nyitva";
							} else if (pieces[i+5].equals("3")) {
								status = "Z�r�s" + "\t";
							} else if (pieces[i+5].equals("4")) {
								status = "�les�t�s";
							} else if (pieces[i+5].equals("5")) {
								status = "Riaszt�s";
							} else if (pieces[i+5].equals("6")) {
								status = "Nyit�s" + "\t";
							} else if (pieces[i+5].equals("7")) {
								status = "Hib�s k�rtya";
							} else if (pieces[i+5].equals("8")) {
								status = "Bekapcsol�s";
							} else if (pieces[i+5].equals("9")) {
								status = "Kikapcsol�s";
							} else {
								status = "null" + nulltab;
							}
							String p0 = null;
							String p1 = null;
							String p2 = null;
							String p3 = null;
							String p4 = null;
							if (pieces[i].equals("0")) {
								p0 = "00";
							} else {
								p0 = String.format("%02d", Integer.valueOf(pieces[i]));
							}
							if (pieces[i+1].equals("0")) {
								p1 = "00";
							} else {
								p1 = String.format("%02d", Integer.valueOf(pieces[i+1]));
							}
							if (pieces[i+2].equals("0")) {
								p2 = "00";
							} else {
								p2 = String.format("%02d", Integer.valueOf(pieces[i+2]));
							}
							if (pieces[i+3].equals("0")) {
								p3 = "00";
							} else {
								p3 = String.format("%02d", Integer.valueOf(pieces[i+3]));
							}
							if (pieces[i+4].equals("0")) {
								p4 = "00";
							} else {
								p4 = String.format("%02d", Integer.valueOf(pieces[i+4]));
							}
							String degree  = "\u00b0";
							output.write(String.format(p0 + "-" + p1 + "  " + p2 + ":" + p3 + ":" + p4 + "  " + status + "\t" + "   " + pieces[i+6] + "\t" + " Akku " + pieces[i+7] + "%%" + "\t" + " H�fok " + pieces[i+8] + degree.replace("[", "").replace("]", "").replace(",", "")));
							output.write(System.lineSeparator());
						}
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
