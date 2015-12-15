package br.edu.ufabc.sd.bank;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

	private static final Bank bank = new Bank();
	private BankClientService bankClientService = new BankClientServiceImpl();

	private void execute() {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String nextOperation = null;
			do {
				System.out.println("1 - Listar\n2 - Criar\n3 - Recuperar\n4 - Sair");
				nextOperation = sc.nextLine();
				switch (nextOperation) {
				case "1":
					for (Account account : Main.bank.getAccounts()) {
						System.out.println(account);
					}
					break;
				case "2":
					System.out.println("Dono:");
					String owner = sc.nextLine();
					System.out.println("Saldo Inicial (#.#):");
					BigDecimal initialBalance = null;
					initialBalance = BigDecimal.valueOf(Double.parseDouble(sc.nextLine()));
					Main.bank.createAccount(owner, initialBalance);
					break;
				case "3":
					System.out.print("Código:");
					Long code = Long.valueOf(sc.nextLine());
					Account account = bank.retriveAccount(code);
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
							this.bankClientService.transfer(account, bank.retriveAccount(codeSink), amount);
							break;
						}
					} while (!accountOption.equals("5"));
					break;
				}
			} while (!nextOperation.equals("4"));
		} finally {
			sc.close();
		}
	}

	public static void main(String[] args) {
		new Main().execute();
	}

}
