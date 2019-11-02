package com.gupao.java.classcode.factoryMethod;

import com.gupao.java.classcode.ICourse;
import com.gupao.java.classcode.JavaCourse;

/**
 * Created by Tom.
 */
public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
