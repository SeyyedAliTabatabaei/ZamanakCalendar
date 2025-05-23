package seyyed.ali.tabatabaei.zamanakCalendar.core.dataSource

import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.ClockFormat
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.HourType

internal interface ClockOperations {

    /**
     * Computes the 12-hour format of the hour.
     * @return The hour in 12-hour format (0-12).
     */
    val formatHour12: Int

    /**
     * Gets the hour type (AM or PM) based on the current hour.
     * @return The [HourType] representing AM or PM.
     */
    fun getHourType() : HourType

    /**
     * Calculates the total number of seconds represented by this clock.
     * @return The total seconds as a [Long].
     */
    fun getTotalSecond() : Long

    /**
     * Formats the clock time based on the provided clock format.
     * @param clockFormat The format in which to display the time ([ClockFormat]).
     * @param amText The text to display for AM.
     * @param pmText The text to display for PM.
     * @return The formatted time as a [String].
     */
    fun format(clockFormat: ClockFormat, amText : String = HourType.AM.name, pmText : String = HourType.PM.name) : String

}