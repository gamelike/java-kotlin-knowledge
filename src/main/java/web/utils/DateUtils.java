package web.utils;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * data utils.
 **/
@Slf4j
public class DateUtils {

  public static final int DEFAULT = -180;

  public static String DateToString(Date date) {
    return DateToString(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static Date StringToDate(String date) {
    return StringToDate(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static String DateToString(Date date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    return dateFormat.format(date);
  }

  public static Date StringToDate(String date, String format) {
    SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    Date curDate = null;
    try {
      curDate = dateFormat.parse(date);
    } catch (ParseException e) {
      log.error("转换日期类型错误");
    }
    return curDate;
  }

  public static Date addDay(Date curDate, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(curDate);
    calendar.add(Calendar.DAY_OF_MONTH, day);
    return calendar.getTime();
  }

  public static DateHistogramInterval dataHistogram(String unit, Integer duration) {
    if (unit == null) return DateHistogramInterval.MONTH;
    switch (unit) {
      case "second":
        return DateHistogramInterval.seconds(duration);
      case "minute":
        return DateHistogramInterval.minutes(duration) ;
      case "hour":
        return DateHistogramInterval.hours(duration);
      case "day":
        return DateHistogramInterval.days(duration);
      case "week":
        return DateHistogramInterval.weeks(duration);
      default:
        return DateHistogramInterval.MONTH;
    }
  }

}
