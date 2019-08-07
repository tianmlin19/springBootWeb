package com.tml.javaCore.concurrent;

/**
 * @author tianmlin19
 * @description 定义线程工作的任务
 * @date 2019/7/22 16:59
 * @since 1.0
 */
public interface MyTask {

    String doTask() throws Exception;


    void statisticsTask() throws Exception;

}
