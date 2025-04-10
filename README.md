# 📅 Zamanak Calendar Library 
**Latest Version:** [![Latest Version](https://img.shields.io/badge/version-1.0.0-blue)](https://github.com/SeyyedAliTabatabaei/ZamanakCalendar/releases/tag/ZamanakCalendar)

**License:** [Apache 2.0](https://opensource.org/licenses/Apache-2.0)  
<div align="left">
  <strong>Select Language:</strong> 
  <a href="#Persian">Persian</a> | 
  <a href="#English">English</a>
</div>



<h1 id="English">English</h1>

## Introduction
`ZamanakCalendar` is a useful library for managing Jalali and Gregorian dates in Android applications. This library allows you to easily design and implement your applications using Jalali and Gregorian dates. The features of this library include date conversion, calculating the remaining days in the year, checking leap years, and formatting dates. This library also allows users to select the application language between Persian and English to provide a better user experience for all users.

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
    implementation 'com.github.SeyyedAliTabatabaei:ZamanakCalendar:[Latest Version]'
}
```

## Usage
```kotlin
val calendar = ZamanakCalendar() // Create an instance
// Set Jalali date
calendar.setDateFromJalali(JalaliDate(1402, 5, 15))
// Date calculations
calendar.addDate(CalendarType.Jalali, TimeUnitType.DAY, 10)
// Formatting output
println(calendar.jalaliDate.format(DateFormat.FULL, Language.ENGLISH))
```

## Methods and Features
### ZamanakCalendar Class
| Method | Description |
|--------|-------------|
| `isToday()` | Checks if the current date is equal to today's date. |
| `toMillis()` | Converts the date and time to milliseconds. |
| `getStartOfDay()` | Sets the time to 00:00:00. |
| `getEndOfDay()` | Sets the time to 23:59:59. |
| `setClock(clock: Clock)` | Sets the clock (time). |
| `setDateFromJalali(jalaliDate: JalaliDate)` | Sets the date using a Jalali date. |
| `setDateFromGregorian(gregorianDate: GregorianDate)` | Sets the date using a Gregorian date. |
| `setDateFromTimeInMillis(time: Long)` | Sets the date from a time in milliseconds. |
| `setTimeZone(timeZone: TimeZone)` | Sets the time zone. |
| `getTimeZone()` | Gets the current time zone. |
| `addDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count: Int)` | Adds a time value to the date. |
| `subDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count: Int)` | Subtracts a time value from the date. |
| `isBefore(zamanakCalendar: ZamanakCalendar)` | Checks if the current date is before another date. |
| `isAfter(zamanakCalendar: ZamanakCalendar)` | Checks if the current date is after another date. |
| `equalsDate(date: ZamanakCalendar)` | Checks if the current date equals another date. |
| `getWeekdaysList()` | Returns the list of weekdays (Saturday to Friday). |
| `getMonthDaysList(calendarType: CalendarType)` | Returns the list of days in the current month. |

### JalaliDate and GregorianDate Classes
| Method | Description |
|--------|-------------|
| `isLeapYear()` | Checks if the year is a leap year. |
| `getDaysRemainingInYear()` | Calculates the number of remaining days in the year. |
| `getDayInYear()` | Returns the day number in the current year. |
| `getNumberOfDaysInYear()` | Returns the number of days in the year. |
| `getMonthName(language: Language = Language.PERSIAN)` | Returns the month name based on the selected language. |
| `getMonthNumber()` | Returns the month number (1-12). |
| `getQuarter(language: Language = Language.PERSIAN)` | Returns the name of the current quarter based on the selected language. |
| `getQuarterNumber()` | Returns the quarter number (1-4). |
| `getWeekNumberOfYear()` | Returns the week number in the year. |
| `getDaysInMonth()` | Returns the number of days in the current month. |
| `getWeekdayName(language: Language = Language.PERSIAN)` | Returns the name of the weekday based on the selected language. |
| `getWeekdayNumber()` | Returns the weekday number (1-7, where 1 is Saturday). |
| `format(dateFormat: DateFormat, language: Language = Language.PERSIAN)` | Formats the current date based on the format and language. |
| `getDailyEvent()` | Returns the list of daily events for the current date. |
| `getMonthlyEvent()` | Returns the list of monthly events for the current month. |
| `getMonthlyHolidays()` | Returns the list of monthly holidays for the current month. |
| `isHoliday()` | Checks if the current date is a holiday. |

### Clock Class
| Method | Description |
|--------|-------------|
| `formatHour12` | Returns the time in 12-hour format. |
| `getHourType()` | Returns the type of hour (AM or PM). |
| `getTotalSecond()` | Calculates the total number of seconds. |
| `format(clockFormat: ClockFormat, amText: String, pmText: String)` | Formats the time based on different formats. |

---

<div align="left">
    <h2>📬 Contact Developer</h2>
    <p>
        ✉️ <a href="mailto:SeyyedAliTabatabaei7@gmail.com">Email</a> |
        💻 <a href="https://github.com/SeyyedAliTabatabaei">GitHub</a>
    </p>
    <p>
        <img src="https://img.shields.io/badge/Star_on_GitHub-🌟-yellow" alt="Star">
        <img src="https://img.shields.io/badge/Report_Issues-🐞-red" alt="Issues">
    </p>
</div>

-----

<h1 id="Persian">فارسی</h1>

## معرفی

`ZamanakCalendar` یک کتابخانه مفید برای مدیریت تاریخ‌های جلالی و میلادی در برنامه‌های اندروید است. این کتابخانه به شما این امکان را می‌دهد تا با استفاده از تاریخ‌های جلالی و میلادی، اپلیکیشن‌های خود را به راحتی طراحی و پیاده‌سازی کنید. امکانات این کتابخانه شامل تبدیل تاریخ‌ها، محاسبه روزهای باقی‌مانده در سال، بررسی سال کبیسه و فرمت‌دهی تاریخ‌ها می‌باشد. این کتابخانه همچنین به کاربران اجازه می‌دهد تا زبان برنامه را بین فارسی و انگلیسی انتخاب کنند تا تجربه کاربری بهتری برای تمامی کاربران فراهم شود.


## نصب

برای اضافه کردن این کتابخانه به پروژه خود، ابتدا تنظیمات زیر را در فایل `settings.gradle` اضافه کنید:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

سپس، کتابخانه را به وابستگی‌های پروژه خود اضافه کنید:

```groovy
dependencies {
    implementation 'com.github.SeyyedAliTabatabaei:ZamanakCalendar:[Latest Version]'
}
```



## نحوه استفاده

بعد از اضافه کردن لایبرری، برای استفاده از آن، ابتدا یک اینستنس از کلاس `ZamanakCalendar` ایجاد کنید:

```kotlin
val calendar = ZamanakCalendar() // ایجاد نمونه
// تنظیم تاریخ جلالی
calendar.setDateFromJalali(JalaliDate(1402, 5, 15))
// محاسبات تاریخی
calendar.addDate(CalendarType.Jalali, TimeUnitType.DAY, 10)
// فرمت‌بندی خروجی
println(calendar.jalaliDate.format(DateFormat.FULL, Language.PERSIAN))
```

## متدها و ویژگی‌ها

### کلاس `ZamanakCalendar`
| متد | توضیحات |
|------|--------|
| `isToday()` | بررسی می‌کند که آیا تاریخ فعلی برابر با تاریخ امروز هست یا نه. |
| `toMillis()` | تبدیل تاریخ و زمان به میلی‌ثانیه. |
| `getStartOfDay()` | تنظیم زمان به ساعت ۰۰:۰۰:۰۰ (شروع روز). |
| `getEndOfDay()` | تنظیم زمان به ساعت ۲۳:۵۹:۵۹ (پایان روز). |
| `setClock(clock: Clock)` | تنظیم ساعت (زمان). |
| `setDateFromJalali(jalaliDate: JalaliDate)` | تنظیم تاریخ با استفاده از تاریخ جلالی. |
| `setDateFromGregorian(gregorianDate: GregorianDate)` | تنظیم تاریخ با استفاده از تاریخ میلادی. |
| `setDateFromTimeInMillis(time: Long)` | تنظیم تاریخ از طریق زمان به میلی‌ثانیه. |
| `setTimeZone(timeZone: TimeZone)` | تنظیم منطقه زمانی. |
| `getTimeZone()` | دریافت منطقه زمانی فعلی. |
| `addDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count: Int)` | افزودن مقدار زمانی به تاریخ. |
| `subDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count: Int)` | کم کردن مقدار زمانی از تاریخ. |
| `isBefore(zamanakCalendar: ZamanakCalendar)` | بررسی می‌کند که آیا تاریخ فعلی قبل از تاریخ مشخص‌شده است یا نه. |
| `isAfter(zamanakCalendar: ZamanakCalendar)` | بررسی می‌کند که آیا تاریخ فعلی بعد از تاریخ مشخص‌شده است یا نه. |
| `equalsDate(date: ZamanakCalendar)` | بررسی می‌کند که آیا تاریخ فعلی با تاریخ مشخص‌شده برابر است یا نه. |
| `getWeekdaysList()` | لیست روزهای هفته را برمی‌گرداند (شنبه تا جمعه). |
| `getMonthDaysList(calendarType: CalendarType)` | لیست روزهای ماه جاری را برمی‌گرداند. |

### کلاس `JalaliDate` و `GregorianDate`
| متد | توضیحات |
|------|--------|
| `isLeapYear()` | بررسی می‌کند که آیا سال کبیسه است یا نه. |
| `getDaysRemainingInYear()` | تعداد روزهای باقی‌مانده تا پایان سال را محاسبه می‌کند. |
| `getDayInYear()` | شماره روز جاری در سال را برمی‌گرداند. |
| `getNumberOfDaysInYear()` | تعداد کل روزهای سال را برمی‌گرداند. |
| `getMonthName(language: Language = Language.PERSIAN)` | نام ماه جاری را با توجه به زبان انتخاب‌شده برمی‌گرداند. |
| `getMonthNumber()` | شماره ماه جاری را برمی‌گرداند (۱ تا ۱۲). |
| `getQuarter(language: Language = Language.PERSIAN)` | نام فصل جاری را با توجه به زبان انتخاب‌شده برمی‌گرداند. |
| `getQuarterNumber()` | شماره فصل جاری را برمی‌گرداند (۱ تا ۴). |
| `getWeekNumberOfYear()` | شماره هفته جاری در سال را برمی‌گرداند. |
| `getDaysInMonth()` | تعداد روزهای ماه جاری را برمی‌گرداند. |
| `getWeekdayName(language: Language = Language.PERSIAN)` | نام روز هفته را با توجه به زبان انتخاب‌شده برمی‌گرداند. |
| `getWeekdayNumber()` | شماره روز هفته را برمی‌گرداند (۱ تا ۷، که ۱ معادل شنبه است). |
| `format(dateFormat: DateFormat, language: Language = Language.PERSIAN)` | تاریخ جاری را بر اساس فرمت و زبان مشخص‌شده قالب‌بندی می‌کند. |
| `getDailyEvent()` | لیست رویدادهای روز جاری را برمی‌گرداند. |
| `getMonthlyEvent()` | لیست رویدادهای ماه جاری را برمی‌گرداند. |
| `getMonthlyHolidays()` | لیست تعطیلات ماه جاری را برمی‌گرداند. |
| `isHoliday()` | بررسی می‌کند که آیا تاریخ جاری یک روز تعطیل است یا نه. |

### کلاس `Clock`

| متد | توضیحات |
|-----|---------|
| `formatHour12` | ساعت را در فرمت 12 ساعته باز می‌گرداند. |
| `getHourType()` | نوع ساعت (AM یا PM) را باز می‌گرداند. |
| `getTotalSecond()` | کل ثانیه‌ها را محاسبه می‌کند. |
| `format(clockFormat: ClockFormat, amText: String, pmText: String)` | زمان را در فرمت‌های مختلف باز می‌گرداند. |

---

<div align="right">
    <h2>📬 تماس با توسعه‌دهنده</h2>
    <p>
        ✉️ <a href="mailto:SeyyedAliTabatabaei7@gmail.com">ایمیل</a> |
        💻 <a href="https://github.com/SeyyedAliTabatabaei">گیت‌هاب</a>
    </p>
    <p>
        <img src="https://img.shields.io/badge/Star_on_GitHub-🌟-yellow" alt="Star">
        <img src="https://img.shields.io/badge/Report_Issues-🐞-red" alt="Issues">
    </p>
</div>
