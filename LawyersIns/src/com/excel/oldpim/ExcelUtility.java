package com.excel.oldpim;

import com.util.InetLogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.excel.downloadexcel.RowColumnBean;
import com.manage.util.ExcelUtil;
import com.util.Context;
import com.util.DateUtils;



public class ExcelUtility {
	private static final InetLogger logger = InetLogger.getInetLogger(ExcelUtility.class);

	public static Object removeAmountFormat(Object arg1){
	      if(arg1 == null)
	          return 0;
	      
	      String amount = arg1.toString();      
	      return amount.replace("$", "").replace(",", "");
	}
	
	public static String getValueFromMap(Map map, String name, boolean isBoolean) throws Exception {
		String result = "";
		if(map == null)
			return result;
		
		result = map.get(name)!=null?map.get(name).toString():"";
		if(isBoolean)
		{
			 if("Y".equals(result))
				 result = "Yes";
			 else if("N".equals(result))
				 result = "No";
		}
		
		return result;	 
	}
	
	/*
	 * This method is going to return the particular Cell
	 */

	public static Cell getCell(Sheet sheet, int rowNum, int columnNum) {
		Row row = sheet.getRow(rowNum);
		return row.getCell(columnNum);
	}

	/*
	 * This method is going to set the value in a particular Cell
	 */
	public static void setCellValue(Sheet sheet, int rowNum, int columnNum,
			Object value) {
		Row row = sheet.getRow(rowNum);
		row = row == null ? sheet.createRow(rowNum) : row;
		Cell cell = row.getCell(columnNum);
		cell = cell == null ? row.createCell(columnNum) : cell;
		if (value == null)
			value = "";
		switch (cell.getCellStyle().getDataFormat()) {
		case 38: {

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue(0);

			break;
		}
		case 207: {

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue("");

			break;
		}
		case 164: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 166: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 167: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 208: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue(0);

			break;
		}
		case 210: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue(0);

			break;
		}
			// case 0: {
			//
			// if(!"".equals(value))
			// cell.setCellValue(Double.parseDouble(value.toString()));
			// else
			// cell.setCellValue(0);
			//			
			//			
			// break;
			// }
		case 205: {

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue(0);

			break;
		}
		case 9: {

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);

			else
				cell.setCellValue(0);

			break;
		}
		case 10: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		
		case 14: {
			if (value == null)
				value = "";
			if (!"".equals(value)) {
				try {
					DateFormat formatter;
					java.util.Date date;
					formatter = new SimpleDateFormat("MM/dd/yyyy");
					date = (java.util.Date) formatter
							.parse(getFormattedDateFromObject(value));
					cell.setCellValue(date);
				} catch (Exception e) {
					logger.debug("Exception :" + e);
				}
				// DateUtil.isValidExcelDate(Double.parseDouble(getFormattedDateFromObject(value)));
			} else
				cell.setCellValue(0);
			break;
		}
		case 203: {
			if (value == null)
				value = "";
			if (!"".equals(value)) {
				try {
					DateFormat formatter;
					java.util.Date date;
					formatter = new SimpleDateFormat("MM/dd/yyyy");
					date = (java.util.Date) formatter
							.parse(getFormattedDateFromObject(value));
					cell.setCellValue(date);
				} catch (Exception e) {
					logger.debug("Exception :" + e);
				}
				// DateUtil.isValidExcelDate(Double.parseDouble(value.toString()));
			} else
				cell.setCellValue(0);

			break;
		}
		case 49:
		{
			if (value == null)
				value = "";
			cell.setCellValue(value.toString());

			break;
		}
		default:

		{

			cell.setCellValue(value.toString());
			break;
		}
			//		
			//
		}
	}

	public static void setCellValue(Sheet sheet, Object value, Object cellInfo,
			int addtoRowNum) throws Exception {
		RowColumnBean cellIndex = null;
		if (cellInfo != null)
			cellIndex = (RowColumnBean) cellInfo;
		else
			return;

		Row row = sheet.getRow(cellIndex.getRownum() + addtoRowNum);
		// row=row==null?sheet.createRow(cellIndex.getRownum()):row;
		Cell cell = row.getCell(cellIndex.getCellnum());
		cell = cell == null ? row.createCell(cellIndex.getCellnum()) : cell;

		switch (cell.getCellStyle().getDataFormat()) {
		
		case 5: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue(Double.parseDouble("0"));

			break;
		}
		case 9: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 10: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 38: {
			if (value == null)
				value = "";
			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue(0);

			break;
		}
		case 210: {
			if (value == null)
				value = "";
			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString()));
			else
				cell.setCellValue(0);

			break;
		}
		case 164: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 166: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 167: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Double.parseDouble(value.toString())/100);
			else
				cell.setCellValue(0);

			break;
		}
		case 170: {
			if (value == null)
				value = "";

			if (!"".equals(value))
				cell.setCellValue(Integer.parseInt(value.toString()));
			else
				cell.setCellValue("");

			break;
		}
		case 14: {
			if (value == null)
				value = "";
			if (!"".equals(value)) {
				try {
					DateFormat formatter;
					java.util.Date date;
					formatter = new SimpleDateFormat("MM/dd/yyyy");
					date = (java.util.Date) formatter
							.parse(getFormattedDateFromObject(value));
					cell.setCellValue(date);
				} catch (Exception e) {
					logger.debug("Exception :" + e);
				}
				// DateUtil.isValidExcelDate(Double.parseDouble(getFormattedDateFromObject(value)));
			} else
				cell.setCellValue(0);
			break;
		}
		case 203: {
			if (value == null)
				value = "";
			if (!"".equals(value)) {
				try {
					DateFormat formatter;
					java.util.Date date;
					formatter = new SimpleDateFormat("MM/dd/yyyy");
					date = (java.util.Date) formatter
							.parse(getFormattedDateFromObject(value));
					cell.setCellValue(date);
				} catch (Exception e) {
					logger.debug("Exception :" + e);
				}
				// DateUtil.isValidExcelDate(Double.parseDouble(value.toString()));
			} else
				cell.setCellValue(0);

			break;
		}
		case 49:
		{
			if (value == null)
				value = "";
			try{
				cell.setCellValue(Integer.parseInt(value.toString()));
			}
			catch(NumberFormatException nfe){
				cell.setCellValue(value.toString());
			}		

			break;
		}
		default:
		{
			if (value == null)
				value = "";
			try{
				cell.setCellValue(Integer.parseInt(value.toString()));
			}
			catch(NumberFormatException nfe){
				cell.setCellValue(value.toString());
			}	

			break;
		}
			//		
			//
		}
		// cell.setCellValue(value.toString());
	}

	/*
	 * This method is going to return the value of particular cell
	 */

	public static String getCellValue(Sheet mainSheet, int rowNum, int columnNum) {
		Cell cell = ExcelUtil.getCell(mainSheet, rowNum, columnNum);
		return cell.getStringCellValue();
	}

	public static void setCellValueInContext(Context ctx, String setKey,
			Sheet mainSheet, int rowNum, int columnNum) {

		String setValue = getCellValue(mainSheet, rowNum, columnNum);

		if (null != setValue && setValue.endsWith(":"))
			setValue = setValue.substring(0, setValue.lastIndexOf(":"));

		ctx.put(setKey, setValue);

	}

	public static String retifyString(String st) {
		if (null != st && st.endsWith(":"))
			return st.substring(0, st.lastIndexOf(":"));

		return st;
	}

//	@SuppressWarnings("unchecked")
//	public static Map getMapFromList(List list, String value) {
//
//		if (list == null || list.isEmpty())
//			return new HashMap();
//
//		for (int i = 0; i <= list.size(); i++) {
//			Map map = (Map) list.get(i);
//			String categorytype = (String) map
//					.get(Constants.RISK_CHARACTERISTIC_DESC);
//			String category = retifyString(categorytype);
//			if (categorytype.toString().equals(category)) {
//				return map;
//			}
//		}
//
//		return new HashMap();
//	}

	public static Object convertTypetoString(Cell cell1) {
		switch (cell1.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: {
			
			switch(cell1.getCellStyle().getDataFormat())
			{
				case 9: {
					return "" + cell1.getNumericCellValue()*100;
				}
				case 10: {
					return "" + cell1.getNumericCellValue()*100;
				}
				case 164: {
					return "" + cell1.getNumericCellValue()*100;
				}
				case 166: {
					return "" + cell1.getNumericCellValue()*100;
				}
				case 167: {
					return "" + cell1.getNumericCellValue()*100;
				}
				case 210: {
					return "" + cell1.getNumericCellValue()*100;
				}
			}
			
			return "" + cell1.getNumericCellValue();
		}
		case Cell.CELL_TYPE_STRING: {
			return cell1.getStringCellValue();
		}
		case Cell.CELL_TYPE_BLANK: {
			return "";
		}
		case Cell.CELL_TYPE_FORMULA: {
			return "" + cell1.getNumericCellValue();
		}
		}
		return "";
	}

	public static String retifyNumericValue(String st) {
		if (null != st && st.contains("."))
			return st.substring(0, st.lastIndexOf("."));

		return st;
	}

	public static String getFormattedDateFromObject(Object object) {
		String value = "";

		if (object == null)
			return value;

		if (object instanceof java.sql.Date)
			value = DateUtils.convertDateToString((java.sql.Date) object,
					"MM/dd/yyyy");
		else if (object instanceof java.sql.Timestamp)
			value = DateUtils.convertDateToString((java.sql.Timestamp) object,
					"MM/dd/yyyy");
		else
			value = object.toString();

		return value;

	}

	public static void copyCellFormula(Sheet templateSheet, int startrownum,
			int lastrownum, int startcolumnnum, int lastcolumnnum) {

		// HSSFRow rowOld = (HSSFRow) templateSheet.getRow(startrownum);
		for (int r = startrownum; r < lastrownum; r++) {
			HSSFRow rowOld = (HSSFRow) templateSheet.getRow(r);

			HSSFRow rowNew = (HSSFRow) templateSheet.createRow(r);
			rowNew.setHeight(rowOld.getHeight());

			for (int c = startcolumnnum; c < lastcolumnnum; c++) {
				HSSFCell cellOld = rowOld.getCell(c);

				if (cellOld != null) {
					HSSFCell cellNew = rowNew.createCell(c);

					copyCell(cellOld, cellNew);

					cellNew.setCellType(cellOld.getCellType());

					String content = checkType(cellOld);

					cellNew.setCellValue(content);
				}
			}
		}
	}

	public static void copyCell(HSSFCell cellOld, HSSFCell cellNew) {

		switch (cellOld.getCellType()) {
		// case HSSFCell.CELL_TYPE_STRING:
		//
		// cellNew.setCellValue(cellOld.getStringCellValue());
		// break;
		// case HSSFCell.CELL_TYPE_NUMERIC:
		//
		// cellNew.setCellValue(cellOld.getNumericCellValue());
		// break;
		// case HSSFCell.CELL_TYPE_BLANK:
		//
		// cellNew.setCellValue(HSSFCell.CELL_TYPE_BLANK);
		// break;
		// case HSSFCell.CELL_TYPE_BOOLEAN:
		//
		// cellNew.setCellValue(cellOld.getBooleanCellValue());
		// break;
		case HSSFCell.CELL_TYPE_FORMULA:

			cellNew.setCellFormula(cellOld.getCellFormula());
			break;
		}
	}

	public static String checkType(HSSFCell cell) {
		String value = null;
		switch (cell.getCellType()) {
		// case HSSFCell.CELL_TYPE_STRING:
		// value = cell.getStringCellValue();
		// break;
		// case HSSFCell.CELL_TYPE_NUMERIC:
		// value = Double.toString(cell.getNumericCellValue());
		// break;
		// case HSSFCell.CELL_TYPE_BLANK:
		// value = "";
		// break;
		case HSSFCell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula();
			break;
		}
		return value;
	}
}
