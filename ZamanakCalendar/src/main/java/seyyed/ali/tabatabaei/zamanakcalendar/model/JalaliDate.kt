package seyyed.ali.tabatabaei.zamanakcalendar.model

import seyyed.ali.tabatabaei.zamanakcalendar.dataSource.Date
import seyyed.ali.tabatabaei.zamanakcalendar.dataSource.DateOperations
import seyyed.ali.tabatabaei.zamanakcalendar.model.enums.DateFormat
import seyyed.ali.tabatabaei.zamanakcalendar.model.enums.Language
import seyyed.ali.tabatabaei.zamanakcalendar.utils.AndroidCalendar
import seyyed.ali.tabatabaei.zamanakcalendar.utils.Constanse
import seyyed.ali.tabatabaei.zamanakcalendar.utils.DateConverter
import seyyed.ali.tabatabaei.zamanakcalendar.utils.EventsManager
import kotlin.math.ceil


/**
 * Data class representing a Jalali (Persian) date.
 *
 * This class encapsulates operations related to Jalali dates, including
 * determining leap years, calculating days remaining in the year, and formatting dates.
 *
 * @property year The year of the Jalali date.
 * @property month The month of the Jalali date (1-12).
 * @property dayOfMonth The day of the month in the Jalali calendar (1-31).
 */
data class JalaliDate(
    override val year: Int,
    override val month: Int,
    override val dayOfMonth: Int
) : Date, DateOperations {

    override fun isLeapYear(): Boolean {
        val leapYears = listOf(1, 5, 9, 13, 17, 22, 26, 30)
        return leapYears.contains(year % 33)
    }

    override fun getDaysRemainingInYear(): Int {
        val maxDays = if (isLeapYear()) 366 else 365
        val currentDay = (1 until month).sumOf { Constanse.jDaysInMonth[it - 1] } + dayOfMonth
        return maxDays - currentDay
    }

    override fun getDayInYear(): Int {
        return (1 until month).sumOf { Constanse.jDaysInMonth[it - 1] } + dayOfMonth
    }

    override fun getNumberOfDaysInYear(): Int {
        return if (isLeapYear()) 366 else 365
    }

    override fun getMonthName(language: Language): String {
        return when(language){
            Language.PERSIAN -> Constanse.jalaliPersianMonths[month-1]
            Language.ENGLISH -> Constanse.jalaliEnglishMonths[month-1]
        }
    }

    override fun getMonthNumber(): Int {
        return month
    }

    override fun getQuarter(language: Language): String {
        return when(language){
            Language.PERSIAN -> Constanse.seasonsPersian.getOrNull(getQuarterNumber() - 1) ?: "--"
            Language.ENGLISH -> Constanse.seasonsEnglish.getOrNull(getQuarterNumber() - 1) ?: "--"
        }
    }

    override fun getQuarterNumber(): Int {
        return when(month){
            1 ,2 ,3 -> 1
            4 ,5 ,6 -> 2
            7 ,8 ,9 -> 3
            10 ,11 ,12 -> 4
            else -> 1
        }
    }

    override fun getWeekNumberOfYear(): Int {
        return ceil((getDayInYear().toDouble() / 7.0)).toInt()
    }

    override fun getDaysInMonth(): Int {
        return if (month == 12 && isLeapYear()) 30 else Constanse.jDaysInMonth[month - 1]
    }

    override fun getWeekdayName(language: Language): String {
        val gDate = DateConverter.jalaliToGregorian(year , month , dayOfMonth)
        return when (language) {
            Language.PERSIAN -> Constanse.persianDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
            Language.ENGLISH -> Constanse.englishDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
        }
    }

    override fun getWeekdayNumber(): Int {
        val gDate = DateConverter.jalaliToGregorian(year , month , dayOfMonth)
        return AndroidCalendar.getDayOfWeek(gDate)
    }

    override fun format(dateFormat: DateFormat, language: Language): String {
        return when (dateFormat) {
            DateFormat.SHORT -> "%04d/%02d/%02d".format(year, month, dayOfMonth)
            DateFormat.FULL -> "%s %d %s %d".format(getWeekdayName(language), dayOfMonth, getMonthName(language), year)
            DateFormat.DAY_MONTH -> "%d %s".format(dayOfMonth, getMonthName(language))
        }
    }

    override fun getDailyEvent(): List<DailyEvent> {
        return EventsManager.events?.persianCalendar?.filter { it.day == dayOfMonth && it.month == month && it.type == EventSource.Iran.name }?.map {
            return@map DailyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = EventSource.Iran
            )
        } ?: emptyList()
    }

    override fun getMonthlyEvent(): List<MonthlyEvent> {
        return EventsManager.events?.persianCalendar?.filter { it.month == month && it.type == EventSource.Iran.name }?.map {
            return@map MonthlyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = EventSource.Iran ,
                day = it.day ?: 0
            )
        } ?: emptyList()
    }

    override fun getMonthlyHolidays(): List<MonthlyEvent> {
        return EventsManager.events?.persianCalendar?.filter { it.month == month && it.type == EventSource.Iran.name && it.holiday == true }?.map {
            return@map MonthlyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = EventSource.Iran ,
                day = it.day ?: 0
            )
        } ?: emptyList()
    }

    override fun isHoliday(): Boolean {
        return !EventsManager.events?.persianCalendar?.filter { it.day == dayOfMonth && it.month == month && it.type == EventSource.Iran.name && it.holiday == true }.isNullOrEmpty() || getWeekdayNumber() == 7
    }

    override fun toString(): String {
        return format(DateFormat.FULL)
    }
}