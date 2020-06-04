package interviewprep;

import java.util.NoSuchElementException;

public class Deque<T> {

	private class Node {

		private Node left;
		private Node right;
		private final T item;

		public Node(T item) {
			this.item = item;
		}

		public void connectRight(Node other) {
			this.right = other;
			other.left = this;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void addFirst(T item) {
		Node prevHead = head;
		Node newHead = new Node(item);

		if (prevHead != null) {
			newHead.connectRight(prevHead);
		} else {
			tail = newHead;
		}

		head = newHead;
		size++;
	}

	public void addLast(T item) {
		Node newTail = new Node(item);
		Node prevTail = tail;

		if (prevTail != null) {
			prevTail.connectRight(newTail);
		} else {
			head = newTail;
		}

		tail = newTail;
		size++;
	}

	public T removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		size--;
		Node prevHead = head;
		head = prevHead.right;
		prevHead.right = null;

		if (head != null) {
			head.left = null;
		}

		return prevHead.item;
	}

	public T removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		size--;
		Node prevTail = tail;
		tail = prevTail.left;
		prevTail.left = null;

		if (tail != null)
			tail.right = null;

		return prevTail.item;
	}

}
