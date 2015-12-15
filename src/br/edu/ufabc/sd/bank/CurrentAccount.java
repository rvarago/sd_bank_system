package br.edu.ufabc.sd.bank;

import java.math.BigDecimal;

/**
 * @author rvarago
 */
public class CurrentAccount extends Account {

	private static final long serialVersionUID = -1369625699816793123L;

	public CurrentAccount() {
	}

	public CurrentAccount(Long code, String owner, BigDecimal balance) {
		super(code, owner, balance);
	}

	@Override
	public void withdraw(BigDecimal amount) throws IllegalArgumentException {
		if (amount.compareTo(super.getBalance()) > 0) {
			throw new IllegalArgumentException("Saldo Insufiente");
		}
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Não é possível sacar valor negativo");
		}
		super.setBalance(super.getBalance().subtract(amount));
	}

	@Override
	public void deposit(BigDecimal amount) {
		if (amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Não é possível depositar valor negativo");
		}
		super.setBalance(super.getBalance().add(amount));
	}

	@Override
	public BigDecimal viewBalance() throws IllegalArgumentException {
		return super.getBalance();
	}

	@Override
	public void transfer(Account accountSink, BigDecimal amount) throws IllegalArgumentException {
		if (accountSink.getBalance().compareTo(amount) > 0) {
			throw new IllegalArgumentException("Saldo Insufiente");
		}
		accountSink.setBalance(accountSink.getBalance().subtract(amount));
		super.setBalance(super.getBalance().add(amount));
	}
}
