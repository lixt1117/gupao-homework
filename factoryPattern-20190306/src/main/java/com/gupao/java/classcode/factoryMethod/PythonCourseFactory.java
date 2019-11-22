package com.gupao.java.classcode.factoryMethod;

import com.gupao.java.classcode.ICourse;
import com.gupao.java.classcode.PythonCourse;

/**
 * Created by Tom.
 */
public class PythonCourseFactory implements ICourseFactory {

    public ICourse create() {
        return new PythonCourse();
    }
}
