package com.mthomasleary.colors.managers;

import java.sql.*;
import java.util.Properties;

public class CharacterManager {
    protected static final String SCHEMA_CREATE = "CREATE SCHEMA IF NOT EXISTS challenge;";
    protected static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS challenge.characters (" +
            "    charName CHAR(10), " +
            "    count INT, " +
            "    PRIMARY KEY (charName)" +
            ");";
    protected static final String WRITE_CHAR = "INSERT INTO challenge.characters (charName, count)" +
            " VALUES (?, '1') " +
            " ON DUPLICATE KEY UPDATE count=count+1; ";
    protected static final String READ_CHAR = "SELECT count FROM challenge.characters WHERE charName=?;";

    protected static Connection getConnection() throws ClassNotFoundException, SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "root");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", connectionProps);
        System.out.println("Connected to database");

        return conn;
    }

    public static void lazyInitTables() throws SQLException, ClassNotFoundException {
        try (
                Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(SCHEMA_CREATE);
        ){
            statement.execute();
        }

        try (
                Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(TABLE_CREATE);
        ){
            statement.execute();
        }
    }

    public static void writeChar(char ch) throws SQLException, ClassNotFoundException {
        String use; // account for whitespace
        if(ch == ' '){
            use = "space";
        } else {
            use = "" + ch;
        }

        try (
                Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(WRITE_CHAR);
        ){
            statement.setString(1, use);
            System.out.println("statement is: " + statement.toString());
            statement.executeUpdate();
            System.out.println("update complete");
        }
    }

    public static int readCharCount(String ch) throws SQLException, ClassNotFoundException {
        int ret = 0;
        try (
                Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(READ_CHAR);
        ){
            statement.setString(1, ch);
            System.out.println("statement is:" + statement.toString());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                ret = rs.getInt("count");
                System.out.println("read complete");
            }
        }

        return ret;
    }
}
