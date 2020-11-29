package com.ssynhtn.medium;

class FooBar {
    private int n;

    final Object lock = new Object();

    boolean shouldPrintFoo = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                if (!shouldPrintFoo) {
                    lock.wait();
                }
                printFoo.run();

                shouldPrintFoo = false;
                lock.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized (lock) {
                if (shouldPrintFoo) {
                    lock.wait();
                }
                shouldPrintFoo = true;
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                lock.notify();
            }

        }
    }
}