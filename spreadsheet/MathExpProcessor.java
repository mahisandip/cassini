public class MathExpProcessor {

	public void process(Object[][] matrix, String expr, int row, int col) {

		String[] tokens = expr.split(" ");
	
		if (tokens.length <= 1) {
			tokens = new String[1];
			tokens[0] = expr;
			matrix[row][col] = String.format("%.5f", Float.parseFloat(tokens[0]));
		} else if (tokens.length == 2) {
			throw new IllegalArgumentException(
					"Error: Length of epsression is less to do an operation");
		} else if (tokens.length == 3) {
			matrix[row][col] = this.performSimpleRPNCalculation(tokens);
		} else if (tokens.length > 3) {
			matrix[row][col] = this.performComplexRPNCalculation(tokens);
		}

	}

	private String performSimpleRPNCalculation(String... tokens) {
		float result;
		if (tokens[2].equals("+"))
			result = Float.parseFloat(tokens[0]) + Float.parseFloat(tokens[1]);
		else if (tokens[2].equals("-"))
			result = Float.parseFloat(tokens[0]) - Float.parseFloat(tokens[1]);
		else if (tokens[2].equals("/"))
			result = Float.parseFloat(tokens[0]) / Float.parseFloat(tokens[1]);
		else if (tokens[2].equals("*"))
			result = Float.parseFloat(tokens[0]) * Float.parseFloat(tokens[1]);
		else if (tokens[2].equals("%"))
			result = Float.parseFloat(tokens[0]) % Float.parseFloat(tokens[1]);
		else
			throw new IllegalArgumentException("Error: No operator found");

		return String.format("%.5f", result);
	}

	private String performComplexRPNCalculation(String... tokens) {

		if (tokens.length == 1)
			return tokens[0];
		
		if (tokens.length == 3)
			return this.performSimpleRPNCalculation(tokens[0],
							tokens[1], tokens[2]);

		int i = 1;
		while (i < tokens.length && Character.isDigit(tokens[i].charAt(0)))
			i++;

		String[] toks = new String[tokens.length-2];
		for (int j = 0; j < tokens.length; j++) {
			if (j != i - 2 || j != i - 1) {
				if (j == i)
					toks[j-2] = this.performSimpleRPNCalculation(tokens[i - 2],
							tokens[i - 1], tokens[i]);
				else if(j>i)
					toks[j-2] = tokens[j];
				else
					toks[j] = tokens[j];
			}
		}

		return this.performComplexRPNCalculation(toks);
	}
}
