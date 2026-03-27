package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook wb;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;

    // Constructor
    public ExcelUtility(String path)
    {
        this.path = path;
    }

    // Get Row Count
    public int getRowCount(String sheetName) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }

    // Get Cell Count
    public int getCellCount(String sheetName,int rownum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }

    // Get Cell Data
    public String getCellData(String sheetName,int rownum,int colnum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        String data;

        try
        {
            data = cell.toString();
        }
        catch(Exception e)
        {
            data = "";
        }

        wb.close();
        fi.close();
        return data;
    }

    // Set Cell Data
    public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);

        row = sheet.getRow(rownum);
        if(row==null)
            row = sheet.createRow(rownum);

        cell = row.getCell(colnum);
        if(cell==null)
            cell = row.createCell(colnum);

        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }

    // Fill Green Color
    public void fillGreenColor(String sheetName,int rownum,int colnum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }

    // Fill Red Color
    public void fillRedColor(String sheetName,int rownum,int colnum) throws IOException
    {
        fi = new FileInputStream(path);
        wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(path);
        wb.write(fo);

        wb.close();
        fi.close();
        fo.close();
    }
}


