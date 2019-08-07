package com.tml.javaCore.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tianmlin19
 * @description java8中的lamdba表达式案例
 * @date 2019/8/1 10:55
 * @since 1.0
 */
public class LamdbaEXpression {

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        result.add("qaz");
        result.add("wsx");
        List<String> collect = result.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());

        List<String> collect1 = result.stream().map(String::toUpperCase).collect(Collectors.toList());

        System.out.println(collect1);
        int  sunm=0;
        sunm += 0.015 * 100;
        System.out.println(sunm);
    }

}
