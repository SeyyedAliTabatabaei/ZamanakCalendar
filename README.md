# 📅 Zamanak Calendar Library
**Latest Version:** [![](https://jitpack.io/v/SeyyedAliTabatabaei/ZamanakCalendar.svg)](https://jitpack.io/#SeyyedAliTabatabaei/ZamanakCalendar)

**License:** [Apache 2.0](https://opensource.org/licenses/Apache-2.0)

**Zamanak Calendar** is a powerful date and time library for Android with full support for **Jalali (Persian)**, **Hijri (Islamic)**, and **Gregorian** calendars. It provides both core functionality and a modern Jetpack Compose UI for seamless date and time selection.

---

## 📦 Modules

### 1. [Core Module](https://github.com/SeyyedAliTabatabaei/ZamanakCalendar/tree/main/core)

Lightweight Kotlin core for date manipulation and conversion.

#### Features:
- Jalali, Hijri, and Gregorian support
- Date conversion and arithmetic
- Format and comparison utilities
- No external dependencies

#### Installation:
```gradle
dependencies {
    implementation 'com.github.SeyyedAliTabatabaei.ZamanakCalendar:core:[Latest Version]'
}
```

#### Example:
```kotlin
val calendar = ZamanakCore()
calendar.setDateFromJalali(JalaliDate(1403, 2, 22))
val formatted = calendar.jalaliDate.format(DateFormat.SHORT, Language.FARSI)
```

---

### 2. [Compose UI Date Picker Module](https://github.com/SeyyedAliTabatabaei/ZamanakCalendar/tree/main/compose-ui-date-picker)

Jetpack Compose-based customizable UI components for date/time picking.

#### Features:
- Calendar support: Jalali, Hijri, Gregorian
- Elegant, responsive, and themeable UI
- Localized labels and texts
- Compose-state friendly

#### Installation:
```gradle
dependencies {
    implementation 'com.github.SeyyedAliTabatabaei.ZamanakCalendar:compose-ui-date-picker:[Latest Version]'
}
```

#### Example:
```kotlin
ZamanakDatePicker(
    config = ZamanakDatePickerConfig(
        calendarType = CalendarType.Jalali,
        language = Language.FARSI
    )
) { selectedDate ->
    Log.d("TAG", "Selected: $selectedDate")
}
```

---

## 🛠 Setup

Add JitPack to your root `settings.gradle`:
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

---

## 👤 Author

**Seyyed Ali Tabatabaei**  
📧 [SeyyedAliTabatabaei7@gmail.com](mailto:SeyyedAliTabatabaei7@gmail.com)  
🌐 [GitHub Profile](https://github.com/SeyyedAliTabatabaei)

---

🌟 Star this repo to support the project!  
🐛 Found a bug? [Open an issue](https://github.com/SeyyedAliTabatabaei/ZamanakCalendar/issues)