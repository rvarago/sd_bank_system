package br.edu.ufabc.sd.bank;

import java.math.BigDecimal;
import java.util.Collection;

import br.edu.ufabc.sd.bank.account.Account;
import br.edu.ufabc.sd.bank.account.CurrentAccount;
import br.edu.ufabc.sd.bank.dao.AccountDAO;

/**
 * Centralizador da lógica bancária.
 *
 * @author rvarago
 *
 */
public class BankManager {

	private static Long accountCodeGenerator = Long.valueOf(1);

	private BankClientService bankClientService;
	private AccountDAO accountDAO;

	public BankManager(BankClientService bankClientService, AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	public Long createAccount(String owner, BigDecimal initialBalance) {
		Account account = new CurrentAccount(accountCodeGenerator++, owner, initialBalance);
		try {
			this.accountDAO.persist(account);
		} catch (Exception e) {
			accountCodeGenerator--;
			e.printStackTrace();
		}
		return account.getCode();
	}

	public void withdraw(Long code, BigDecimal amount) {
		try {
			Account account = this.accountDAO.find(code);
			this.bankClientService.withdraw(account, amount);
			this.accountDAO.persist(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deposit(Long code, BigDecimal amount) {
		try {
			Account account = this.accountDAO.find(code);
			this.bankClientService.deposit(account, amount);
			this.accountDAO.persist(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BigDecimal viewBalance(Long code) {
		try {
			Account account = this.accountDAO.find(code);
			this.bankClientService.viewBalance(account);
			return account.viewBalance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void transfer(Long accountSourceCode, Long accountSinkCode, BigDecimal amount) {
		try {
			Account accountSource = this.accountDAO.find(accountSourceCode);
			Account accountSink = this.accountDAO.find(accountSinkCode);
			this.bankClientService.transfer(accountSource, accountSink, amount);
			this.accountDAO.persist(accountSource);
			this.accountDAO.persist(accountSink);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Account retriveAccount(Long code) {
		try {
			return this.accountDAO.find(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void removeAccount(Long code) {
		try {
			this.accountDAO.remove(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<Account> getAccounts() {
		try {
			return this.accountDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
