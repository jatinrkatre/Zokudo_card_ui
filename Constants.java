package com.ui.product.zokudo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Constants {

    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat dateFormat1 = new SimpleDateFormat("ddMMyyyy");
    public static final DateFormat dateFormat_YYMM = new SimpleDateFormat("yyMM");
    public static final DateFormat dateFormat_MMYY = new SimpleDateFormat("MMYY");


    public static final String urlEscapeConstant ="\\{\\}";

    public static final String debit = "D";
    public static final String credit = "C";

    public static final boolean debit_true = true;
    public static final boolean debit_false = false;


    public static String txnType(boolean isDebit){
        return isDebit ? debit : credit;
    }
    
    public static void main(String[] args) {
		
		System.out.println("Start Time : " + getStartOfDay(new Date()));
		System.out.println("End Time : " + getEndOfDay(new Date()));
		
	}
    
    private static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    private static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day, 23, 59, 59);
        return calendar.getTime();
    }

}
