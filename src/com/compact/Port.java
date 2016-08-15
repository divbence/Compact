package com.compact;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class Port extends JFrame {

	static int txtport;
	static int width = 300;
	static int height = 150;
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	static JTextPane txtpnA;
	static JTextField txtpnB;
	static JButton button0;
	static int value;

	public Port() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Port megadása");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(width, height);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		Font f = new Font(Font.SANS_SERIF, 0, 13);

		txtpnA = new JTextPane();
		txtpnA.setEditable(false);
		txtpnA.setFont(f);
		txtpnA.setText("Adja meg a használni kívánt portot:");
		txtpnA.setBounds(10, 20, 220, 30);
		txtpnA.setOpaque(false);
		contentPane.add(txtpnA);

		txtpnB = new JTextField();
		txtpnB.setEditable(true);
		txtpnB.setFont(f);
		txtpnB.setBounds(225, 24, 30, 20);
		txtpnB.setOpaque(false);
		contentPane.add(txtpnB);

		button0 = new JButton();
		button0.setText("Mentés");
		button0.setBounds(110, 60, 70, 30);
		button0.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				BufferedWriter output = null;
				try {
					//File file = new File("example.txt");
					output = new BufferedWriter(new FileWriter("data/port.txt"));
					output.write(txtpnB.getText() + System.lineSeparator());
					value = Integer.parseInt(txtpnB.getText());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (output != null) {
						try {
							output.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				Window.txtport = value;
				Window.txtpnD.setText(" " + txtpnB.getText());
				dispose();
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
		button0.setVisible(true);
		contentPane.add(button0);
	}

	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Port frame = new Port();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
