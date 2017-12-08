package com.github.cyc.javademo.createthread;

/**
 * Created by cyc on 2017/12/8.
 */
public class MyThread1 extends Thread {

    public MyThread1(String name) {
        // 指定线程名
        super(name);
    }

    @Override
    public void run() {
        System.out.println("run(), current thread is " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("main(), current thread is " + Thread.currentThread().getName());
        // 创建线程
        MyThread1 myThread1 = new MyThread1("MyThread1");
        myThread1.start();
    }
}
