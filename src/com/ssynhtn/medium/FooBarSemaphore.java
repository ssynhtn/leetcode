package com.ssynhtn.medium;


import java.util.concurrent.Semaphore;

class FooBarSemaphore {
    private int n;

    Semaphore foo;
    Semaphore bar;

    public FooBarSemaphore(int n) {
        this.n = n;
        foo = new Semaphore(1);
        bar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            foo.acquire();
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
        	bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            bar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
        	foo.release();
        }
    }

    public static void main(String[] args) {
        FooBarSemaphore fooBar = new FooBarSemaphore(10);
        Runnable printFoo = new Runnable() {
            @Override
            public void run() {
                System.out.println("foo");
            }
        };
        Runnable printBar = new Runnable() {
            @Override
            public void run() {
                System.out.println("bar");
            }
        };



        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.bar(printBar);
                } catch (InterruptedException e) {

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}