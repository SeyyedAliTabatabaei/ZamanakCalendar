package seyyed.ali.tabatabaei.zamanakcalendar

import seyyed.ali.tabatabaei.zamanakcalendar.dataSource.ZamanakCalendarOperations
import seyyed.ali.tabatabaei.zamanakcalendar.model.enums.CalendarType
import seyyed.ali.tabatabaei.zamanakcalendar.model.enums.TimeUnitType
import seyyed.ali.tabatabaei.zamanakcalendar.model.Clock
import seyyed.ali.tabatabaei.zamanakcalendar.model.GregorianDate
import seyyed.ali.tabatabaei.zamanakcalendar.model.JalaliDate
import seyyed.ali.tabatabaei.zamanakcalendar.utils.AndroidCalendar
import seyyed.ali.tabatabaei.zamanakcalendar.utils.CalendarValidation
import seyyed.ali.tabatabaei.zamanakcalendar.utils.Constanse
import seyyed.ali.tabatabaei.zamanakcalendar.utils.DateConverter
import java.util.Calendar
import java.util.TimeZone

/**
 * Class representing a Zamanak calendar that supports both Jalali and Gregorian dates.
 *
 * This class provides methods to manipulate and retrieve date and time information.
 */
class ZamanakCalendar() : ZamanakCalendarOperations {

    private var jDate: JalaliDate = DateConverter.gregorianToJalali(AndroidCalendar.getCurrentYear() , AndroidCalendar.getCurrentMonth(), AndroidCalendar.getCurrentDay())
    private var gDate: GregorianDate = GregorianDate(year = AndroidCalendar.getCurrentYear() , month = AndroidCalendar.getCurrentMonth(), dayOfMonth = AndroidCalendar.getCurrentDay())
    private var clockTime: Clock = AndroidCalendar.getCurrentClock()

    override val jalaliDate get() = jDate
    override val gregorianDate get() = gDate
    override val clock get() = clockTime

    override fun isToday(): Boolean = AndroidCalendar.getCurrentTimeInMillis() in getStartOfDay().toMillis()..getEndOfDay().toMillis()

    override fun toMillis(): Long = AndroidCalendar.getTimeInMillis(gDate, clock)

    override fun getStartOfDay() = apply { clockTime = Clock(0, 0, 0) }

    override fun getEndOfDay() = apply { clockTime = Clock(23, 59, 59) }

    override fun setClock(clock: Clock) = apply {
        CalendarValidation.isValidClock(clock)
        clockTime = clock
    }

    override fun setDateFromJalali(jalaliDate: JalaliDate) = apply {
        CalendarValidation.isValidJalaliDate(jalaliDate , jalaliDate.isLeapYear())
        jDate = jalaliDate
        gDate = DateConverter.jalaliToGregorian(jalaliDate.year, jalaliDate.month, jalaliDate.dayOfMonth)
    }

    override fun setDateFromGregorian(gregorianDate: GregorianDate) = apply {
        CalendarValidation.isValidGregorianDate(gregorianDate , gregorianDate.isLeapYear())
        gDate = gregorianDate
        jDate = DateConverter.gregorianToJalali(gregorianDate.year, gregorianDate.month, gregorianDate.dayOfMonth)
    }

    override fun setDateFromTimeInMillis(time: Long): ZamanakCalendar = apply {
        val (gDate , clock) = AndroidCalendar.convertTimeMillisToGregorianDate(time)
        this.gDate = gDate
        this.clockTime = clock
        this.jDate = DateConverter.gregorianToJalali(gDate.year , gDate.month , gDate.dayOfMonth)
    }

    override fun setTimeZone(timeZone: TimeZone) = apply {
        val timeInMillis = AndroidCalendar.getTimeInMillis(gDate, clockTime)
        AndroidCalendar.setTimeZone(timeZone)
        setDateFromTimeInMillis(timeInMillis)
        return this
    }

    override fun getTimeZone(): TimeZone = AndroidCalendar.getTimeZone()


    override fun addDate(
        calendarType: CalendarType,
        timeUnitType: TimeUnitType,
        count: Int
    ): ZamanakCalendar = apply {
        require(count > 0) { "Count must be > 0" }
        applyDateChange(calendarType, timeUnitType, count, isAddition = true)
    }

    override fun subDate(
        calendarType: CalendarType,
        timeUnitType: TimeUnitType,
        count: Int
    ): ZamanakCalendar = apply {
        require(count > 0) { "Count must be > 0" }
        applyDateChange(calendarType, timeUnitType, count, isAddition = false)
    }

    private fun applyDateChange(
        calendarType: CalendarType,
        timeUnitType: TimeUnitType,
        count: Int,
        isAddition: Boolean
    ): ZamanakCalendar {
        var jYear = jDate.year
        var jMonth = jDate.month
        var jDay = jDate.dayOfMonth
        var gYear = gDate.year
        var gMonth = gDate.month
        var gDay = gDate.dayOfMonth
        val hour = clockTime.hour
        val min = clockTime.minute
        val sec = clockTime.second

        fun normalizeClock(
            hour: Int,
            min: Int,
            sec: Int
        ): Triple<Int, Int, Int> {
            var newSec = sec
            var newMin = min
            var newHour = hour

            newMin += newSec / 60
            newSec %= 60
            if (newSec < 0) {
                newSec += 60
                newMin--
            }

            newHour += newMin / 60
            newMin %= 60
            if (newMin < 0) {
                newMin += 60
                newHour--
            }

            val extraDays = newHour / 24
            newHour %= 24
            if (newHour < 0) {
                newHour += 24
                return Triple(extraDays - 1, newHour, newMin)
            }

            return Triple(extraDays, newHour, newMin)
        }

        when (timeUnitType) {
            TimeUnitType.SECOND -> {
                val totalSec = if (isAddition) sec + count else sec - count
                val (extraDays, newHour, newMin) = normalizeClock(hour, min, totalSec)
                val base = if (extraDays != 0) {
                    if (isAddition) addDate(calendarType, TimeUnitType.DAY, extraDays)
                    else subDate(calendarType, TimeUnitType.DAY, -extraDays)
                } else this
                base.clockTime = Clock(newHour, newMin, (totalSec % 60 + 60) % 60)
                return base
            }
            TimeUnitType.MINUTE -> {
                val totalMin = if (isAddition) min + count else min - count
                val (extraDays, newHour, newMin) = normalizeClock(hour, totalMin, sec)
                val base = if (extraDays != 0) {
                    if (isAddition) addDate(calendarType, TimeUnitType.DAY, extraDays)
                    else subDate(calendarType, TimeUnitType.DAY, -extraDays)
                } else this
                base.clockTime = Clock(newHour, newMin, sec)
                return base
            }
            TimeUnitType.HOUR -> {
                val totalHour = if (isAddition) hour + count else hour - count
                val (extraDays, newHour, newMin) = normalizeClock(totalHour, min, sec)
                val base = if (extraDays != 0) {
                    if (isAddition) addDate(calendarType, TimeUnitType.DAY, extraDays)
                    else subDate(calendarType, TimeUnitType.DAY, -extraDays)
                } else this
                base.clockTime = Clock(newHour, newMin, sec)
                return base
            }
            else -> {
                if (calendarType == CalendarType.Jalali) {
                    val jDaysInMonth = Constanse.jDaysInMonth.toMutableList().apply {
                        this[11] = if (JalaliDate(jYear, 1, 1).isLeapYear()) 30 else 29
                    }

                    when (timeUnitType) {
                        TimeUnitType.YEAR -> {
                            jYear += if (isAddition) count else -count
                            if (jMonth == 12 && jDay == 30 && !JalaliDate(jYear, 1, 1).isLeapYear()) jDay = 29
                        }
                        TimeUnitType.MONTH -> {
                            var totalMonths = jMonth + if (isAddition) count else -count
                            while (totalMonths > 12) {
                                totalMonths -= 12
                                jYear++
                            }
                            while (totalMonths < 1) {
                                totalMonths += 12
                                jYear--
                            }
                            jMonth = totalMonths
                            jDaysInMonth[11] = if (JalaliDate(jYear, 1, 1).isLeapYear()) 30 else 29
                            if (jDay > jDaysInMonth[jMonth - 1]) jDay = jDaysInMonth[jMonth - 1]
                        }
                        TimeUnitType.DAY -> {
                            var remaining = count
                            if (!isAddition) remaining *= -1
                            while (remaining != 0) {
                                val daysInMonth = if (jMonth == 12)
                                    if (JalaliDate(jYear, 1, 1).isLeapYear()) 30 else 29
                                else jDaysInMonth[jMonth - 1]

                                if (remaining > 0) {
                                    val daysLeft = daysInMonth - jDay
                                    if (remaining <= daysLeft) {
                                        jDay += remaining
                                        remaining = 0
                                    } else {
                                        remaining -= (daysLeft + 1)
                                        jDay = 1
                                        jMonth++
                                        if (jMonth > 12) {
                                            jMonth = 1
                                            jYear++
                                        }
                                    }
                                } else {
                                    if (Math.abs(remaining) < jDay) {
                                        jDay += remaining
                                        remaining = 0
                                    } else {
                                        remaining += jDay
                                        jMonth--
                                        if (jMonth < 1) {
                                            jMonth = 12
                                            jYear--
                                        }
                                        jDay = if (jMonth == 12)
                                            if (JalaliDate(jYear, 1, 1).isLeapYear()) 30 else 29
                                        else jDaysInMonth[jMonth - 1]
                                    }
                                }
                            }
                        }
                        else -> {}
                    }

                    setDateFromJalali(JalaliDate(jYear, jMonth, jDay))
                    setClock(Clock(hour, min, sec))
                } else {
                    val gDaysInMonth = Constanse.gDaysInMonth.toMutableList().apply {
                        this[1] = if (GregorianDate(gYear, 1, 1).isLeapYear()) 29 else 28
                    }

                    when (timeUnitType) {
                        TimeUnitType.YEAR -> {
                            gYear += if (isAddition) count else -count
                            if (gMonth == 2 && gDay == 29 && !GregorianDate(gYear, 1, 1).isLeapYear()) gDay = 28
                        }
                        TimeUnitType.MONTH -> {
                            var totalMonths = gMonth + if (isAddition) count else -count
                            while (totalMonths > 12) {
                                totalMonths -= 12
                                gYear++
                            }
                            while (totalMonths < 1) {
                                totalMonths += 12
                                gYear--
                            }
                            gMonth = totalMonths
                            gDaysInMonth[1] = if (GregorianDate(gYear, 1, 1).isLeapYear()) 29 else 28
                            val maxDay = if (gMonth == 2) gDaysInMonth[1] else gDaysInMonth[gMonth - 1]
                            if (gDay > maxDay) gDay = maxDay
                        }
                        TimeUnitType.DAY -> {
                            var remaining = count
                            if (!isAddition) remaining *= -1
                            while (remaining != 0) {
                                val daysInMonth = if (gMonth == 2)
                                    if (GregorianDate(gYear, 1, 1).isLeapYear()) 29 else 28
                                else gDaysInMonth[gMonth - 1]

                                if (remaining > 0) {
                                    val daysLeft = daysInMonth - gDay
                                    if (remaining <= daysLeft) {
                                        gDay += remaining
                                        remaining = 0
                                    } else {
                                        remaining -= (daysLeft + 1)
                                        gDay = 1
                                        gMonth++
                                        if (gMonth > 12) {
                                            gMonth = 1
                                            gYear++
                                        }
                                    }
                                } else {
                                    if (Math.abs(remaining) < gDay) {
                                        gDay += remaining
                                        remaining = 0
                                    } else {
                                        remaining += gDay
                                        gMonth--
                                        if (gMonth < 1) {
                                            gMonth = 12
                                            gYear--
                                            gDaysInMonth[1] = if (GregorianDate(gYear, 1, 1).isLeapYear()) 29 else 28
                                        }
                                        gDay = if (gMonth == 2) gDaysInMonth[1] else gDaysInMonth[gMonth - 1]
                                    }
                                }
                            }
                        }
                        else -> {}
                    }

                    setDateFromGregorian(GregorianDate(gYear, gMonth, gDay))
                    setClock(Clock(hour, min, sec))
                }
            }
        }

        return this
    }

    override fun isBefore(zamanakCalendar: ZamanakCalendar): Boolean = AndroidCalendar.getTimeInMillis(zamanakCalendar.gDate , zamanakCalendar.clockTime) > AndroidCalendar.getTimeInMillis(gDate, clockTime)

    override fun isAfter(zamanakCalendar: ZamanakCalendar): Boolean = AndroidCalendar.getTimeInMillis(zamanakCalendar.gDate , zamanakCalendar.clockTime) < AndroidCalendar.getTimeInMillis(gDate, clockTime)

    override fun equalsDate(date: ZamanakCalendar): Boolean {
        return date.gDate.year == this.gDate.year &&
                date.gDate.month == this.gDate.month &&
                date.gDate.dayOfMonth == this.gDate.dayOfMonth &&
                date.jDate.year == this.jDate.year &&
                date.jDate.month == this.jDate.month &&
                date.jDate.dayOfMonth == this.jDate.dayOfMonth &&
                date.clockTime.hour == this.clockTime.hour &&
                date.clockTime.minute == this.clockTime.minute &&
                date.clockTime.second == this.clockTime.second
    }


    override fun getWeekdaysList(): List<ZamanakCalendar> {
        val (startGDate, startClock) = AndroidCalendar.addTime(gDate, clockTime, Calendar.DAY_OF_MONTH, -gDate.getWeekdayNumber()+1)
        return (0..6).map {
            val (newGDate, newClock) = AndroidCalendar.addTime(startGDate, startClock, Calendar.DAY_OF_MONTH, it)
            ZamanakCalendar().setDateFromGregorian(newGDate).setClock(newClock)
        }
    }

    override fun getMonthDaysList(calendarType: CalendarType): List<ZamanakCalendar> {
        when(calendarType){
            CalendarType.Gregorian -> {
                val (startGDate, startClock) = AndroidCalendar.addTime(gDate, clockTime, Calendar.DAY_OF_MONTH, -gDate.dayOfMonth)
                return (1..gDate.getDaysInMonth()).map {
                    val (newGDate, newClock) = AndroidCalendar.addTime(startGDate, startClock, Calendar.DAY_OF_MONTH, it)
                    ZamanakCalendar().setDateFromGregorian(newGDate).setClock(newClock)
                }
            }
            CalendarType.Jalali -> {
                val (startJDate, startClock) = AndroidCalendar.addTime(gDate, clockTime, Calendar.DAY_OF_MONTH, -jDate.dayOfMonth)
                return (1..jDate.getDaysInMonth()).map {
                    val (newGDate, newClock) = AndroidCalendar.addTime(startJDate, startClock, Calendar.DAY_OF_MONTH, it)
                    ZamanakCalendar().setDateFromGregorian(newGDate).setClock(newClock)
                }
            }
        }
    }

    override fun toString(): String {
        return "JalaliDate -> $jDate \tGregorianDate -> $gDate \tClock -> $clockTime \t"
    }
}

