package br.edu.ufabc.sd.bank.ui;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import br.edu.ufabc.sd.servers.BankServerService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.Naming;

public class Login {

	//User Interface
	private JFrame frame;
	private JTextField textConta;
	private JTextField textSenha;
	private JLabel lblConta;
	private JLabel lblSenha;
	private JComboBox<Object> comboBox;
	private JLabel lblBanco_1;
	private JButton btnEntrar;
	
	
	//RMI Interface
	private BankServerService bancoBrancoService;
	private BankServerService bancoAzulService;
	private final String BANCO_BRANCO_URL = "rmi://localhost/bancoBranco";
	private final String BANCO_AZUL_URL = "rmi://localhost/bancoAzul";
	
	
	
	public void connectHandler() throws Exception {
		try{
			this.bancoBrancoService = (BankServerService) Naming.lookup(this.BANCO_BRANCO_URL);
			this.bancoAzulService = (BankServerService) Naming.lookup(this.BANCO_AZUL_URL);
		}catch (Exception e) {
			throw new Exception("Não foi possível conectar ao servidor");
		}
	}
	
	public void disconnectHandler() throws Exception {
		try {
			Naming.unbind(this.BANCO_BRANCO_URL);
			Naming.unbind(this.BANCO_AZUL_URL);
			System.out.println("Desconectado do servidor");
		} catch (Exception e) {
			throw new Exception("Não foi possível desconectar do servidor");
		}
	}
	

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
		
		comboBox.addItem("Selecione seu Banco");
		comboBox.addItem("Banco Branco");
		comboBox.addItem("Banco Azul");
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedIndex() == 1){
					try {
						connectHandler();
						
						if (bancoBrancoService.retriveAccount(Long.parseLong(textConta.getText())) != null){
							new BancoBranco(Long.parseLong(textConta.getText())).frame.setVisible(true);

							
							frame.dispose();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	
				}else if(comboBox.getSelectedIndex() == 2){
					try {
						connectHandler();
						
						if (bancoAzulService.retriveAccount(Long.parseLong(textConta.getText())) != null){
							new BancoAzul(Long.parseLong(textConta.getText())).frame.setVisible(true);
							
							
							frame.dispose();	
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
		
				}else{
					JOptionPane.showMessageDialog(comboBox, "Selectione um Banco");
				}
			}
		});
		btnEntrar.setBounds(125, 182, 200, 29);
		frame.getContentPane().add(btnEntrar);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 450, 300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JLabel lblBanco = new JLabel("SISTEMA BANCÁRIO");
		lblBanco.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanco.setBounds(0, 18, 450, 16);
		this.frame.getContentPane().add(lblBanco);

		this.textConta = new JTextField();
		this.textConta.setBounds(125, 144, 106, 26);
		this.frame.getContentPane().add(this.textConta);
		this.textConta.setColumns(10);

		this.textSenha = new JTextField();
		this.textSenha.setColumns(10);
		this.textSenha.setBounds(243, 144, 82, 26);
		this.frame.getContentPane().add(this.textSenha);

		this.lblConta = new JLabel("Conta");
		this.lblConta.setBounds(125, 126, 61, 16);
		this.frame.getContentPane().add(this.lblConta);

		this.lblSenha = new JLabel("Senha");
		this.lblSenha.setBounds(243, 126, 105, 16);
		this.frame.getContentPane().add(this.lblSenha);

		this.comboBox = new JComboBox<Object>();
		this.comboBox.setBounds(125, 82, 200, 50);
		this.frame.getContentPane().add(this.comboBox);

		this.lblBanco_1 = new JLabel("Banco");
		this.lblBanco_1.setBounds(125, 75, 211, 16);
		this.frame.getContentPane().add(this.lblBanco_1);
	}
}
