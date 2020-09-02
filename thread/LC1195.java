package thread;

import java.util.function.IntConsumer;

/**
 * created by mercury on 2020-09-02
 *
 * 交替打印字符串
 *
 * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
 *
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
 *
 * 假设有这么一个类：
 *
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 *   public void fizz(printFizz) { ... }          // only output "fizz"
 *   public void buzz(printBuzz) { ... }          // only output "buzz"
 *   public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 *   public void number(printNumber) { ... }      // only output the numbers
 * }
 *
 * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
 *
 * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
 * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
 * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
 * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
 *
 */

public class LC1195 {

    public static void main(String[] args) {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        Thread thread1 = new Thread() {

            @Override
            public void run() {
                try {
                    fizzBuzz.fizz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("fizz,");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread() {

            @Override
            public void run() {
                try {
                    fizzBuzz.buzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("buzz,");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread3 = new Thread() {

            @Override
            public void run() {
                try {
                    fizzBuzz.fizzbuzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("fizzbuzz,");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread4 = new Thread() {

            @Override
            public void run() {
                try {
                    fizzBuzz.number(new IntConsumer() {
                        @Override
                        public void accept(int value) {
                            System.out.print(value + ",");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread3.start();
        thread1.start();
        thread4.start();
        thread2.start();

    }

}


/**
 * 设置一个线程共有的变量i
 * 将每一个判断并打印的操作，放在一个同步代码块内部
 * 每一个线程内部，当i<=n，不断争用同一把锁
 * 线程得到锁后，判断当前数字i是否满足打印条件，满足则打印并且+1，否则放弃这把锁
 *
 * 可以理解为，对于同一个i，有4个线程去判断并处理，不管谁先判断当前的i，只要获取了锁就进入自己的判断，不满足则放弃锁大家重新竞争
 */
class FizzBuzz {
    private int n;

    private int i = 1;
    private final Object lock = new Object();


    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (i <= n) {
            synchronized (lock) {
                while (i <= n && i % 3 == 0 && i % 5 != 0) {
                    printFizz.run();
                    i++;
                }
            }
        }

    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (i <= n) {
            synchronized (lock) {
                while (i <= n && i % 5 == 0 && i % 3 != 0) {
                    printBuzz.run();
                    i++;
                }
            }
        }

    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (i <= n) {
            synchronized (lock) {
                while (i <= n && i % 3 == 0 && i % 5 == 0) {
                    printFizzBuzz.run();
                    i++;
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (i <= n) {
            synchronized (lock) {
                while (i <= n && i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                    i++;
                }
            }
        }
    }
}



