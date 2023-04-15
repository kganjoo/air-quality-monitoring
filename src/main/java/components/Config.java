package components;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Config {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);



    public static ExecutorService getExecutorService() {
        return executorService;
    }
}
