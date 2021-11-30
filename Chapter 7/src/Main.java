import ru.croc.test.TestRunnable;
import ru.croc.test.TestThread;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {
    public static int counter = 0;
    public static void main(String[] args) throws Exception {
        System.out.println("begin");

        // Testing Runnable and Thread
        /*new Thread(new TestRunnable()).start();
        new TestThread().start();
        new Thread(new TestRunnable()).start();*/

        Runnable task1 = () -> System.out.println("Hello, all guys from task1");
        Runnable task2 = () -> {for (int i = 0; i < 1_000_000_000; i++) counter++;};
        ExecutorService executorService = null;

        //Testing Single Thread Executor
/*        try {
            executorService = Executors.newSingleThreadExecutor();
            executorService.execute(task1);
            executorService.execute(task2);
            executorService.execute(task1);
        } finally {
            if (Objects.nonNull(executorService)) executorService.shutdown();
        }*/

        //Testing polling with using methods of Future interface
        try {
            executorService = Executors.newSingleThreadExecutor();
            Future<?> future = executorService.submit(task2);
            future.get(1, TimeUnit.SECONDS);
            System.out.println("Reached!");
            System.out.println(counter);
        } catch (TimeoutException e) {
            System.out.println("Not reached yet!" + counter);
        } finally {
            if (Objects.nonNull(executorService)) executorService.shutdown();
        }
        System.out.println("end");
    }
}
