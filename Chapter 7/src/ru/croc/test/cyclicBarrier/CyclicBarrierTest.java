package ru.croc.test.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    private void removeLions() {
        System.out.println("Remove lions");
    }

    private void cleaningPen() {
        System.out.println("Cleaning pen");
    }

    private void addingLions() {
        System.out.println("Adding lions");
    }

    private void performTasks(CyclicBarrier c1) {
        try {
            removeLions();
            c1.await();
            cleaningPen();
            c1.await();
            addingLions();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        CyclicBarrier c1 = new CyclicBarrier(4);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        var manager = new CyclicBarrierTest();
        try {
            for (int i = 0; i < 4; i++) {
                executorService.submit(() -> manager.performTasks(c1));
            }
        } finally {
            executorService.shutdown();
        }
    }
}
