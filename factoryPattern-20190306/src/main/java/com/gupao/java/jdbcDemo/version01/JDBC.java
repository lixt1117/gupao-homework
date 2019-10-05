package com.gupao.java.jdbcDemo.version01;

import com.gupao.java.jdbcDemo.Student;

import java.sql.*;

public class JDBC {

    public void save(Student stu) {
        String sql = "INSERT INTO t_student(name,age) VALUES(?,?)";
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lxt?useSSL=false&useUnicode=true" +
                            "&characterEncoding=UTF8&useOldAliasMetadataBehavior=true",
                    "root", "lxtmysql666");
            // 3. 创建语句对象
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, stu.getName());
            ps.setObject(2, stu.getAge());
            // 4. 执行SQL语句
            ps.executeUpdate();
            // 5. 释放资源
        } catch (Exception e) {
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

    // 删除学生信息
    public void delete(Long id) {
        String sql = "DELETE  FROM t_student WHERE id=?";
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lxt?useSSL=false&useUnicode=true" +
                            "&characterEncoding=UTF8&useOldAliasMetadataBehavior=true",
                    "root", "lxtmysql666");
            // 3. 创建语句对象
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            // 4. 执行SQL语句
            ps.executeUpdate();
            // 5. 释放资源
        } catch (Exception e) {
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

    // 修改学生信息
    public void update(Student stu) {
        String sql = "UPDATE t_student SET name=?,age=? WHERE id=?";
        Connection conn = null;
        Statement st = null;
        try {
            // 1. 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lxt?useSSL=false&useUnicode=true" +
                            "&characterEncoding=UTF8&useOldAliasMetadataBehavior=true",
                    "root", "lxtmysql666");
            // 3. 创建语句对象
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, stu.getName());
            ps.setObject(2, stu.getAge());
            ps.setObject(3, stu.getId());
            // 4. 执行SQL语句
            ps.executeUpdate();
            // 5. 释放资源
        } catch (Exception e) {
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

    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id=?";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            // 1. 加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 获取数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lxt?useSSL=false&useUnicode=true" +
                            "&characterEncoding=UTF8&useOldAliasMetadataBehavior=true",
                    "root", "lxtmysql666");
            // 3. 创建语句对象
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            // 4. 执行SQL语句
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                Student stu = new Student(id, name, age, sex);
                return stu;
            }
            // 5. 释放资源
        } catch (Exception e) {
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
        return null;
    }
}
