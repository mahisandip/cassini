package interviewprep;

import java.util.Stack;

/**
 * Find the lowest common ancestor of a node
 * 
 * eg:
 * 				1
 * 			  /   \
 *          2      3
 *         / \    / \
 *        4   5  6   7
 *        			/ \
 *        		   8   9
 *  
 * lca(2,3) = 1
 * lca(6,9) = 3
 * lca(2,7) = 1
 * lca(4,7) = 1
 *
 */
public class LowestCommonAncestor {
	
	private class Node {
		Node left;
		Node right;
	}
	
	public Node findLowestCommonAncestor(Node tree, Node n1, Node n2) {
		
		if(n1.equals(n2))
			return n1;
		
		Stack<Node> pathToN1 = pathToNode(tree, n1);
		Stack<Node> pathToN2 = pathToNode(tree, n2);
		
		Node prev = null;
		while(!pathToN1.isEmpty() && !pathToN2.isEmpty()) {
			Node l = pathToN1.pop();
			Node r = pathToN2.pop();
			if(l.equals(r))
				prev = l;
			else
				break;
		}
		return prev;
	}
	
	private Stack<Node> pathToNode(Node tree, Node n) {
		
		if(tree == null)
			return null;
		
		if(tree.equals(n)) {
			Stack<Node> s = new Stack<>();
			s.push(tree);
			return s;
		}
		
		Stack<Node> left = pathToNode(tree, tree.left);
		Stack<Node> right  = pathToNode(tree, n.right);
		
		if(left != null) {
			left.push(tree);
			return left;
		}
		
		if(right != null) {
			right.push(tree);
			return right;
		}
		return null;
	}

}
