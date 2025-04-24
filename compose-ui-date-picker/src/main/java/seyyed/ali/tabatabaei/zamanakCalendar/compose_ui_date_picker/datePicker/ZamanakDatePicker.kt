package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.datePicker


import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.R
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.WheelPicker
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model.MonthFormat
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model.ZamanakDatePickerConfig
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.theme.ZamanakCalendarDatePickerTheme
import seyyed.ali.tabatabaei.zamanakCalendar.core.ZamanakCore
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.GregorianDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.HijriDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.JalaliDate
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.CalendarType
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.Language

@Composable
fun ZamanakDatePicker(
    config: ZamanakDatePickerConfig = ZamanakDatePickerConfig(),
    dateSelected: (ZamanakCore) -> Unit
) {
    val yearList = (config.minYear..config.maxYear).toList()
    val monthNumberList = (1..12).toList()
    var dayList by remember { mutableStateOf((1..30).toList()) }

    val defaultDate = config.defaultDate
    val monthNameList = getMonthNameList(config)

    var yearSelected by remember { mutableIntStateOf(yearList.indexOf(getYearFromCore(defaultDate, config.calendarType))) }
    var monthSelected by remember { mutableIntStateOf(monthNumberList.indexOf(getMonthFromCore(defaultDate, config.calendarType))) }
    var daySelected by remember { mutableIntStateOf(dayList.indexOf(getDayFromCore(defaultDate, config.calendarType))) }

    LaunchedEffect(yearSelected, monthSelected) {
        val year = yearList.getOrNull(yearSelected) ?: config.minYear
        val month = monthNumberList.getOrNull(monthSelected) ?: 1
        val daysInMonth = getDaysInMonth(config.calendarType, defaultDate, year, month)

        if (dayList.size != daysInMonth) {
            dayList = (1..daysInMonth).toList()
        }
    }

    LaunchedEffect(yearSelected, monthSelected, daySelected) {
        val year = yearList.getOrNull(yearSelected) ?: config.minYear
        val month = monthNumberList.getOrNull(monthSelected) ?: 1
        val day = dayList.getOrNull(daySelected) ?: 1

        val selectedDate = ZamanakCore().apply {
            when (config.calendarType) {
                CalendarType.Gregorian -> setDateFromGregorian(GregorianDate(year, month, day))
                CalendarType.Jalali -> setDateFromJalali(JalaliDate(year, month, day))
                CalendarType.Hijri -> setDateFromHijri(HijriDate(year, month, day))
            }
        }
        dateSelected(selectedDate)
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val pickerModifier = Modifier.weight(1f)
            val visibleItemsCount = config.unfocusedCount * 2 + 1

            if (config.showYearPicker) {
                WheelPicker(
                    modifier = pickerModifier,
                    items = yearList,
                    initialSelectedIndex = yearSelected,
                    visibleItemsCount = visibleItemsCount,
                    maxRotation = config.maxRotation,
                    maxFontSize = config.maxFontSize,
                    minFontSize = config.minFontSize,
                    fontFamily = config.fontFamily,
                    textColor = config.textColor,
                    textColorSelected = config.textColorSelected,
                    itemHeight = config.itemHeight,
                    focusBackground = config.focusBackground,
                    onItemSelected = { _, pos -> yearSelected = pos ?: 0 }
                )
            }

            if (config.showMonthPicker) {
                val monthItems = when (config.monthFormat) {
                    MonthFormat.NUMBER -> monthNumberList
                    MonthFormat.NAME -> monthNameList.toList()
                    MonthFormat.NUMBER_NAME -> monthNumberList.map {
                        "${monthNameList.getOrNull(it - 1) ?: ""} ($it)"
                    }
                }
                WheelPicker(
                    modifier = pickerModifier,
                    items = monthItems,
                    initialSelectedIndex = monthSelected,
                    visibleItemsCount = visibleItemsCount,
                    maxRotation = config.maxRotation,
                    maxFontSize = config.maxFontSize,
                    minFontSize = config.minFontSize,
                    fontFamily = config.fontFamily,
                    textColor = config.textColor,
                    textColorSelected = config.textColorSelected,
                    itemHeight = config.itemHeight,
                    focusBackground = config.focusBackground,
                    onItemSelected = { _, pos -> monthSelected = pos ?: 0 }
                )
            }

            if (config.showDayOfMonthPicker) {
                WheelPicker(
                    modifier = pickerModifier,
                    items = dayList,
                    initialSelectedIndex = daySelected,
                    visibleItemsCount = visibleItemsCount,
                    maxRotation = config.maxRotation,
                    maxFontSize = config.maxFontSize,
                    minFontSize = config.minFontSize,
                    fontFamily = config.fontFamily,
                    textColor = config.textColor,
                    textColorSelected = config.textColorSelected,
                    itemHeight = config.itemHeight,
                    focusBackground = config.focusBackground,
                    onItemSelected = { _, pos -> daySelected = pos ?: 0 }
                )
            }
        }
    }
}

@Composable
private fun getMonthNameList(config: ZamanakDatePickerConfig) =
    when (config.calendarType) {
        CalendarType.Gregorian -> when (config.language) {
            Language.ENGLISH -> stringArrayResource(R.array.gregorian_english_months)
            Language.PERSIAN -> stringArrayResource(R.array.gregorian_persian_months)
            Language.ARABIC -> stringArrayResource(R.array.gregorian_arabic_months)
        }

        CalendarType.Jalali -> when (config.language) {
            Language.ENGLISH -> stringArrayResource(R.array.jalali_english_months)
            Language.PERSIAN -> stringArrayResource(R.array.jalali_persian_months)
            Language.ARABIC -> stringArrayResource(R.array.jalali_arabic_months)
        }

        CalendarType.Hijri -> when (config.language) {
            Language.ENGLISH -> stringArrayResource(R.array.hijri_english_months)
            Language.PERSIAN -> stringArrayResource(R.array.hijri_persian_months)
            Language.ARABIC -> stringArrayResource(R.array.hijri_arabic_months)
        }
    }

private fun getYearFromCore(core: ZamanakCore, type: CalendarType) = when (type) {
    CalendarType.Gregorian -> core.gregorianDate.year
    CalendarType.Jalali -> core.jalaliDate.year
    CalendarType.Hijri -> core.hijriDate.year
}

private fun getMonthFromCore(core: ZamanakCore, type: CalendarType) = when (type) {
    CalendarType.Gregorian -> core.gregorianDate.month
    CalendarType.Jalali -> core.jalaliDate.month
    CalendarType.Hijri -> core.hijriDate.month
}

private fun getDayFromCore(core: ZamanakCore, type: CalendarType) = when (type) {
    CalendarType.Gregorian -> core.gregorianDate.dayOfMonth
    CalendarType.Jalali -> core.jalaliDate.dayOfMonth
    CalendarType.Hijri -> core.hijriDate.dayOfMonth
}

private fun getDaysInMonth(
    type: CalendarType,
    core: ZamanakCore,
    year: Int,
    month: Int
): Int {
    val updated = when (type) {
        CalendarType.Gregorian -> core.setDateFromGregorian(core.gregorianDate.copy(year = year, month = month))
        CalendarType.Jalali -> core.setDateFromJalali(core.jalaliDate.copy(year = year, month = month))
        CalendarType.Hijri -> core.setDateFromHijri(core.hijriDate.copy(year = year, month = month))
    }

    return when (type) {
        CalendarType.Gregorian -> updated.gregorianDate.getDaysInMonth()
        CalendarType.Jalali -> updated.jalaliDate.getDaysInMonth()
        CalendarType.Hijri -> updated.hijriDate.getDaysInMonth()
    }
}

@Preview(showBackground = true , showSystemUi = true)
@Composable
private fun ZamanakTimePickerPreview() {
    ZamanakCalendarDatePickerTheme {
        ZamanakDatePicker(){

        }
    }
}