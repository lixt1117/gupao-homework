package com.gupao.java.course;


import com.gupao.java.classcode.ICourse;
import com.gupao.java.classcode.factoryMethod.ICourseFactory;
import com.gupao.java.classcode.factoryMethod.JavaCourseFactory;
import com.gupao.java.classcode.factoryMethod.PythonCourseFactory;

/**
 * Created by Tom.
 */
public class FactoryMethodTest {

    public static void main(String[] args) {

        ICourseFactory factory = new PythonCourseFactory();
        ICourse course = factory.create();
        course.record();

        factory = new JavaCourseFactory();
        course = factory.create();
        course.record();

    }

}