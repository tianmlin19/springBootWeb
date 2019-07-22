package com.tml.javaCore.concurrent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tianmlin19
 * @description
 * @date 2019/7/22 17:26
 * @since 1.0
 */
public class MyTaskImpl implements MyTask {

    private static final Logger logger = LoggerFactory.getLogger(MyTaskImpl.class);

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();


    @Override
    public void doTask() throws Exception {
        logger.info("线程：{}doTask enter!", Thread.currentThread().getName());
        Thread.sleep(1500);
        logger.info("线程：{}doTask success!", Thread.currentThread().getName());
    }

    @Override
    public void statisticsTask() throws Exception {
        logger.info("线程：{}statisticsTask enter!", Thread.currentThread().getName());
        logger.info("线程：{}statisticsTask success!", Thread.currentThread().getName());

    }
}
