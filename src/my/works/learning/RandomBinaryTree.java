package my.works.learning;

import java.util.Random;

public class RandomBinaryTree {

	class Node {
		int value;
		int children;
		Node left;
		Node right;
	}
	
	private Node root;
	private Random rand;
	
	public int getRandomNode() {
		
		if(root == null) throw new NullPointerException();
		int count = rand.nextInt(root.children + 1);
		return getRandomNode(root, count);
	}
	
	private int getRandomNode(Node curr, int count) {
		
		if(count == children(curr.left)) return curr.value;
		if(count < children(curr.left)) return getRandomNode(curr.left, count);
		return getRandomNode(curr.right, count-children(curr.left)-1);
	}
	
	private int children(Node n) {
		if(n==null) return 0;
		return n.children + 1;
	}

}
