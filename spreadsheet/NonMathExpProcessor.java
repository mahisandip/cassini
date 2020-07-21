import java.util.HashSet;
import java.util.Map;

public class NonMathExpProcessor {

	public void process(Object[][] matrix, Map<String, String> map,
			String expr, int row, int col, String key) {

		String[] tokens = expr.split(" ");
		StringBuilder toks = new StringBuilder();
		boolean flag = true;
		
		if(tokens.length < 1) {
			tokens = new String[1];
			tokens[0] = expr;
		}

		for (int i = 0; i < tokens.length; i++) {
			String s = tokens[i];
			if (!isMathematicalOperator(tokens[i])) {
				s = checkDependecies(matrix, map, tokens[i], row, col,
						key);
			}
				if (s != null) {
					toks.append(s);
					toks.append(" ");
				} else {
					map.put(new StringBuilder(RowType.getRowAlphabet(row))
							.append(col).toString(), expr);
					flag = false;
					break;
				}
		}
		if (flag) {
			MathExpProcessor mep = new MathExpProcessor();
			mep.process(matrix,
					toks.deleteCharAt(toks.length() - 1).toString(), row, col);
			if (map.containsKey(key)) {
				map.remove(key);
			}
		}
	}

	private String checkDependecies(Object[][] matrix, Map<String, String> map,
			String token, int row, int col, String key) {

		if (token == null)
			return null;

		if (token.matches("^[0-9.]*$"))
			return token;
		
		String[] part = token.split("(?<=\\D)(?=\\d)");
		HashSet<String> cycDepSet = new HashSet<>();
		cycDepSet.add(token);
		String newStr;
		int expRow = RowType.getRowNumber(part[0]);
		int expCol = Integer.parseInt(part[1].split(" ").length > 1 ? part[1]
				.substring(0, part[1].length() - 2) : part[1].split(" ")[0]);
		if ((expRow > row || (expRow == row && expCol > col)) && (key.equals("0")))
			return null;

		newStr = (String) matrix[expRow][expCol];
		if (cycDepSet.contains(newStr))
			throw new IllegalArgumentException("Error: Cyclic dependency found");
		else
			return checkDependecies(matrix, map, newStr, row, col, key);

	}

	private boolean isMathematicalOperator(String token) {
		if (token.equals("+") || token.equals("-") || token.equals("*")
				|| token.equals("/"))
			return true;
		return false;
	}

	public void processMapCleanup(Object[][] matrix, Map<String, String> map) {
		while (map.size() > 0) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String expr = entry.getValue();
				String rowCol = entry.getKey();
				String[] parts = rowCol.split("(?<=\\D)(?=\\d)");
				this.process(matrix, map, expr, RowType.getRowNumber(parts[0]),
						Integer.parseInt(parts[1]), rowCol);
			}
		}
	}
}