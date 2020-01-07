package com.sensesnet.util;

import com.sensesnet.constant.ConstantProvider;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sensesnet <br />
 * Copyright 2019 Eshted LLC. All rights reserved.
 * <p>
 * DateUtils: All operations with Date
 */
public class DateUtils
{
//    SimpleDateFormat format = new SimpleDateFormat();
//format.applyPattern("dd.mm.yyyy");
//    Date docDate= format.parse(docDate);
    public static String getCurrentDate(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantProvider.dateFormat().YYYY_MM_DD);
        return dateFormat.format(timestamp);
    }

    public static String getMinutesSecondsCurrentTime (String dateTime){
        String [] currentMinutess= dateTime.split(" ");
        return currentMinutess[1];
    }

    public static String getTomorrowDate (String dateFormat) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        return sdf.format(c.getTime());
    }

    public static String getYesterdayDate (String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return sdf.format(c.getTime());
    }

    public static String getCustomDate (String dateFormat, int customDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE,customDate);
        return sdf.format(c.getTime());
    }

    public static String convertDateToAnotherFormat(String existedDateTime, String actualDateTimeFormat, String expectedDateTimeFormat){
        String newDateFormat = null;
        SimpleDateFormat format1 = new SimpleDateFormat(actualDateTimeFormat);
        SimpleDateFormat format2 = new SimpleDateFormat(expectedDateTimeFormat);
        try {
            Date date = format1.parse(existedDateTime);
            newDateFormat = format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newDateFormat;
    }

    public static long convertDateToMilliSeconds  (String dateString, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        try
        {
            date = sdf.parse(dateString);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static java.sql.Date getSQLDate (String dateFormat, String dateTime){
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);  // United States style of format.
        Date sqlDate = null;  // Notice the ".util." of package name.
        try
        {
            sqlDate = format.parse(dateTime);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        java.sql.Date sqlDateTime = new java.sql.Date( sqlDate.getTime() );
        return sqlDateTime;
    }
}
