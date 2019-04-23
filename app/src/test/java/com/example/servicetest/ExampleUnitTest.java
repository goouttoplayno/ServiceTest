package com.example.servicetest;

import android.os.SystemClock;

import org.junit.Test;

import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private AtomicInteger a = new AtomicInteger();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        //
        System.out.println( "当前线程id： " + Thread.currentThread().getId());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println( "start前线程id： " + Thread.currentThread().getId());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println( "run前线程id： " + Thread.currentThread().getId());
            }
        }).run();//run在主线程中执行，start新启动一个线程

        //加入同步块后可以交替输出
//        Runnable r1 = new ThreadTest();
//        Runnable r2 = new ThreadTest();
//        Thread t1 = new Thread(r1);
//        Thread t2 = new Thread(r2);
//        t1.start();
//        t2.start();

    }

    public class ThreadTest implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(" " + i);
                }
            }
        }
    }
}

