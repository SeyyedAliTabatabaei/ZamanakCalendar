package seyyed.ali.tabatabaei.zamanakCalendar.core.model

import seyyed.ali.tabatabaei.zamanakCalendar.core.dataSource.Date
import seyyed.ali.tabatabaei.zamanakCalendar.core.dataSource.DateOperations
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.DateFormat
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.Language
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.AndroidCalendar
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.Constanse
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.DateConverter
import seyyed.ali.tabatabaei.zamanakCalendar.core.utils.EventsManager
import kotlin.math.ceil

data class HijriDate(
    override val year: Int,
    override val month: Int,
    override val dayOfMonth: Int
) : Date, DateOperations {

    override fun isLeapYear(): Boolean {
        return ((11 * year + 14) % 30) < 11
    }

    override fun getDaysRemainingInYear(): Int {
        val maxDays = if (isLeapYear()) 366 else 365
        val currentDay = (1 until month).sumOf { Constanse.hDaysInMonth[it - 1] } + dayOfMonth
        return maxDays - currentDay
    }

    override fun getDayInYear(): Int {
        return (1 until month).sumOf { Constanse.hDaysInMonth[it - 1] } + dayOfMonth
    }

    override fun getNumberOfDaysInYear(): Int {
        return if (isLeapYear()) 366 else 365
    }

    override fun getMonthName(language: Language): String {
        return when(language){
            Language.PERSIAN -> Constanse.hijriPersianMonths[month-1]
            Language.ENGLISH -> Constanse.hijriEnglishMonths[month-1]
            Language.ARABIC ->  Constanse.hijriArabicMonths[month-1]
        }
    }

    override fun getMonthNumber(): Int {
        return month
    }

    override fun getQuarter(language: Language): String {
        return when(language){
            Language.PERSIAN -> Constanse.seasonsPersian.getOrNull(getQuarterNumber() - 1) ?: "--"
            Language.ENGLISH -> Constanse.seasonsEnglish.getOrNull(getQuarterNumber() - 1) ?: "--"
            Language.ARABIC ->  Constanse.seasonsArabic.getOrNull(getQuarterNumber() - 1) ?: "--"
        }
    }

    override fun getQuarterNumber(): Int {
        return when (month) {
            10, 11, 12 -> 1
            1, 2, 3 -> 2
            4, 5, 6 -> 3
            7, 8, 9 -> 4
            else -> 1
        }
    }

    override fun getWeekNumberOfYear(): Int {
        return ceil((getDayInYear().toDouble() / 7.0)).toInt()
    }

    override fun getDaysInMonth(): Int {
        return if (month == 12 && isLeapYear()) 30 else Constanse.hDaysInMonth[month - 1]
    }

    override fun getWeekdayName(language: Language): String {
        val gDate = DateConverter.hijriToGregorian(year , month , dayOfMonth)
        return when (language) {
            Language.PERSIAN -> Constanse.persianDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
            Language.ENGLISH -> Constanse.englishDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
            Language.ARABIC ->  Constanse.arabicDayOfTheWeek[AndroidCalendar.getDayOfWeek(gDate) - 1]
        }
    }

    override fun getWeekdayNumber(): Int {
        val gDate = DateConverter.hijriToGregorian(year , month , dayOfMonth)
        return AndroidCalendar.getDayOfWeek(gDate)
    }

    override fun format(dateFormat: DateFormat, language: Language): String {
        return when (dateFormat) {
            DateFormat.SHORT -> "%04d/%02d/%02d".format(year, month, dayOfMonth)
            DateFormat.FULL -> "%s %d %s %d".format(getWeekdayName(language), dayOfMonth, getMonthName(language), year)
            DateFormat.DAY_MONTH -> "%d %s".format(dayOfMonth, getMonthName(language))
        }
    }

    override fun getDailyEvent(vararg eventSource: EventSource): List<DailyEvent> {
        return EventsManager.events?.hijriCalendar?.filter { it.day == dayOfMonth && it.month == month && eventSource.any { src -> it.type == src.name } }?.map {
            return@map DailyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = when(it.type){
                    EventSource.Iran.name -> EventSource.Iran
                    EventSource.Afghanistan.name -> EventSource.Afghanistan
                    EventSource.International.name -> EventSource.International
                    EventSource.Nepal.name -> EventSource.Nepal
                    EventSource.AncientIran.name -> EventSource.AncientIran
                    else -> EventSource.None
                }
            )
        } ?: emptyList()
    }

    override fun getMonthlyEvent(vararg eventSource: EventSource): List<MonthlyEvent> {
        return EventsManager.events?.hijriCalendar?.filter { it.month == month  && eventSource.any { src -> it.type == src.name } }?.map {
            return@map MonthlyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = when(it.type){
                    EventSource.Iran.name -> EventSource.Iran
                    EventSource.Afghanistan.name -> EventSource.Afghanistan
                    EventSource.International.name -> EventSource.International
                    EventSource.Nepal.name -> EventSource.Nepal
                    EventSource.AncientIran.name -> EventSource.AncientIran
                    else -> EventSource.None
                } ,
                day = it.day ?: 0
            )
        } ?: emptyList()
    }

    override fun getMonthlyHolidays(vararg eventSource: EventSource): List<MonthlyEvent> {
        return EventsManager.events?.hijriCalendar?.filter { it.month == month  && eventSource.any { src -> it.type == src.name } && it.holiday == true }?.map {
            return@map MonthlyEvent(
                isHoliday = it.holiday ?: false ,
                title = it.title ?: "" ,
                type = when(it.type){
                    EventSource.Iran.name -> EventSource.Iran
                    EventSource.Afghanistan.name -> EventSource.Afghanistan
                    EventSource.International.name -> EventSource.International
                    EventSource.Nepal.name -> EventSource.Nepal
                    EventSource.AncientIran.name -> EventSource.AncientIran
                    else -> EventSource.None
                } ,
                day = it.day ?: 0
            )
        } ?: emptyList()
    }

    override fun isHoliday(vararg eventSource: EventSource): Boolean {
        return !EventsManager.events?.hijriCalendar?.filter { it.day == dayOfMonth && it.month == month  && eventSource.any { src -> it.type == src.name } && it.holiday == true }.isNullOrEmpty() || getWeekdayNumber() == 7
    }


    override fun toString(): String {
        return format(DateFormat.SHORT)
    }
}