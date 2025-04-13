package seyyed.ali.tabatabaei.zamanakCalendar.core.utils

internal object Constanse {

    val gDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val jDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)

    val persianDayOfTheWeek = listOf("شنبه" ,"یکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه")
    val englishDayOfTheWeek = listOf("Saturday" , "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

    val jalaliPersianMonths = listOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند")
    val jalaliEnglishMonths = listOf("Farvardin", "Ordibehesht", "Khordad", "Tir", "Mordad", "Shahrivar", "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand")

    val gregorianEnglishMonths = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val gregorianPersianMonths = listOf("ژانویه", "فوریه", "مارس", "آوریل", "می", "ژوئن", "ژوئیه", "اوت", "سپتامبر", "اکتبر", "نوامبر", "دسامبر")

    val seasonsPersian = listOf("بهار" , "تابستان", "پاییز", "زمستان")
    val seasonsEnglish = listOf("Spring", "Summer", "Autumn", "Winter")
}