package com.tml.constants;


import lombok.Data;


@Data
public class Student extends Person{

    public int score;

    @Override
    public Student testOverRide() {
        Student student = new Student();
        student.name = "tml";
        return student;
    }
}
