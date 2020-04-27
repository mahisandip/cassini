package interviewprep;

import java.util.Arrays;

public class MinIntegerHeap {
	
	private int size = 0;
	private int capacity = 10;
	private int[] items = new int[capacity];
	
	private int getLeftChildIndex(int parentIndex) { 
		return 2 * parentIndex + 1; 
	};
	
	private int getRightChildIndex(int parentIndex) { 
		return 2 * parentIndex; 
	};
	
	private int getParentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}
	
	private boolean hasLeftChild(int index) {
		return getLeftChildIndex(index) < size;
	}
	
	private boolean hasRightChildIndex(int index) {
		return getRightChildIndex(index) < size;
	}
	
	private boolean hasParent(int index) {
		return getParentIndex(index) >= 0;
	}
	
	private int leftChild(int index) {
		return items[getLeftChildIndex(index)];
	}
	
	private int rightChild(int index) {
		return items[getRightChildIndex(index)];
	}
	
	private int parent(int index) {
		return items[getParentIndex(index)];
	}
	
	private void swap(int i, int j) {
		int temp = items[i];
		items[i] = items[j];
		items[j] = temp;
	}
	
	private void ensureExtraCapacity() {
		if(size == capacity) {
			capacity *= 2;
			items = Arrays.copyOf(items, capacity);
		}
	}
	
	public int peek() {
		if(size == 0)
			throw new IllegalStateException();
		return items[0];
	}
	
	public int poll() {
		if(size == 0)
			throw new IllegalStateException();
		int item  = items[0];
		items[0] = items[size-1];
		size--;
		heapifyDown();
		return item;
	}
	
	public void add(int item) {
		ensureExtraCapacity();
		items[size] = item;
		size++;
		heapifyUp();
	}
	
	private void heapifyUp() {
		int index = size-1;
		while(hasParent(index) && parent(index) > items[index]) {
			swap(getParentIndex(index), index);
			index = getParentIndex(index);
		}
	}
	
	private void heapifyDown() {
		int index = 0;
		while(hasLeftChild(index)) {
			int smallerChildIndex = getLeftChildIndex(index);
			if(hasRightChildIndex(index) && rightChild(index)<leftChild(index)) 
				smallerChildIndex = getRightChildIndex(index);
						
			if(items[index] < items[smallerChildIndex]) 
				break;
			else
				swap(index, smallerChildIndex);
			
			index = smallerChildIndex;
		}
	}

}
