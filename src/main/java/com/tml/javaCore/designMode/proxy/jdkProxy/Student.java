package com.tml.javaCore.designMode.proxy.jdkProxy;

/**
 * @author tianmlin19
 * @description
 * @date 2019/8/26 14:42
 * @since 1.0
 */
public class Student implements Person {

    private String name;

    public Student(String name){
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
