package Study.DevCalc;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWorker {
	
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	
	public ExcelWorker() {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("График платежей");
	}
	
	//Заполнение шапки
	private void CreateHead() {
		Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("№");
        row.createCell(1).setCellValue("Дата платежа");
        row.createCell(2).setCellValue("Сумма платежа");
        row.createCell(3).setCellValue("Основной долг");
        row.createCell(4).setCellValue("Проценты");
        row.createCell(5).setCellValue("Остаток долга");
        
	}
	
	//Заполнение подвала
	private void CreateUnder(int rowNum, double sumOfPay, double mainDebt, double percents) {
		Row row = sheet.createRow(rowNum);
        row.createCell(1).setCellValue("ИТОГО:");
        row.createCell(2).setCellValue(sumOfPay);
        row.createCell(3).setCellValue(mainDebt);
        row.createCell(4).setCellValue(percents);
	}
	
	//Заполнение данных
	public void FillData(List<Payment> data) {
		CreateHead();
		int rowNum = 1;
		double sumOfPay = 0;
		double mainDebt = 0;
		double percents = 0;
		for (Payment pay : data){
			Row row = sheet.createRow(rowNum);
	        row.createCell(0).setCellValue(pay.getNumOfPay());
	        row.createCell(1).setCellValue(pay.getdateOfPay().toString());
	        row.createCell(2).setCellValue(pay.getSumOfPay());
	        row.createCell(3).setCellValue(pay.getMainDebt());
	        row.createCell(4).setCellValue(pay.getPercents());
	        row.createCell(5).setCellValue(pay.getRemainDebt());
	        sumOfPay = Calc.RoundTo(sumOfPay + pay.getSumOfPay(), 2);
	        mainDebt = Calc.RoundTo(mainDebt + pay.getMainDebt(),2);
	        percents = Calc.RoundTo(percents + pay.getPercents(),2);
	        rowNum++;
		}
		CreateUnder(rowNum, sumOfPay, mainDebt, percents);
		for (int clmn = 0; clmn <=5; clmn++)
		{
			sheet.autoSizeColumn(clmn);
		}
	}
	
	//Сохранение файла
	public void Save(String path) {
		try (FileOutputStream out = new FileOutputStream(new File(path))) {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
