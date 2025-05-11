package seyyed.ali.tabatabaei.zamanak

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import seyyed.ali.tabatabaei.zamanak.ui.theme.CustomFontFamily
import seyyed.ali.tabatabaei.zamanak.ui.theme.ZamanakTheme
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.datePicker.ZamanakDatePicker
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.datePicker.ZamanakDatePickerBottomSheet
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model.MonthFormat
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model.ZamanakDatePickerConfig
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.timePicker.ZamanakTimePicker
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.timePicker.ZamanakTimePickerBottomSheet
import seyyed.ali.tabatabaei.zamanakCalendar.compose_ui_date_picker.model.ZamanakTimePickerConfig
import seyyed.ali.tabatabaei.zamanakCalendar.core.ZamanakCore
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.Clock
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.CalendarType
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.ClockFormat
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.DateFormat
import seyyed.ali.tabatabaei.zamanakCalendar.core.model.enums.Language

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    ZamanakTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                DatePicker()
                TimePicker()
//                Main()
            }
        }
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker() {
    var showBottomSheet by remember { mutableStateOf(false) }
    if (showBottomSheet) {
        ZamanakTimePickerBottomSheet(
            config = ZamanakTimePickerConfig(
                defaultClock = Clock(20 , 55 , 30) ,
                focusBackground = null ,
                is24HourFormat = false ,
                minFontSize = 16f,
                maxFontSize = 16f,
                maxRotation = 0f ,
                unfocusedCount = 1 ,
            ),
            clockFormat = ClockFormat.H12_HMS_AMPM,
            onDismissBottomSheet = { showBottomSheet = false } ,
            onConfirm = {
                Log.i("TAG", "MyApp: $it")
            }
        )
    }

    Button(
        onClick = { showBottomSheet = true },
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White ,
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        Text(stringResource(R.string.time_picker) , style = MaterialTheme.typography.bodyMedium)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker() {
    var showBottomSheet by remember { mutableStateOf(false) }
    if (showBottomSheet) {
        ZamanakDatePickerBottomSheet(
            config = ZamanakDatePickerConfig(
                calendarType = CalendarType.Gregorian,
                language = Language.ENGLISH ,
            ),
            dateFormat = DateFormat.FULL,
            language = Language.ENGLISH,
            onDismissBottomSheet = { showBottomSheet = false } ,
            onConfirm = {
                Log.i("TAG", "MyApp: $it")
            }
        )
    }

    Button(
        onClick = { showBottomSheet = true },
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White ,
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        Text(stringResource(R.string.date_picker) , style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun Main() {
    val zamanakCore = remember { mutableStateOf(ZamanakCore()) }
    val formatDate = remember { mutableStateOf(DateFormat.FULL) }
    val formatClock = remember { mutableStateOf(ClockFormat.H24_HMS) }

    LaunchedEffect(Unit) {
        while (true){
            zamanakCore.value = ZamanakCore()
            delay(1000)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(20.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize() ,
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier.fillMaxWidth() ,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = zamanakCore.value.jalaliDate.format(formatDate.value) ,
                    style = MaterialTheme.typography.bodyMedium ,
                )

                Text(
                    text = "تاریخ جلالی" ,
                    style = MaterialTheme.typography.bodyMedium ,
                )
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp) ,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = zamanakCore.value.gregorianDate.format(formatDate.value) ,
                    style = MaterialTheme.typography.bodyMedium ,
                )

                Text(
                    text = "تاریخ میلادی" ,
                    style = MaterialTheme.typography.bodyMedium ,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp) ,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = zamanakCore.value.clock.format(formatClock.value) ,
                    style = MaterialTheme.typography.bodyMedium ,
                )

                Text(
                    text = "ساعت" ,
                    style = MaterialTheme.typography.bodyMedium ,
                )
            }


            FormatShowDate(formatDate = formatDate)
            FormatShowClock(formatClock = formatClock)



        }
    }


}

@Composable
fun FormatShowDate(formatDate : MutableState<DateFormat>) {
    Text(
        text = "فرمت نمایش تاریخ" ,
        style = MaterialTheme.typography.bodyMedium ,
        modifier = Modifier.padding(top = 25.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth() ,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        CustomRadioButton(title = "کامل", selected = formatDate.value == DateFormat.FULL) {
            formatDate.value = DateFormat.FULL
        }

        CustomRadioButton(title = "کوتاه", selected = formatDate.value == DateFormat.SHORT) {
            formatDate.value = DateFormat.SHORT
        }

        CustomRadioButton(title = "روز ماه", selected = formatDate.value == DateFormat.DAY_MONTH) {
            formatDate.value = DateFormat.DAY_MONTH
        }
    }
}

@Composable
fun FormatShowClock(formatClock : MutableState<ClockFormat>) {
    Text(
        text = "فرمت نمایش ساعت" ,
        style = MaterialTheme.typography.bodyMedium ,
        modifier = Modifier.padding(top = 25.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth() ,
    ) {
        CustomRadioButton(title = "۲۴ساعت - ساعت و دقیقه", selected = formatClock.value == ClockFormat.H24_HM) {
            formatClock.value = ClockFormat.H24_HM
        }

        CustomRadioButton(title = "۲۴ساعت - ساعت و دقیقه و ثانیه", selected = formatClock.value == ClockFormat.H24_HMS) {
            formatClock.value = ClockFormat.H24_HMS
        }

        CustomRadioButton(title = "۱۲ساعت - ساعت و دقیقه", selected = formatClock.value == ClockFormat.H12_HM) {
            formatClock.value = ClockFormat.H12_HM
        }

        CustomRadioButton(title = "۱۲ساعت - ساعت و دقیقه به همراه AM/PM", selected = formatClock.value == ClockFormat.H12_HM_AMPM) {
            formatClock.value = ClockFormat.H12_HM_AMPM
        }

        CustomRadioButton(title = "۱۲ساعت - ساعت و دقیقه و ثانیه", selected = formatClock.value == ClockFormat.H12_HMS) {
            formatClock.value = ClockFormat.H12_HMS
        }

        CustomRadioButton(title = "۱۲ساعت - ساعت و دقیقه و ثانیه به همراه AM/PM", selected = formatClock.value == ClockFormat.H12_HMS_AMPM) {
            formatClock.value = ClockFormat.H12_HMS_AMPM
        }

        CustomRadioButton(title = "۱۲ساعت - ساعت به همراه AM/PM", selected = formatClock.value == ClockFormat.H12_H_AMPM) {
            formatClock.value = ClockFormat.H12_H_AMPM
        }

        CustomRadioButton(title = "۱۲ساعت کامپکت", selected = formatClock.value == ClockFormat.H24_COMPACT) {
            formatClock.value = ClockFormat.H24_COMPACT
        }
    }

}

@Composable
fun CustomRadioButton(title : String , selected : Boolean , onClick : () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically ,
        modifier = Modifier.height(35.dp)
    ) {
        RadioButton(selected = selected , onClick = onClick)
        Text(text = title , style = MaterialTheme.typography.bodySmall ,)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}