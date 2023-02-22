package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
	private static EntityManagerFactory emf;

	static {
		emf = Persistence.createEntityManagerFactory("dbinfo");
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();

	}

	public static void close() {
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}
}