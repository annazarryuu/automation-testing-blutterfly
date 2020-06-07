package com.id.perpus.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class POIUtility {
	/**
	 * Prepate excel template file.
	 * @param fileName the template file.
	 * @return workbook
	 * @throws IOException
	 */
    @SuppressWarnings("unused")
	public static HSSFWorkbook prepareTemplate(String fileName) throws IOException {
        HSSFWorkbook wb;        
        String[] arFileName = split(fileName, ".");
        String stFileName = arFileName[0];
        stFileName += '.' + arFileName[1];
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
        wb = new HSSFWorkbook(fs);
        return wb;
    }
    
    public static String[] split(String str, String delim) {
		StringTokenizer st = new StringTokenizer(str, delim);
		String[] arStr = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++)
			arStr[i] = st.nextToken();
		return arStr;
	}
    
    /**
     * Prepare excel template file.
     * @param isStream the stream template.
     * @return workbook.
     * @throws IOException
     */
    public static HSSFWorkbook prepareTemplate(InputStream isStream) throws IOException {
        HSSFWorkbook wb;        
        POIFSFileSystem fs = new POIFSFileSystem(isStream);
        wb = new HSSFWorkbook(fs);
        return wb;
    }    
    
    /**
     * Return the specified cell.
     * @param sheet the worksheet.
     * @param intRow the row.
     * @param intCol the column.
     * @return the specified cell.
     */
    public static HSSFCell getCell(HSSFSheet sheet, int intRow, int intCol) {
        HSSFRow row = sheet.getRow(intRow);
        
        if (row==null) return null;
        HSSFCell cell = row.getCell((short)intCol);
                            
        if (cell == null) cell = row.createCell((short) intCol);
        return cell;
    }

    /**
     * Add by Irwan Wijaya
     * @param sheet the worksheet.
     * @param intRow the row.
     * @param intCol the column. 
     * @param isString is flag to indicated that the cell in Excel file is String.
     * if isString = yes then it will convert to exactly String
     * if isString = no then it will get the value from Excel (return exponen number)
     * Add new checking to allow get input Formula from Excel File.
     */    
    @SuppressWarnings("deprecation")
	public static String getCellString(HSSFSheet sheet, int intRow, int intCol, boolean isString) {
        
        String stValue = "";
        HSSFCell cell = getCell(sheet, intRow, intCol);
        if (cell==null) return "";
        else {            
	        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
	            stValue = cell.getStringCellValue();
	        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
	            if  (isString) {
	                stValue = formatDoubleToString(new Double (cell.getNumericCellValue()),"###");
	            }
	            else {
	                stValue = Double.toString(cell.getNumericCellValue());    
	            }	                
	        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
	            stValue = Boolean.toString(cell.getBooleanCellValue());
	        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
	            stValue = "";
	        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
	            stValue = Double.toString(cell.getNumericCellValue());
	        }
	        return stValue;
        }
    }
    
    
    public static String formatDoubleToString(Double dbValue, String stFormat) {
    	DecimalFormat df = new DecimalFormat("###,###.##############");	
    	
    	String stReturn;
        synchronized (df) {
            df.applyPattern(stFormat); 
            stReturn = dbValue == null ? "" : df.format(dbValue);
            df.applyPattern("###,###.##############");
        }
        return stReturn;
    }
    
    /**
     * give cell value in double type.
     * @param sheet the worksheet.
     * @param intRow the row.
     * @param intCol the column.
     * @return cell value in double type.
     */
    @SuppressWarnings("deprecation")
	public static double getCellDouble(HSSFSheet sheet, int intRow, int intCol) {
        double dblValue = 0;
        try {
            HSSFRow row = sheet.getRow(intRow);
            HSSFCell cell = row.getCell((short) intCol);
            if (cell == null) {
                return 0;
            }
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                if (isInteger(cell.getStringCellValue())) { 
                    dblValue = Double.parseDouble(cell.getStringCellValue());
                }
            } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                dblValue = cell.getNumericCellValue();
            }
        } catch (Exception e) {
            dblValue = 0;
        }        
        return dblValue;
    }
    
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
    
    
    public static String getWEBINFPath(){
    	String stWebInf="";    	
    	Properties prop = System.getProperties();
    	prop.list(System.out);               
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("was.install.root"));
        System.out.println(System.getProperty("db2j.system.home"));
        System.out.println(System.getProperty("was.repository.temp"));
        System.out.println(System.getProperty("sun.boot.library.path"));
        System.out.println(System.getProperty("sun.boot.library.path"));
        System.out.println(System.getProperty("server.root"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.security.policy"));
        System.out.println(System.getProperty("was.repository.root"));
        System.out.println(System.getProperty("java.library.path"));        
        System.out.println(System.getProperty("ws.ext.dirs"));
        System.out.println(System.getProperty("java.io.tmpdir"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.install.root"));
        //System.out.println(System.getProperty("com.ibm.itp.location"));
        System.out.println(System.getProperty("com.ibm.oti.system.class.path"));
        System.out.println(System.getProperty("java.security.auth.login.config"));        
    	return stWebInf;
    }
    
    /**
     * To replace the new value for parameter array.
     * @param temp the array.
     * @param newValue the new value for temp[temp.length-1].
     * @param Counter the length of temp.
     * @return new array contain newValue.
     */
    @SuppressWarnings("unused")
	private static String[] newArr(String[] temp, String newValue, int Counter) {    	
        String[] tmpVal = new String[Counter];
        System.arraycopy(temp, 0, tmpVal, 0, temp.length);
        temp = tmpVal;
        temp[Counter - 1] = newValue;
        return temp;
    }
    
    
    
    /**
     * split a string to array.
     * @param s the parameter string to be splitted.
     * @param sep the separator.
     * @return an array of string.
     */
    @SuppressWarnings("unused")
	private static String[] Split2Array(String s, String sep) {
        StringBuffer buf = new StringBuffer(s);
        int arraysize = 1;
        for (int i = 0; i < buf.length(); i++) {
            if (sep.indexOf(buf.charAt(i)) != -1)
                arraysize++;
        }
        String[] elements = new String[arraysize];
        int y, z = 0;
        if (buf.toString().indexOf(sep) != -1) {
            while (buf.length() > 0) {
                if (buf.toString().indexOf(sep) != -1) {
                    y = buf.toString().indexOf(sep);
                    if (y != buf.toString().lastIndexOf(sep)) {
                        elements[z] = buf.toString().substring(0, y);
                        z++;
                        buf.delete(0, y + 1);
                    } else if (buf.toString().lastIndexOf(sep) == y) {
                        elements[z] = buf.toString().substring(0, buf.toString().indexOf(sep));
                        z++;
                        buf.delete(0, buf.toString().indexOf(sep) + 1);
                        elements[z] = buf.toString();
                        z++;
                        buf.delete(0, buf.length());
                    }
                }
            }
        } else {
            elements[0] = buf.toString();
        }
        buf = null;
        return elements;
    }
    
     /**
      * copy a row to another row.
      * @param sheet the worksheet.
      * @param source row source.
      * @param dest row destination.
      */
     public static void copyRow(HSSFSheet sheet, int source, int dest) {
        copyRow(sheet, source, dest, false);
     }

     /**
      * copy a row to another row.
      * @param sheet the worksheet.
      * @param source row source.
      * @param dest row destination.
      * @param blSameHeight flag whether destination row will be set the same height.
      */
     @SuppressWarnings("deprecation")
	public static void copyRow(HSSFSheet sheet, int source, int dest, boolean blSameHeight) {

         HSSFRow rowSource = sheet.getRow(source);
         HSSFRow rowDest = sheet.createRow(dest);

         for (short col=0; col<=rowSource.getLastCellNum(); col++) {
             HSSFCell cellSource = rowSource.getCell(col);
             HSSFCell cellDest = rowDest.createCell(col);

             if (cellSource != null)
             {
                 int cellType = cellSource.getCellType();

                 switch (cellType) {
                     case HSSFCell.CELL_TYPE_STRING :
                         cellDest.setCellValue(cellSource.getStringCellValue());
                         break;
                     case HSSFCell.CELL_TYPE_NUMERIC :
                         cellDest.setCellValue(cellSource.getNumericCellValue());
                         break;
                     default :
                         break;
                 }
                 cellDest.setCellStyle(cellSource.getCellStyle());
             }
         }
         if (blSameHeight) {
             rowDest.setHeightInPoints(rowSource.getHeightInPoints());
         }

     }

     /**
      * copy a row to another row.
      * @param sheet the worksheet.
      * @param source row source.
      * @param dest row destination.
      */
     public static void copyCell(HSSFSheet sheet, int rowSource, int colSource, int rowDest, int ColDest) {
        copyCell(sheet, rowSource, colSource,rowDest, ColDest, false);
     }

     /**
      * copy a row to another row.
      * @param sheet the worksheet.
      * @param source row source.
      * @param dest row destination.
      * @param blSameHeight flag whether destination row will be set the same height.
      */
     @SuppressWarnings("deprecation")
	public static void copyCell(HSSFSheet sheet, int rowSource, int colSource, int rowDest, int ColDest, boolean blSameHeight) {

         HSSFRow rowSourceData = sheet.getRow(rowSource);
         HSSFRow rowDestData = sheet.getRow(rowDest);
         HSSFCell cellSource = rowSourceData.getCell((short)colSource);
         HSSFCell cellDest = rowSourceData.createCell((short)ColDest);

             if (cellSource != null)
             {
                 int cellType = cellSource.getCellType();

                 switch (cellType) {
                     case HSSFCell.CELL_TYPE_STRING :
                         cellDest.setCellValue(cellSource.getStringCellValue());
                         break;
                     case HSSFCell.CELL_TYPE_NUMERIC :
                         cellDest.setCellValue(cellSource.getNumericCellValue());
                         break;
                     default :
                         break;
                 }
                 cellDest.setCellStyle(cellSource.getCellStyle());
             }
         
         if (blSameHeight) {
             rowDestData.setHeightInPoints(rowSourceData.getHeightInPoints());
         }

     }
     /**
      * Create a new cell.
      * @param sheet the worksheet.
      * @param intRow the row.
      * @param intCol the column.
      * @return new cell.
      */
     private static HSSFCell createCell(HSSFSheet sheet, int intRow, int intCol) {
         HSSFRow row = sheet.getRow(intRow);
         if (row==null) row = sheet.createRow(intRow);
         HSSFCell cell = row.getCell((short)intCol);
         if (cell==null) cell = row.createCell((short)intCol);
         return cell;
     }
     
     /**
      * Set cell value with a string.
      * @param sheet the worksheet.
      * @param row the row.
      * @param col the column.
      * @param text the cell value.
      */
     public static void setText(HSSFSheet sheet, int row, int col, String text) {
         HSSFCell cell = createCell(sheet, row, col);
         cell.setCellValue(text);
     }

     /**
      * Set cell value with an integer.
      * @param sheet the worksheet.
      * @param row the row.
      * @param col the column.
      * @param value the cell value.
      */
     public static void setInt(HSSFSheet sheet, int row, int col, int value) {
         HSSFCell cell = createCell(sheet, row, col);
         cell.setCellValue(value);
     }

     /**
      * Set cell value with a date.
      * @param sheet the worksheet.
      * @param row the row.
      * @param col the column.
      * @param date the cell value.
      */
     public static void setDate(HSSFSheet sheet, int row, int col, java.util.Date date) {
         HSSFCell cell = createCell(sheet, row, col);
         if (date==null) cell.setCellValue("");
         else cell.setCellValue(date);
     }

     /**
      * Write workbook via HttpServletResponse.
      * @param report the workbook.
      * @param response the HttpServletResponse
      * @param stFileName the file name.
      * @throws IOException
      */
     public static void writeToResponse(HSSFWorkbook report, HttpServletResponse response, String stFileName)
             throws IOException {
         response.setContentType("application/vnd.ms-excel");
         String currentDate = "";
         stFileName = stFileName + currentDate + ".xls";
         response.setHeader("Content-Disposition", "attachment;filename=" + stFileName);
         ServletOutputStream fileOut = response.getOutputStream();
         report.write(fileOut);
         fileOut.flush();
     }

     /**
      * Set cell value.
      * @param sheet the worksheet.
      * @param row the row.
      * @param col the column.
      * @param obj the value of cell.
      */
     public static void setObject(HSSFSheet sheet, int row, int col, Object obj) {
         HSSFCell cell = createCell(sheet, row, col);
         if (obj==null) cell.setCellValue("");
         else if (obj instanceof String)
             cell.setCellValue((String)obj);
         //else if (obj instanceof Number)
             //cell.setCellValue(((Number)obj).intValue());
         else if (obj instanceof Number)
             cell.setCellValue(((Number)obj).doubleValue());
         else if (obj instanceof Date)
             cell.setCellValue((Date)obj);
     }
     
     /**
      * Get the list of column names.
      * @param sheet the worksheet.
      * @param intRow the row.
      * @param startCol the starting column.
      * @return list of column names.
      */
     @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getColNames(HSSFSheet sheet, int intRow, int startCol) {
         List colNames = new ArrayList();
         HSSFRow row = sheet.getRow(intRow-1);
         for (short col = (short)(startCol-1); col < row.getLastCellNum(); col++) {
             HSSFCell cell = row.getCell(col);
             if (cell==null) break;
             String colName = row.getCell(col).getStringCellValue();
             if (colName == null || colName.trim().equals( "" ) ) break;
             colNames.add(colName);
         }
         return colNames;
     }
     
     /**
      * To give border bottom to specified row (usually last row).
      * @param wb the workbook.
      * @param intSheet	thw worksheet.
      * @param intRow the last row.
      */
     @SuppressWarnings("deprecation")
	public static void lastRow(HSSFWorkbook wb, int intSheet, int intRow) {
         HSSFRow row = wb.getSheetAt(intSheet).getRow(intRow);
         for (short i=0; i<row.getLastCellNum()-2; i++) {
             try {
                 HSSFCellStyle oldStyle = row.getCell(i).getCellStyle();
                 HSSFCellStyle newStyle = wb.createCellStyle();
                 newStyle.setAlignment(oldStyle.getAlignment());
                 newStyle.setBorderLeft(oldStyle.getBorderLeft());
                 newStyle.setBorderRight(oldStyle.getBorderRight());
                 newStyle.setDataFormat(oldStyle.getDataFormat());
                 newStyle.setFont(wb.getFontAt(oldStyle.getFontIndex()));
                 newStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
                 row.getCell(i).setCellStyle(newStyle);
             } catch(NullPointerException npe) {}
         }
     }
     
     /**
      * To give border bottom to specified row (usually last row).
      * @param wb the workbook.
      * @param intRow the last row.
      */
     @SuppressWarnings("deprecation")
	public static void lastRow(HSSFWorkbook wb, int intRow) {
         HSSFRow row = wb.getSheetAt(0).getRow(intRow);
         for (short i=0; i<row.getLastCellNum()-2; i++) {
             try {
                 HSSFCellStyle oldStyle = row.getCell(i).getCellStyle();
                 HSSFCellStyle newStyle = wb.createCellStyle();
                 newStyle.setAlignment(oldStyle.getAlignment());
                 newStyle.setBorderLeft(oldStyle.getBorderLeft());
                 newStyle.setBorderRight(oldStyle.getBorderRight());
                 newStyle.setDataFormat(oldStyle.getDataFormat());
                 newStyle.setFont(wb.getFontAt(oldStyle.getFontIndex()));
                 newStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
                 row.getCell(i).setCellStyle(newStyle);
             } catch(NullPointerException npe) {}
         }
     }
     
     /**
      * To give border bottom to specified row (usually last row).
      * @param row the last row.
      */
     @SuppressWarnings("deprecation")
	public static void lastRow(HSSFRow row) {
         for (short i=0; i<row.getLastCellNum(); i++) {
             try {
                 HSSFCellStyle style = row.getCell(i).getCellStyle();
                 style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
                 row.getCell(i).setCellStyle(style);
             } catch(NullPointerException npe) {}
         }
     }

     /**
      * To copy format (style) of a row.
      * @param rowSource the source row.
      * @param rowDest	the destination row.
      */
     public static void copyFormat(HSSFRow rowSource, HSSFRow rowDest) {
         rowDest.setHeight(rowSource.getHeight());
         for (short i=0; i<rowSource.getLastCellNum(); i++) {
             try {
                 rowDest.getCell(i).setCellStyle(rowSource.getCell(i).getCellStyle());
             } catch(NullPointerException npe) {}
         }
     }
     
     /**
      * To copy format (style) of a row.
      * @param rowSource the source row.
      * @param rowDest	the destination row.
      */
     public static void copyFormatCell(HSSFSheet sheet, int rowSource,int colSource, int rowDest, int colDest) {
            try {
            	HSSFRow rowResource  = getRow(sheet, rowSource);
            	HSSFRow rowDestination  = getRow(sheet, rowDest);
            	copyFormatCell(getCell(rowResource, colSource), getCell(rowDestination, colDest));
             } catch(NullPointerException npe) {}
     }
     /**
      * To copy format (style) of a row.
      * @param rowSource the source row.
      * @param rowDest	the destination row.
      */
     public static void copyFormatCell(HSSFCell cellSource, HSSFCell cellDest) {
            try {
            	 cellDest.setCellStyle(cellSource.getCellStyle());
             } catch(NullPointerException npe) {}
     }
    

     /**
      * To get the specified row.
      * @param sheet the worksheet.
      * @param intRow the specified row index.
      * @return the specified HSSFRow.
      */
     public static HSSFRow getRow(HSSFSheet sheet, int intRow) {
         HSSFRow row = sheet.getRow(intRow);
         if (row==null) row = sheet.createRow(intRow);
         return row;
     }

     /**
      * To get the specified cell.
      * @param row the row.
      * @param intCol the column.
      * @return the specified HSSFCell.
      */
     public static HSSFCell getCell(HSSFRow row, int intCol) {
         HSSFCell cell = row.getCell((short)intCol);
         if (cell==null) cell = row.createCell((short)intCol);
         return cell;
     }
     
     /**
      * Delete several rows.
      * @param sheet the worksheet.
      * @param from index of starting row to be deleted.
      * @param count number of row to be deleted.
      */
     public static void deleteRow(HSSFSheet sheet, int from, int count) {
         deleteRow(sheet, from, count, false);
     }
     
     /**
      * Delete several rows, and replace the deleted row with the new row.
      * @param sheet the worksheet.
      * @param from index of starting row to be deleted.
      * @param count number of row to be deleted.
      * @param blSameHeight flag whether the new row will have the same height.
      */
     public static void deleteRow(HSSFSheet sheet, int from, int count, boolean blSameHeight) {
 		for (int i=from+count; i<=sheet.getLastRowNum(); i++) {
			copyRow(sheet,i,from++, blSameHeight);
         }
 		for ( ; from<=sheet.getLastRowNum(); from++) {
 			HSSFRow rowDelete = sheet.getRow(from);
 			if (rowDelete!=null) sheet.removeRow(rowDelete);
 		}
 	 }

     /**
      * Merge several cells.
      * @param sheet the worksheet.
      * @param rowFrom starting row.
      * @param colFrom starting column.
      * @param rowTo ending row.
      * @param colTo ending column.
      */
     /*public static void mergeCells(HSSFSheet sheet, int rowFrom, int colFrom, int rowTo, int colTo) {
         sheet.addMergedRegion(new Region(rowFrom, (short) colFrom, rowTo, (short) colTo));
     }*/    
}
