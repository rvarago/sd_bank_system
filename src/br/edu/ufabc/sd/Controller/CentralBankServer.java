package br.edu.ufabc.sd.Controller;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CentralBankServer {

	private static final String URL = "rmi://localhost/bancoCentral";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			LocateRegistry.createRegistry(1098);
			
			CentralBankServerService serviceBancoCentral = new CentralBankServerServiceImpl();
			
			Naming.rebind(URL, serviceBancoCentral);
			System.out.println("Servidor expondo serviços em: " + URL);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
