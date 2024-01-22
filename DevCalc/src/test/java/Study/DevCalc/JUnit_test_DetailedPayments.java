package Study.DevCalc;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class JUnit_test_DetailedPayments {

	@Test
    public void testCalculateDetailedPayments() {
        // Preparation
        int loanAmount = 10000;
        int loanTerm = 12;
        double interestRate = 5.0;
        Calc loan = new Calc(loanAmount, loanTerm, interestRate);

        // Action
        List<Payment> payments = loan.CalculateDetailedPayments();

        // Assertion
        assertFalse(payments.isEmpty());
        assertEquals(12, payments.size());

        // Check some values in the payments list
        Payment firstPayment = payments.get(0);
        assertEquals(1, firstPayment.getNumOfPay());
        assertEquals("2024 февраль", firstPayment.getdateOfPay());
        assertEquals(856.0748, firstPayment.getSumOfPay(), 0.00001);
        // Add more assertions based on your specific requirements
    }
}
