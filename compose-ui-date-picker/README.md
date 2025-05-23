# 📅 Zamanak Calendar Library - Date Picker Module
**Latest Version:** [![](https://jitpack.io/v/SeyyedAliTabatabaei/ZamanakCalendar.svg)](https://jitpack.io/#SeyyedAliTabatabaei/ZamanakCalendar)

**License:** [Apache 2.0](https://opensource.org/licenses/Apache-2.0)  

## Screen Shots
<p align="center">
  <img src="ScreenShots/shot1.png" width="24%" style="margin:15px"/>
  <img src="ScreenShots/shot2.png" width="24%" style="margin:15px"/>
  <img src="ScreenShots/shot3.png" width="24%" style="margin:15px"/>
  <img src="ScreenShots/shot4.png" width="24%" style="margin:15px"/>
</p>

## Introduction
`compose-ui-date-picker` is a modern, flexible, and fully customizable date and time picker built with Jetpack Compose, designed to support **Jalali (Persian)**, **Hijri (Islamic)**, and **Gregorian** calendars. It works seamlessly with the zamanak-calendar-core module.

**✨Features**
-	Elegant and user-friendly **date & time selection UI**
-	Supports **Jalali**, **Hijri**, and **Gregorian** calendar systems
-	Built entirely using **Jetpack Compose**
-	Fully **customizable appearance** to match your app’s theme
-	Easy integration with Compose State, ViewModel, or remember patterns
-	Localized texts and labels for better UX


## Installation
To add this library to your project, first, add the following settings to your `settings.gradle` file:
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Then, add the library to your project dependencies:
```groovy
dependencies {
    implementation 'com.github.SeyyedAliTabatabaei.ZamanakCalendar:compose-ui-date-picker:[Latest Version]'
}
```

# Time Picker
## Usage - Time Picker
```kotlin
// Time Picker
ZamanakTimePicker(
    config = ZamanakTimePickerConfig(
        is24HourFormat = false,
        defaultClock = Clock(10, 30, 0)
    )
) {  selectedClock ->
    // Handle selected time (Clock object with hour, minute, second)
    Log.i("TAG", "Selected time: $selectedClock")
}

// Time Picker shown as a Bottom Sheet Dialog
var isShowBottomSheet by remember { mutableStateOf(false) }
if (isShowBottomSheet) {
    ZamanakTimePickerBottomSheet(
        config = ZamanakTimePickerConfig(
            defaultClock = Clock(20 , 55 , 30) ,
            focusBackground = null ,
        ),
        onDismissBottomSheet = { isShowBottomSheet = false } ,
        onConfirm = {  selectedClock ->
            // Handle selected time (Clock object with hour, minute, second)
            Log.i("TAG", "Selected time: $selectedClock")
        }
    )
}
```

## Methods and Features
### ZamanakTimePicker Composable
| Parameter                         | Description                                                                   |
|-----------------------------------|-------------------------------------------------------------------------------|
| `config: ZamanakTimePickerConfig` | Configuration object to customize appearance and behavior of the time picker. |
| `timeSelected: (Clock) -> Unit`   | Callback invoked with the selected time whenever user changes it.             |


### ZamanakTimePickerConfig Class
| Property            | Description                                                         |
|---------------------|---------------------------------------------------------------------|
| `unfocusedCount`    | Number of unfocused items to show above and below the selected one. |
| `maxRotation`       | Max rotation angle for items in the picker.                         |
| `maxFontSize`       | Maximum font size for the selected item.                            |
| `minFontSize`       | Minimum font size for unfocused items.                              |
| `fontFamily`        | Font family used for text rendering.                                |
| `textColor`         | Color of unfocused text items.                                      |
| `textColorSelected` | Color of the selected item text.                                    |
| `itemHeight`        | Height of each item in the wheel.                                   |
| `showHourPicker`    | Whether to display the hour picker.                                 |
| `showMinutePicker`  | Whether to display the minute picker.                               |
| `showSecondPicker`  | Whether to display the second picker.                               |
| `is24HourFormat`    | Enables 24-hour format if set to true, otherwise shows AM/PM mode.  |
| `amText, pmText`    | Customizable labels for AM/PM.                                      |
| `defaultClock`      | Default time shown when the picker is first displayed.              |
| `focusBackground`   | Optional background shown behind the selected row (highlight).      |

# Date Picker
## Usage - Date Picker
```kotlin
// Date Picker
ZamanakDatePicker(
    config = ZamanakDatePickerConfig(
        calendarType = CalendarType.Gregorian ,
        language = Language.ENGLISH
    )
) { selectedDate ->
    // Handle selected date (Zamanak Core object)
    Log.i("TAG", "Selected date: $selectedDate")
}

// Date Picker shown as a Bottom Sheet Dialog
var isShowBottomSheet by remember { mutableStateOf(false) }
if (isShowBottomSheet) {
    ZamanakDatePickerBottomSheet(
        config = ZamanakDatePickerConfig(
            calendarType = CalendarType.Gregorian,
            language = Language.ENGLISH ,
        ),
        dateFormat = DateFormat.FULL,
        language = Language.ENGLISH,
        onDismissBottomSheet = { showBottomSheet = false } ,
        onConfirm = { selectedDate ->
            // Handle selected date (Zamanak Core object)
            Log.i("TAG", "Selected date: $selectedDate")
        }
    )
}
```

## Methods and Features
### ZamanakDatePicker Composable
| Parameter                             | Description                                                                   |
|---------------------------------------|-------------------------------------------------------------------------------|
| `config: ZamanakDatePickerConfig`     | Configuration object to customize appearance and behavior of the date picker. |
| `dateSelected: (ZamanakCore) -> Unit` | Callback invoked with the selected date whenever user changes it.             |


### ZamanakDatePickerConfig Class
| Property               | Description                                                               |
|------------------------|---------------------------------------------------------------------------|
| `unfocusedCount`       | Number of unfocused items to show above and below the selected one.       |
| `maxRotation`          | Max rotation angle for items in the picker.                               |
| `maxFontSize`          | Maximum font size for the selected item.                                  |
| `minFontSize`          | Minimum font size for unfocused items.                                    |
| `fontFamily`           | Font family used for text rendering.                                      |
| `textColor`            | Color of unfocused text items.                                            |
| `textColorSelected`    | Color of the selected item text.                                          |
| `itemHeight`           | Height of each item in the wheel.                                         |
| `showYearPicker`       | Whether to display the year picker.                                       |
| `showMonthPicker`      | Whether to display the month picker.                                      |
| `showDayOfMonthPicker` | Whether to display the day picker.                                        |
| `calendarType`         | Type of calendar used (Jalali, Gregorian, Hijri).                         |
| `minYear`              | Minimum selectable year.                                                  |
| `maxYear`              | Maximum selectable year.                                                  |
| `defaultDate`          | The default date shown when the picker is opened.                         |
| `monthFormat`          | Format of month display (e.g. number and name).                           |
| `language`             | Language used for text (Persian, English, etc.).                          |
| `focusBackground`      | Optional background composable shown behind the selected row (highlight). |


---

## 👤 Author

**Seyyed Ali Tabatabaei**  
📧 [SeyyedAliTabatabaei7@gmail.com](mailto:SeyyedAliTabatabaei7@gmail.com)  
🌐 [GitHub Profile](https://github.com/SeyyedAliTabatabaei)

---

🌟 Star this repo to support the project!  
🐛 Found a bug? [Open an issue](https://github.com/SeyyedAliTabatabaei/ZamanakCalendar/issues)