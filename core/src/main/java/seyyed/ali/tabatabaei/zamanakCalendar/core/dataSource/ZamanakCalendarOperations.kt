package seyyed.ali.tabatabaei.zamanakCalendar.core.dataSource

import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.CalendarType
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.TimeUnitType
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.Clock
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.GregorianDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.JalaliDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.ZamanakCore
import java.util.TimeZone


internal interface ZamanakCalendarOperations{

    val jalaliDate : JalaliDate

    val gregorianDate : GregorianDate

    val clock : Clock

    /**
     * Checks if the current date is today.
     * @return True if the current date is today; otherwise, false.
     */
    fun isToday() : Boolean

    /**
     * Converts the current date and time to milliseconds since epoch.
     * @return The time in milliseconds.
     */
    fun toMillis() : Long

    /**
     * Sets the clock time to the start of the day (00:00:00).
     * @return The current instance of [ZamanakCore].
     */
    fun getStartOfDay() : ZamanakCore

    /**
     * Sets the clock time to the end of the day (23:59:59).
     * @return The current instance of [ZamanakCore].
     */
    fun getEndOfDay() : ZamanakCore

    /**
     * Sets the clock time to the specified value.
     * @param clock The clock to set.
     * @return The current instance of [ZamanakCore].
     */
    fun setClock(clock: Clock) : ZamanakCore

    /**
     * Sets the date from a Jalali date.
     * @param jalaliDate The Jalali date to set.
     * @return The current instance of [ZamanakCore].
     */
    fun setDateFromJalali(jalaliDate: JalaliDate) : ZamanakCore

    /**
     * Sets the date from a Gregorian date.
     * @param gregorianDate The Gregorian date to set.
     * @return The current instance of [ZamanakCore].
     */
    fun setDateFromGregorian(gregorianDate: GregorianDate) : ZamanakCore

    /**
     * Sets the date from a time in milliseconds.
     * @param time The time in milliseconds.
     * @return The current instance of [ZamanakCore].
     */
    fun setDateFromTimeInMillis(time : Long) : ZamanakCore

    /**
     * Sets the time zone for the calendar.
     * @param timeZone The time zone to set.
     * @return The current instance of [ZamanakCore].
     */
    fun setTimeZone(timeZone: TimeZone) : ZamanakCore

    /**
     * Gets the current time zone of the calendar.
     * @return The current time zone.
     */
    fun getTimeZone() : TimeZone

    /**
     * Adds a specified amount of time to the current date.
     * @param calendarType The type of calendar to add to ([CalendarType]).
     * @param timeUnitType The unit of time to add ([TimeUnitType]).
     * @param count The amount of time to add.
     * @return The current instance of [ZamanakCore].
     */
    fun addDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count : Int) : ZamanakCore

    /**
     * Subtracts a specified amount of time from the current date.
     * @param calendarType The type of calendar to subtract from ([CalendarType]).
     * @param timeUnitType The unit of time to subtract ([TimeUnitType]).
     * @param count The amount of time to subtract.
     * @return The current instance of [ZamanakCore].
     */
    fun subDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count : Int) : ZamanakCore

    /**
     * Checks if this calendar is before the specified calendar.
     * @param zamanakCore The calendar to compare against.
     * @return True if this calendar is before the specified calendar; otherwise, false.
     */
    fun isBefore(zamanakCore: ZamanakCore) : Boolean

    /**
     * Checks if this calendar is after the specified calendar.
     * @param zamanakCore The calendar to compare against.
     * @return True if this calendar is after the specified calendar; otherwise, false.
     */
    fun isAfter(zamanakCore: ZamanakCore) : Boolean

    /**
     * Checks if this calendar date is equal to the specified calendar date.
     * @param date The calendar to compare.
     * @return True if the dates are equal; otherwise, false.
     */
    fun equalsDate(date: ZamanakCore) : Boolean

    /**
     * Gets a list of weekdays for the current date.
     * This method calculates the start date of the week that contains the current date,
     * then generates a list of ZamanakCalendar instances for each day of that week (Saturday to Friday).
     * @return A list of [ZamanakCore] representing the weekdays of the current week.
     */
    fun getWeekdaysList() : List<ZamanakCore>

    /**
     * Gets a list of days in the current month for the specified calendar type.
     * This method generates a list of ZamanakCalendar instances for each day of the current month,
     * either in the Gregorian or Jalali calendar format.
     * @param calendarType The type of calendar to generate days for ([CalendarType]).
     * @return A list of [ZamanakCore] representing each day of the current month.
     */
    fun getMonthDaysList(calendarType: CalendarType) : List<ZamanakCore>

}
