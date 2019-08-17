package org.niraj.application;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;

public class ExpressionTest {

	private Expression expression;
	private Map<Character, Boolean> bindings;

	@Before
	public void setUp() {
		bindings = new HashMap<>();
		bindings.put('X', Boolean.FALSE);
		bindings.put('Y', Boolean.TRUE);
		bindings.put('Z', Boolean.TRUE);
	}

	/*
	 * Tests the case when the expression is valid
	 */
	@Test
	public void validExpressionTest() throws ExpressionException {
		expression = new Expression("|&XYZ");
		assertEquals(Boolean.TRUE, expression.eval(bindings));
	}

	/*
	 * Tests the case when the expression is valid but variable is not mapped and
	 * exception is thrown
	 */
	@Test(expected = ExpressionException.class)
	public void validExpressionWithVariableNotMppedTest() throws ExpressionException {
		expression = new Expression("&|&XYZU");
		expression.eval(bindings);
	}

	/*
	 * Tests the case when the expression is invalid and exception is thrown
	 */
	@Test(expected = ExpressionException.class)
	public void invalidExpressionTest() throws ExpressionException {
		expression = new Expression("|XYZ");
		expression.eval(bindings);
	}

	/*
	 * Tests the case when the expression is invalid having extra operator and
	 * exception is thrown
	 */
	@Test(expected = ExpressionException.class)
	public void invalidExpressionWithExtraOperatorTest() throws ExpressionException {
		expression = new Expression("|&XY");
		expression.eval(bindings);
	}

	/*
	 * Tests the case when the expression is invalid having invalid operator and
	 * exception is thrown
	 */
	@Test(expected = ExpressionException.class)
	public void invalidExpressionWithInvalidOperatorTest() throws ExpressionException {
		expression = new Expression("+XY");
		expression.eval(bindings);
	}

	/*
	 * Tests the case when the expression is empty and exception is thrown
	 */
	@Test(expected = ExpressionException.class)
	public void emptyExpressionTest() throws ExpressionException {
		expression = new Expression("");
		expression.eval(bindings);
	}

	/*
	 * Tests the case when the expression is valid and we try to find set of
	 * bindings that make expression true and set of bindings is returned
	 */
	@Test
	public void validSetOfBindingsForExpressionTest() throws ExpressionException {
		expression = new Expression("||XYP");
		Set<String> actual = expression.solve();
		assertEquals(7, actual.size());
	}

	/*
	 * Tests the case when the expression is valid and we try to find set of
	 * bindings that make expression true but null is returned
	 */
	@Test
	public void noValidSetOfBindingsForExpressionTest() throws ExpressionException {
		expression = new Expression("|10");
		Set<String> actual = expression.solve();
		assertEquals(null, actual);
	}

	/*
	 * Tests the case when the expression is invalid and we try to find set of
	 * bindings that make expression true but exception is thrown
	 */
	@Test(expected = ExpressionException.class)
	public void invalidExpressionSetOfBindingsForExpressionTest() throws ExpressionException {
		expression = new Expression("+XY");
		expression.solve();
	}

	/*
	 * Tests the case when the expression is empty and we try to find set of
	 * bindings that make expression true but exception is thrown
	 */
	@Test(expected = ExpressionException.class)
	public void emptyExpressionSetOfBindingsForExpressionTest() throws ExpressionException {
		expression = new Expression("");
		expression.solve();
	}
}
