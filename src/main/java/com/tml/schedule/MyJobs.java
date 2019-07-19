package com.tml.schedule;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>定时任务</p>
 * Created by admin on 2019/3/4.
 */

@Component
public class MyJobs {

    private static Logger logger = LoggerFactory.getLogger(MyJobs.class);

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping()
                    .create();

   @Scheduled(cron = "0/5 * * * * ?")
    public void doTest() throws Exception {
        logger.info("doTest begin！");
        logger.info("**************************");
        logger.info("doTest end!");
    }


}
