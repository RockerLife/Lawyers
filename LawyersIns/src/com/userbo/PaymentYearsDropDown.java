package com.userbo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.manage.managemetadata.metadata.IDropDown;
import com.util.IContext;

public class PaymentYearsDropDown implements IDropDown {

	public List<HashMap<String, String>> getDropDownData(IContext ctx,String field)throws Exception {
		ArrayList<HashMap<String, String>> paymentYearsList = new ArrayList<HashMap<String, String>>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for(int yearsCount = 0; yearsCount < 10; yearsCount++){
			HashMap<String, String> paymentYearsMap = new HashMap<String, String>();
			paymentYearsMap.put("CreditCardExYearValue",String.valueOf((year)).substring(2, 4));
			paymentYearsMap.put("CreditCardExYearDisplayValue",year++ + "");
			paymentYearsList.add(paymentYearsMap);
		}
		
		return paymentYearsList;
	}
}
