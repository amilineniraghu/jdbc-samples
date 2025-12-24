package org.jdbc.examples;

import java.sql.*;

public class JdbcConnectionLifeCycle {
    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        //DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        if (url == null || user == null || password == null) {
            System.err.println("Set environment variables: DB_URL, DB_USER, DB_PASSWORD");
            System.exit(1);
        }
        Connection connection = DriverManager.getConnection(url, user, password);
        if (null == connection) {
            System.err.println("Unable to establish connection to the database");
            throw new SQLException("Connection is null");
        }

        System.out.println("Connection established successfully to the database");

        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        try (ResultSet rs = statement.executeQuery("select * from users")) {
            System.out.println("Query executed successfully");
            while (rs.next()) {
                System.out.println("User: " + rs.getString("user_name") + ", UserId: " + rs.getInt("userid"));
            }
        }

        connection.close();
        System.out.println("Connection closed successfully");
    }
}
