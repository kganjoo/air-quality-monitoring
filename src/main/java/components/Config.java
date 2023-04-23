package components;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Config {
    /**
     * We need 7 threads for the entire round.
     *  6 threads started by data preprocessor for each of its computation
     *  1 thread started by aqi calculator , where index selector keeps running and polls for values
     *
     */
    private static final ExecutorService executorService = Executors.newFixedThreadPool(7);
    // 8 threads are running in total , 7 of them are spun by executor service, and 1 main thread
    private static CyclicBarrier barrier = new CyclicBarrier(8);
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
