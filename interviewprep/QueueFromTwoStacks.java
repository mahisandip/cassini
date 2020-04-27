package interviewprep;

import java.util.Stack;

/**
 * 
 * Implemenyt a Queue data structure form two stacks
 * 
 */
public class QueueFromTwoStacks<T> {

	private Stack<T> stackNewestOnTop = new Stack<>();

	private Stack<T> stackOldestOnTop = new Stack<>();

	public void enqueue(T value) {
		stackNewestOnTop.push(value);
	}

	public T peek() {
		shiftStacks();
		return stackOldestOnTop.peek();
	}

	public T dequeue() {
		shiftStacks();
		return stackOldestOnTop.pop();
	}

	private void shiftStacks() {
		if (stackOldestOnTop.isEmpty()) {
			while (!stackNewestOnTop.isEmpty()) {
				stackOldestOnTop.add(stackNewestOnTop.pop());
			}
		}
	}
		
}
