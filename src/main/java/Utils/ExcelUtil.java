package Utils;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


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

    public static String createExcelOutputResults(String pathToInputFile, String tag, String user) {
        if (tag.startsWith("@")) tag = tag.substring(1);

        DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String pathToOutputFile = pathToInputFile.replace("Input", "Output");
        File folder = new File(pathToOutputFile + "\\" + "Results");

        if (folder.mkdirs()) System.out.println("'Results' folder was created");

        String folderPath = folder.getAbsolutePath() + "\\" + tag + sdf.format(Calendar.getInstance().getTime()) + (user.isEmpty() ? "" : "_" + user) + ".xlsx";
        File file = new File(folderPath);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            workbook.createSheet(tag);
            FileOutputStream output_file = new FileOutputStream(file);
            workbook.write(output_file);
            output_file.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return folderPath;
    }
}