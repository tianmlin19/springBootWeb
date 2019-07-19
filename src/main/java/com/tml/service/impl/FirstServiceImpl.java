package com.tml.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tml.constants.Student;
import com.tml.service.FirstService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2019/2/11.
 */
@Service
public class FirstServiceImpl implements FirstService {

    private static Logger logger = LoggerFactory.getLogger(FirstServiceImpl.class);

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping()
                    .create();

    @Override
    public List<Student> listAllStudent() {
        Student s1 = new Student();
        s1.name = "tml";
        s1.score = 90;
        s1.age = 20;

        Student s2 = new Student();
        s2.name = "haozi";
        s2.score = 99;
        s2.age = 20;

        Student s3 = new Student();
        s3.name = "田茂林";
        s3.score = 100;
        s3.age = 18;

        Student s4 = new Student();
        s4.name = "tianmlin";
        s4.score = 150;
        s4.age = 16;

        Student s5 = new Student();
        s5.name = "tml19";
        s5.score = 129;
        s5.age = 21;



        List<Student> result = new ArrayList<>();
        result.add(s1);
        result.add(s2);
        result.add(s3);
        result.add(s4);
        result.add(s5);

        return result;
    }
}
