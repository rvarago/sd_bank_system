package br.edu.ufabc.sd.bank.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

public class BancoAzul {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BancoAzul window = new BancoAzul();
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
	public BancoAzul() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 517, 415);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JLabel lblBancoAzul = new JLabel("BANCO AZUL");
		lblBancoAzul.setBounds(6, 16, 438, 16);
		this.frame.getContentPane().add(lblBancoAzul);

		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(58, 57, 61, 16);
		this.frame.getContentPane().add(lblSaldo);

		JLabel lblNewLabel = new JLabel("0.00");
		lblNewLabel.setBounds(131, 57, 208, 16);
		this.frame.getContentPane().add(lblNewLabel);

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBackground(UIManager.getColor("CheckBox.background"));
		textPane.setBounds(131, 85, 313, 82);
		this.frame.getContentPane().add(textPane);

		JLabel lblExtrato = new JLabel("Extrato");
		lblExtrato.setBounds(58, 85, 61, 16);
		this.frame.getContentPane().add(lblExtrato);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(18, 182, 474, 193);
		this.frame.getContentPane().add(tabbedPane);

		JPanel panelTransferencia = new JPanel();
		tabbedPane.addTab("Transfer\u00EAncias", null, panelTransferencia, null);
		panelTransferencia.setLayout(null);

		JLabel label = new JLabel("Banco Destino");
		label.setBounds(11, 28, 90, 16);
		panelTransferencia.add(label);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(106, 23, 131, 27);
		panelTransferencia.add(comboBox);

		JButton button = new JButton("Efetuar Transfer\u00EAncia");
		button.setBounds(249, 23, 177, 29);
		panelTransferencia.add(button);

		JLabel label_1 = new JLabel("Conta Destino");
		label_1.setBounds(11, 64, 90, 16);
		panelTransferencia.add(label_1);

		this.textField = new JTextField();
		this.textField.setBounds(105, 59, 130, 26);
		this.textField.setColumns(10);
		panelTransferencia.add(this.textField);

		JButton button_1 = new JButton("Atualizar");
		button_1.setBounds(295, 59, 100, 29);
		panelTransferencia.add(button_1);

		JPanel panelSaque = new JPanel();
		tabbedPane.addTab("Saques", null, panelSaque, null);
		panelSaque.setLayout(null);

		JLabel lblValorDoSaque = new JLabel("Valor do Saque");
		lblValorDoSaque.setBounds(6, 19, 93, 16);
		panelSaque.add(lblValorDoSaque);

		this.textField_1 = new JTextField();
		this.textField_1.setBounds(16, 47, 93, 26);
		panelSaque.add(this.textField_1);
		this.textField_1.setColumns(10);

		JButton btnEfetuarSaque = new JButton("Efetuar Saque");
		btnEfetuarSaque.setBounds(6, 79, 108, 26);
		panelSaque.add(btnEfetuarSaque);

		JPanel panelDeposito = new JPanel();
		tabbedPane.addTab("Dep\u00F3sitos", null, panelDeposito, null);
		panelDeposito.setLayout(null);

		JLabel lblValorDoDeposito = new JLabel("Valor do Deposito");
		lblValorDoDeposito.setBounds(17, 26, 148, 16);
		panelDeposito.add(lblValorDoDeposito);

		this.textField_2 = new JTextField();
		this.textField_2.setColumns(10);
		this.textField_2.setBounds(27, 54, 93, 26);
		panelDeposito.add(this.textField_2);

		JButton btnEfetuarDepsitp = new JButton("Efetuar Dep\u00F3sito");
		btnEfetuarDepsitp.setBounds(17, 86, 148, 26);
		panelDeposito.add(btnEfetuarDepsitp);
	}
}
