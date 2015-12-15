package br.edu.ufabc.sd.bank;

import java.math.BigDecimal;

import br.edu.ufabc.sd.bank.account.Account;

/**
 * @author rvarago
 */
public interface BankClientService {
	public void withdraw(Account account, BigDecimal amount) throws IllegalArgumentException;

	public void deposit(Account account, BigDecimal amount) throws IllegalArgumentException;

	public BigDecimal viewBalance(Account account);

	public void transfer(Account accountSource, Account accountSink, BigDecimal amount) throws IllegalArgumentException;
}
