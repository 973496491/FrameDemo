package com.lolko.frame_module.helper

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * <h2> 日期帮助类 </h2>
 *
 * @author Lolko.yao luolikongyao@gmail.com
 * @version 1.0
 * @since 2020年07月16日 15:46
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object DateHelper {

    //默认时间格式
    private const val DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"

    //默认时间格式
    private const val DATE_PATTERN_2 = "yyyy年MM月dd日 HH时mm分"

    /**
     * 获取指定格式时间的时间戳
     *
     * @param dateStr 时间字符串
     * @param pattern 时间格式
     * @return 时间戳
     */
    fun getTimestamp(dateStr: String, pattern: String): Long {
        try {
            val sdf = SimpleDateFormat(pattern, Locale("zh", "CN"))
            val date: Date? = sdf.parse(dateStr)
            date?.run { return time }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 获取当天指定小时时间
     *
     * @return 当前指定小时的时间
     */
    fun setHourGetDayTime(hour: Int): Long {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = hour
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        return cal.time.time
    }

    /**
     * 获取当天指定小时日期
     *
     * @return 当前指定小时的日期
     */
    fun setHourGetDayDate(hour: Int): String? {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = hour
        cal[Calendar.MINUTE] = 0
        cal[Calendar.SECOND] = 0
        return formatTime(cal.time.time)
    }

    /**
     * 指定地区以默认方式格式化时间格式化时间
     *
     * @param time 时间戳
     * @return 格式化后的时间
     */
    fun formatTime(time: Long): String {
        return formatTime(time, DATE_PATTERN, Locale("zh", "CN"))
    }

    /**
     * 指定地区格式化时间
     *
     * @param time    时间戳
     * @param pattern 时间格式
     * @return 格式化后的时间
     */
    fun formatTime(time: Long, pattern: String): String {
        return formatTime(time, pattern, Locale("zh", "CN"))
    }

    /**
     * 指定地区格式化时间
     *
     * @param time    时间戳
     * @param pattern 时间格式
     * @return 格式化后的时间
     */
    fun formatTime(time: String, pattern: String): String {
        return try {
            var sTime: Long = time.toLong()
            //如果是秒级别的则转化为毫秒
            if (sTime < 10000000000L)
                sTime *= 1000
            formatTime(sTime, pattern, Locale("zh", "CN"))
        } catch (ex: NumberFormatException) {
            ex.printStackTrace()
            formatTime(0, pattern, Locale("zh", "CN"))
        }
    }

    /**
     * 指定地区以默认方式转化时间格式
     *
     * @param date 日期
     * @return 新的日期格式格式化后的时间
     */
    fun formatTime(date: String): String {
        return formatTime(date, DATE_PATTERN, DATE_PATTERN_2)
    }

    /**
     * 指定地区指定方式格式化时间
     *
     * @param time    时间戳
     * @param pattern 时间格式
     * @param locale  地区
     * @return 格式化后的时间
     */
    fun formatTime(time: Long, pattern: String, locale: Locale): String {
        var sTime: Long = time
        //如果是秒级别的
        if (sTime < 10000000000L)
            sTime *= 1000
        val sdf = SimpleDateFormat(pattern, locale)
        return sdf.format(sTime)
    }

    /**
     * @param date       日期
     * @param patternOld 旧的日期格式
     * @param patternNew 新的日期格式
     * @return 新的日期格式格式化后的时间
     */
    fun formatTime(date: String, patternOld: String, patternNew: String): String {
        try {
            var formatter = SimpleDateFormat(patternOld, Locale("zh", "CN"))
            formatter.isLenient = false
            val newDate: Date? = formatter.parse(date)
            formatter = SimpleDateFormat(patternNew, Locale("zh", "CN"))
            newDate?.let {
                return formatter.format(it)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }

    /**
     * 获取time2与time1的差值
     *
     * @return 差值转化后的日期时间字符串
     */
    fun getDistanceTime(time1: Long, time2: Long): String {
        val day: Long
        val hour: Long
        val min: Long
        val sec: Long

        val diff: Long = if (time1 < time2)
            time2 - time1
        else
            time1 - time2

        day = diff / (24 * 60 * 60 * 1000)
        hour = diff / (60 * 60 * 1000) - day * 24
        min = diff / (60 * 1000) - day * 24 * 60 - hour * 60
        sec = diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60
        if (day != 0L) return day.toString() + "天" + hour + "小时" + min + "分钟" + sec + "秒"
        if (hour != 0L) return hour.toString() + "小时" + min + "分钟" + sec + "秒"
        if (min != 0L) return min.toString() + "分钟" + sec + "秒"
        return if (sec != 0L) sec.toString() + "秒" else "0秒"
    }

}