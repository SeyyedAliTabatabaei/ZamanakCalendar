package seyyed.ali.tabatabaei.zamanakCalendar.core.model

import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.ClockFormat
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.HourType
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.CalendarValidation

/**
 * Data class representing a clock with hours, minutes, and seconds.
 *
 * This class provides functionalities to operate with time and format it in different clock formats.
 *
 * @property hour The hour component of the clock (0-23).
 * @property minute The minute component of the clock (0-59).
 * @property second The second component of the clock (0-59).
 */
data class Clock(
    var hour: Int = 0,
    val minute: Int = 0,
    val second: Int = 0,
) : seyyed.ali.tabatabaei.zamanakCalendar.core.dataSource.ClockOperations {

    init {
        CalendarValidation.isValidClock(this)
    }

    override val formatHour12: Int
        get() = when(hour){
            0 -> 0
            12 -> 12
            else -> hour % 12
        }

    override fun getHourType(): HourType {
        return if (hour < 12) HourType.AM else HourType.PM
    }

    override fun getTotalSecond(): Long {
        return hour.toLong() * 3600L + minute.toLong() * 60L + second.toLong()
    }

    override fun format(clockFormat: ClockFormat, amText: String, pmText: String): String {
        val amPmText = if (hour < 12) amText else pmText
        return when(clockFormat){
            ClockFormat.H24_HM -> "%02d:%02d".format(hour, minute)
            ClockFormat.H24_HMS -> "%02d:%02d:%02d".format(hour, minute, second)
            ClockFormat.H12_HM ->  "%02d:%02d".format(formatHour12, minute)
            ClockFormat.H12_HM_AMPM -> "%02d:%02d %s".format(formatHour12, minute, amPmText)
            ClockFormat.H12_HMS -> "%02d:%02d:%02d".format(formatHour12, minute, second)
            ClockFormat.H12_HMS_AMPM -> "%02d:%02d:%02d %s".format(formatHour12, minute, second, amPmText)
            ClockFormat.H12_H_AMPM -> "%02d %s".format(formatHour12, amPmText)
            ClockFormat.H24_COMPACT -> "%02d%02d%02d".format(hour, minute, second)
        }
    }

    override fun toString(): String {
        return format(ClockFormat.H24_HMS)
    }

}