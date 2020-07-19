package stack;

import javax.sound.midi.Soundbank;
import java.util.Stack;

/**
 * created by mercury on 2020-07-19
 *
 * 最小栈
 */
public class LC155 {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

}

/**
 * 一个简洁的做法
 * 只需要一个辅助栈，同时保存当前值和栈内最小值
 * 当push(x),x <= min时，先将min入栈，再将x入栈
 * 这样在出栈的时候如果栈顶是最小值，则出栈后要将新的栈顶元素出栈，并且更新为当前最小值
 */
class MinStack {

    private Stack<Integer> stack;
    private int min;

    public MinStack() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

}
