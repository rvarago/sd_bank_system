package br.edu.ufabc.sd.Controller;

import java.math.BigDecimal;

import br.edu.ufabc.sd.bank.account.Account;

/**
 * Implementação de {@link BankClientService}.
 *
 * @author rvarago
 */
public class BankClientServiceImpl implements BankClientService {

	@Override
	public void withdraw(Account account, BigDecimal amount) throws IllegalArgumentException {
		try{
			account.withdraw(amount);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void deposit(Account account, BigDecimal amount) throws IllegalArgumentException {
		
		try{
			account.deposit(amount);
		}catch (Exception e){
			e.printStackTrace();
		}
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
