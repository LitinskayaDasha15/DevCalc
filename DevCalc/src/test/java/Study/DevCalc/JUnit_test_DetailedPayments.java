package Study.DevCalc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class JUnit_test_DetailedPayments {

	@Test
    public void testCalculateDetailedPayments() {
        // Preparation
        int depAmount = 153089;
        int depTerm = 34;
        double interestRate = 5.9;
        int capFreq = 4;
        Calc dep = new Calc(depAmount, depTerm, interestRate, capFreq);

        // Action
        List<Payment> payments = dep.CalculateDetailedPayments();

        // Assertion
        assertFalse(payments.isEmpty());
        assertEquals(13, payments.size());

        // Check some values in the payments list
        Payment firstPayment = payments.get(1);
        assertEquals(1, firstPayment.getNumOfPay());
        assertEquals("2024-04-25", firstPayment.getdateOfPay());
        assertEquals(155334.73, firstPayment.getDeposit(), 0.005);
        // Add more assertions based on your specific requirements
    }
}
