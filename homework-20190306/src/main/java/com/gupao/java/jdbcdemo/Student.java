package com.gupao.java.jdbcdemo;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Student {

    private long id;
    private String name;
    private int age;
    private String sex;
}
