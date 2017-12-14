package com.github.cyc.javademo.threadpool;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cyc on 2017/12/14.
 */
public class MyThreadPool {
    // 核心线程数
    private static final int CORE_POOL_SIZE = Math.max(Runtime.getRuntime().availableProcessors() - 1, 2);
    // 最大线程数
    private static final int MAXIMUM_POOL_SIZE = CORE_POOL_SIZE * 2;
    // 存活时间
    private static final int KEEP_ALIVE_SECONDS = 60;
    // 工作队列
    private static final BlockingQueue<Runnable> sWorkQueue = new LinkedBlockingQueue<>(100);
    // 线程工厂
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "MyThreadPool #" + mCount.getAndIncrement());
        }
    };

    public static void main(String[] args) {
        // 创建线程池
        Executor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_SECONDS, TimeUnit.SECONDS, sWorkQueue, sThreadFactory);
        // 提交任务
        System.out.println(new Date() + ", main(), the number of tasks is " + MAXIMUM_POOL_SIZE);
        for (int i = 0; i < MAXIMUM_POOL_SIZE; i++) {
            executor.execute(new MyRunnableTask());
        }
    }

    private static class MyRunnableTask implements Runnable {

        @Override
        public void run() {
            System.out.println(new Date() + ", run(), current thread is " + Thread.currentThread().getName());
            // 模拟任务执行
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
