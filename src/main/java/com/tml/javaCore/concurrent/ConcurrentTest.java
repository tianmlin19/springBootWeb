package com.tml.javaCore.concurrent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tianmlin19
 * @description 并发测试类
 * @date 2019/7/22 16:51
 * @since 1.0
 */
public class ConcurrentTest {

    private static final Logger logger = LoggerFactory.getLogger(ConcurrentTest.class);

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();

    private MyTask myTask;


    public static void main(String[] args) {
        ConcurrentTest test = new ConcurrentTest();
        test.myTask = new MyTaskImpl();
        test.currentWork(5);

    }


    /**
     * 并发性测试
     */
    public void currentWork(int threads) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch threadWork = new CountDownLatch(1);
        CountDownLatch statisticsThread = new CountDownLatch(threads);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                try {
                    logger.info("线程:{}等待执行任务！", threadName);
                    //线程等待，一起并发执行
                    threadWork.await();
                    myTask.doTask();
                    logger.info("线程:{}执行任务成功，消费时间：{}", threadName, (System.currentTimeMillis() - startTime));

                } catch (Exception e) {
                    logger.error("线程：{}执行任务异常！", threadName, e);

                } finally {
                    statisticsThread.countDown();
                }

            });
        }

        try {
            //此处唤醒以上等待的线程，让其同时并发执行
            logger.info("统计线程：{}开始唤醒工作线程了!", Thread.currentThread().getName());
            threadWork.countDown();
            statisticsThread.await();
            logger.info("统计线程：{}开始统计结果了!", Thread.currentThread().getName());
            myTask.statisticsTask();
            logger.info("统计线程:{}执行任务成功，线程总的消费时间：{}", Thread.currentThread().getName(),
                            (System.currentTimeMillis() - startTime));

        } catch (Exception e) {
            logger.error("统计线程：{}统计出现异常！", Thread.currentThread().getName(), e);
        } finally {
            executor.shutdown();
        }
    }

}
