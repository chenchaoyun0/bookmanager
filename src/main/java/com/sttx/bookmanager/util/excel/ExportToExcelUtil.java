package com.sttx.bookmanager.util.excel;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sttx.bookmanager.po.Book;

/**
 * 
 * @类名: ExportToExcelUtil
 * @描述: 设置excel'的信息
 * @作者: chenchaoyun0
 * @日期: 2016-1-28 下午11:19:58
 *
 */
public class ExportToExcelUtil {
    public static void out(HttpServletResponse response, List<Book> list) throws Exception {
        /**
         * 指定下载名
         */
        String fileName = "数通天下-书籍列表.xls";
        /**
         * 编码
         */
        fileName = new String(fileName.getBytes("GBK"), "iso8859-1");
        /**
         * 去除空白行
         */
        response.reset();
        /**
         * 指定下载的文件名与设置相应头
         */
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        /**
         * 下载文件类型
         */
        response.setContentType("application/vnd.ms-excel");
        /**
         * 别给我缓存
         */
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        /**
         * 得到输出流,放到缓冲区
         */
        OutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);

        /**
         * 下面是导出的excel格式的设置
         */
        /******************* 我是分割线 **************************************/

        // 定义单元格报头
        String worksheetTitle = "数通天下-图书列表";

        HSSFWorkbook wb = new HSSFWorkbook();

        // 创建单元格样式
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 指定单元格垂直居中对齐
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 指定当单元格内容显示不下时自动换行
        cellStyleTitle.setWrapText(true);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置单元格字体
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyleTitle.setFont(font);

        /**************** wo是分割线 *****************************************/

        /**
         * 工作表列名
         */
        String bookNo = "编号";
        String bookImage = "图片";
        String bookName = "书名";
        String bookAuthor = "作者";
        String bookDesc = "描述";
        String bookCountry = "国家";
        String bookHouse = "出版社";
        String bookType = "书籍所属";
        String bookUser = "书籍主人";
        String bookPrice = "购入价格";
        String bookCount = "数量";
        String bookByTime = "购入日期";
        String bookRemain = "剩余数量";
        String bookUploadTime = "上传时间";
        String bookStatus = "状态";

        HSSFSheet sheet = wb.createSheet();
        ExportExcel exportExcel = new ExportExcel(wb, sheet);
        /**
         * 创建报表头部
         */
        exportExcel.createNormalHead(worksheetTitle, 14);
        // 定义第一行
        HSSFRow row1 = sheet.createRow(1);
        HSSFCell cell1 = row1.createCell(0);

        /**
         * 第一行第一列
         */

        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookNo));
        /**
         * 第一行第二列
         */
        cell1 = row1.createCell(1);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookImage));

        /**
         * 第一行第三列
         */
        cell1 = row1.createCell(2);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookName));
        /**
         * 
         */
        cell1 = row1.createCell(3);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookAuthor));
        /**
         * 
         */
        cell1 = row1.createCell(4);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookDesc));
        /**
         * 
         */
        cell1 = row1.createCell(5);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookCountry));
        /**
         * 
         */
        cell1 = row1.createCell(6);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookHouse));
        /**
         * 
         */
        cell1 = row1.createCell(7);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookType));
        /**
         * 
         */
        cell1 = row1.createCell(8);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookUser));
        /**
         * 
         */
        cell1 = row1.createCell(9);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookPrice));
        /**
         * 
         */
        cell1 = row1.createCell(10);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookCount));
        /**
         * 
         */
        cell1 = row1.createCell(11);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookByTime));
        /**
         * 
         */
        cell1 = row1.createCell(12);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookRemain));
        /**
         * 
         */
        cell1 = row1.createCell(13);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookUploadTime));
        /**
         * 
         */
        cell1 = row1.createCell(14);
        cell1.setCellStyle(cellStyleTitle);
        cell1.setCellValue(new HSSFRichTextString(bookStatus));

        /******************** 我是分割线 *********************************/

        /**
         * 定义第二行,就是我们的数据
         */
        HSSFRow row = sheet.createRow(2);
        HSSFCell cell = row.createCell(1);
        /**
         * 便利我们传进来的user-list
         */
        Book book = null;
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        for (int i = 0; i < list.size(); i++) {
            book = list.get(i);
            /**
             * 从i+2行开始，因为我们之前的表的标题和列的标题已经占用了两行
             */
            row = sheet.createRow(i + 2);

            // no
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookNo() + ""));

            // ...user_headImg
            short h = 2;// 定义图片所在行
            short l = 1;// 定义图片所在列
            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            // 得到user的图片
            BufferedImage image = book.getBookImage();
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOut);
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, l, i + h, (short) (l + 1), (i + 1) + h);
            anchor.setAnchorType(0);
            // 插入图片
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));

            // book name
            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookName()));
            // --
            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookAuthor()));
            // --
            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookDesc()));
            // --
            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookCountry() == 0 ? "国外" : "国内"));
            // --
            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookHouse()));
            // --
            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookType() == 1 ? "公司书籍" : "个人书籍"));
            // --
            cell = row.createCell(8);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getUser().getRealName()));
            // --
            cell = row.createCell(9);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(book.getBookPrice())));
            // --
            cell = row.createCell(10);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(book.getBookCount())));
            // --
            cell = row.createCell(11);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(book.getBookByTime())));
            // --
            cell = row.createCell(12);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(book.getBookRemain())));
            // --
            cell = row.createCell(13);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(book.getBookUploadTime())));
            // --
            cell = row.createCell(14);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(book.getBookStatus() == 1 ? "在架" : "下架"));

        }
        try {
            /**
             * 输出到浏览器
             */
            bufferedOutPut.flush();
            wb.write(bufferedOutPut);
            bufferedOutPut.close();// 关流
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            list.clear();
        }
    }
}
