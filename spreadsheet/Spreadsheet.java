
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spreadsheet {

	public static void main(String[] args) {
		
		File file  = new File(args[0]);
		Scanner scanner;
		try {
			scanner  = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Error reading file");
		}
		
		String rowsAndCols = scanner.nextLine();
		int rows = Integer.parseInt(rowsAndCols.split(" ")[0]);
		int cols = Integer.parseInt(rowsAndCols.split(" ")[1]);
		new SpreadsheetProcessor().process(scanner, rows, cols);
		scanner.close();
	}	
	
}
