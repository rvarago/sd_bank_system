package br.edu.ufabc.sd.bank.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import br.edu.ufabc.sd.bank.account.Account;
import br.edu.ufabc.sd.servers.BankServerService;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class BancoAzul {

	//GUI
	protected JFrame frame;
	private JComboBox<String> comboBox;
	private JTextField textContaDestino;
	private JTextField textValorSaque;
	private JTextField textValorDeposito;
	private JLabel lblSaldo;
	
	private Account account;
	
	//RMI Interface
	private BankServerService bancoBrancoService;
	private BankServerService bancoAzulService;
	private final String BANCO_BRANCO_URL = "rmi://localhost/bancoBranco";
	private final String BANCO_AZUL_URL = "rmi://localhost/bancoAzul";
	
	//Banco Branco = 1
	//Banco Azul = 2
	private static final int COD_BANCO = 2;
	private JTextField textValorTransfer;
	
	public void connectHandler() throws Exception {
		try{
			this.bancoBrancoService = (BankServerService) Naming.lookup(this.BANCO_BRANCO_URL);
			this.bancoAzulService = (BankServerService) Naming.lookup(this.BANCO_AZUL_URL);
		}catch (Exception e) {
			throw new Exception("Não foi possível conectar ao servidor");
		}
	}
	
	public void disconnectHandler() throws Exception {
		/*try {
			Naming.unbind(this.BANCO_BRANCO_URL);
			Naming.unbind(this.BANCO_AZUL_URL);
			System.out.println("Desconectado do servidor");
		} catch (Exception e) {
			throw new Exception("Não foi possível desconectar do servidor");
		}*/
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BancoAzul window = new BancoAzul(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void reload() throws RemoteException{
		account = bancoAzulService.retriveAccount(account.getCode());
		lblSaldo.setText(account.getBalance().toString());
		textValorSaque.setText("0.00");
		textValorDeposito.setText("0.00");

	}
	
	private void transfer(Long contaDestino, int bancoDestino, String valor) throws RemoteException{
		Account destino;
		Account origem = this.account;
		BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(valor));
		
		if(bancoDestino == COD_BANCO){
			destino = bancoAzulService.retriveAccount(contaDestino);
			if(destino == null){
				JOptionPane.showMessageDialog(textValorTransfer, "Conta Não Existe");
			}else{
				//bancoAzulService.transfer(origem.getCode(), destino.getCode(), amount);
				bancoAzulService.withdraw(origem.getCode(), amount);
				bancoAzulService.deposit(destino.getCode(), amount);
				reload();
				JOptionPane.showMessageDialog(textValorTransfer, "Transferência Realizada com Sucesso!");
			}
		}else{
			destino = bancoBrancoService.retriveAccount(contaDestino);
			if(destino == null){
				JOptionPane.showMessageDialog(textValorTransfer, "Conta Não Existe");
			}else{
				try{
					bancoAzulService.withdraw(origem.getCode(), amount);
					bancoBrancoService.deposit(destino.getCode(), amount);
					
					reload();
					JOptionPane.showMessageDialog(textValorTransfer, "Transferência Realizada com Sucesso!");
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		
		}
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public BancoAzul(long codConta) throws Exception {
		connectHandler();
		
		account = bancoAzulService.retriveAccount(codConta);
		
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 517, 427);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JLabel lblBancoAzul = new JLabel("BANCO AZUL");
		lblBancoAzul.setBounds(6, 16, 208, 16);
		this.frame.getContentPane().add(lblBancoAzul);

		JLabel lbl1 = new JLabel("Saldo");
		lbl1.setBounds(58, 57, 61, 16);
		this.frame.getContentPane().add(lbl1);

		lblSaldo = new JLabel("0.00");
		lblSaldo.setBounds(131, 57, 208, 16);
		this.frame.getContentPane().add(lblSaldo);

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
		tabbedPane.addTab("Transferências", null, panelTransferencia, null);
		panelTransferencia.setLayout(null);

		JLabel label = new JLabel("Banco Destino");
		label.setBounds(11, 28, 90, 16);
		panelTransferencia.add(label);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(106, 23, 131, 27);
		panelTransferencia.add(comboBox);

		JButton btnTransferencia = new JButton("Efetuar Transferência");
		btnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int bancoDestino = comboBox.getSelectedIndex() ;
					BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(textValorTransfer.getText()));
					reload();
					
					if(bancoDestino == 0){
						JOptionPane.showMessageDialog(comboBox, "Selectione um Banco");
					}else if(amount.compareTo(account.getBalance()) == 1){
						JOptionPane.showMessageDialog(btnTransferencia, "Saldo Insuficiente");
					}else{
						transfer(Long.parseLong(textContaDestino.getText()), bancoDestino, amount.toString());
					}
				}catch(RemoteException rm){
					rm.printStackTrace();
				}
			}
		});
		btnTransferencia.setBounds(247, 59, 177, 29);
		panelTransferencia.add(btnTransferencia);

		JLabel label_1 = new JLabel("Conta Destino");
		label_1.setBounds(11, 64, 90, 16);
		panelTransferencia.add(label_1);

		this.textContaDestino = new JTextField();
		this.textContaDestino.setBounds(105, 59, 130, 26);
		this.textContaDestino.setColumns(10);
		panelTransferencia.add(this.textContaDestino);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(260, 28, 44, 16);
		panelTransferencia.add(lblValor);
		
		textValorTransfer = new JTextField();
		textValorTransfer.setText("0.00");
		textValorTransfer.setColumns(10);
		textValorTransfer.setBounds(306, 23, 103, 26);
		panelTransferencia.add(textValorTransfer);

		JPanel panelSaque = new JPanel();
		tabbedPane.addTab("Saques", null, panelSaque, null);
		panelSaque.setLayout(null);

		JLabel lblValorDoSaque = new JLabel("Valor do Saque");
		lblValorDoSaque.setBounds(24, 59, 93, 16);
		panelSaque.add(lblValorDoSaque);

		this.textValorSaque = new JTextField();
		this.textValorSaque.setBounds(160, 54, 93, 26);
		panelSaque.add(this.textValorSaque);
		this.textValorSaque.setColumns(10);

		JButton btnEfetuarSaque = new JButton("Efetuar Saque");
		btnEfetuarSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(textValorSaque.getText()));
					reload();
					
					if(amount.compareTo(account.getBalance()) == 1){
						JOptionPane.showMessageDialog(btnEfetuarSaque, "Saldo Insuficiente");
					}else{
						System.out.println(amount);
						bancoAzulService.withdraw(account.getCode(), amount);
						reload();
						JOptionPane.showMessageDialog(btnEfetuarSaque, "Saque Efetuado Com Sucesso");
					}
				}catch(RemoteException rm){
					rm.printStackTrace();
				}
				
			}
		});
		btnEfetuarSaque.setBounds(288, 54, 144, 26);
		panelSaque.add(btnEfetuarSaque);

		JPanel panelDeposito = new JPanel();
		tabbedPane.addTab("Depósitos", null, panelDeposito, null);
		panelDeposito.setLayout(null);

		JLabel lblValorDoDeposito = new JLabel("Valor do Deposito");
		lblValorDoDeposito.setBounds(24, 59, 148, 16);
		panelDeposito.add(lblValorDoDeposito);

		this.textValorDeposito = new JTextField();
		this.textValorDeposito.setColumns(10);
		this.textValorDeposito.setBounds(160, 54, 93, 26);
		panelDeposito.add(this.textValorDeposito);

		JButton btnEfetuarDeposito = new JButton("Efetuar Depósito");
		btnEfetuarDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
					BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(textValorDeposito.getText()));
					
					bancoAzulService.deposit(account.getCode(), amount);
					reload();
					JOptionPane.showMessageDialog(btnEfetuarDeposito, "Depósito Efetuado Com Sucesso");
				}catch(RemoteException rm){
					rm.printStackTrace();
				}	
			}
		});
		btnEfetuarDeposito.setBounds(288, 54, 144, 26);
		panelDeposito.add(btnEfetuarDeposito);
		
		JLabel lblInicio = new JLabel("Olá ");
		lblInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInicio.setBounds(259, 16, 239, 16);
		frame.getContentPane().add(lblInicio);
		
		//Populando os campos com os dados da conta
		
		lblInicio.setText("Olá " + account.getOwner());
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try{
					reload();
				}catch(RemoteException rm){
					rm.printStackTrace();
				}	
			}
		});
		btnAtualizar.setBounds(366, 366, 91, 29);
		frame.getContentPane().add(btnAtualizar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					disconnectHandler();
				}catch(Exception e1){
					e1.printStackTrace();
				}	
				frame.dispose();
			}
		});
		btnSair.setBounds(450, 366, 61, 29);
		frame.getContentPane().add(btnSair);
		try{
			reload();
		}catch(RemoteException rm){
			rm.printStackTrace();
		}	
		
		comboBox.addItem("Selecione seu Banco");
		comboBox.addItem("Banco Branco");
		comboBox.addItem("Banco Azul");
		
	}
}
