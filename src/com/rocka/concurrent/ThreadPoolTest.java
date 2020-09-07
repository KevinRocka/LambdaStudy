package com.rocka.concurrent;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 */
public class ThreadPoolTest {


    @Test
    public void testPool() {
        System.out.println("pool start");
        final AtomicInteger id = new AtomicInteger(1);
        ScheduledExecutorService sPool = Executors.newScheduledThreadPool(30,
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("CronManager-Scheduled-Thread-" + id.getAndAdd(1));
                    return thread;
                });
        sPool.schedule(s1, 6000, TimeUnit.MILLISECONDS);
        System.out.println("pool end");
    }

    Runnable s1 = () -> {
        System.out.println("xxx");
    };
}
