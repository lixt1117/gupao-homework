package com.gupao.java.jdbc;

import com.gupao.java.jdbcDemo.Student;
import com.gupao.java.jdbcDemo.version03.JDBC;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class Jdbc03Test {
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
        Student stu = Student.builder().sex("0").age(17).name("李效天").build();
        jdbc.save(stu);
    }

    @Test
    public void testJdbcDelete() {
        jdbc.delete(33L);
    }

    @Test
    public void testJdbcUpdate() {
        Student stu = Student.builder().name("毛诗婷").id(2L).age(17).sex("1").build();
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
        Student stu = Student.builder().name("maoshiting").age(18).build();
        list = jdbc.list(stu);
        System.out.println(list);
    }
}
