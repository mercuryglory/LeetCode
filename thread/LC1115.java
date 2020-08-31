package thread;

/**
 * created by mercury on 2020-08-31
 *
 * 交替打印FooBar
 *
 * 我们提供一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 *
 * 示例 2:
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 *
 */

public class LC1115 {

    public static void main(String[] args) {
        FooBar test = new FooBar(20);
        Runnable foo = new Runnable() {

            @Override
            public void run() {
                System.out.print("foo");
            }
        };
        Runnable bar = new Runnable() {
            @Override
            public void run() {
                System.out.print("bar");
            }
        };
        Thread thread1 = new Thread(() -> {
            try {
                test.foo(foo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                test.bar(bar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}


class FooBar {
    private int n;

    private boolean fooTurn = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this){

                // printFoo.run() outputs "foo". Do not change or remove this line.
                while (!fooTurn) {
                    wait();
                }
                printFoo.run();
                fooTurn = false;
                notify();
            }

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            synchronized (this) {

                // printBar.run() outputs "bar". Do not change or remove this line.
                while(fooTurn){
                    wait();
                }
                printBar.run();
                fooTurn = true;
                notify();
            }

        }
    }
}
