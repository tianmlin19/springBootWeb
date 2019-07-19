package com.tml.constants;

import lombok.Data;

/**
 * @author tianmlin19
 * @description
 * @date 2019/7/12 16:15
 * @since 1.0
 */
@Data
public class Person {

    public String name;

    public int age;

    public Person testOverRide(){
        Person person = new Person();
        person.name = "human";
        return person;
    }
}