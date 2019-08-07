package com.tml.javaCore.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * @author tianmlin19
 * @description 线程池学习
 * 调用future.get()方法时，若对应的线程没有返回结果的时候，主线程会陷入阻塞状态；
 * 而调用future.isDone()方法时，若对应的线程没有返回结果的时候，改方法获取的值是false,同时主线程不会阻塞；
 * @date 2019/8/5 14:48
 * @since 1.0
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> submit = executorService
                            .submit(() -> {
                                System.out.println("线程：" + Thread.currentThread().getName());
                                Thread.sleep(1000);
                                return 110;
                            });
            futures.add(submit);

            //System.out.println(Thread.currentThread().getName() + ":" + submit.isDone());

        }

        //Thread.sleep(5000);
        for (Future<Integer> future : futures) {
            /*if (future.isDone()) {
                System.out.println("已经处理成功了！");
                System.out.println(future.get());
            } else {
                System.out.println("暂时还未处理成功啊！");
            }*/
            //System.out.println(future.isDone());
            System.out.println(future.get());
        }

    }

}
