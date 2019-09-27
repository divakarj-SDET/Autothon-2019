package autothon.webui.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static StringBuilder excelFilePath = new StringBuilder(System.getProperty("user.dir"));
	private static Workbook wb;
	private static Sheet sheet;
	private static int iTestCaseIdColumn;
	private static int iTestCaseIdRow;
	private static Row row;
	
	/**
	 * This method will read the value of a specied column for a named test case in the first column
	 * @param strFileNameWithExtension
	 * @param strTestcaseId
	 * @param strSheetName
	 * @param strColumnName
	 * @return
	 * @throws Exception
	 */
	public static String getExcelData(String strFileNameWithExtension, String strTestcaseId, String strSheetName, String strColumnName) throws Exception{
				
		File file =    new File(excelFilePath+"\\resources\\TestData\\"+strFileNameWithExtension);
		
		FileInputStream inputStream = new FileInputStream(file);
		String fileExtensionName = strFileNameWithExtension.substring(strFileNameWithExtension.indexOf("."));
		if(fileExtensionName.equals(".xlsx")) {
			wb= new XSSFWorkbook(inputStream);
		}
		else if(fileExtensionName.equals(".xls")){

	        //If it is xls file then create object of HSSFWorkbook class

	        wb = new HSSFWorkbook(inputStream);

	    }
		
		sheet = wb.getSheet(strSheetName);
		
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		
		//Create a loop over all the rows of excel file to read it
		for(int i=0;i<=rowCount;i++) {
			
			row = sheet.getRow(i);
			
			if(row.getCell(iTestCaseIdColumn).getCellType().equals(CellType.STRING)) {
				if(row.getCell(iTestCaseIdColumn).getStringCellValue().equals(strTestcaseId)) {
					iTestCaseIdRow=i;
				}
			}
			
		}
		
		String strColumnValue=null; int iColumnName = 0;
		//Loop over column
		for(int col= 0;col<row.getLastCellNum();col++) {
			row = sheet.getRow(0);
			if(row.getCell(col).getCellType() .equals(CellType.STRING))
				if(row.getCell(col).getStringCellValue().equals(strColumnName)) {
					iColumnName = col; break;}
			
				else
					continue;
			
			
		}
		
		row = sheet.getRow(iTestCaseIdRow);
		
		if(row.getCell(iColumnName).getCellType().equals(CellType.STRING)) {
			strColumnValue = row.getCell(iColumnName).getStringCellValue();
		}
		else
			throw new Exception("Invalid Column Value for column name:"+strColumnName +"for test Case Id"+strTestcaseId);
		
		
		
		
		
		return strColumnValue;
		
		
	}
}
