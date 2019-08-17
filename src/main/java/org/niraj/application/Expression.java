package org.niraj.application;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Expression {

	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final char LOGICAL_AND = '&';
	public static final char LOGICAL_OR = '|';
	public static final char LOGICAL_NOT = '~';

	String exp;

	public Expression() {

	}

	public Expression(String exp) {
		this.exp = exp;
	}

	public Set<String> solve() throws ExpressionException {
		Map<Character, Boolean> bindings = new LinkedHashMap<>();
		Set<String> set = new HashSet<>();
		int numOfBindings = 0;
		for (int i = exp.length() - 1; i >= 0; i--) {
			char ch = exp.charAt(i);
			if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) {
				bindings.put(ch, Boolean.FALSE);
			}
		}

		int size = bindings.size();

		int rows = (int) Math.pow(2, size);
		int k;
		boolean[] arr = new boolean[size];

		for (int i = 0; i < rows; i++) {
			int j = size - 1;
			for (; j >= 0; j--) {
				k = (i / (int) Math.pow(2, j)) % 2;

				if (k == 0) {
					arr[j] = Boolean.FALSE;
				} else
					arr[j] = Boolean.TRUE;
			}

			for (Map.Entry<Character, Boolean> entry : bindings.entrySet()) {
				Character key = entry.getKey();
				bindings.put(key, arr[++j]);
			}
			boolean result = eval(bindings);

			if (result == true) {
				numOfBindings++;
				String str = "";
				for (Map.Entry<Character, Boolean> entry : bindings.entrySet()) {
					Character key = entry.getKey();
					Boolean value = entry.getValue();
					str = str + key + " " + value + " ";
					// System.out.print(entry.getKey() + " " + entry.getValue() + " ");
				}
				set.add(str);
				// System.out.println();
			}
		}
		if (set.size() == 0 || set.contains(""))
			return null;
		else
			return set;
	}

	public boolean eval(Map<Character, Boolean> hm) throws ExpressionException {

		Stack<String> stack = new Stack<>();

		for (int i = exp.length() - 1; i >= 0; i--) {
			char ch = exp.charAt(i);

			if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122) || ch == 48 || ch == 49) {
				stack.push(Character.toString(ch));
			} else if (ch != LOGICAL_NOT && stack.size() >= 2) {
				boolean fVal = false;
				boolean sVal = false;
				String first = "";
				String second = "";
				first = stack.peek();
				stack.pop();
				second = stack.peek();
				stack.pop();
				if (hm.containsKey(first.charAt(0))) {
					fVal = hm.get(first.charAt(0));
				} else {
					if (first.equals(ZERO) || first.equals(ONE)) {
						if (first.equals(ZERO))
							fVal = false;
						else
							fVal = true;
					} else {
						throw new ExpressionException("Expression uses a variable not in the mapping");
					}
				}
				if (hm.containsKey(second.charAt(0))) {
					sVal = hm.get(second.charAt(0));
				} else {
					if (second.equals(ZERO) || second.equals(ONE)) {
						if (second.equals(ZERO))
							sVal = false;
						else
							sVal = true;
					} else {
						throw new ExpressionException("Expression uses a variable not in the mapping");
					}
				}

				switch (ch) {
				case LOGICAL_AND:
					if (fVal == false || sVal == false)
						stack.push(ZERO);
					else
						stack.push(ONE);
					break;
				case LOGICAL_OR:
					if (fVal == true || sVal == true)
						stack.push(ONE);
					else
						stack.push(ZERO);
					break;
				}
			} else if (ch == LOGICAL_NOT) {
				String first = stack.peek();
				stack.pop();
				boolean fVal = false;
				if (hm.containsKey(first.charAt(0))) {
					fVal = hm.get(first.charAt(0));
				} else {
					if (first.equals(ZERO) || first.equals(ONE)) {
						if (first.equals(ZERO))
							fVal = false;
						else
							fVal = true;
					} else {
						throw new ExpressionException("Expression uses a variable not in the mapping");
					}
				}

				if (fVal == false)
					stack.push(ONE);
				else
					stack.push(ZERO);
			} else {
				throw new ExpressionException("The given expression is not in correct form");
			}

		}

		int size = stack.size();
		if (size == 1) {
			String result = stack.peek();
			stack.pop();
			if (result.equals(ZERO))
				return false;
			else if (result.equals(ONE))
				return true;
			else {
				throw new ExpressionException("The given expression is not in correct form");
			}
		} else {
			throw new ExpressionException("The given expression is not in correct form");
		}
	}

}
