package com.sttx.bookmanager.po;

import java.io.Serializable;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;

public class GetExcelbean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int topleft_row; // 起始行
    private int topleft_column; // 起始列
    private int bottomright_row = 0; // 终止行
    private int bottomright_column = 0; // 终止列
    private int rowspan = 1; // 行
    private int colspan = 1; // 列
    private Range[] ranges = null; // 合并单元格集合
    private int min_row; // 最小行
    private int max_row; // 最大行
    private int min_column; // 最小列
    private int max_column; // 最大列

    /**
     * 获取本sheet里合并单元格的集合
     * 
     * @param sheet
     */
    public void readRange(Sheet sheet, int row, int column) {
        ranges = sheet.getMergedCells();
        for (Range space : ranges) {
            this.topleft_row = space.getTopLeft().getRow();
            this.topleft_column = space.getTopLeft().getColumn();
            if (row != topleft_row || column != topleft_column) {
                this.setRowspan(1);
                this.setColspan(1);
            }
            if (row == topleft_row && column == topleft_column) {
                this.setTopleft_row(this.topleft_row);
                this.setTopleft_column(this.topleft_column);
                this.setBottomright_row(space.getBottomRight().getRow());
                this.setBottomright_column(space.getBottomRight().getColumn());
                this.setRowspan(2);
                this.setColspan(2);
                break;
            }

        }
    }

    /**
     * 
     * @return
     */
    public void setBetweenRowColumn(int row, int column) {
        for (Range space : ranges) {
            // 获取行的范围
            int min_row = space.getTopLeft().getRow(); // min row
            int max_row = space.getBottomRight().getRow(); // max row
            int min_column = space.getTopLeft().getColumn(); // min column
            int max_column = space.getBottomRight().getColumn(); // max column
            if (min_row <= row && row <= max_row) {
                // 获取列的范围
                if (min_column <= column && column <= max_column) {
                    this.setMin_row(min_row); // min row
                    this.setMax_row(max_row); // max row
                    this.setMin_column(min_column); // min column
                    this.setMax_column(max_column); // max column
                    break;
                }
            }
        }
    }

    /**
     * 获取背景颜色的值
     * 
     * @param cell:
     *            当前单元格
     * @return
     */
    public String getBgcolor(Cell cell) {
        int r = cell.getCellFormat().getBackgroundColour().getDefaultRed();
        int g = cell.getCellFormat().getBackgroundColour().getDefaultGreen();
        int b = cell.getCellFormat().getBackgroundColour().getDefaultBlue();
        return "#" + Integer.toHexString(r << 16 | g << 8 | b);
    }

    /**
     * 获取字体的颜色
     * 
     * @param cell:
     *            当前单元格
     * @return
     */
    public String getFontColor(Cell cell) {
        int r = cell.getCellFormat().getFont().getColour().getDefaultBlue();
        int g = cell.getCellFormat().getFont().getColour().getDefaultGreen();
        int b = cell.getCellFormat().getFont().getColour().getDefaultBlue();
        return "#" + Integer.toHexString(r << 16 | g << 8 | b);
    }

    public int getTopleft_row() {
        return topleft_row;
    }

    public void setTopleft_row(int topleft_row) {
        this.topleft_row = topleft_row;
    }

    public int getTopleft_column() {
        return topleft_column;
    }

    public void setTopleft_column(int topleft_column) {
        this.topleft_column = topleft_column;
    }

    public int getBottomright_row() {
        return bottomright_row;
    }

    public void setBottomright_row(int bottomright_row) {
        this.bottomright_row = bottomright_row;
    }

    public int getBottomright_column() {
        return bottomright_column;
    }

    public void setBottomright_column(int bottomright_column) {
        this.bottomright_column = bottomright_column;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        // rowspan :1 表示无; 2 表示有;
        if (rowspan == 1) {
            this.rowspan = 1;
        }
        if (rowspan == 2) {
            this.rowspan = this.getBottomright_row() - this.getTopleft_row() + 1;
        }
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        // colspan :1 表示无; 2 表示有;
        if (colspan == 1) {
            this.colspan = 1;
        }
        if (colspan == 2) {
            this.colspan = this.getBottomright_column() - this.getTopleft_column() + 1;
        }
    }

    // ------------------------获取所合并的单元格的的所表示范围的行和列-----------
    public Range[] getRanges() {
        if (ranges == null) {
            return null;
        }
        return ranges.clone();
    }

    public void setRanges(Range[] ranges) {
        if (ranges != null) {
            this.ranges = ranges.clone();
        }

    }

    public int getMin_row() {
        return min_row;
    }

    public void setMin_row(int min_row) {
        this.min_row = min_row;
    }

    public int getMax_row() {
        return max_row;
    }

    public void setMax_row(int max_row) {
        this.max_row = max_row;
    }

    public int getMin_column() {
        return min_column;
    }

    public void setMin_column(int min_column) {
        this.min_column = min_column;
    }

    public int getMax_column() {
        return max_column;
    }

    public void setMax_column(int max_column) {
        this.max_column = max_column;
    }

}
