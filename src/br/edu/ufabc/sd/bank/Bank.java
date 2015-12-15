package br.edu.ufabc.sd.bank;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Bank {
	private static Long accountCodeGenerator = Long.valueOf(1);

	private Map<Long, Account> accounts;

	public Bank() {
		this.accounts = new HashMap<>();
	}

	public Long createAccount(String owner, BigDecimal initialBalance) {
		Account account = new CurrentAccount(accountCodeGenerator++, owner, initialBalance);
		this.accounts.put(account.getCode(), account);
		return account.getCode();
	}

	public Account retriveAccount(Long code) {
		return this.accounts.get(code);
	}

	public Collection<Account> getAccounts() {
		return this.accounts.values();
	}
}
