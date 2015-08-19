package com.excelutility.writer;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RecordWriter {
    private String maxLengthString = "";
    //streaming version of workbook
    private SXSSFWorkbook workbook;
    private SXSSFSheet sheet;
    private File outputFile;

    public RecordWriter(SXSSFWorkbook workbook, String sheetName, File outputFile) {
        this.workbook = workbook;
        this.sheet = (SXSSFSheet) workbook.createSheet(sheetName);
        this.outputFile = outputFile;
    }

    public void addSequentialNumbersWithConstantString(Integer startValue, int stepValue, int numberOfRecords, int constantStringLength) {
        setupConstantString(constantStringLength);
        int i = 0;
        int fromRowNum = 0;
        while (i++ < numberOfRecords) {
            Row row = sheet.createRow(fromRowNum++);
            String stringToAppend = maxLengthString;
            Object[] objArr = new Object[]{stringToAppend + startValue};
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
            startValue += stepValue;
        }
        sheet.autoSizeColumn(0);
    }

    public void write() throws IOException {
        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(outputFile);
        workbook.write(out);
        out.close();
    }

    private void setupConstantString(int maxLength) {
        StringBuilder str = new StringBuilder();
        maxLengthString = "";
        for (int j = 0; j < maxLength; j++) {
            str.append("S");
        }
        maxLengthString = str.toString();
    }
}
