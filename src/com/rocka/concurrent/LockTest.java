package com.rocka.concurrent;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock是一个轻量级同步锁，使用小Demo
 *
 * lock() 与 tryLock()
 */
public class LockTest {
    public static List<Integer> s1 = new ArrayList<>();
    Lock lock = new ReentrantLock();

    public static void main(String args[]) {
        LockTest lockTest = new LockTest();

        new Thread() {
            @Override
            public void run() {
                setName("Thread _ 1");
                lockTest.insertDataTwo(Thread.currentThread());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                setName("Thread _ 2");
                lockTest.insertDataTwo(Thread.currentThread());
            }
        }.start();
    }

    /**
     * 获取锁.lock()
     *
     * @param thread
     */
    public void insertData(Thread thread) {
        lock.lock();//获取锁
        try {
            //处理任务
            System.out.println("Thread Name : " + thread.getName() + " 得到了锁!");
            for (int i = 0; i < 100; i++) {
                s1.add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread Name : " + thread.getName() + " 释放了锁!");
            lock.unlock(); //释放锁
        }
    }

    /**
     * 获得锁.tryLock()
     *
     * @param thread
     */
    public void insertDataTwo(Thread thread) {
        if (lock.tryLock()) {
            try {
                //处理任务
                System.out.println("Thread Name : " + thread.getName() + " 得到了锁!");
                for (int i = 0; i < 100; i++) {
                    s1.add(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread Name : " + thread.getName() + " 释放了锁!");
                lock.unlock(); //释放锁
            }
        } else {
            System.out.println(thread.getName() + "获取锁失败");
        }
    }
    

}
