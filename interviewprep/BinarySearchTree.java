package interviewprep;

public class BinarySearchTree {
	
	public static class Node {
		
		Node left;
		Node right;
		int data;
		
		private Node(int data) {
			this.data = data;
		}
	}
	
	private Node root;
	
	public void insert(int value) {
		
		if(root == null) {
			root = new Node(value);
			return;
		}
		
		insert(value, root);
	}
	
	private void insert(int value, Node node) {
		
		if(value <= root.data) {
			if(node.left == null) 
				node.left = new Node(value);
			else
				insert(value, node.left);
		} else {
			if(node.right == null) 
				node.right = new Node(value);
			else
				insert(value, node.right);
		}
	}
	
	public boolean contains(int value) {
		
		if(root == null)
			return false;
		
		return contains(value, root);
	}
	
	private boolean contains(int value, Node node) {
	
		if(value == node.data) {
			return true;
		
		} else if(value < node.data) {
			if(node.left == null)
				return false;
			else 
				return contains(value, node.left);
		} else {
			if(node.right == null)
				return false;
			else 
				return contains(value, node.right);
		}
		
	}
	
	public void printInOrder() {
		if(root == null) 
			return;
		
		inOrderTraversal(root);
	}
	
	private void inOrderTraversal(Node node) {
		if(node.left != null)
			inOrderTraversal(node.left);
		
		System.out.println(node.data);
		
		if(node.right != null) 
			inOrderTraversal(node.right);
	}
	
	public void printPreOrder() {
		if(root == null) 
			return;
		
		preOrderTraversal(root);
	}
	
	private void preOrderTraversal(Node node) {
		
		System.out.println(node.data);
		
		if(node.left != null)
			inOrderTraversal(node.left);
		
		if(node.right != null) 
			inOrderTraversal(node.right);
	}
	
	public void printPostOrder() {
		if(root == null) 
			return;
		
		postOrderTraversal(root);
	}
	
	private void postOrderTraversal(Node node) {
		
		if(node.left != null)
			inOrderTraversal(node.left);
		
		if(node.right != null) 
			inOrderTraversal(node.right);

		System.out.println(node.data);
	}

}
