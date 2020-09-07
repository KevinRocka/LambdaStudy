package com.rocka.concurrent;

import java.util.concurrent.*;

/**
 * 线程基础
 * <p>
 * 启动线程的三种方式   1.实现Runnable接口   2.继承Thread  3.用Executors创建单线程线城池，实现Callable接口
 * 结束线程的三种方式   1.interrupt()方法中断      2.标志位结束线程
 */
public class ThreadBase {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
//        startDemos();
        endDemos();
    }

    /**
     * 启动线程的三种方式
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void startDemos() throws ExecutionException, InterruptedException {
        //方式1：
        Thread thread1 = new Thread(new ThreadStartDemo1());
        thread1.start();
        //方式2：
        new ThreadStartDemo2().start();
        //方式3：
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new ThreadStartDemo3());
        System.out.println(future.get().toString());
    }

    /**
     * 方式一启动线程  实现Runnable接口
     * 在主线程启
     */
    public static class ThreadStartDemo1 implements Runnable{
        @Override
        public void run() {
            System.out.println("启动一个线程方式1： " + Thread.currentThread().getName());
        }
    }

    /**
     * 方式二启动线程  继承Thread
     */
    public static class ThreadStartDemo2 extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("启动一个线程方式2： " + Thread.currentThread().getName());
        }
    }

    /**
     * 方式三启动线程 实现Callable接口，用ExecutorService线程池创建线程
     */
    public static class ThreadStartDemo3 implements Callable<String>{
        @Override
        public String call(){
            return "启动一个线程方式3： " + Thread.currentThread().getName();
        }
    }


    /******************************************************************************************************************/

    private static void endDemos() throws InterruptedException{
        ThreadStopDemo1 threadStopDemo1 = new ThreadStopDemo1();
        Thread threadStop1 = new Thread(threadStopDemo1);
        threadStop1.start();

        //延迟5秒后进行中断
        Thread.sleep(5000);
        threadStop1.interrupt();

        System.out.println("test");

        System.out.println("------------------------------------------------------------------------------------------");
//        ThreadStopDemo2 threadStopDemo2 = new ThreadStopDemo2();
//        Thread threadStop2 = new Thread(threadStopDemo2);
//        threadStop2.start();
//        Thread.sleep(5000);
//        threadStopDemo2.isExit = true;
//        threadStopDemo2.join();
//        System.out.println("线程2退出");
    }

    /**
     * 方式一停止线程： 使用Thread.interrupt()「通知线程应该中断了」
     */
    public static class ThreadStopDemo1 implements Runnable {

        private long i = 0;

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(1000);
                    i++;
                    System.out.println("Thread 1 running output i :" + i);
                }
            } catch (InterruptedException  e) {
                System.out.println("interrupt");
                // 抛出 InterruptedException 后中断标志被清除，标准做法是再次调用 interrupt 恢复中断
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 方式二停止线程：
     */
    public static class ThreadStopDemo2 extends Thread {
        public volatile boolean isExit = false;//必须用volatile来修饰，会把isExit从工作内存即使更新到主存中，保证可见性

        @Override
        public void run() {
            while (!isExit){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread stop em 2");
            }
        }
    }


}
