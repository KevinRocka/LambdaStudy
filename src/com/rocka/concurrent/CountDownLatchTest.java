package com.rocka.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信（而不是用作互斥的作用）
 *
 * await():使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断或超出了指定的等待时间。
 * countDown():递减锁存器的计数，如果计数到达零，则释放所有等待的线程。如果当前计数大于零，则将计数减少.
 */
public class CountDownLatchTest {

    public static void main(String args[]) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch cdCountDownLatch = new CountDownLatch(1);
        final CountDownLatch orderCountDownLatch = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            Runnable runnable = () -> {
                try {
                    System.out.println("选手" + Thread.currentThread().getName() + "等待发布命令");
                    cdCountDownLatch.await();
                    System.out.println("选手" + Thread.currentThread().getName() + "已经接受命令");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("选手" + Thread.currentThread().getName() + "已完成到达");
                    orderCountDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            executorService.submit(runnable);
        }


        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("裁判" + Thread.currentThread().getName() + "即将发布口令");
            cdCountDownLatch.countDown();
            System.out.println("裁判" + Thread.currentThread().getName() + "已发送口令，正在等待所有人到达");
            orderCountDownLatch.await();
            System.out.println("...................所有选手到达终点...................");
            System.out.println("裁判" + Thread.currentThread().getName() + "汇总成绩排名");
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }

}
