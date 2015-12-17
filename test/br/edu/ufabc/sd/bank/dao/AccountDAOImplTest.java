package br.edu.ufabc.sd.bank.dao;

import br.edu.ufabc.sd.Controller.AccountDAOImpl;

/**
 * Testes unitários de {@link AccountDAOImpl}.
 *
 * @author rvarago
 *
 */
public class AccountDAOImplTest {

//	/**
//	 * Stuff
//	 */
//	private AccountDAO dao = new AccountDAOImpl();
//	private Account a1 = new CurrentAccount(1L, "Teste 1", BigDecimal.valueOf(100));
//	private Account a2 = new CurrentAccount(2L, "Teste 2", BigDecimal.valueOf(200));
//	private Account a3 = new CurrentAccount(3L, "Teste 3", BigDecimal.valueOf(300));
//
//	private void persistTest() throws Exception {
//		System.out.println("****************");
//		System.out.println("Teste do persist");
//		this.dao.persist(this.a1);
//		this.dao.persist(this.a2);
//		this.dao.persist(this.a3);
//	}
//
//	private void findTest() throws Exception {
//		System.out.println("****************");
//		System.out.println("Teste do find");
//		Account a1 = this.dao.find(1L);
//		System.out.println("Recuperou: " + a1);
//	}
//
//	private void removeTest() throws Exception {
//		System.out.println("****************");
//		System.out.println("Teste do remove");
//		this.dao.remove(1L);
//	}
//
//	private void listTest() throws Exception {
//		System.out.println("****************");
//		System.out.println("Teste do list");
//		for (Account account : this.dao.list()) {
//			System.out.println("Recuperou: " + account);
//		}
//	}

	private void run() {
//		try {
//			this.persistTest();
//			this.findTest();
//			this.removeTest();
//			this.listTest();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public static void main(String[] args) {
		new AccountDAOImplTest().run();
	}
}
