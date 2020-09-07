package com.rocka.concurrent;

import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程间协作的方式: wait(),notify() 等待-通知机制
 * <p>
 * 例如：wait()和notify()方法需要放在synchronized同步代码块中
 * <p>
 * Join的小用法，主要是将并行执行的线程变为串行执行
 * <p>
 * 例如：在A线程中调用了B线程的join()时，表示只有当B线程执行完成后才能执行A线程
 */
public class ObjectTest {

    private static Boolean run = true;//控制是否生产和消费
    private static final Integer MAX_CAPACITY = 5;//缓冲区最大数量
    private static final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();//缓冲队列

    public static void main(String[] args) {
        //wait(),notify()
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        producer.start();
        consumer.start();

        //join
        MakeCount t1 = new MakeCount();
        MakeCount t2 = new MakeCount();
        t1.start();
        try {
            t1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        t2.start();
    }

    /**
     * 生产者
     */
    static class Producer extends Thread {
        @Override
        public void run() {
            while (run) {
                synchronized (queue) {
                    while (queue.size() >= MAX_CAPACITY) {
                        try {
                            System.out.println("队列已经满员，等待消费.");
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        String str = UUID.randomUUID().toString();
                        queue.put(str);
                        System.out.println("工厂生产了: " + str);
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    queue.notifyAll();
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer extends Thread {
        @Override
        public void run() {
            while (run) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("队列为空，等待生产");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        System.out.println("消费：" + queue.take());
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    queue.notifyAll();//通知生产者和消费者
                }
            }
        }
    }

    /**
     * 计数
     */
    static class MakeCount extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 31; i++) {
                System.out.println("the thread : " + Thread.currentThread().getName() + "  output  count number : " + i);
            }
        }
    }

}
