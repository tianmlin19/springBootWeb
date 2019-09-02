package com.tml.javaCore.designMode.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author tianmlin19
 * @description
 * @date 2019/8/26 14:44
 * @since 1.0
 */
public class PersonHandler<T> implements InvocationHandler{

    private T target;

    public PersonHandler(T target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //前置操作
        ProxyUtil.start();
        Object invoke = method.invoke(target, args);
        //打印日志
        ProxyUtil.log(Objects.isNull(args[0]) ? "null" : args[0].toString());
        //调用接收之后的操作
        ProxyUtil.finish();

        return invoke;
    }
}
