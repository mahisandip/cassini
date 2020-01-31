package my.works.learning;

/**
 * 
 * Check if there is any cycles in a given linked list
 * 
 * 1---2---3
 * 			\
 * 	   7---	4
 * 	   |	/
 * 	   6---5
 * 
 */
public class CyclesInLinkedList {
	
	public static boolean hasCycles(Node head) {
			
		if (head == null)
			return false;
		
		Node faster = head.next;
		Node slower = head;
		while(faster!=null && faster.next!=null && slower!=null) {
			if(faster == slower)
				return true;
			slower = slower.next;
			faster = faster.next.next;
		}
		
		return false;
	}
	
	public static class Node {
		int data;
		Node next;
	}

}
