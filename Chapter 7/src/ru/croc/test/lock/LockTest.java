package ru.croc.test.lock;

import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private static int counter = 0;

    public static void printMessage(Lock lock) {
        try {
            if (lock.tryLock(900, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("Получен lock. Я поток " + ++counter);
                    Thread.sleep(2000);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Доступ не получен, делаю что то другое, печатаю данное смс");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Begin");
        Lock lock = new ReentrantLock();
        Runnable printTask1 = () -> LockTest.printMessage(lock);
        Runnable printTask2 = () -> LockTest.printMessage(lock);
        Runnable printTask3 = () -> LockTest.printMessage(lock);
        Runnable printTask4 = () -> LockTest.printMessage(lock);

        ExecutorService executorService1 = null;
        try {
            executorService1 = Executors.newFixedThreadPool(2);
            executorService1.submit(printTask1);
            executorService1.submit(printTask2);
            executorService1.submit(printTask3);
            executorService1.submit(printTask4);

        } finally {
            executorService1.shutdown();
        }



        System.out.println("End");
    }
}
