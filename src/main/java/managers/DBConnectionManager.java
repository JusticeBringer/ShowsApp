package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    public Connection createConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/pao?useSSL=false";
            String username = "root";
            String password = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static DBConnectionManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private final static class SingletonHolder {
        private static final DBConnectionManager INSTANCE =
                new DBConnectionManager();
    }
}