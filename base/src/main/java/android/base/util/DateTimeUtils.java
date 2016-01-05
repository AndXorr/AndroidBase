package android.base.util;

import android.base.R;
import android.content.Context;
import android.text.TextUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by clickapps on 24/7/15.
 * <p/>
 * All the date time util methods are available for the app
 */
public class DateTimeUtils {

    private final static String TAG = DateTimeUtils.class.getSimpleName();
    public final static String FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final static String DATE_TIME_FORMAT = "dd MMM - HH:mm";
    public final static String TIME_FORMAT = "HH:mm:ss";
    public final static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT_DHM_DATE_MO = "E HH:mm  dd MMM";
    public final static String DATE="yyyy-MM-dd";
    private final static long ONE_SECOND = 1000;
    private final static long HALF_SECONDS = ONE_SECOND * 15;

    private final static long ONE_MINUTE = ONE_SECOND * 60;
    private final static long MINUTES = 60;

    private final static long ONE_HOUR = ONE_MINUTE * 60;
    private final static long HOURS = 24;

    private final static long ONE_DAY = ONE_HOUR * 24;

    /**
     * Functionality to get current date and time with requested date time
     * format
     *
     * @param dateFormat date format default will be yyyy-MM-dd hh:mm:ss
     * @return current date
     */
    public static String getCurrentDateTime(String dateFormat) {
        if (TextUtils.isEmpty(dateFormat)) {
            dateFormat = "yyyy-MM-dd hh:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        return sdf.format(new Date());
    }

    /**
     * Functionality to find time in UTC after conversion of device local time
     *
     * @param format required format of date. if null/empty then default format is
     *               yyyy-MM-dd HH:mm:ss
     * @return date time string
     */
    public static String getCurrentUTCDateTime(String format) {
        String convertedDateTime = null;
        try {
            if (format == null || format.equals("")
                    || format.equalsIgnoreCase("null")) {
                format = "MM/dd/yyyy kk:mm:ss";
            }

            Calendar calendar = Calendar.getInstance();
            int ro = calendar.getTimeZone().getRawOffset();
            int dst = calendar.getTimeZone().getDSTSavings();

            boolean isDayLight = TimeZone.getDefault().inDaylightTime(
                    calendar.getTime());

            long gmtMillis = calendar.getTimeInMillis() - (ro);
            if (isDayLight) {
                gmtMillis = calendar.getTimeInMillis() - ro - dst;
            }

            SimpleDateFormat formatter = new SimpleDateFormat(format);
            // formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            convertedDateTime = formatter.format(gmtMillis);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertedDateTime;
    }

    /**
     * Functionality to convert utc time into device's locale time
     *
     * @param utcDateTime utc date-time that need to convert
     * @param format      of required output time if you pass null/empty then default is
     *                    (MM/dd/yyyy hh:mm:ss aa)
     * @return locale date time string
     */
    public static String convertUtcToLocal(String utcDateTime, String format) {
        String convertedDateTime = utcDateTime;
        if (convertedDateTime != null
                && !convertedDateTime.equalsIgnoreCase("")
                && !convertedDateTime.equalsIgnoreCase("null")) {
            try {

                if (format == null || format.equals("")
                        || format.equalsIgnoreCase("null")) {
                    format = "MM/dd/yyyy kk:mm:ss";
                }

                SimpleDateFormat formatter = new SimpleDateFormat(format);

                Calendar calendar = Calendar.getInstance();
                int ro = calendar.getTimeZone().getRawOffset();
                int dst = calendar.getTimeZone().getDSTSavings();

                Date convertedDate = formatter
                        .parse(convertedDateTime);

                boolean isDayLight = TimeZone.getDefault().inDaylightTime(
                        convertedDate);

                if (isDayLight) {
                    convertedDateTime = formatter.format(convertedDate
                            .getTime() + ro + dst);
                } else {
                    convertedDateTime = formatter.format(convertedDate
                            .getTime() + ro /* + dst */);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertedDateTime;
    }

    /**
     * Functionality to convert utc time into device's locale time
     *
     * @param utcDateTime   utc date-time that need to convert
     * @param format        of required output time if you pass null/empty then default is
     *                      (MM/dd/yyyy hh:mm:ss aa)
     * @param currentFormat
     * @return locale date time string
     */
    public static String convertUtcToLocal(String utcDateTime, String format,
                                           String currentFormat) {
        String convertedDateTime = utcDateTime;
        if (convertedDateTime != null
                && !convertedDateTime.equalsIgnoreCase("")
                && !convertedDateTime.equalsIgnoreCase("null")) {
            try {

                if (format == null || format.equals("")
                        || format.equalsIgnoreCase("null")) {
                    format = "MM/dd/yyyy kk:mm:ss";
                }

                SimpleDateFormat formatter = new SimpleDateFormat(format);
                SimpleDateFormat currentFormatter = new SimpleDateFormat(currentFormat);

                Calendar calendar = Calendar.getInstance();
                int ro = calendar.getTimeZone().getRawOffset();
                int dst = calendar.getTimeZone().getDSTSavings();

                Date convertedDate = currentFormatter
                        .parse(convertedDateTime);

                boolean isDayLight = TimeZone.getDefault().inDaylightTime(
                        convertedDate);

                if (isDayLight) {
                    convertedDateTime = formatter.format(convertedDate
                            .getTime() + ro + dst);
                } else {
                    convertedDateTime = formatter.format(convertedDate
                            .getTime() + ro /* + dst */);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return convertedDateTime;
    }

    /**
     * Functionality to calculate time difference
     *
     * @param firstDateTime
     * @param secondDateTime
     * @return time difference in milliseconds
     */
    public long getTimeDifferenceInMilliseconds(Date firstDateTime,
                                                Date secondDateTime) throws ParseException {
        long diffInMs = secondDateTime.getTime() - firstDateTime.getTime();
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        return diffInSec;
    }

    /**
     * Functionality to get addition of two dates {@link <ahref http
     * ://stackoverflow.com/questions/2067657/sum-two-dates-in-java/>}
     *
     * @param firstDateTimeString
     * @param secondDateTimeString this date time string should be appended with either + or - to
     *                             identify addition or subtraction. If not appended with either
     *                             sign then default will be + sign
     * @param format               in which the date-time need to convert. If null/empty then
     *                             default format is MM/dd/yyyy hh:mm:ss aa
     * @return new date-time string
     */
    public static String addSubstractTwoDates(String firstDateTimeString,
                                              String secondDateTimeString, String format) {
        String updatedDateTime = "";
        try {
            if (TextUtils.isEmpty(format) || format.equalsIgnoreCase("null")) {
                format = "MM/dd/yyyy kk:mm:ss";
            }

            if (!TextUtils.isEmpty(firstDateTimeString)
                    && !TextUtils.isEmpty(secondDateTimeString)) {
                // check for + sign in first date time string
                if (firstDateTimeString.startsWith("+")) {
                    firstDateTimeString = firstDateTimeString.replace("+", "");
                }
                // check for - sign in first date time string
                if (firstDateTimeString.startsWith("-")) {
                    firstDateTimeString = firstDateTimeString.replace("-", "");
                }

                // check for +/- sign in second date time string
                boolean isAdding = true;
                if (secondDateTimeString.startsWith("+")) {
                    secondDateTimeString = secondDateTimeString
                            .replace("+", "");
                } else if (secondDateTimeString.startsWith("-")) {
                    secondDateTimeString = secondDateTimeString
                            .replace("-", "");
                    isAdding = false;
                }

                Date newDate = addSubtractTimeWithDate(
                        convertStringToDate(firstDateTimeString, format),
                        secondDateTimeString, isAdding);

                // convert this new date time into requested date time string
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                updatedDateTime = sdf.format(newDate);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedDateTime;

    }

    /**
     * Functionality to convert Date to day/hours/mins/seconds
     */
    public static Calendar getCalendar(long milliSecond) {
        if (milliSecond != 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(milliSecond));
            return calendar;
        }
        return null;
    }


    /**
     * Functionality to convert string to date
     *
     * @param dateTimeString that need to convert into date format
     * @param format         in which the date-time need to convert. If null/empty then
     *                       default format is MM/dd/yyyy kk:mm:ss
     */
    public static Date convertStringToDate(String dateTimeString, String format) {
        try {
            if (dateTimeString == null) {
                return null;
            }

            if (TextUtils.isEmpty(format) || format.equalsIgnoreCase("null")) {
                format = "MM/dd/yyyy kk:mm:ss";
            }
            SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
            Date stringDate = simpledateformat.parse(dateTimeString);
            return stringDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Functionality to convert string to date
     *
     * @param dateTimeString that need to convert into date format
     * @param format         in which the date-time need to convert. If null/empty then
     *                       default format is MM/dd/yyyy kk:mm:ss
     * @param currentFormat  in which the date-time is now.
     */
    public static String convertStringToDate(String dateTimeString, String format, String currentFormat) {
        try {
            if (dateTimeString == null) {
                return null;
            }

            if (TextUtils.isEmpty(format) || format.equalsIgnoreCase("null")) {
                format = "MM/dd/yyyy kk:mm:ss";
            }
            SimpleDateFormat simpledateformat = new SimpleDateFormat(currentFormat);
            SimpleDateFormat currentFormatter = new SimpleDateFormat(format);
            Date stringDate = simpledateformat.parse(dateTimeString);
            String date = currentFormatter.format(stringDate);

            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Functionality to get date time after addition or subtract the time
     * accordingly
     *
     * @param date                     in which the time need to add/subtract
     * @param addingSubtractTimeString date time string that need to add/subtract by default 00:00
     * @param isNeedToAdd              true if + else false
     * @return new date object
     */
    public static Date addSubtractTimeWithDate(Date date,
                                               String addingSubtractTimeString, boolean isNeedToAdd) {
        if (!TextUtils.isEmpty(addingSubtractTimeString) && date != null) {
            String[] hourMinuteArr = addingSubtractTimeString.split(":");

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (isNeedToAdd) {
                calendar.add(Calendar.HOUR,
                        +(Integer.parseInt(hourMinuteArr[0])));
                calendar.add(Calendar.MINUTE,
                        +(Integer.parseInt(hourMinuteArr[1])));
            } else {
                calendar.add(Calendar.HOUR,
                        -(Integer.parseInt(hourMinuteArr[0])));
                calendar.add(Calendar.MINUTE,
                        -(Integer.parseInt(hourMinuteArr[1])));
            }

            return calendar.getTime();
        }
        return null;

    }

    /**
     * Functionality to get time of requested country/zone {@link <ahref http
     * ://stackoverflow
     * .com/questions/9108356/get-time-of-different-time-zones-on
     * -selection-of-time-from-time-picker/>}
     *
     * @param countryCityZoneString name of the country/city/zone
     * @param format                in which the date-time need to convert. If null/empty then
     *                              default format is MM/dd/yyyy kk:mm:ss
     * @return date time of requested country
     */
    public static String getDateTimeByCountryCityOrZone(
            String countryCityZoneString, String format) {
        if (format == null || format.equals("")
                || format.equalsIgnoreCase("null")) {
            format = "MM/dd/yyyy kk:mm:ss";
        }

        SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
        TimeZone utc = TimeZone.getTimeZone(countryCityZoneString);
        Calendar c = Calendar.getInstance(utc);
        simpledateformat.setTimeZone(utc);
        return simpledateformat.format(c.getTime());
    }

    /**
     * Functionality to compare current date with requested date string
     *
     * @param dateString date string in the format of
     * @param format     date time format default will be yyyy-MM-dd hh:mm:ss
     * @return >0 if dateString is old date with compare to today
     */
    public static int compareWithCurrentDateTime(String dateString, String format) {
        int returnValue = 0;
        try {
            if (TextUtils.isEmpty(format)) {
                format = "yyyy-MM-dd hh:mm:ss";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date oldDate = sdf.parse(dateString);
            Date currentDate = sdf.parse(getCurrentDateTime(format));
            returnValue = currentDate.compareTo(oldDate);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return returnValue;
    }

    /**
     * Functionality to find yesterday date in milliseconds
     *
     * @param numberOfDays should be greater than 0 otherwise return current date
     * @return date in milliseconds
     */
    public static long getOldDateFromCurrentDateInMilli(int numberOfDays) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c1.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c1.get(Calendar.MONTH));
        c2.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH));
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);

        if (numberOfDays > 0) {
            c2.add(Calendar.DATE, -numberOfDays);
            return c2.getTimeInMillis();
        }
        return c2.getTimeInMillis();
    }


    /**
     * Functionality to find yesterday date in milliseconds
     *
     * @param numberOfDays should be greater than 0 otherwise return current date
     * @return date in milliseconds
     */
    public static long getFutureDateFromCurrentDateInMilli(int numberOfDays) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, c1.get(Calendar.YEAR));
        c2.set(Calendar.MONTH, c1.get(Calendar.MONTH));
        c2.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH));
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);

        if (numberOfDays > 0) {
            c2.add(Calendar.DATE, numberOfDays);
            return c2.getTimeInMillis();
        }
        return c2.getTimeInMillis();
    }

    /**
     * Functionality to convert date in milli seconds
     *
     * @param year
     * @param month
     * @param day
     * @return converted date in to milliseconds
     */
    public static long convertDateToMilli(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(year, month, day);

        return calendar.getTimeInMillis();
    }

    /**
     * http://www.rgagnon.com/javadetails/java-0585.html
     * converts time (in milliseconds) to human-readable format
     * "<w> days, <x> hours, <y> minutes and (z) seconds"
     */
    public static String millisToLongDHMS(Context context, long duration) {
        StringBuffer res = new StringBuffer();

        long temp = 0;
        if (duration >= HALF_SECONDS) {
            temp = duration / ONE_DAY;
            if (temp > 0) {
                duration -= temp * ONE_DAY;
                res.append(temp).append(" " + context.getString(R.string.days)).append(temp > 1 ? "" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_HOUR;
            if (temp > 0) {
                duration -= temp * ONE_HOUR;
                res.append(temp).append(" " + context.getString(R.string.hours)).append(temp > 1 ? "" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_MINUTE;
            if (temp > 0) {
                duration -= temp * ONE_MINUTE;
                res.append(temp).append(" " + context.getString(R.string.minutes)).append(temp > 1 ? "" : "");
            }

            if (!res.toString().equals("") && duration >= ONE_SECOND) {
//                res.append(" and ");
                res.append(",");
            }

            temp = duration / ONE_SECOND;
            if (temp > 0) {
                res.append(temp).append(" " + context.getString(R.string.seconds)).append(temp > 1 ? "" : "");
            }
            return res.toString();
        } else {
            return context.getString(R.string.justnow_time);
        }
    }

    public static String getTimeAgo(Context context, String createdDate) {
        Date date ;
        if (createdDate.contains("T")) {
            String value = DateTimeUtils.convertUtcToLocal(createdDate, DateTimeUtils.DATETIME_FORMAT, DateTimeUtils.FORMAT);
            date = DateTimeUtils.convertStringToDate(value, DateTimeUtils.DATETIME_FORMAT);
        } else {
            date = DateTimeUtils.convertStringToDate(createdDate, DateTimeUtils.DATETIME_FORMAT);
        }
        Date currentDate = DateTimeUtils.getCalendar(System.currentTimeMillis()).getTime();
        long diff = currentDate.getTime() - date.getTime();
        String time = DateTimeUtils.millisToLongDHMS(context, diff);
        String[] timeAgo = time.split(",");
        if (timeAgo.length > 0)
            return timeAgo[0];
        return "";
    }

    public static String getTimeAgo(Context context, String createdDate, String currentFormat) {
        Date date ;
            String value = DateTimeUtils.convertUtcToLocal(createdDate, DateTimeUtils.DATETIME_FORMAT, currentFormat);
            date = DateTimeUtils.convertStringToDate(value, DateTimeUtils.DATETIME_FORMAT);
        Date currentDate = DateTimeUtils.getCalendar(System.currentTimeMillis()).getTime();
        long diff = currentDate.getTime() - date.getTime();
        String time = DateTimeUtils.millisToLongDHMS(context, diff);
        String[] timeAgo = time.split(",");
        if (timeAgo.length > 0)
            return timeAgo[0];
        return "";
    }

    public static String getTimeToGo(Context context, String createdDate) {
        String value = DateTimeUtils.convertUtcToLocal(createdDate, "yyyy-MM-dd HH:mm:ss", DateTimeUtils.FORMAT);
        Date date = DateTimeUtils.convertStringToDate(value, "yyyy-MM-dd HH:mm:ss");
        Date currentDate = DateTimeUtils.getCalendar(System.currentTimeMillis()).getTime();
        long diff = date.getTime() - currentDate.getTime();
        String time = DateTimeUtils.millisToLongDHMS(context, diff);
        String[] timeAgo = time.split(",");
        if (timeAgo.length > 0)
            return timeAgo[0];
        return "";
    }

    /**
     * Functionality to get current timezone of the device
     * <ahref http://stackoverflow.com/questions/15068113/how-to-get-the-timezone-offset-in-gmtlike-gmt700-from-android-device/>
     *
     * @return timezone in the format GMT+00:00
     */
    public static String getCurrentTimezoneOffset() {

        TimeZone tz = TimeZone.getDefault();
        Calendar cal = GregorianCalendar.getInstance(tz);
        int offsetInMillis = tz.getOffset(cal.getTimeInMillis());

        String offset = String.format("%02d:%02d", Math.abs(offsetInMillis / 3600000), Math.abs((offsetInMillis / 60000) % 60));
        offset = "GMT" + (offsetInMillis >= 0 ? "+" : "-") + offset;

        return offset;
    }
}
