package com.sun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author SunG
 * @date 2020/6/6 14:39
 */
public class JdbcTest {
    public static final String DRIVER = "com.mysql.jdbc.Driver";

    public static final String URL = "jdbc:mysql://localhost:3306/test";

    public static final String USER = "root";

    public static final String PASS = "123456";


    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;

        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL,USER,PASS);
        stmt = conn.createStatement();
        result = stmt.executeQuery("show variables like 'basedir'");

        while (result.next()){
            String name = result.getString("variable_name");
            String value = result.getString("value");
            System.out.println(name + " " + value);
        }

        stmt.close();
        result.close();
        conn.close();

    }
}
