package com.excel.downloadexcel;

import org.apache.poi.ss.usermodel.Cell;
public class RowColumnBean {
	private int rownum ;
	private int cellnum;
	private Cell cell;
	public RowColumnBean(int rownum, int cellnum, Cell cell){
		this.rownum = rownum;
		this.cellnum = cellnum;
		this.cell = cell;
	}
	public void setRownum(int rownum){
		this.rownum = rownum;
	}
    public int getRownum(){
    	return rownum;
    }
    public void setCellnum(int cellnum){
		this.cellnum = cellnum;
	}
    public int getCellnum(){
    	return cellnum;
    }
    
    public void setCell(Cell cell){
		this.cell = cell;
	}
    public Cell getCell(){
    	return cell;
    }
}
