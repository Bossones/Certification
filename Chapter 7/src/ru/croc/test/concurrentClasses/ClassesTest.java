package ru.croc.test.concurrentClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ClassesTest {

    int s2 = 0;

    public void method() throws InterruptedException {

        var ser = Executors.newSingleThreadExecutor();

        s2 = 1111;
        try {
            ser.execute(() -> {
                s2++;
            });
        } finally {
            if (ser != null) ser.shutdown();
        }

        ser.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println(s2);


    }

    static int getInt() {
        synchronized (ClassesTest.class) {
            return 3;
        }
    }

    public static void main(String[] args) throws InterruptedException {

/*        List<Integer> testList = new ArrayList<>(List.of(1, 2, 3, 4));
        for (int n: testList) {
            System.out.println(n);
            testList.add(9);
        }
        System.out.println(testList);*/

        List.of("kaka", "ronaldo", "dodik").parallelStream().reduce(0,
                (c1, c2) -> c1 + c2.length(),
                Integer::sum);
    }
}
