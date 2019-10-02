package com.gupao.java;

import com.gupao.java.jdbcdemo.Student;
import com.gupao.java.jdbcdemo.version01.JDBC;
import org.junit.Test;

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
        Student stu = new Student(1, "maoshiting", 26, null);
        jdbc.save(stu);
    }

    @Test
    public void testJdbcDelete() {
        jdbc.delete(1L);
    }

    @Test
    public void testJdbcUpdate() {
        Student stu = new Student(1, "maoshiting", 18, null);
        jdbc.update(stu);
    }

    @Test
    public void testJdbcSearch() {
        Student stu = jdbc.get(3L);
        System.out.println(stu);
    }
}
