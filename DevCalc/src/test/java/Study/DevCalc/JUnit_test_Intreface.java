package Study.DevCalc;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class JUnit_test_Intreface {

	@Test
    public void testSave() {
        ExcelWorker excelWorker = new ExcelWorker();
        int loanAmount = 10000;
        int loanTerm = 12;
        double interestRate = 5.0;
        Calc loan = new Calc(loanAmount, loanTerm, interestRate);
        List<Payment> testData = loan.CalculateDetailedPayments();
        excelWorker.FillData(testData);

        String testFilePath = "test.xlsx";
        excelWorker.Save(testFilePath);

        File testFile = new File(testFilePath);
        assertTrue(testFile.exists());

        // Clean up after the test
        testFile.delete();
    }
}
