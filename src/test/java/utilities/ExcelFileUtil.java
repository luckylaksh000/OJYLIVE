package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.formula.functions.Index;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	//Constructor for Reading Excel Path
	public ExcelFileUtil(String ExcelPath) throws Throwable {
		FileInputStream file = new FileInputStream(ExcelPath);
		wb=WorkbookFactory.create(file);


	}
	//Method for Counting Rows
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();

	}
	//read Cell Data
	public String getcelldata(String sheetName, int row, int column)
	{
		String data= "";
		if(wb.getSheet(sheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata= (int) wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
			data= String.valueOf(celldata);

		}
		else
		{
			data= wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}


//method for Setcell data(fir writing)
public void setCellData(String sheetName, int row, int column, String status, String writeExcel) throws Throwable
{
	//get sheet from workbook
	Sheet ws =wb.getSheet(sheetName);
	//get row from sheet
	Row rowNum = ws.getRow(row);
	//create cell
	Cell cell = rowNum.createCell(column);
	//write status
	cell.setCellValue(status);
	if (status.equalsIgnoreCase("pass"))
		
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
		
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	
		
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rowNum.getCell(column).setCellStyle(style);
	
		
	}
	FileOutputStream file = new FileOutputStream(writeExcel);
	wb.write(file);
			
}



}



