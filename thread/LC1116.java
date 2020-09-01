package thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * created by mercury on 2020-09-01
 *
 * 打印零与奇偶数
 *
 * 假设有这么一个类：
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // 构造函数
 *   public void zero(printNumber) { ... }  // 仅打印出 0
 *   public void even(printNumber) { ... }  // 仅打印出 偶数
 *   public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *  
 *
 * 示例 1：
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
 *
 * 示例 2：
 * 输入：n = 5
 * 输出："0102030405"
 *
 */

public class LC1116 {

    public static void main(String[] args) {
        ZeroEvenOdd test = new ZeroEvenOdd(5);
        Thread thread1 = new Thread(() -> {
            try {
                test.zero(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                test.even(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                test.odd(new IntConsumer() {
                    @Override
                    public void accept(int value) {
                        System.out.print(value);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }


}

/**
 * 线程间存在前驱关系，可以用信号量解决
 * 打印0是优先分配资源执行的，优先执行的结束后释放受限执行的线程的资源，受限线程才能继续执行
 *
 * 设置odd和even的初始信号量为0，而zero的为1
 * 因为线程开始时执行顺序不确定，有可能先进入odd或even的for循环，而初始许可证为0，获取时会阻塞直到该信号量有一个可用
 * 这样zero的打印任务先执行，每次打印0之后根据当前循环次数决定释放even还是odd的信号量
 */
class ZeroEvenOdd {
    private int n;

    private Semaphore zero = new Semaphore(1);
    private Semaphore odd = new Semaphore(0);
    private Semaphore even = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if ((i & 1) == 1) {
                odd.release();
            } else {
                even.release();
            }
        }


    }

    //odd或even打印完，都要释放打印0的信号量，使0继续打印
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }

    }
}
