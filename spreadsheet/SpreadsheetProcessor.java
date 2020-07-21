import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class SpreadsheetProcessor {

	public void process(Scanner scanner, int rows, int cols) {

		Object[][] matrix = new Object[rows + 1][cols + 1];
		int rowCount = 1;
		int colCount = 1;
		Map<String, String> map = new ConcurrentHashMap<>();
		NonMathExpProcessor nmep = new NonMathExpProcessor();
		MathExpProcessor mep = new MathExpProcessor();

		while (scanner.hasNext() && rowCount <= rows) {

			if (colCount > cols) {
				colCount = 1;
				rowCount++;
			}
			String line = scanner.nextLine();
			if (!line.matches(".*[a-zA-Z]+.*")) 
				mep.process(matrix, line, rowCount, colCount);
			else
				nmep.process(matrix, map, line, rowCount, colCount, "0");

			colCount++;
		}

		nmep.processMapCleanup(matrix, map);
		
		for(int i=1; i<=rows; i++) {
	        for(int j=1; j<=cols; j++) {
	            System.out.println(matrix[i][j]);
	        }
	    }

	}
}
