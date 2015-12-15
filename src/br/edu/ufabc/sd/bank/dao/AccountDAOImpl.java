package br.edu.ufabc.sd.bank.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

import br.edu.ufabc.sd.bank.account.Account;

/**
 * Implementação de {@link AccountDAO}.
 *
 * @author rvarago
 *
 */
public class AccountDAOImpl implements AccountDAO {

	private static final String BASE_DIR = "accounts/";

	@Override
	public void persist(Account account) throws Exception {
		if (account == null) {
			throw new Exception("Não é permitido gravar conta em branco");
		}
		try (ObjectOutputStream writer = new ObjectOutputStream(
				new FileOutputStream(BASE_DIR + account.getCode() + ".ser"))) {
			writer.writeObject(account);
		}
	}

	@Override
	public Account find(Long code) throws Exception {
		Account account;
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(BASE_DIR + code + ".ser"))) {
			account = (Account) reader.readObject();
		}
		return account;
	}

	@Override
	public void remove(Long code) throws Exception {
		new File(BASE_DIR + code).delete();
	}

	@Override
	public Set<Account> list() throws Exception {
		Set<Account> accounts = new TreeSet<>();
		for (String file : new File(BASE_DIR).list()) {
			try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(BASE_DIR + file))) {
				accounts.add((Account) reader.readObject());
			}
		}
		return accounts;
	}
}