package com.github.cyc.javademo.createthread;

/**
 * Created by cyc on 2017/12/8.
 */
public class MyThread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("run(), current thread is " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("main(), current thread is " + Thread.currentThread().getName());
        // 创建线程
        Thread thread = new Thread(new MyThread2(), "MyThread2");
        thread.start();
    }
}
