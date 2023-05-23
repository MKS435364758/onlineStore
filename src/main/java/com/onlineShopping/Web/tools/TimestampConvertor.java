package com.onlineShopping.Web.tools;

import com.onlineShopping.Web.exception.DataNotFound;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConvertor {
    public static Timestamp convertStringToTimestamp(String strDate) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            // you can change format of date
            Date date = formatter.parse(strDate);

            return new Timestamp(date.getTime());
        } catch (DataNotFound | ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }
}
