package seyyed.ali.tabatabaei.zamanakCalendar.core.model

import seyyed.ali.tabatabaei.zamanakCalendar.core.dataSource.Date
import seyyed.ali.tabatabaei.zamanakCalendar.core.dataSource.DateOperations
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.DateFormat
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.Language
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.AndroidCalendar
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.Constanse
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.EventsManager
import kotlin.math.ceil

/**
 * Data class representing a Gregorian date.
 *
 * This class encapsulates operations related to Gregorian dates, including
 * determining leap years, calculating days remaining in the year, and formatting dates.
 *
 * @property year The year of the Gregorian date.
 * @property month The month of the Gregorian date (1-12).
 * @property dayOfMonth The day of the month in the Gregorian calendar (1-31).
 */
data class GregorianDate(
    override val year: Int,
    override val month: Int,
    override val dayOfMonth: Int
) : Date, DateOperations {

    override fun isLeapYear(): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    override fun getDaysRemainingInYear(): Int {
        val maxDays = if (isLeapYear()) 366 else 365
        val currentDay = (1 until month).sumOf { Constanse.gDaysInMonth[it - 1] } + dayOfMonth
        return maxDays - currentDay
    }

    override fun getDayInYear(): Int {
        return (1 until month).sumOf { Constanse.gDaysInMonth[it - 1] } + dayOfMonth
    }

    override fun getNumberOfDaysInYear(): Int {
        return if (isLeapYear()) 366 else 365
    }

    override fun getMonthName(language: Language): String {
        return when(language){
            Language.PERSIAN -> Constanse.gregorianPersianMonths[month-1]
            Language.ENGLISH -> Constanse.gregorianEnglishMonths[month-1]
            Language.ARABIC ->  Constanse.gregorianArabicMonths[month-1]
        }
    }

    override fun getMonthNumber(): Int {
        return dayOfMonth
    }

    override fun getQuarter(language: Language): String {
        return when(language){
            Language.PERSIAN -> Constanse.seasonsPersian.getOrNull(getQuarterNumber() - 1) ?: "--"
            Language.ENGLISH -> Constanse.seasonsEnglish.getOrNull(getQuarterNumber() - 1) ?: "--"
            Language.ARABIC ->  Constanse.seasonsArabic.getOrNull(getQuarterNumber() - 1) ?: "--"
        }
    }

    override fun getQuarterNumber(): Int {
        return when(month){
            3, 4, 5 -> 1
            6, 7, 8 -> 2
            9, 10, 11 -> 3
            12, 1, 2 -> 4
            else -> 1
        }
    }

    override fun getWeekNumberOfYear(): Int {
        return ceil((getDayInYear().toDouble() / 7.0)).toInt()
    }

    override fun getDaysInMonth(): Int {
        return if (month == 2 && isLeapYear()) 29 else Constanse.gDaysInMonth[month - 1]
    }

    override fun getWeekdayName(language: Language): String {
        val gDate = GregorianDate(year, month, dayOfMonth)
        return when (language) {
            Language.PERSIAN -> Constanse.persianDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
            Language.ENGLISH -> Constanse.englishDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
            Language.ARABIC ->  Constanse.arabicDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
        }
    }

    override fun getWeekdayNumber(): Int {
        return AndroidCalendar.getDayOfWeek(GregorianDate(year, month, dayOfMonth))
    }

    override fun format(dateFormat: DateFormat, language: Language): String {
        return when (dateFormat) {
            DateFormat.SHORT -> "%04d/%02d/%02d".format(year, month, dayOfMonth)
            DateFormat.FULL -> "%s %d %s %d".format(getWeekdayName(language), dayOfMonth, getMonthName(language), year)
            DateFormat.DAY_MONTH -> "%d %s".format(dayOfMonth, getMonthName(language))
        }
    }

    override fun getDailyEvent(): List<DailyEvent> {
        return EventsManager.events?.gregorianCalendar?.filter { it.day == dayOfMonth && it.month == month }?.map {
            return@map DailyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = EventSource.Iran
            )
        } ?: emptyList()
    }

    override fun getMonthlyEvent(): List<MonthlyEvent> {
        return EventsManager.events?.gregorianCalendar?.filter { it.month == month }?.map {
            return@map MonthlyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = EventSource.Iran ,
                day = it.day ?: 0
            )
        } ?: emptyList()
    }

    override fun getMonthlyHolidays(): List<MonthlyEvent> {
        return EventsManager.events?.gregorianCalendar?.filter { it.month == month && it.holiday == true }?.map {
            return@map MonthlyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = EventSource.Iran ,
                day = it.day ?: 0
            )
        } ?: emptyList()
    }

    override fun isHoliday(): Boolean {
        return !EventsManager.events?.gregorianCalendar?.filter { it.day == dayOfMonth && it.month == month && it.holiday == true }.isNullOrEmpty() || getWeekdayNumber() == 7
    }

    override fun toString(): String {
        return format(DateFormat.SHORT)
    }
}