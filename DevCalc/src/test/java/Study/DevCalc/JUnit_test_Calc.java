package Study.DevCalc;

import org.junit.Test;

import junit.framework.TestCase;

public class JUnit_test_Calc extends TestCase {
	@Test
	public void testCalculateDeposit() {
		// Preparation
		int depAmount = 100000;
		int depTerm = 12;
		double interestRate = 5.0;
		int freq = 4;
		Calc dep = new Calc(depAmount, depTerm, interestRate, freq);

		// Action
		double result = dep.CalculateDeposit();

		// Assertion
		assertEquals(116075.45, result);
	}
}