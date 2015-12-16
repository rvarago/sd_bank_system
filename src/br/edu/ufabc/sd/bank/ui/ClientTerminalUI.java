package br.edu.ufabc.sd.bank.ui;

import java.math.BigDecimal;
import java.util.Scanner;

import br.edu.ufabc.sd.bank.BankAzulManager;
import br.edu.ufabc.sd.bank.BankBrancoManager;
import br.edu.ufabc.sd.bank.BankClientService;
import br.edu.ufabc.sd.bank.BankClientServiceImpl;
import br.edu.ufabc.sd.bank.BankManager;
import br.edu.ufabc.sd.bank.account.Account;
import br.edu.ufabc.sd.bank.dao.AccountAzulDAO;
import br.edu.ufabc.sd.bank.dao.AccountAzulDAOImpl;
import br.edu.ufabc.sd.bank.dao.AccountBrancoDAO;
import br.edu.ufabc.sd.bank.dao.AccountBrancoDAOImpl;
import br.edu.ufabc.sd.bank.dao.AccountDAO;
import br.edu.ufabc.sd.bank.dao.AccountDAOImpl;

public class ClientTerminalUI {

	private BankClientService bankClientService = new BankClientServiceImpl();
	private AccountDAO accountDAO = new AccountDAOImpl();
	private BankManager bankManager = new BankManager(this.bankClientService, this.accountDAO);
	
	private AccountAzulDAO accountAzulDAO = new AccountAzulDAOImpl();
	private BankAzulManager bankAzulManager = new BankAzulManager(this.bankClientService, this.accountAzulDAO);
	
	private AccountBrancoDAO accountBrancoDAO = new AccountBrancoDAOImpl();
	private BankBrancoManager bankBrancoManager = new BankBrancoManager(this.bankClientService, this.accountBrancoDAO);

	private void execute() {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String nextOperation = null;
			do {
				System.out.println("1 - Listar\n2 - Criar\n3 - Recuperar\n4 - Apagar\n5 - Sair");
				nextOperation = sc.nextLine();
				switch (nextOperation) {
				case "1":
					System.out.println("Banco Azul");
					for (Account account : this.bankAzulManager.getAccounts()) {
						System.out.println(account);
					}
					System.out.println("Banco Branco");
					for (Account account : this.bankBrancoManager.getAccounts()) {
						System.out.println(account);
					}
					break;
				case "2":
					System.out.println("Banco Branco 1 | Banco Azul 2");
					String bank = sc.nextLine();
					if(bank.equals("1")){
						System.out.println("Dono:");
						String owner = sc.nextLine();
						System.out.println("Saldo Inicial (#.#):");
						BigDecimal initialBalance = null;
						initialBalance = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
						this.bankBrancoManager.createAccount(owner, initialBalance);
					}else{
						System.out.println("Dono:");
						String owner = sc.nextLine();
						System.out.println("Saldo Inicial (#.#):");
						BigDecimal initialBalance = null;
						initialBalance = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
						this.bankAzulManager.createAccount(owner, initialBalance);
					}
					
					break;
				case "3":
					System.out.print("Código:");
					Long code = Long.valueOf(sc.nextLine());
					Account account = this.bankBrancoManager.retriveAccount(code);
					String accountOption = null;
					do {
						System.out.println(
								"1 - Saque\n2 - Depósito\n3 - Consulta de Saldo\n4 - Transferência\n5 - Voltar");
						accountOption = sc.nextLine();
						BigDecimal amount;
						switch (accountOption) {
						case "1":
							System.out.println("Quantia:");
							amount = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
							this.bankClientService.withdraw(account, amount);
							break;
						case "2":
							System.out.println("Quantia:");
							amount = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
							this.bankClientService.deposit(account, amount);
							break;
						case "3":
							System.out.println(this.bankClientService.viewBalance(account));
							break;
						case "4":
							System.out.println("Código da Conta de Destino");
							Long codeSink = Long.valueOf(sc.nextLine());
							System.out.println("Quantia:");
							amount = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
							this.bankClientService.transfer(account, this.bankManager.retriveAccount(codeSink), amount);
							break;
						}
					} while (!accountOption.equals("5"));
					break;
				case "4":
					System.out.print("Código:");
					Long codeToRemove = Long.valueOf(sc.nextLine());
					this.bankManager.removeAccount(codeToRemove);
					break;
				}
			} while (!nextOperation.equals("5"));
		} finally {
			sc.close();
		}
	}

	public static void main(String[] args) {
		new ClientTerminalUI().execute();
	}

}
