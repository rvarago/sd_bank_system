package br.edu.ufabc.sd.bank.ui;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblConta;
	private JLabel lblSenha;
	private JComboBox<Object> comboBox;
	private JLabel lblBanco_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 450, 300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JLabel lblBanco = new JLabel("SISTEMA BANC\u00C1RIO");
		lblBanco.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanco.setBounds(0, 18, 450, 16);
		this.frame.getContentPane().add(lblBanco);

		this.textField = new JTextField();
		this.textField.setBounds(125, 93, 106, 26);
		this.frame.getContentPane().add(this.textField);
		this.textField.setColumns(10);

		this.textField_1 = new JTextField();
		this.textField_1.setColumns(10);
		this.textField_1.setBounds(125, 144, 200, 26);
		this.frame.getContentPane().add(this.textField_1);

		this.lblConta = new JLabel("Conta");
		this.lblConta.setBounds(125, 75, 61, 16);
		this.frame.getContentPane().add(this.lblConta);

		this.lblSenha = new JLabel("Senha");
		this.lblSenha.setBounds(125, 126, 61, 16);
		this.frame.getContentPane().add(this.lblSenha);

		this.comboBox = new JComboBox<Object>();
		this.comboBox.setBounds(243, 82, 78, 50);
		this.frame.getContentPane().add(this.comboBox);

		this.lblBanco_1 = new JLabel("Banco");
		this.lblBanco_1.setBounds(243, 75, 61, 16);
		this.frame.getContentPane().add(this.lblBanco_1);
	}
}
