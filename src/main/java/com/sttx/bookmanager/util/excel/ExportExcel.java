package com.sttx.bookmanager.util.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

/**
 * 
 * @类名: ExportExcel
 * @描述: excel工具类
 * @作者: chenchaoyun0
 * @日期: 2016-1-28 下午10:28:18
 * 
 */
public class ExportExcel {

    private HSSFWorkbook wb = null;
    private HSSFSheet sheet = null;

    /**
     * 
     * <p>
     * 标题:
     * </p>
     * <p>
     * 描述:
     * </p>
     * 
     * @param wb
     * @param sheet
     */
    public ExportExcel(HSSFWorkbook wb, HSSFSheet sheet) {
        // super();
        this.wb = wb;
        this.sheet = sheet;
    }

    /**
     * 
     * @标题: createNormalHead
     * @作者: chenchaoyun0
     * @描述: TODO(这里用一句话描述这个方法的作用)
     * @参数: @param headString 头部的字符
     * @参数: @param colSum 报表的列数 @返回： void 返回类型
     * @抛出:
     */
    @SuppressWarnings("deprecation")
    public void createNormalHead(String headString, int colSum) {
        HSSFRow row = sheet.createRow(0);
        // 设置第一行
        HSSFCell cell = row.createCell(0);
        // row.setHeight((short) 1000);

        // 定义单元格为字符串类型
        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
        cell.setCellValue(new HSSFRichTextString(headString));

        // 指定合并区域
        /**
         * public Region(int rowFrom, short colFrom, int rowTo, short colTo)
         */
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));

        // 定义单元格格式，添加单元格表样式，并添加到工作簿
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 设置单元格水平对齐类型
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 300);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 
     * @标题: createNormalTwoRow
     * @作者: chenchaoyun0
     * @描述: 创建通用报表第二行
     * @参数: @param params 二行统计条件数组
     * @参数: @param colSum 需要合并到的列索引 @返回： void 返回类型
     * @抛出:
     */
    @SuppressWarnings("deprecation")
    public void createNormalTwoRow(String[] params, int colSum) {
        // 创建第二行
        HSSFRow row1 = sheet.createRow(1);

        row1.setHeight((short) 400);

        HSSFCell cell2 = row1.createCell(0);

        cell2.setCellType(HSSFCell.ENCODING_UTF_16);
        cell2.setCellValue(new HSSFRichTextString("时间：" + params[0] + "至" + params[1]));

        // 指定合并区域
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);

        cell2.setCellStyle(cellStyle);
    }

    /**
     * 
     * @标题: createColumHeader
     * @作者: chenchaoyun0
     * @描述: 设置报表标题
     * @参数: @param columHeader 标题字符串数组 @返回： void 返回类型
     * @抛出:
     */
    public void createColumHeader(String[] columHeader) {

        // 设置列头 在第三行
        HSSFRow row2 = sheet.createRow(2);

        // 指定行高
        row2.setHeight((short) 600);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);

        // 设置单元格背景色
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFCell cell3 = null;

        for (int i = 0; i < columHeader.length; i++) {
            cell3 = row2.createCell(i);
            cell3.setCellType(HSSFCell.ENCODING_UTF_16);
            cell3.setCellStyle(cellStyle);
            cell3.setCellValue(new HSSFRichTextString(columHeader[i]));
        }
    }

    /**
     * 
     * @标题: cteateCell
     * @作者: chenchaoyun0
     * @描述: 创建内容单元格
     * @参数: @param wb HSSFWorkbook
     * @参数: @param row HSSFRow
     * @参数: @param col short型的列索引
     * @参数: @param align 对齐方式
     * @参数: @param val 列 @返回： void 返回类型
     * @抛出:
     */
    public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, short align, String val) {
        HSSFCell cell = row.createCell(col);
        cell.setCellType(HSSFCell.ENCODING_UTF_16);
        cell.setCellValue(new HSSFRichTextString(val));
        HSSFCellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(align);
        cell.setCellStyle(cellstyle);
    }

    /**
     * 
     * @标题: createLastSumRow
     * @作者: chenchaoyun0
     * @描述: 创建合计行
     * @参数: @param colSum 需要合并到的列索引
     * @参数: @param cellValue 设定文件 @返回： void 返回类型
     * @抛出:
     */
    @SuppressWarnings("deprecation")
    public void createLastSumRow(int colSum, String[] cellValue) {

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        cellStyle.setWrapText(true);// 指定单元格自动换行

        // 单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 250);
        cellStyle.setFont(font);
        // 获取工作表最后一行
        HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));
        HSSFCell sumCell = lastRow.createCell(0);

        sumCell.setCellValue(new HSSFRichTextString("合计"));
        sumCell.setCellStyle(cellStyle);
        // 合并 最后一行的第零列-最后一行的第一列
        sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0, sheet.getLastRowNum(), (short) colSum));// 指定合并区域

        for (int i = 2; i < (cellValue.length + 2); i++) {
            // 定义最后一行的第三列
            sumCell = lastRow.createCell(i);
            sumCell.setCellStyle(cellStyle);
            // 定义数组 从0开始。
            sumCell.setCellValue(new HSSFRichTextString(cellValue[i - 2]));
        }
    }

    /**
     * 
     * @标题: outputExcel
     * @作者: chenchaoyun0
     * @描述: 输出excel下载
     * @参数: @param fileName 文件名 @返回： void 返回类型
     * @抛出:
     */
    public void outputExcel(String fileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(fileName));
            wb.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @返回： HSSFSheet 返回类型
     */
    public HSSFSheet getSheet() {
        return sheet;
    }

    public void setSheet(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * 
     * @返回： HSSFWorkbook 返回类型
     */
    public HSSFWorkbook getWb() {
        return wb;
    }

    /**
     * 
     */
    public void setWb(HSSFWorkbook wb) {
        this.wb = wb;
    }
}