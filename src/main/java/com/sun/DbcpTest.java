package com.sun;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author SunG
 * @date 2020/6/6 14:39
 */
public class DbcpTest {
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(DbcpTest.class.getClassLoader().
                    getResourceAsStream("dbcp.properties"));

            // 通过基础数据源工厂类  来创建连接池
            dataSource = BasicDataSourceFactory.createDataSource(properties);
            Connection conn = getConn();
            DatabaseMetaData metaData = conn.getMetaData();

            System.out.println(metaData.getDatabaseProductName() + " " +
                    metaData.getDatabaseProductVersion());

            closeConn(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Connection getConn() {
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static void closeConn(Connection conn) {

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
