package hzqing.com.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final SimpleDateFormat yearAandMonth = new SimpleDateFormat("yyyyMM");

    /**
     *
     * @return yyyyMM
     */
    public  static  String getYearAndMonth(){
        return yearAandMonth.format(new Date());
    }

    /**
     *
     * @param date
     * @return yyyyMM
     */
    public  static  String getYearAndMonth(Date date){
        return yearAandMonth.format(date);
    }




}
