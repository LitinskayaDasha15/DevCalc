package Study.DevCalc;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWorker implements Saveable {
	
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	
	public ExcelWorker() {
		workbook = new HSSFWorkbook();
		sheet = workbook.createSheet("Детализация по вкладу");
	}
	
	//Заполнение шапки
	private void CreateHead() {
		Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("№");
        row.createCell(1).setCellValue("Дата платежа");
        row.createCell(2).setCellValue("Начисленные проценты");
        row.createCell(3).setCellValue("Баланс");
	}
	
	//Заполнение подвала
	private void CreateUnder(int rowNum, double sumOfPercents, double sumOfDeposit) {
		Row row = sheet.createRow(rowNum);
        row.createCell(1).setCellValue("ИТОГО:");
        row.createCell(2).setCellValue(String.format("%1$.2f", sumOfPercents));
        row.createCell(3).setCellValue(String.format("%1$.2f", sumOfDeposit));
	}
	
	//Заполнение данных
	public void FillData(List<Payment> data) {
		CreateHead();
		int rowNum = 1;
		double sumOfPercents = 0;
		
		for (Payment pay : data){
			Row row = sheet.createRow(rowNum);
	        row.createCell(0).setCellValue(pay.getNumOfPay());
	        row.createCell(1).setCellValue(pay.getdateOfPay());
	        row.createCell(2).setCellValue(String.format("%1$.2f",pay.getPercents()));
	        row.createCell(3).setCellValue(String.format("%1$.2f",pay.getDeposit()));
	        sumOfPercents = sumOfPercents + pay.getPercents();
	        rowNum++;
		}
		
		CreateUnder(rowNum, sumOfPercents, data.get(rowNum-2).getDeposit());
		
		for (int clmn = 0; clmn <=3; clmn++)
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
