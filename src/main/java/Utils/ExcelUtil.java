package Utils;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;



public class ExcelUtil {
    public static void addRowToExcel(ArrayList<String> results, String filePath, String sheetName) throws IOException {
        try {
            FileInputStream fIPS = new FileInputStream(filePath);
            XSSFWorkbook wb = new XSSFWorkbook(fIPS);
            XSSFSheet worksheet;

            if (sheetName.length() > 31) {
                int diff = sheetName.length() - 31;
                sheetName = sheetName.substring(0, sheetName.length() - diff);
            }

            worksheet = wb.getSheet(sheetName);
            int lastRowNum = worksheet.getLastRowNum();
            XSSFRow rowx;
            int x;

            if (lastRowNum != 0) x = lastRowNum + 1;

            else {
                int num = worksheet.getPhysicalNumberOfRows();

                if (num == 0) x = 0;
                else x = 1;
            }

            rowx = worksheet.createRow(x);
            int i = 0;

            for (String s : results) {
                XSSFCell cellx = rowx.createCell(i);
                cellx.setCellValue(s);
                i++;
            }

            fIPS.close();
            FileOutputStream output_file = new FileOutputStream(filePath);
            wb.write(output_file);
            output_file.close();
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}