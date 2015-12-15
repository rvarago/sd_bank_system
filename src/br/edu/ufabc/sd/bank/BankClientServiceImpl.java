package br.edu.ufabc.sd.bank;

import java.math.BigDecimal;

public class BankClientServiceImpl implements BankClientService {

	@Override
	public void withdraw(Account account, BigDecimal amount) throws IllegalArgumentException {
		account.withdraw(amount);
	}

	@Override
	public void deposit(Account account, BigDecimal amount) throws IllegalArgumentException {
		account.deposit(amount);
	}

	@Override
	public BigDecimal viewBalance(Account account) {
		return account.viewBalance();
	}

	@Override
	public void transfer(Account accountSource, Account accountSink, BigDecimal amount)
			throws IllegalArgumentException {
		accountSource.transfer(accountSink, amount);
	}
}
