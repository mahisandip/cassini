package interviewprep;

public class BalancedBinaryTree {
	
	class Node {
		int val;
		Node left;
		Node right;
	}
	
	public boolean isBalanced(Node n) {
		if(balanced(n) > -1) return true;
		return false;
	}
	
	private int balanced(Node n) {
		
		if(n==null) return 0;
		
		int h1 = balanced(n.left);
		int h2 = balanced(n.right);
		
		if(h1==-1 || h2==-1) return -1;
		if(Math.abs(h1-h2) > 1) return -1;
		
		if(h1>h2) return h1+1;
		return h2+1;
	}

}
