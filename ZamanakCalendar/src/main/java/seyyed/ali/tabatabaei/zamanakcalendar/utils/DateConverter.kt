package seyyed.ali.tabatabaei.zamanakcalendar.utils

import seyyed.ali.tabatabaei.zamanakcalendar.model.GregorianDate
import seyyed.ali.tabatabaei.zamanakcalendar.model.JalaliDate

internal object DateConverter {
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


}