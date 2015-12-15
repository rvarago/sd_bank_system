package br.edu.ufabc.sd.bank;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author rvarago
 */
public abstract class Account implements Comparable<Account>, Serializable {

	private static final long serialVersionUID = -9009058536140781612L;

	private Long code;
	private String owner;
	private BigDecimal balance;

	public Account() {
	}

	public Account(Long code, String owner, BigDecimal balance) {
		this.code = code;
		this.owner = owner;
		this.balance = balance;
	}

	public Long getCode() {
		return this.code;
	}

	public String getOwner() {
		return this.owner;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Account) {
			return this.code.equals(((Account) obj).getCode());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getCode().hashCode();
	}

	@Override
	public int compareTo(Account o) {
		return this.getCode().compareTo(o.getCode());
	}

	@Override
	public String toString() {
		return "Conta: " + this.getCode() + " - Cliente: " + this.getOwner() + " - Saldo: " + this.getBalance();
	}

	/**
	 * Saque
	 *
	 * @param amount
	 *            quantia a sacar
	 * @return saldo em conta
	 * @throws IllegalArgumentException
	 */
	public abstract void withdraw(BigDecimal amount) throws IllegalArgumentException;

	/**
	 * Depósito
	 *
	 * @param amount
	 *            quantia a depositar
	 * @return saldo em conta
	 * @throws IllegalArgumentException
	 */
	public abstract void deposit(BigDecimal amount) throws IllegalArgumentException;

	/**
	 * Consulta de saldo
	 *
	 * @return saldo em conta
	 * @throws IllegalArgumentException
	 */
	public abstract BigDecimal viewBalance() throws IllegalArgumentException;

	/**
	 * Transferência entre contas
	 *
	 * @param accountSink
	 *            conta a receber a transferência
	 * @param amount
	 *            quantia a transferir
	 * @throws IllegalArgumentException
	 */
	public abstract void transfer(Account accountSink, BigDecimal amount) throws IllegalArgumentException;
}
