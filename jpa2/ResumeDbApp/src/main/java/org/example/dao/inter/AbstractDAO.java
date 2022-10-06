package org.example.dao.inter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDAO {
    public Connection connect() throws Exception {
        Class.forName("org.postgresql.Driver");
        //Class.forName("com.mysql.jdbc.Driver");
        // com.mysql.jdbc.Driver s;
        //String url = "jdbc:mysql://localhost:3306/postgres";
        String url1 = "jdbc:postgresql:postgres";
        String username = "postgres";
        String password = "admin";
        Connection c = DriverManager.getConnection(url1, username, password);

        return c;
    }

    private static EntityManagerFactory emf = null;

    public EntityManager em() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("resumeAppPU");
        }
        EntityManager em = emf.createEntityManager();
        return em;

    }

    public void closeEmf() {
        emf.close();

    }

}
