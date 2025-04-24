package seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import seyyed.ali.tabatabaei.zamanakCalendar.core.ZamanakCore
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.Clock
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.CalendarType
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.Language


data class ZamanakDatePickerConfig(
    val unfocusedCount: Int = 2,
    val maxRotation: Float = 70f,
    val maxFontSize: Float = 20f,
    val minFontSize: Float = 12f,
    val fontFamily: FontFamily? = null,
    val textColor: Color = Color.Gray,
    val textColorSelected: Color = Color.Blue,
    val itemHeight: Dp = 30.dp,
    val showYearPicker: Boolean = true,
    val showMonthPicker: Boolean = true,
    val showDayOfMonthPicker: Boolean = true,
    val calendarType : CalendarType = CalendarType.Jalali ,
    val minYear : Int = 1342 ,
    val maxYear : Int = 1450 ,
    val defaultDate : ZamanakCore = ZamanakCore(),
    val monthFormat: MonthFormat = MonthFormat.NUMBER_NAME ,
    val language: Language = Language.PERSIAN ,
    val focusBackground: @Composable (() -> Unit?)? = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = 0.3f))
        )
    }
)
