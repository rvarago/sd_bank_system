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
public class AccountBrancoDAOImpl implements AccountBrancoDAO {

	private static final String FILE_SUFFIX = ".ser";
	private static final String BASE_DIR = "accounts/bancoBranco/";

	@Override
	public void persist(Account account) throws Exception {
		if (account == null) {
			throw new Exception("Não é permitido gravar conta em branco");
		}
		try (ObjectOutputStream writer = new ObjectOutputStream(
				new FileOutputStream(BASE_DIR + account.getCode() + FILE_SUFFIX))) {
			writer.writeObject(account);
		}
	}

	@Override
	public Account find(Long code) throws Exception {
		Account account;
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(BASE_DIR + code + FILE_SUFFIX))) {
			account = (Account) reader.readObject();
		}
		return account;
	}

	@Override
	public void remove(Long code) throws Exception {
		new File(BASE_DIR + code + FILE_SUFFIX).delete();
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
