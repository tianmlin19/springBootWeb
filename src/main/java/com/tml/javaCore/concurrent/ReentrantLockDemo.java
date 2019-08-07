package com.tml.javaCore.concurrent;

/**
 * @author tianmlin19
 * @description 可重入锁实验
 * @date 2019/7/31 10:40
 * @since 1.0
 */
public class ReentrantLockDemo {

    private static int number = 0;


   /* public static void increaseNum() {
        new Thread(() -> number++).start();

    }*/

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 300; i++) {
            new Thread(() -> number = number + 1).start();
        }

        Thread.sleep(3000);
        System.out.println("number:" + number);

    }

}
