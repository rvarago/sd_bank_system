package br.edu.ufabc.sd.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblConta;
	private JLabel lblSenha;
	private JComboBox comboBox;
	private JLabel lblBanco_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBanco = new JLabel("SISTEMA BANC\u00C1RIO");
		lblBanco.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanco.setBounds(0, 18, 450, 16);
		frame.getContentPane().add(lblBanco);
		
		textField = new JTextField();
		textField.setBounds(125, 93, 106, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(125, 144, 200, 26);
		frame.getContentPane().add(textField_1);
		
		lblConta = new JLabel("Conta");
		lblConta.setBounds(125, 75, 61, 16);
		frame.getContentPane().add(lblConta);
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(125, 126, 61, 16);
		frame.getContentPane().add(lblSenha);
		
		comboBox = new JComboBox();
		comboBox.setBounds(243, 82, 78, 50);
		frame.getContentPane().add(comboBox);
		
		lblBanco_1 = new JLabel("Banco");
		lblBanco_1.setBounds(243, 75, 61, 16);
		frame.getContentPane().add(lblBanco_1);
	}
}
