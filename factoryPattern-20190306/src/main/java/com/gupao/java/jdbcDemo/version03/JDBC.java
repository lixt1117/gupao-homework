package com.gupao.java.jdbcDemo.version03;

import com.gupao.java.jdbcDemo.Student;

import java.util.ArrayList;
import java.util.List;

public class JDBC {

    public void save(Student stu) {
        String sql = "INSERT INTO t_student(name,age,sex) VALUES(?,?,?)";
        JdbcTemplate.insert(sql, stu.getName(), stu.getAge(), stu.getSex());
    }

    // 删除学生信息
    public void delete(Long id) {
        String sql = "DELETE  FROM t_student WHERE id=?";
        JdbcTemplate.delete(sql, id);
    }

    // 修改学生信息
    public void update(Student stu) {
        String sql = "UPDATE t_student SET name=?,age=?,sex=? WHERE id=?";
        JdbcTemplate.update(sql, stu.getName(), stu.getAge(), stu.getSex(), stu.getId());
    }

    //按ID获取学生信息
    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id=?";
        return JdbcTemplate.query(sql, id).get(0);
    }

    //按条件获取学生信息集合
    public List<Student> list(Student stu) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM t_student WHERE name=? AND age=?";
        return JdbcTemplate.query(sql, stu.getName(), stu.getAge());
    }
}
