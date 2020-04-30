package interviewprep;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	
	public static class Node {
		
		private int id;
		private List<Node> adjacent = new LinkedList<>();
		
		private Node(int id) {
			this.id = id;
		}
	}
	
	private Map<Integer, Node> nodeLookUp = new HashMap<>();
	
	private Node getNode(int id) {
		return nodeLookUp.get(id);
	}
	
	public void addEdge(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		s.adjacent.add(d);
	}
	
	public boolean hasPathDFS(int source, int destination) {
		Node s = getNode(source);
		Node d = getNode(destination);
		Set<Integer> visited = new HashSet<>();
		return hasPathDFS(s, d, visited);
	}
	
	private boolean hasPathDFS(Node s, Node d, Set<Integer> visited) {
		if(visited.contains(s.id))
			return false;
		
		visited.add(s.id);
		if(s == d) {
			return true;
		}
		
		for(Node child : s.adjacent) {
			if(hasPathDFS(child, d, visited)) 
				return true;
		}
		
		return false;
	}
	
	public boolean hasPathBFS(Node source, Node destination) {
		LinkedList<Node> nextToVisit = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		nextToVisit.add(source);
		
		while(!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();
			
			if(node == destination)
				return true;
			
			if(visited.contains(node.id))
				continue;
			
			visited.add(node.id);
			
			for(Node child : node.adjacent) 
				nextToVisit.add(child);
		}
		
		return false;
	}

}
