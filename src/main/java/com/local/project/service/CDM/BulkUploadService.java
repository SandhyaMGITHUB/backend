package com.local.project.service.CDM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.local.project.model.CDM.CompanyAddress;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class BulkUploadService {
	static String[] HEADERs = { "company_name", "company_mail_id"};
	static String SHEET = "CompanyAddress";
	
	@Transactional
	public List<String> save(MultipartFile addressfile) throws IOException {
		//Cell cellvalue = null;
		Workbook workbook = new XSSFWorkbook(addressfile.getInputStream());
		Sheet sheet = workbook.getSheet(SHEET);
		Iterator<Row> rows = sheet.iterator();
		int rowNumber = 0;
		List<String> tutorials = new ArrayList<>();
		while(rows.hasNext()) {
			Row currentRow = rows.next();
			// skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }
	        Iterator<Cell> cellsInRow = currentRow.iterator();
	        CompanyAddress cmpaddress = new CompanyAddress();
	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	        	
	        	Cell currentCell = cellsInRow.next();
	        	switch (cellIdx) {
	        	case 0:
	        		String x = currentCell.getStringCellValue();
	        		tutorials.add(x);
	        		break;
	        	}
	        }
		}
		return tutorials;
		
	}

}
