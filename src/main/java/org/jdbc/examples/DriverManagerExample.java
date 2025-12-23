
package org.jdbc.examples;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DriverManagerExample {
    public static void main(String[] args) throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        if (url == null || user == null || password == null) {
            System.err.println("Set environment variables: DB_URL, DB_USER, DB_PASSWORD");
            System.exit(1);
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData md = conn.getMetaData();
            System.out.println("Driver name: " + md.getDriverName());
            System.out.println("Driver version: " + md.getDriverVersion());
            System.out.println("Driver major.minor: " + md.getDriverMajorVersion() + "." + md.getDriverMinorVersion());
            System.out.println("JDBC API supported: " + md.getJDBCMajorVersion() + "." + md.getJDBCMinorVersion());
            System.out.println("Database product: " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion());
        }

        System.out.println("Loaded JDBC drivers on classpath:");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver d = drivers.nextElement();
            System.out.println(" - " + d.getClass().getName() + " " + d.getMajorVersion() + "." + d.getMinorVersion());
        }
    }
}