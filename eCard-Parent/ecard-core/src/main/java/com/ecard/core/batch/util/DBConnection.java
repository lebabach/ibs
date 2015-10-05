/*
 * DBConnection
 */
package com.ecard.core.batch.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author vinhla
 */
public class DBConnection {
    @Value("${jdbc.second.url}")
    private static String dbURL;
    
    @Value("${jdbc.second.username}")
    private static String dbUser;
    
    @Value("${jdbc.second.password}")
    private static String dbPassword;
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException
    {
        Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        return connection;
    }
}
