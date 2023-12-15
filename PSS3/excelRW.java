import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/*
Uses apache POI
sources: https://www.callicoder.com/java-write-excel-file-apache-poi/
 */
public class excelRW
{

    private static String[] columns = {"X", "Y"};
    public excelRW()
    {

    }
    //method to create and open excel using csv file
    public void createExcelSheet(List<Point> pointList,String excelSheetName,String programType)
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(excelSheetName);
        Row headerRow = sheet.createRow(0);
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        int rowNum = 1;
        for(Point point : pointList)
        {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(point.getX());

            row.createCell(1)
                    .setCellValue(point.getY());

        }
        for(int i = 0; i < columns.length; i++)
        {
            sheet.autoSizeColumn(i);
        }

        for(int i = 0; i < 2; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        try
        {
            FileOutputStream fileOut = new FileOutputStream(programType+".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            //opens excel file
            Desktop.getDesktop().open(new File(programType+".xlsx"));
        }
        catch (FileNotFoundException ex)
        {
           // ex.printStackTrace();
        }
        catch (IOException ex2)
        {
           // ex2.printStackTrace();
        }
    }
}
