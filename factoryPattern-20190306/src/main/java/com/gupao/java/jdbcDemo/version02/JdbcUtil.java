package com.gupao.java.jdbcDemo.version02;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Auther: lixiaotian
 * @Date: 2019/10/3 09:14
 * @Description:
 */
public class JdbcUtil {
    public static String url;
    public static String user;
    public static String password;
    static {
        // 1. 加载注册驱动
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/resourses/jdbc.properties"));
            url = (String)properties.get("mysql.url");
            user = (String)properties.get("mysql.user");
            password = (String)properties.get("mysql.password");
            Class.forName((String)properties.get("mysql.driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            // 2. 获取数据库连接
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 释放资源
    public static void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
