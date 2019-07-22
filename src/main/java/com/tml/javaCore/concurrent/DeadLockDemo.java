package com.tml.javaCore.concurrent;

/**
 * @author tianmlin19
 * @description 模拟死锁
 * @date 2019/7/22 14:43
 * @since 1.0
 */
public class DeadLockDemo {

    private static String A = "A";

    private static String B = "B";

    public static void main(String[] args) {
        new DeadLockDemo().deadLock();

    }


    private void deadLock() {
        new Thread(() -> {
            synchronized (A){
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }

        },"thread_01").start();

        new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("2");
                }
            }

        },"thread_02").start();
    }

}
