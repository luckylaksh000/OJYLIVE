package utilities;



import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	public ExcelFileUtil(String ExcelPath)throws Throwable {
		FileInputStream fi = new FileInputStream(ExcelPath);
		wb= WorkbookFactory.create(fi);
	}
}

