package com.tml.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tianmlin19
 * @description 排序的工具类
 * @date 2019/6/5 16:26
 * @since 1.0
 */
public class SortUtil {

    private static Logger logger = LoggerFactory.getLogger(SortUtil.class);

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping()
                    .create();


    /**
     * 选择排序
     * 思路：
     * 选择排序的第一趟处理是从数据序列所有n个数据中选择一个最小的数据作为有序序列中的第1个元素并将它定位在第一号存储位置，
     * 第二趟处理从数据序列的n-1个数据中选择一个第二小的元素作为有序序列中的第2个元素并将它定位在第二号存储位置，依此类推，
     * 当第n-1趟处理从数据序列的剩下的2个元素中选择一个较小的元素作为有序序列中的最后第2个元素并将它定位在倒数第二号存储位置，
     * 至此，整个的排序处理过程就已完成。
     */
    public static void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int tmp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j <= array.length - 1; j++) {
                logger.info("array[i]:{}--array[j]:{}", array[i], array[j]);
                if (array[i] > array[j]) {
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
            logger.info("第：{}次的排序结果为：{}", (i + 1), Arrays.toString(array));
        }
    }

    /**
     * 插入排序
     * 思路：
     * 直接插入排序法的排序原则是：将一组无序的数字排列成一排，左端第一个数字为已经完成排序的数字，其他数字为未排序的数字。然后从左
     * 到右依次将未排序的数字插入到已排序的数字中。
     */
    public static void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int temp, j;
        for (int i = 1; i <= array.length - 1; i++) {
            temp = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
            logger.info("第：{}次插入排序的结果为：{}", i, Arrays.toString(array));
        }
    }

    /**
     * 希尔排序：
     * 希尔排序又称“缩小增量排序”，该方法的基本思想是：先将整个待排元素序列分割成若干个子序列（由相隔某 个“增量”的元素组成的）分别
     * 进行直接插入排序，然后依次缩减增量再进行排序，待整个序列中的元素基本有序（增量足够小）时，再对全体元素进行一次直接插 入排序。
     * 因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的，因此希尔排序在时间效率上比前两种方法有较大提高。
     */
    public static void shellSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int gap, tmp, j;
        for (gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                tmp = array[i];
                j = i - gap;
                while (j >= 0 && tmp < array[j]) {
                    array[j + gap] = array[j];
                    j -= gap;
                }
                array[j + gap] = tmp;
            }

        }
    }


    /**
     * 冒泡排序：
     * 原理是基于元素的比较交换排序
     * 每一次外层循环，通过相邻两个元素的比较，最后一个元素都是本次排序中最大的元素
     */
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int tmp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
            logger.info("本次：{}排序的结果为：{}", i, Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        int[] array = {13, 4, 89, 6, 55, 2};
        //selectionSort(array);
        //insertSort(array);
        //shellSort(array);
        //bubbleSort(array);

        Integer d = new Integer(100);
        Integer a = 100;
        Integer b = 100;
        Integer c = Integer.valueOf(100);


        /*logger.info("a == b:{}", a == b);
        logger.info("a == c:{}", a == c);
        logger.info("a == d:{}", a == d);
        logger.info("a.hashCode():{}", a.hashCode());
        logger.info("b.hashCode():{}", b.hashCode());
        logger.info("c.hashCode():{}", c.hashCode());
        logger.info("d.hashCode():{}", d.hashCode());

        logger.info("array:{}", Arrays.toString(array));*/


        logger.info("*********************");
        List<String> resultList = new ArrayList();
        resultList.add("tml");
        resultList.add("yy");
        resultList.add("ldt");
        resultList.add("wyl");
        logger.info("resultList:{}",resultList);

        List<String> change = new ArrayList<>();
        for (String str : resultList) {
            change.add(str);
        }
        change.remove("tml");
        logger.info("resultList:{}",resultList);
        logger.info("change:{}",change);
    }

}
