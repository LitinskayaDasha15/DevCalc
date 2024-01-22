package Study.DevCalc;

import org.junit.Test;

import junit.framework.TestCase;

public class JUnit_test_Calc extends TestCase {
	@Test
	public void testCalculateStaticPayment() {
		// Preparation
		int loanAmount = 10000;
		int loanTerm = 12;
		double interestRate = 5.0;
		Calc loan = new Calc(loanAmount, loanTerm, interestRate);

		// Action
		double result = loan.CalculateStaticPayment();

		// Assertion
		assertEquals(856.0748, result);
	}

}
