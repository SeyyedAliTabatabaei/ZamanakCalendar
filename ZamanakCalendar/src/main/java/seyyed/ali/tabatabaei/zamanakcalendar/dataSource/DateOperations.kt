package seyyed.ali.tabatabaei.zamanakcalendar.dataSource

import seyyed.ali.tabatabaei.zamanakcalendar.model.enums.DateFormat
import seyyed.ali.tabatabaei.zamanakcalendar.model.enums.Language
import seyyed.ali.tabatabaei.zamanakcalendar.model.DailyEvent
import seyyed.ali.tabatabaei.zamanakcalendar.model.MonthlyEvent


internal interface DateOperations{
    /**
     * Checks if the current year is a leap year.
     *
     * @return True if the year is a leap year; otherwise, false.
     */
    fun isLeapYear() : Boolean

    /**
     * Gets the number of days remaining in the current year.
     *
     * @return The number of days remaining in the year.
     */
    fun getDaysRemainingInYear() : Int

    /**
     * Gets the day number of the year.
     *
     * @return The day number in the year (1-365 or 1-366).
     */
    fun getDayInYear() : Int

    /**
     * Gets the total number of days in the current year.
     *
     * @return The total number of days in the year (365 or 366).
     */
    fun getNumberOfDaysInYear() : Int

    /**
     * Gets the name of the current month based on the specified language.
     *
     * @param language The language in which to get the month name ([Language]).
     * @return The name of the month in the specified language.
     */
    fun getMonthName(language: Language = Language.PERSIAN) : String

    /**
     * Gets the month number.
     *
     * @return The month number (1-12).
     */
    fun getMonthNumber() : Int

    /**
     * Gets the name of the current quarter based on the specified language.
     *
     * @param language The language in which to get the quarter name ([Language]).
     * @return The name of the quarter in the specified language.
     */
    fun getQuarter(language: Language = Language.PERSIAN) : String

    /**
     * Gets the quarter number of the current month.
     *
     * @return The quarter number (1-4).
     */
    fun getQuarterNumber() : Int

    /**
     * Gets the week number of the year.
     *
     * @return The week number in the year.
     */
    fun getWeekNumberOfYear() : Int

    /**
     * Gets the number of days in the current month.
     *
     * @return The number of days in the month (28, 29, 30 or 31).
     */
    fun getDaysInMonth() : Int

    /**
     * Gets the name of the weekday for the current date based on the specified language.
     *
     * @param language The language in which to get the weekday name ([Language]).
     * @return The name of the weekday in the specified language.
     */
    fun getWeekdayName(language: Language = Language.PERSIAN) : String

    /**
     * Gets the number of the weekday for the current date.
     *
     * @return The weekday number (1-7, where 1 is Saturday).
     */
    fun getWeekdayNumber() : Int

    /**
     * Formats the current date based on the specified date format and language.
     *
     * @param dateFormat The format in which to display the date ([DateFormat]).
     * @param language The language in which to format the date ([Language]).
     * @return The formatted date as a [String].
     */
    fun format(dateFormat: DateFormat, language: Language = Language.PERSIAN) : String

    /**
     * Gets a list of daily events for the current date.
     *
     * @return A list of [DailyEvent] objects occurring on this date.
     */
    fun getDailyEvent() : List<DailyEvent>

    /**
     * Gets a list of monthly events for the current month.
     *
     * @return A list of [MonthlyEvent] objects for the current month.
     */
    fun getMonthlyEvent() : List<MonthlyEvent>

    /**
     * Gets a list of monthly holidays for the current month.
     *
     * @return A list of [MonthlyEvent] objects that represent holidays for the current month.
     */
    fun getMonthlyHolidays() : List<MonthlyEvent>

    /**
     * Checks if the current date is a holiday.
     *
     * @return True if the current date is a holiday; otherwise, false.
     */
    fun isHoliday(): Boolean
}
