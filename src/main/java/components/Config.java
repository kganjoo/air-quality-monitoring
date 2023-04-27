package components;

import constants.Constants;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Config {
    /**
     * We need 7 threads for the entire round.
     *  7 threads started by data preprocessor for each of its computation
     *  1 thread started by aqi calculator , where index selector keeps running and polls for values
     *   1 thread started by display component , where it polls for display values
     *
     */

    private static final ExecutorService executorService = Executors.newFixedThreadPool(Constants.numThreads);
    // numThreads +1 are running in total , numThreads started by executor service, and 1 main thread
    private static CyclicBarrier barrier = new CyclicBarrier(Constants.numThreads+1);
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
