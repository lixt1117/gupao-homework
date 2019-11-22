package com.gupao.java.classcode.jdbcDemo.version03;

import com.gupao.java.classcode.jdbcDemo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lixiaotian
 * @Date: 2019/10/3 21:25
 * @Description:
 */
public class JdbcTemplate {
    /**
     *
     * @Description:查询统一模板
     *
     * @auther: lixiaotian
     * @date: 2019/10/3 21:26
     */
    public static List<Student> query(String sql, Object... params) {
        List<Student> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            // 设置值
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                Student stu = new Student(id, name, age, sex);
                list.add(stu);
            }
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ps, conn);
        }
        return list;
    }

    /**
     *
     * @Description:更新模板方法
     * @Param: sql-查询语句,params-查询条件参数
     * @Return: 0-更新失败,1-更新成功
     * @auther: lixiaotian
     * @date: 2019/10/4 09:42
     */
    public static int update(String sql, Object... params) {
        int result = 0;
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            // 4. 执行SQL语句
            preparedStatement.executeUpdate();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 释放资源
            JdbcUtil.close(null, null, connection);

        }
        return result;
    }

    /**
     *
     * @Description:插入数据的模板方法
     * @Param:
     * @Return: 0-插入失败;1-插入成功
     * @auther: lixiaotian
     * @date: 2019/10/4 11:43
     */
    public static int insert(String sql, Object... params) {
        int result = 0;
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, null, connection);
        }
        return result;
    }

    public static int delete(String sql, Object... params) {
        int result = 0;
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
            result = 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, null, connection);
        }
        return result;
    }
}
