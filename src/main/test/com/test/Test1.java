package com.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2020/6/30 0030.
 */
public class Test1 {


    public static void main(String[]args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>() ;
        Producer p = new Producer(blockingQueue);
        Producer p1 = new Producer(blockingQueue);
        Producer p2 = new Producer(blockingQueue);
        Consumer c1 = new Consumer(blockingQueue);
        ExecutorService es =Executors.newCachedThreadPool();
        es.execute(p);
        es.execute(c1);
        es.execute(p2);
        es.execute(p1);
        Thread.sleep(10*1000);
        p.stop();
        p1.stop();
        p2.stop();
        Thread.sleep(2000);
        es.shutdown();
    }
}
class Producer implements  Runnable{
    private BlockingQueue<String> blockingQueue;
    AtomicInteger ai = new AtomicInteger();
    private boolean isrunning = true;
    public Producer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;

    }
    String data = "";
    public void run() {
        while(isrunning){
            data = data+ ai.getAndIncrement();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                boolean bb=blockingQueue.offer(data,2, TimeUnit.SECONDS);
                if (bb == true) {
                    System.out.println(Thread.currentThread().getName()+"生产了"+data+"放入队列成功");
                }else{
                    System.out.println("放入队列失败");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        isrunning = false;
    }
}
class Consumer implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private boolean isrunning = true;
    public Consumer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }


    public void run() {
        while(isrunning){
            try {
                String data=blockingQueue.poll(2,TimeUnit.SECONDS);
                if (data == null) {
                    System.out.println("超过2s没有获取数据，退出消费者线程");
                    isrunning=false;
                }else{
                    System.out.println("成功消费了数据"+data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}