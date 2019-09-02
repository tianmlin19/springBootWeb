package com.tml.javaCore.designMode.proxy.jdkProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianmlin19
 * @description
 * @date 2019/8/26 14:47
 * @since 1.0
 */
@Slf4j
public class ProxyUtil {

    public static void start() {
        log.info("start: {}", System.currentTimeMillis());
    }

    public static void finish() {
        log.info("finish: {}", System.currentTimeMillis());
    }

    public static void log(String name) {
        log.info("log: {}", name);
    }

}
