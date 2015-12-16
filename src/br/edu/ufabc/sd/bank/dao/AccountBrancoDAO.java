package br.edu.ufabc.sd.bank.dao;

import java.util.Set;

import br.edu.ufabc.sd.bank.account.Account;

/**
 * Persistência de classes que implementam {@link Account}.
 *
 * @author rvarago
 *
 */
public interface AccountBrancoDAO {

	/**
	 * Grava uma {@link Account} no banco.
	 *
	 * @param account
	 *            conta a gravar
	 * @throws Exception
	 */
	public void persist(Account account) throws Exception;

	/**
	 * Recupera uma {@link Account} pelo código.
	 *
	 * @param code
	 *            código da conta
	 * @return conta recupera
	 * @throws Exception
	 */
	public Account find(Long code) throws Exception;

	/**
	 * Remove uma {@link Account} do banco.
	 *
	 * @param code
	 *            código da conta
	 * @throws Exception
	 */
	public void remove(Long code) throws Exception;

	/**
	 * Lista as {@link Account}s no banco.
	 *
	 * @return conjunto de contas no banco
	 * @throws Exception
	 */
	public Set<Account> list() throws Exception;

}
