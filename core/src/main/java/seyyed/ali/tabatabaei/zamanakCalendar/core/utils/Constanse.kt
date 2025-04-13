package seyyed.ali.tabatabaei.zamanakCalendar.core.utils

internal object Constanse {

    val gDaysInMonth = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val jDaysInMonth = intArrayOf(31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29)
    val hDaysInMonth = intArrayOf(30, 29, 30, 29, 30, 29, 30, 29, 30, 29, 30, 29)


    val persianDayOfTheWeek = listOf("شنبه" ,"یکشنبه", "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه")
    val englishDayOfTheWeek = listOf("Saturday" , "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    val arabicDayOfTheWeek = listOf("السبت", "الأحد", "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة")

    val jalaliPersianMonths = listOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند")
    val jalaliEnglishMonths = listOf("Farvardin", "Ordibehesht", "Khordad", "Tir", "Mordad", "Shahrivar", "Mehr", "Aban", "Azar", "Dey", "Bahman", "Esfand")
    val jalaliArabicMonths = listOf("فروردين", "أرديبهشت", "خرداد", "تير", "مرداد", "شهريور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند")

    val gregorianEnglishMonths = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val gregorianPersianMonths = listOf("ژانویه", "فوریه", "مارس", "آوریل", "می", "ژوئن", "ژوئیه", "اوت", "سپتامبر", "اکتبر", "نوامبر", "دسامبر")
    val gregorianArabicMonths = listOf("يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", "يوليو", "أغسطس", "سبتمبر", "أكتوبر", "نوفمبر", "ديسمبر")

    val hijriPersianMonths = listOf("محرم", "صفر", "ربیع‌الاول", "ربیع‌الثانی", "جمادی‌الاول", "جمادی‌الثانی", "رجب", "شعبان", "رمضان", "شوال", "ذی‌القعده", "ذی‌الحجه")
    val hijriEnglishMonths = listOf("Muharram", "Safar", "Rabi' al-awwal", "Rabi' al-thani", "Jumada al-awwal", "Jumada al-thani", "Rajab", "Sha'ban", "Ramadan", "Shawwal", "Dhu al-Qi'dah", "Dhu al-Hijjah")
    val hijriArabicMonths = listOf("محرم", "صفر", "ربيع الأول", "ربيع الآخر", "جمادى الأولى", "جمادى الآخرة", "رجب", "شعبان", "رمضان", "شوال", "ذو القعدة", "ذو الحجة")

    val seasonsPersian = listOf("بهار" , "تابستان", "پاییز", "زمستان")
    val seasonsEnglish = listOf("Spring", "Summer", "Autumn", "Winter")
    val seasonsArabic = listOf("الربيع", "الصيف", "الخريف", "الشتاء")
}