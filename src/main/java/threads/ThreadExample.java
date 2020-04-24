package threads;

import lombok.SneakyThrows;

public class ThreadExample {
    @SneakyThrows
    public static void main(String[] args) {
        Thread codeAnalyzer = new Thread(new BackGroundTask(), "BG");
        codeAnalyzer.setDaemon(true); //зависимость от недемон - потоков
        codeAnalyzer.start();

        Thread helloWorld = new Thread(new HelloWorldRunnable(), "HelloWThread");
        helloWorld.start();
        Thread counter = new CounterThread();
        counter.start();
        Thread counter2 = new CounterThread();
        counter2.start();
        // System.out.println("Main thread");
        String threadName = Thread.currentThread().getName();
        System.out.println("Main thread  " + threadName);
        Thread.sleep(13000);
        System.out.println("Main finished");
    }


    static class CounterThread extends Thread {
        @Override
        @SneakyThrows
        public void run() {
            //    System.out.println("Main thread");
            String threadName = Thread.currentThread().getName();
            //   System.out.println(threadName+"   2 thread");
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(threadName + "" + i);
            }
        }
    }

    static class HelloWorldRunnable implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            Thread.sleep(4000);
            System.out.println("Hello from " + Thread.currentThread().getName());
        }
    }

    static class BackGroundTask implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                Thread.sleep(500);
                System.out.println("Started");
            }
        }
    }

}