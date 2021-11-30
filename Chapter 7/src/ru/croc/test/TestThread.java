package ru.croc.test;

import java.util.stream.IntStream;

public class TestThread extends Thread {

    @Override
    public void run() {
        IntStream.rangeClosed(4, 7).forEach(System.out::println);
    }
}
