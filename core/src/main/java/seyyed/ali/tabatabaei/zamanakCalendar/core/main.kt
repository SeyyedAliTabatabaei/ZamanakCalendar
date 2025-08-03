package seyyed.ali.tabatabaei.zamanakCalendar.core

import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.CalendarType
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.TimeUnitType

fun main() {
    val a = ZamanakCore().getMonthListWithPadding(CalendarType.Jalali)

    a.forEach {
        println(it)
    }
}