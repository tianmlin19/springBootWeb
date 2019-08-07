package com.tml.javaCore.concurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tianmlin19
 * @description 线程池的拒绝策略案例
 * @date 2019/8/7 17:28
 * @since 1.0
 */
@Slf4j
public class AbortPolicyDemo {

    public static void main(String[] args) {
        /*
        创建一个核心池大小为1 最大池数量为1 阻塞队列长度为1 的线程池
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>(1));

        discardOldestPolicy(executor);

        for (int i = 0; i < 5; i++) {
            executor.execute(()->{
                log.info("线程：{}正在执行！", Thread.currentThread().getName());
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }


    /**
     * 抛弃任务并抛出{@Code RejectedExecutionException}异常
     * @param executor
     */
    public static void abortPolicy(ThreadPoolExecutor executor){
        executor.setRejectedExecutionHandler(new AbortPolicy());
    }


    /**
     * 任务不丢弃，由当前线程执行，不再额外的创建线程
     * @param executor
     */
       public static void callerRunsPolicy(ThreadPoolExecutor executor){
        executor.setRejectedExecutionHandler(new CallerRunsPolicy());
    }


    /**
     * 丢弃任务，但不抛出异常
     * @param executor
     */
    public static void discardPolicy(ThreadPoolExecutor executor){
        executor.setRejectedExecutionHandler(new DiscardPolicy());
    }

    /**
     * 丢弃队列中的任务
     * @param executor
     */
    public static void discardOldestPolicy(ThreadPoolExecutor executor){
        executor.setRejectedExecutionHandler(new DiscardOldestPolicy());
    }





}
