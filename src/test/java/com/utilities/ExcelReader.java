package com.lms.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	public static Map<String, List<Map<String, String>>> loadExcelData() {
		
		Workbook wb = null;
		Map<String, List<Map<String, String>>> sheetNameRowsMap = new HashMap<>();
		
		try {
			
			wb = WorkbookFactory.create(ExcelReader.class.getResourceAsStream("/testData/LMSData.xlsx"));
			
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				List<Map<String, String>> recordList = new ArrayList<>();
				Row headerRow = sheet.getRow(0);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {
					Row row = sheet.getRow(j);
				int colCount = headerRow.getPhysicalNumberOfCells();
					Map<String, String> record = new HashMap<>();
					for (int k = 0; k < colCount; k++) {
				//	for (int k = 0; k < row.getLastCellNum(); k++) {
						Cell cell = row.getCell(k);
						String cellValue = cell!= null ? cell.getCellType()==CellType.NUMERIC ? ((long)cell.getNumericCellValue() + "") : cell.getStringCellValue():null;
						String key = headerRow.getCell(k)!=null? headerRow.getCell(k).getStringCellValue():"";
						record.put(key, cellValue);
					}
					recordList.add(record);
				}
				sheetNameRowsMap.put(sheet.getSheetName(), recordList);
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sheetNameRowsMap;
	}
	
}
