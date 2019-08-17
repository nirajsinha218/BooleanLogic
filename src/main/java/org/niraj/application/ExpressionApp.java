package org.niraj.application;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExpressionApp {

	public static void main(String[] args) {

		Expression expression = new Expression("|&XYZ");
		Map<Character, Boolean> bindings = new HashMap<>();
		bindings.put('X', Boolean.FALSE);
		bindings.put('Y', Boolean.TRUE);
		bindings.put('Z', Boolean.TRUE);
		bindings.put('a', Boolean.TRUE);
		bindings.put('b', Boolean.FALSE);
		try {
			System.out.println(expression.eval(bindings));
		} catch (ExpressionException e) {
			System.out.println(e.getMessage());
		}
		Set<String> result = null;
		try {
			result = expression.solve();
		} catch (ExpressionException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(result);

	}

}
