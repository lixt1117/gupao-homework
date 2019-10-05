package com.gupao.java.jdbc;

import com.gupao.java.jdbcDemo.Student;
import com.gupao.java.jdbcDemo.version02.JDBC;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class Jdbc02Test {
    private JDBC jdbc = new JDBC();

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testJdbcSave() {
        Student stu = new Student(1, "lixiaotian", 26, null);
        jdbc.save(stu);
    }

    @Test
    public void testJdbcDelete() {
        jdbc.delete(1L);
    }

    @Test
    public void testJdbcUpdate() {
        Student stu = new Student(2, "maoshiting", 18, null);
        jdbc.update(stu);
    }

    @Test
    public void testJdbcSearch() {
        Student stu = jdbc.get(3L);
        System.out.println(stu);
    }

    @Test
    public void testJdbcSearchAll() {
        List<Student> list = new ArrayList<>();
        list = jdbc.list();
        System.out.println(list);
    }
}
