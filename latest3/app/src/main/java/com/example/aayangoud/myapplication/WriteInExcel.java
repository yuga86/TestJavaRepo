package com.example.aayangoud.myapplication;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.aayangoud.myapplication.DTO.TransportFormDTO;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Aayan Goud on 9/3/2016.
 */
public class WriteInExcel {

    public static boolean saveExcelFile(Context context, String fileName, List<TransportFormDTO> list) {

        // check if available and not read only
       /* if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Log.w("FileUtils", "Storage not available or read only");
            return false;
        }
      */
        boolean success = false;
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //handle case of no SDCARD present
        } else {

            //New Workbook
            Workbook wb = new HSSFWorkbook();

            Font font= wb.createFont();
            font.setFontHeightInPoints((short)10);
            font.setFontName("Segoe UI");
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setBold(true);
            font.setItalic(false);

            Cell c = null;

            //Cell style for header row
            CellStyle cs = wb.createCellStyle();
            cs.setFillForegroundColor(HSSFColor.LIME.index);
            cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs.setAlignment(CellStyle.ALIGN_CENTER);
            cs.setFont(font);
            //New Sheet
            Sheet sheet1 = null;
            sheet1 = wb.createSheet(Comman.getCurrentMonth());

            // Generate column headings
            Row headerRow = sheet1.createRow(0);
            c = headerRow.createCell(0);
            c.setCellValue("Transport Details");
            c.setCellStyle(cs);
            sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));


            Row row1 = sheet1.createRow(1);
            c = row1.createCell(0);
            c.setCellValue("Date");
            c.setCellStyle(cs);

            c = row1.createCell(1);
            c.setCellValue("Driver Name");
            c.setCellStyle(cs);

            c = row1.createCell(2);
            c.setCellValue("From");
            c.setCellStyle(cs);

            c = row1.createCell(3);
            c.setCellValue("Time");
            c.setCellStyle(cs);

            c = row1.createCell(4);
            c.setCellValue("To");
            c.setCellStyle(cs);

            c = row1.createCell(5);
            c.setCellValue("Time");
            c.setCellStyle(cs);

            c = row1.createCell(6);
            c.setCellValue("Duration");
            c.setCellStyle(cs);

            c = row1.createCell(7);
            c.setCellValue("ACFT REGN");
            c.setCellStyle(cs);

            c = row1.createCell(8);
            c.setCellValue("MDC InTime");
            c.setCellStyle(cs);

            c = row1.createCell(9);
            c.setCellValue("MDC OutTime");
            c.setCellStyle(cs);

            c = row1.createCell(10);
            c.setCellValue("Delay");
            c.setCellStyle(cs);

            c = row1.createCell(11);
            c.setCellValue("Type");
            c.setCellStyle(cs);

            CellStyle cs2 = wb.createCellStyle();
            //cs.setFillForegroundColor(HSSFColor.LIME.index);
            //cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs2.setAlignment(CellStyle.ALIGN_CENTER);

            CellStyle cs3 = wb.createCellStyle();
            cs3.setFillForegroundColor(HSSFColor.YELLOW.index);
            cs3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            cs3.setAlignment(CellStyle.ALIGN_CENTER);

            if (list != null && list.size() > 0) {
                for (int count = 0; count < list.size(); count++) {
                    TransportFormDTO dto = list.get(count);
                    Row loopRow = sheet1.createRow(count + 2);

                    if(dto.isNegative()){
                        loopRow.setRowStyle(cs3);
                    }
                    c = loopRow.createCell(0);
                    c.setCellValue(dto.getTransportDate());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(1);
                    c.setCellValue(dto.getDriverName());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(2);
                    c.setCellValue(dto.getFrom());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(3);
                    c.setCellValue(dto.getFromTime());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(4);
                    c.setCellValue(dto.getTo());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(5);
                    c.setCellValue(dto.getToTime());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(6);
                    c.setCellValue(dto.getActualTripDuration());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(7);
                    c.setCellValue(dto.getAcftRegin());

                    c = loopRow.createCell(8);
                    c.setCellValue(dto.getMdcTimeIn());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(9);
                    c.setCellValue(dto.getMdcTimeOut());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(10);
                    c.setCellValue(dto.getDelayTime());
                    c.setCellStyle(cs2);

                    c = loopRow.createCell(11);
                    c.setCellValue(dto.getTransportType() == 1 ? "PickUp" : "Drop");
                    c.setCellStyle(cs2);

                }
            }

            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            folder = new File(folder, "/TransGuard/Reports");
            if (!folder.exists())
                folder.mkdirs();
            MediaScannerConnection.scanFile(context, new String[] {folder.toString()}, null, null);
            /* File folder = new File(Environment.getExternalStorageDirectory() + "/TransGuard/Reports");
            if (!folder.exists())
                folder.mkdirs();*/

            File file = new File(folder, fileName);

            // Create a path where we will place our List of objects on external storage
            //File file = new File(context.getExternalFilesDir(null), fileName);
            FileOutputStream os = null;

            try {
                os = new FileOutputStream(file);
                wb.write(os);

                Log.w("FileUtils", "Writing file" + file);
                success = true;
            } catch (IOException e) {
                Log.w("FileUtils", "Error writing " + file, e);
            } catch (Exception e) {
                Log.w("FileUtils", "Failed to save file", e);
            } finally {
                try {
                    if (null != os)
                        os.close();
                } catch (Exception ex) {
                }
            }

        }

        return success;
    }

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

}
