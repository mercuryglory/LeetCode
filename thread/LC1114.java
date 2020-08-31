package thread;

/**
 * created by mercury on 2020-08-31
 *
 * 按序打印
 *
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 *  
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 *
 *
 * 示例 2:
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。
 *
 */

public class LC1114 {

    /**
     * 最安全的做法肯定是加锁，其次CAS。但是在这里，volatile是可以利用的
     * 当满足1、运算结果并不依赖变量的当前值，或者在同一时期只有单一线程修改变量的值
     * 2、变量不需要与其他的状态变量参与不变约束
     *
     * 这里我们加2个屏障就行，方法second依赖first，那么first完成后就把first的屏障移除掉，同理third依赖second
     */

    private volatile boolean firstDone = false;
    private volatile boolean secondDone = false;

    public LC1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstDone = true;
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while(!firstDone){
            //等待第一个任务完成
        }
        printSecond.run();
        secondDone = true;
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!secondDone) {

        }
        printThird.run();

    }


    public static void main(String[] args) {
        LC1114 test = new LC1114();
        Thread thread1 = new Thread(() -> {
            try {
                test.first(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("first");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                test.second(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("second");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                test.third(new Runnable() {
                    @Override
                    public void run() {
                        System.out.print("third");
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread2.start();
        thread3.start();
        thread1.start();
    }


}
