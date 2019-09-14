package com.stu.zk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单锁的理解
 */
public class TreeLock {
    static int n = 500;

    public static void secskill() {
        --n;
    }

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock(true);
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    boolean b = lock.tryLock(2, TimeUnit.SECONDS);
                    if (b){
                        String name = Thread.currentThread().getName();
                        secskill();
                        System.out.println("name:"+name+"--"+n);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }


            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                secskill();
                System.out.println("name:"+name+"--"+n);
            }
        };

        for (int i = 0; i < 20; i++) {
            // 测试：runnable和runnable2结果如何。
            new Thread(runnable).start();
        }
    }
}
