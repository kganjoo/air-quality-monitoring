package components;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Config {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static CyclicBarrier barrier = new CyclicBarrier(2);
    private static AtomicInteger round = new AtomicInteger(1);



    public static ExecutorService getExecutorService() {
        return executorService;
    }

    public static CyclicBarrier getBarrier() {
        return barrier;
    }

    public static void incrementRound(){
        round.incrementAndGet();
    }
    public static int getRoundValue(){
        return round.get();
    }


}
