package com.tml.javaCore.concurrent;

import java.lang.ref.WeakReference;

/**
 * @author tianmlin19
 * @description java引用类型案例，测试案例需要设置虚拟机的内存-Xms2M -Xmx3M
 * @date 2019/8/8 10:38
 * @since 1.0
 */
public class ReferenceDemo {

    public static void main(String[] args) {
        ReferenceDemo demo = new ReferenceDemo();
        WeakReference sf = new WeakReference(demo);
        demo = null;
        System.out.println("是否被回收"+sf.get());
        System.gc();
        System.out.println("是否被回收"+sf.get());


    }

    private static void testStrongReference() {
        //创建一个1Mde字节数组
        //byte[] buff = new byte[1024 * 1024 * 600];
    }

}
