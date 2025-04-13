package seyyed.ali.tabatabaei.zamanak

import android.os.Bundle
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import seyyed.ali.tabatabaei.zamanak.ui.theme.ZamanakTheme
import seyyed.ali.tabatabaei.zamanakCore.ZamanakCore
import seyyed.ali.tabatabaei.zamanakCore.model.enums.ClockFormat
import seyyed.ali.tabatabaei.zamanakCore.model.enums.DateFormat

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        ZamanakTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Main()
            }
        }
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