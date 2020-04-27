package codility;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ProperlyNestedString {
	
	private static Map<Character, Character> map;
	
	static {
		map = new HashMap<>();
		map.put('{', '}');
		map.put('[', ']');
		map.put('(', ')');
	}
	
	public static boolean isStringProperlyNested(String S) {
	
		Stack<Character> stack = new Stack<Character>();
		for(Character c : S.toCharArray()) {
			if(map.containsKey(c)) {
				stack.push(c);
			} else {
				if(stack.size() == 0 || c != map.get(stack.lastElement())) 
					return false;
				else {
					stack.pop();
				}
			}
		}
		
		if(stack.size() != 0)
			return false;
		
		return true;
	}
	
	public static boolean isParanthesisOnlyStringProperlyNested(String S) {
		
		if(S.startsWith(")") || S.endsWith("("))
			return false;
		
		Stack<Character> stack = new Stack<Character>();
		for(Character c : S.toCharArray()) {
			if(c == '(')
				stack.push(c);
			else {
				if(stack.size() > 0)
					stack.pop();
				else 
					return false;
			}
		}
		
		if(stack.size() != 0)
			return false;
		
		return true;
	}

}
