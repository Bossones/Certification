package ru.croc.test;

import java.util.stream.IntStream;

public class TestRunnable implements Runnable {

    @Override
    public void run() {
        IntStream.range(1, 4).forEach(System.out::println);
    }
}
