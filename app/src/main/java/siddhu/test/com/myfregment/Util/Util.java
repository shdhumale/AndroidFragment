package siddhu.test.com.myfregment.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 8/6/2016.
 */

public class Util {

    public static String  getDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date objDate = format.parse(date);
        String formattedDate = format.format(objDate);
        System.out.println(formattedDate);
        return formattedDate;
    }
}
