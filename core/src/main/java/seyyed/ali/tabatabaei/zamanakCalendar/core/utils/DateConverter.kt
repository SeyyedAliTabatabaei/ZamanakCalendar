package seyyed.ali.tabatabaei.zamanakCalendar.core.utils

import seyyed.ali.tabatabaei.zamanakCalendar.core.model.GregorianDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.HijriDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.JalaliDate

internal object DateConverter {
    fun gregorianToHijri(gYear: Int, gMonth: Int, gDay: Int): HijriDate {
        fun isGregorianLeapYear(year: Int): Boolean = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

        fun lastDayOfGregorianMonth(month: Int, year: Int): Int = when (month) {
            2 -> if (isGregorianLeapYear(year)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }

        fun calcAbsGregorianDays(d: Int, m: Int, y: Int): Int {
            var n = d
            for (i in m - 1 downTo 1) {
                n += lastDayOfGregorianMonth(i, y)
            }
            return n + (y - 1) * 365 + (y - 1) / 4 - (y - 1) / 100 + (y - 1) / 400
        }

        fun isIslamicLeapYear(year: Int): Boolean = ((11 * year + 14) % 30) < 11

        fun lastDayOfIslamicMonth(month: Int, year: Int): Int = if (month % 2 == 1 || (month == 12 && isIslamicLeapYear(year))) 30 else 29

        fun islamicDate(month: Int, day: Int, year: Int): Int {
            return day + 29 * (month - 1) + (month / 2) +
                    354 * (year - 1) + (3 + 11 * year) / 30 + 227014
        }

        val absDays = calcAbsGregorianDays(gDay, gMonth, gYear)
        var iYear = (absDays - 227014) / 355

        while (absDays >= islamicDate(1, 1, iYear)) {
            iYear++
        }
        iYear--

        var iMonth = 1
        while (absDays > islamicDate(iMonth, lastDayOfIslamicMonth(iMonth, iYear), iYear)) {
            iMonth++
        }

        val iDay = absDays - islamicDate(iMonth, 1, iYear) + 1

        return HijriDate(iYear, iMonth, iDay)
    }

    fun gregorianToJalali(gYear: Int, gMonth: Int, gDay: Int): JalaliDate {
        val gy = gYear - 1600
        val gm = gMonth - 1
        val gd = gDay - 1

        var gDayNo = 365 * gy + (gy + 3) / 4 - (gy + 99) / 100 + (gy + 399) / 400
        for (i in 0 until gm) gDayNo += Constanse.gDaysInMonth[i]
        if (gm > 1 && ((gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0))) gDayNo++
        gDayNo += gd

        var jDayNo = gDayNo - 79
        val jNp = jDayNo / 12053
        jDayNo %= 12053

        var jy = 979 + 33 * jNp + 4 * (jDayNo / 1461)
        jDayNo %= 1461

        if (jDayNo >= 366) {
            jy += (jDayNo - 1) / 365
            jDayNo = (jDayNo - 1) % 365
        }

        val isLeap = listOf(1, 5, 9, 13, 17, 22, 26, 30).contains(jy % 33)
        val a = Constanse.jDaysInMonth
        if (isLeap) a[11] = 30

        var jm = 1
        for (i in 0.. 11) {
            if (jDayNo < a[i]) {
                jm = i + 1
                break
            }
            jDayNo -= a[i]
        }
        return JalaliDate(jy, jm, jDayNo + 1)
    }


    fun jalaliToGregorian(jYear: Int, jMonth: Int, jDay: Int): GregorianDate {
        val jy = jYear - 979
        val jm = jMonth - 1
        val jd = jDay - 1

        var jDayNo = 365 * jy + (jy / 33) * 8 + (jy % 33 + 3) / 4
        for (i in 0 until jm) jDayNo += Constanse.jDaysInMonth[i]
        jDayNo += jd

        var gDayNo = jDayNo + 79
        var gy = 1600 + 400 * (gDayNo / 146097)
        gDayNo %= 146097

        var leap = true
        if (gDayNo >= 36525) {
            gDayNo--
            gy += 100 * (gDayNo / 36524)
            gDayNo %= 36524
            if (gDayNo >= 365) gDayNo++ else leap = false
        }

        gy += 4 * (gDayNo / 1461)
        gDayNo %= 1461
        if (gDayNo >= 366) {
            leap = false
            gDayNo--
            gy += gDayNo / 365
            gDayNo %= 365
        }

        var gm = 0
        for (i in 0..11) {
            val daysInMonth = Constanse.gDaysInMonth[i] + if (i == 1 && leap) 1 else 0
            if (gDayNo < daysInMonth) {
                gm = i + 1
                break
            }
            gDayNo -= daysInMonth
        }
        return GregorianDate(gy, gm, gDayNo + 1)
    }

    fun hijriToGregorian(hYear: Int, hMonth: Int, hDay: Int): GregorianDate {
        fun calcAbsGregorianDays(d: Int, m: Int, y: Int): Int {
            var n = d
            for (i in m - 1 downTo 1) {
                n += when (i) {
                    2 -> if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0) 29 else 28
                    4, 6, 9, 11 -> 30
                    else -> 31
                }
            }
            return n + (y - 1) * 365 + (y - 1) / 4 - (y - 1) / 100 + (y - 1) / 400
        }

        fun islamicToAbsDay(year: Int, month: Int, day: Int): Int {
            return day + 29 * (month - 1) + (month / 2) +
                    354 * (year - 1) + (3 + 11 * year) / 30 + 227014
        }

        fun isGregorianLeapYear(year: Int): Boolean =
            (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)

        fun lastDayOfGregorianMonth(month: Int, year: Int): Int = when (month) {
            2 -> if (isGregorianLeapYear(year)) 29 else 28
            4, 6, 9, 11 -> 30
            else -> 31
        }

        val absDay = islamicToAbsDay(hYear, hMonth, hDay)

        var gYear = (absDay * 400) / 146097  // 146097 = روزهای 400 سال میلادی
        while (true) {
            val daysInYear = if (isGregorianLeapYear(gYear)) 366 else 365
            val yearStartAbs = calcAbsGregorianDays(1, 1, gYear)
            if (absDay < yearStartAbs) {
                gYear--
            } else if (absDay >= yearStartAbs + daysInYear) {
                gYear++
            } else {
                break
            }
        }

        var gMonth = 1
        while (absDay > calcAbsGregorianDays(lastDayOfGregorianMonth(gMonth, gYear), gMonth, gYear)) {
            gMonth++
        }

        val gDay = absDay - calcAbsGregorianDays(1, gMonth, gYear) + 1

        return GregorianDate(gYear, gMonth, gDay)
    }

}