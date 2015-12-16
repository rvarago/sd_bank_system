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

import br.edu.ufabc.sd.bank.BankAzulManager;
import br.edu.ufabc.sd.bank.BankBrancoManager;
import br.edu.ufabc.sd.bank.BankClientService;
import br.edu.ufabc.sd.bank.BankClientServiceImpl;
import br.edu.ufabc.sd.bank.account.Account;
import br.edu.ufabc.sd.bank.dao.AccountAzulDAO;
import br.edu.ufabc.sd.bank.dao.AccountAzulDAOImpl;
import br.edu.ufabc.sd.bank.dao.AccountBrancoDAO;
import br.edu.ufabc.sd.bank.dao.AccountBrancoDAOImpl;

import javax.swing.SwingConstants; 

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

public class BancoBranco {

	//GUI
	protected JFrame frame;
	private JComboBox<String> comboBox;
	private JTextField textContaDestino;
	private JTextField textValorSaque;
	private JTextField textValorDeposito;
	private JLabel lblSaldo;
	
	//BackEnd
	private BankClientService bankClientService;
	private AccountBrancoDAO accountBrancoDAO;
	private BankBrancoManager bankBrancoManager;
	private Account account;
	
	//Banco Branco = 1
	private static final int COD_BANCO = 1;
	private JTextField textValorTransfer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BancoBranco window = new BancoBranco(1);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void reload(){
		account = bankBrancoManager.retriveAccount(account.getCode());
		lblSaldo.setText(account.getBalance().toString());
		textValorSaque.setText("0.00");
		textValorDeposito.setText("0.00");

	}
	
	private void transfer(Long contaDestino, int bancoDestino, String valor){
		Account destino;
		Account origem = this.account;
		BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(valor));
		
		if(bancoDestino == COD_BANCO){
			destino = bankBrancoManager.retriveAccount(contaDestino);
			if(destino == null){
				JOptionPane.showMessageDialog(textValorTransfer, "Conta Não Existe");
			}else{
				bankBrancoManager.transfer(origem.getCode(), destino.getCode(), amount);
				reload();
				JOptionPane.showMessageDialog(textValorTransfer, "Transferência Realizada com Sucesso!");
			}
		}else{
			AccountAzulDAO accountAzulDAO = new AccountAzulDAOImpl();
			BankAzulManager bankAzulManager = new BankAzulManager(bankClientService, accountAzulDAO);
			
			destino = bankAzulManager.retriveAccount(contaDestino);
			if(destino == null){
				JOptionPane.showMessageDialog(textValorTransfer, "Conta Não Existe");
			}else{
				try{
					bankBrancoManager.withdraw(origem.getCode(), amount);
					bankAzulManager.deposit(destino.getCode(), amount);
					
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
	 */
	public BancoBranco(long codConta) {
		bankClientService = new BankClientServiceImpl();
		accountBrancoDAO = new AccountBrancoDAOImpl();
		bankBrancoManager = new BankBrancoManager(bankClientService, accountBrancoDAO);
		
		account = bankBrancoManager.retriveAccount(codConta);
		
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 517, 427);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);

		JLabel lblBancoAzul = new JLabel("BANCO BRANCO");
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
				int bancoDestino = comboBox.getSelectedIndex() ;
				reload();
				BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(textValorTransfer.getText()));
				if(bancoDestino == 0){
					JOptionPane.showMessageDialog(comboBox, "Selectione um Banco");
				}else if(amount.compareTo(account.getBalance()) == 1){
					JOptionPane.showMessageDialog(btnTransferencia, "Saldo Insuficiente");
				}else{
					transfer(Long.parseLong(textContaDestino.getText()), bancoDestino, textValorTransfer.getText());
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
				reload();
				BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(textValorSaque.getText()));
				if(amount.compareTo(account.getBalance()) == 1){
					JOptionPane.showMessageDialog(btnEfetuarSaque, "Saldo Insuficiente");
				}else{
					bankBrancoManager.withdraw(account.getCode(), amount);
					reload();
					JOptionPane.showMessageDialog(btnEfetuarSaque, "Saque Efetuado Com Sucesso");
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
				BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(textValorDeposito.getText()));
				
				bankBrancoManager.deposit(account.getCode(), amount);
				reload();
				JOptionPane.showMessageDialog(btnEfetuarDeposito, "Depósito Efetuado Com Sucesso");
				
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
				reload();
			}
		});
		btnAtualizar.setBounds(366, 366, 91, 29);
		frame.getContentPane().add(btnAtualizar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSair.setBounds(450, 366, 61, 29);
		frame.getContentPane().add(btnSair);
		reload();
		
		comboBox.addItem("Selecione seu Banco");
		comboBox.addItem("Banco Branco");
		comboBox.addItem("Banco Azul");
		
	}
}
