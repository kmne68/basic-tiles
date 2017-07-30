/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadmanagement;

import java.util.LinkedList;

/**
 *
 * @author Keith
 * 
 * Based on examples in Developing Games in Java by David Brackeen
 */

public class ThreadPool extends ThreadGroup {
    
    private boolean isAlive;
    private LinkedList taskQueue;
    private int threadID;
    private static int threadPoolID;
    
    
    public ThreadPool(int numThreads) {
        
        super("ThreadPoool-" + (threadPoolID++));
        setDaemon(true);
        
        isAlive = true;
        
        taskQueue = new LinkedList();
        for (int i = 0; i < numThreads; i++) {
            new PooledThread().start();
        }    
    }
    
    
    private class PooledThread extends Thread {
        
        public PooledThread() {
            super(ThreadPool.this, "PooledThread-" + (threadID++));
        }
    }
    
    
    public synchronized void runTask(Runnable task) {
        
        if(!isAlive) {
            throw new IllegalStateException();
        }
        if(task != null) {
            taskQueue.add(task);
            notify();
        }
    }
    
    
    
}
