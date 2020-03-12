package my.works.leetcode;

public class ReverseLinkedList {
	
	public Node reverseLinkedList(Node head) {
		
		if(head == null)
			return null;
		if(head.next == null)
			return head;
		
		Node prev = null;
		Node curr = head;
		Node next = head;
		
		while(next != null) {
			next = curr.next;
			prev = curr;
			curr = next;
		}
		
		return prev;
	}
	
	private class Node {
		int value;
		Node next;
	}

}
