package interviewprep;

public class LongestPathToTopRightElement {
	
	private static int LEN;
	
	private static int maxCost = 0;
	
	private static class Point {
		int x,y,cost;
		public Point(int x, int y, int cost) { 
			this.x = x; 
			this.y = y;
			this.cost = cost;
		}
	
	}
	
	private static boolean isValidPoint(int x, int y) {
		return x >= 0 && y < LEN;
	}
	
	public static int costOfLongestPath(int[][] mat) {
		
		LEN = mat[0].length;
		int row = mat.length-1;
		int col = 0;
				
		Point curr = new Point(row, col, mat[row][col]);
		helper(mat, row, col, curr);
		
		return maxCost;
	}
	
	private static void helper(int[][] mat, int row, int col, Point curr) {
		
		if(!isValidPoint(row, col))
			return;
		
		if(row ==0 && col == LEN-1 && maxCost < curr.cost)
			maxCost = curr.cost;
		
		if(isValidPoint(row-1, col)) {
			Point up = new Point(row-1, col, (curr.cost + mat[row-1][col]));
			helper(mat, up.x, up.y, up);
			
		}
		
		if(isValidPoint(row, col+1)) {
			Point right = new Point(row, col+1, (curr.cost + mat[row][col+1]));
			helper(mat, right.x, right.y, right);
			
		}
					
	}

}
