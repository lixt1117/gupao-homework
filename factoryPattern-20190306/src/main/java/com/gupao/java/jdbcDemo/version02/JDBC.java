package com.gupao.java.jdbcDemo.version02;

import com.gupao.java.jdbcDemo.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBC {

    public void save(Student stu) {
        String sql = "INSERT INTO t_student(name,age) VALUES(?,?)";
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
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
            JdbcUtil.close(null, null, conn);
        }
    }

    // 删除学生信息
    public void delete(Long id) {
        String sql = "DELETE  FROM t_student WHERE id=?";
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            // 3. 创建语句对象
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            // 4. 执行SQL语句
            ps.executeUpdate();
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, null, conn);
        }
    }

    // 修改学生信息
    public void update(Student stu) {
        String sql = "UPDATE t_student SET name=?,age=? WHERE id=?";
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
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
            JdbcUtil.close(null, null, conn);
        }
    }

    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id=?";
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection();            // 3. 创建语句对象
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
            JdbcUtil.close(null, null, conn);
        }
        return null;
    }

    public List<Student> list() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM t_student ";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        try {
            conn=JdbcUtil.getConnection();
            // 3. 创建语句对象
            ps = conn.prepareStatement(sql);
            // 4. 执行SQL语句
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
                Student stu = new Student(id, name, age,sex);
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
}
