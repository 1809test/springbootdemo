package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2020/7/1 0001.
 */
public class Test2 {
    public static void main(String[] args) throws Exception{
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0;i<10;i++){
            //Thread.sleep(1000);
            es.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        es.shutdown();
        Thread.sleep(1000);
        System.out.println("----------------------------------");
        //newFixedThreadPoolTest();

        Thread.sleep(1000);
        System.out.println("------------------------------------");
       // newSingleThreadPoolTest();


        Thread.sleep(1000);
        System.out.println("---------------------------------------");
        newScheduleThreadPoolTest();
    }

    public static void newFixedThreadPoolTest(){
        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i = 0;i<10;i++){
            es.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        es.shutdown();
    }

    public static void newSingleThreadPoolTest(){
        ExecutorService es = Executors.newSingleThreadExecutor();
        for(int i = 0;i<10;i++){
            es.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

    }


    public static void newScheduleThreadPoolTest() throws Exception{
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

        for(int i = 0;i<10;i++){
            scheduledExecutorService.schedule(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            },10, TimeUnit.SECONDS);
        }

        scheduledExecutorService.shutdown();
    }
}
