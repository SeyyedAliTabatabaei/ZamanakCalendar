package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.timePicker

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.sp
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.WheelPicker
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model.ZamanakTimePickerConfig
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.theme.ZamanakCalendarDatePickerTheme
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.Clock
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.HourType

@Composable
fun ZamanakTimePicker(
    config: ZamanakTimePickerConfig = ZamanakTimePickerConfig(),
    timeSelected: (clock: Clock) -> Unit
) {
    val hourList = if (config.is24HourFormat) (0..23).toList() else (1..12).toList()
    val minuteList = (0..59).toList()
    val secondList = (0..59).toList()

    var hourSelected by remember { mutableIntStateOf(
        if (config.is24HourFormat) config.defaultClock.hour else config.defaultClock.formatHour12-1
    ) }
    var minuteSelected by remember { mutableIntStateOf(config.defaultClock.minute) }
    var secondSelected by remember { mutableIntStateOf(config.defaultClock.second) }
    var isAm by remember { mutableStateOf(
        if (config.is24HourFormat) true else config.defaultClock.getHourType() == HourType.AM
    ) }

    LaunchedEffect(hourSelected, minuteSelected, secondSelected, isAm) {
        val finalHour = if (config.is24HourFormat) {
            hourSelected
        } else {
            when {
                hourSelected == 12 && isAm -> 0
                hourSelected == 12 && !isAm -> 12
                isAm -> hourSelected
                else -> hourSelected + 12
            }
        }
        timeSelected(Clock(finalHour, minuteSelected, secondSelected))
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            modifier = Modifier.fillMaxWidth() ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val pickerModifier = Modifier.weight(1f)
            val visibleItemsCount = config.unfocusedCount * 2 + 1

            if (config.showHourPicker) {
                WheelPicker(
                    modifier = pickerModifier,
                    items = hourList,
                    initialSelectedIndex = hourSelected,
                    visibleItemsCount = visibleItemsCount,
                    maxRotation = config.maxRotation,
                    maxFontSize = config.maxFontSize,
                    minFontSize = config.minFontSize,
                    fontFamily = config.fontFamily,
                    textColor = config.textColor,
                    textColorSelected = config.textColorSelected,
                    itemHeight = config.itemHeight,
                    focusBackground = config.focusBackground,
                    onItemSelected = { item, _ -> hourSelected = item ?: 0 }
                )
            }

            if (config.showMinutePicker) {
                Text(
                    text = ":" ,
                    style = TextStyle(
                        fontSize = config.maxFontSize.sp,
                        fontWeight = FontWeight.W900
                    ) ,
                    color = config.dotColor
                )

                WheelPicker(
                    modifier = pickerModifier,
                    items = minuteList,
                    initialSelectedIndex = minuteSelected,
                    visibleItemsCount = visibleItemsCount,
                    maxRotation = config.maxRotation,
                    maxFontSize = config.maxFontSize,
                    minFontSize = config.minFontSize,
                    fontFamily = config.fontFamily,
                    textColor = config.textColor,
                    textColorSelected = config.textColorSelected,
                    itemHeight = config.itemHeight,
                    focusBackground = config.focusBackground,
                    onItemSelected = { item, _ -> minuteSelected = item ?: 0 }
                )
            }

            if (config.showSecondPicker) {
                Text(
                    text = ":" ,
                    style = TextStyle(
                        fontSize = config.maxFontSize.sp,
                        fontWeight = FontWeight.W900
                    ) ,
                    color = config.dotColor
                )

                WheelPicker(
                    modifier = pickerModifier,
                    items = secondList,
                    initialSelectedIndex = secondSelected,
                    visibleItemsCount = visibleItemsCount,
                    maxRotation = config.maxRotation,
                    maxFontSize = config.maxFontSize,
                    minFontSize = config.minFontSize,
                    fontFamily = config.fontFamily,
                    textColor = config.textColor,
                    textColorSelected = config.textColorSelected,
                    itemHeight = config.itemHeight,
                    focusBackground = config.focusBackground,
                    onItemSelected = { item, _ -> secondSelected = item ?: 0 }
                )
            }

            if (!config.is24HourFormat) {
                WheelPicker(
                    modifier = pickerModifier,
                    items = listOf(config.amText , config.pmText),
                    initialSelectedIndex = if (isAm) 0 else 1,
                    visibleItemsCount = visibleItemsCount,
                    maxRotation = config.maxRotation,
                    maxFontSize = config.maxFontSize,
                    minFontSize = config.minFontSize,
                    fontFamily = config.fontFamily,
                    textColor =config. textColor,
                    textColorSelected = config.textColorSelected,
                    itemHeight = config.itemHeight,
                    focusBackground = config.focusBackground,
                    onItemSelected = { _ , posSelected ->
                        isAm = posSelected == 0
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true , showSystemUi = true)
@Composable
private fun ZamanakTimePickerPreview() {
    ZamanakCalendarDatePickerTheme {
        ZamanakTimePicker(){

        }
    }
}