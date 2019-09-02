package com.tml.javaCore.designMode.proxy.jdkProxy;

import java.lang.reflect.Proxy;

/**
 * @author tianmlin19
 * @description
 * @date 2019/8/26 14:59
 * @since 1.0
 */
public class Clent {

    public static void main(String[] args) {

        PersonHandler<Person> personHandler = new PersonHandler<>(new Student("tml"));

        Person person = (Person) Proxy
                        .newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class}, personHandler);


        person.setName("zj");

        ProxyUtil.log(person.getClass().getCanonicalName());

    }

}
