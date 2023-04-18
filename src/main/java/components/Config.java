package components;

import java.util.concurrent.*;

public class Config {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static CyclicBarrier barrier = new CyclicBarrier(2);



    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static CyclicBarrier getBarrier() {
        return barrier;
    }


}
