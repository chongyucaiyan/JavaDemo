package com.github.cyc.javademo.createthread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by cyc on 2017/12/8.
 */
public class MyThread3 implements Callable<Integer> {
    private int a;
    private int b;

    public MyThread3(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("call(), current thread is " + Thread.currentThread().getName());
        // 模拟执行
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 返回结果
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println("main(), current thread is " + Thread.currentThread().getName());
        // 创建线程
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread3(1, 2));
        Thread thread = new Thread(futureTask, "MyThread3");
        thread.start();
        // 获取结果
        try {
            Integer result = futureTask.get();
            System.out.println("main(), result is " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
