package seyyed.ali.tabatabaei.zamanakCalendar.core.utils

import seyyed.ali.tabatabaei.zamanakCalendar.core.model.Clock
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.GregorianDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.JalaliDate

internal object CalendarValidation {

    private val TAG = "ZamanakCalendar Exception"

    private val minHour = 0
    private val maxHour = 23

    private val minMinute = 0
    private val maxMinute = 59

    private val minSecond = 0
    private val maxSecond = 59

    private val minDay = 1

    private val minMonth = 1
    private val maxMonth = 12

    private val minJalaliYear = 1342
    private val maxJalaliYear = 1450

    private val minGregorianYear = 1960
    private val maxGregorianYear = 2070

    fun isValidClock(clock: Clock) {
        require(clock.hour in minHour..maxHour) {
            "$TAG Invalid hour: ${clock.hour}. Hour must be between $minHour and $maxHour."
        }
        require(clock.minute in minMinute..maxMinute) {
            "$TAG Invalid minute: ${clock.minute}. Minute must be between $minMinute and $maxMinute."
        }
        require(clock.second in minSecond..maxSecond) {
            "$TAG Invalid second: ${clock.second}. Second must be between $minSecond and $maxSecond."
        }
    }

    fun isValidJalaliDate(jalaliDate: JalaliDate , isLeapYear : Boolean){
        require(jalaliDate.month in minMonth..maxMonth) {
            "$TAG Invalid jalali month: ${jalaliDate.month}. Jalali month must be between $minMonth and $maxMonth."
        }
        val maxDay = if (jalaliDate.month == 12 && isLeapYear) 30 else Constanse.jDaysInMonth[jalaliDate.month-1]
        require(jalaliDate.dayOfMonth in minDay..maxDay) {
            "$TAG Invalid jalali day: ${jalaliDate.dayOfMonth}. Jalali day must be between $minDay and $maxDay."
        }

        require(jalaliDate.year in minJalaliYear..maxJalaliYear) {
            "$TAG Invalid jalali year: ${jalaliDate.year}. Jalali year must be between $minJalaliYear and $maxJalaliYear."
        }
    }

    fun isValidGregorianDate(gregorianDate: GregorianDate , isLeapYear : Boolean){
        require(gregorianDate.month in minMonth..maxMonth) {
            "$TAG Invalid gregorianDate month: ${gregorianDate.month}. GregorianDate month must be between $minMonth and $maxMonth."
        }
        val maxDay = if (gregorianDate.month == 2 && isLeapYear) 29 else Constanse.gDaysInMonth[gregorianDate.month-1]
        require(gregorianDate.dayOfMonth in minDay..maxDay) {
            "$TAG Invalid gregorianDate day: ${gregorianDate.dayOfMonth}. GregorianDate day must be between $minDay and $maxDay."
        }

        require(gregorianDate.year in minGregorianYear..maxGregorianYear) {
            "$TAG Invalid gregorianDate year: ${gregorianDate.year}. GregorianDate year must be between $minJalaliYear and $maxJalaliYear."
        }
    }


}