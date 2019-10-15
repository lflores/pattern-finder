package com.triadsoft;

/**
 * @author triad (leonardo.flores@overactive.com)
 * @created 10/15/2019 2:20 PM
 */
public class MyTask {
    private final int duration;
    public MyTask(int duration) {
        this.duration = duration;
    }
    public int calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(duration * 5000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return duration;
    }
}
