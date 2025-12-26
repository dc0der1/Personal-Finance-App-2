package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtility {

    public static Date localdateToDate(LocalDate localdate) {
        return Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static java.sql.Date dateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static String sqlDateToString(java.util.Date date) {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);

        return df.format(date);
    }

}
