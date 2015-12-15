package br.edu.ufabc.sd.bank;

import java.math.BigDecimal;

import br.edu.ufabc.sd.bank.account.Account;

/**
 * Define operações entre cliente e banco.
 * 
 * @author rvarago
 */
public interface BankClientService {
	/**
	 * Saque.
	 *
	 * @param account
	 * @param amount
	 * @throws IllegalArgumentException
	 */
	public void withdraw(Account account, BigDecimal amount) throws IllegalArgumentException;

	/**
	 * Depósito.
	 *
	 * @param account
	 * @param amount
	 * @throws IllegalArgumentException
	 */
	public void deposit(Account account, BigDecimal amount) throws IllegalArgumentException;

	/**
	 * Consulta de saldo.
	 *
	 * @param account
	 * @return
	 * @throws IllegalArgumentException
	 */
	public BigDecimal viewBalance(Account account) throws IllegalArgumentException;;

	/**
	 * Transferência.
	 *
	 * @param accountSource
	 * @param accountSink
	 * @param amount
	 * @throws IllegalArgumentException
	 */
	public void transfer(Account accountSource, Account accountSink, BigDecimal amount) throws IllegalArgumentException;
}
