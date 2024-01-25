package Study.DevCalc;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class JUnit_test_Intreface {

  @Test
    public void testSave() {
        ExcelWorker excelWorker = new ExcelWorker();
        int depAmount = 153089;
        int depTerm = 34;
        double interestRate = 5.9;
        int capFreq = 4;
        Calc dep = new Calc(depAmount, depTerm, interestRate, capFreq);
        List<Payment> testData = dep.CalculateDetailedPayments();
        excelWorker.FillData(testData);

        String testFilePath = "test.xlsx";
        excelWorker.Save(testFilePath);

        File testFile = new File(testFilePath);
        assertTrue(testFile.exists());

        // Clean up after the test
        testFile.delete();
    }
}