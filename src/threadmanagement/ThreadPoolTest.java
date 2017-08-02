/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadmanagement;

/**
 *
 * @author Keith
 */
public class ThreadPoolTest {
    
    private int numThreads = 0;
    private int numTasks = 0;
    
     /**
         * ThreadPool test code follows
        */
    public ThreadPoolTest(int tasks, int threads) {
        this.numTasks = tasks;
        this.numThreads = threads;
        if(tasks == 0 || threads == 0) {
            System.out.println("Tests the ThreadPool task.");
            System.out.println("Usage: java ThreadPoolTest numTasks numThrads");
            System.out.println("  numTasks - integer: number of task to run:.");
            System.out.println("numThreads - integer: number of threads in the thread pool.");
            return;
        }
        
        // create the thread pool
        ThreadPool threadPool = new ThreadPool(numThreads);
        
        // run example tasks
        for(int i = 0; i < numTasks; i++) {
            threadPool.runTask(createTask(i));
        }
        
        // close the pool and wait for tasks to finish
        threadPool.join();
    }

        
        
        /**
         * Create a single Runnable that prints an ID, wait 500 milliseconds
         * then print the ID again.
         */
        private static Runnable createTask(final int taskID) {
            return new Runnable() {
                public void run() {
                    System.out.println("Task " + taskID + ": start");
                    
                    // simulate a long running task
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ie) {
                        
                    }
                    
                    System.out.println("Task " + taskID + ": end");
                }
            };
        }   
}
