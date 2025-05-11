# 📅 Zamanak Calendar Library - Core Module
**Latest Version:** [![](https://jitpack.io/v/SeyyedAliTabatabaei/ZamanakCalendar.svg)](https://jitpack.io/#SeyyedAliTabatabaei/ZamanakCalendar)

**License:** [Apache 2.0](https://opensource.org/licenses/Apache-2.0)  

## Introduction
`zamanak-calendar-core` is the core module of the ZamanakCalendar library. It provides robust and easy-to-use utilities for working with Jalali (Persian), Hijri (Islamic), and Gregorian dates in Android applications.

✨ Features
•	Full support for **Jalali**, **Hijri**, and **Gregorian** calendars
•	Date conversion between calendar systems
•	Date comparison, formatting, and basic operations
•	Lightweight and dependency-free core logic
•	Written in Kotlin with clean and maintainable code


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
    implementation 'com.github.SeyyedAliTabatabaei.ZamanakCalendar:core:[Latest Version]'
}
```

## Usage
```kotlin
val calendar = ZamanakCore() // Create an instance
// Set Jalali date
calendar.setDateFromJalali(JalaliDate(1402, 5, 15))
// Date calculations
calendar.addDate(CalendarType.Jalali, TimeUnitType.DAY, 10)
// Formatting output
println(calendar.jalaliDate.format(DateFormat.FULL, Language.ENGLISH))
```

## Methods and Features
### ZamanakCalendar Class
| Method                                                                        | Description                                          |
|-------------------------------------------------------------------------------|------------------------------------------------------|
| `isToday()`                                                                   | Checks if the current date is equal to today's date. |
| `toMillis()`                                                                  | Converts the date and time to milliseconds.          |
| `getStartOfDay()`                                                             | Sets the time to 00:00:00.                           |
| `getEndOfDay()`                                                               | Sets the time to 23:59:59.                           |
| `setClock(clock: Clock)`                                                      | Sets the clock (time).                               |
| `setDateFromJalali(jalaliDate: JalaliDate)`                                   | Sets the date using a Jalali date.                   |
| `setDateFromGregorian(gregorianDate: GregorianDate)`                          | Sets the date using a Gregorian date.                |
| `setDateFromTimeInMillis(time: Long)`                                         | Sets the date from a time in milliseconds.           |
| `setTimeZone(timeZone: TimeZone)`                                             | Sets the time zone.                                  |
| `getTimeZone()`                                                               | Gets the current time zone.                          |
| `addDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count: Int)` | Adds a time value to the date.                       |
| `subDate(calendarType: CalendarType, timeUnitType: TimeUnitType, count: Int)` | Subtracts a time value from the date.                |
| `isBefore(zamanakCalendar: ZamanakCalendar)`                                  | Checks if the current date is before another date.   |
| `isAfter(zamanakCalendar: ZamanakCalendar)`                                   | Checks if the current date is after another date.    |
| `equalsDate(date: ZamanakCalendar)`                                           | Checks if the current date equals another date.      |
| `getWeekdaysList()`                                                           | Returns the list of weekdays (Saturday to Friday).   |
| `getMonthDaysList(calendarType: CalendarType)`                                | Returns the list of days in the current month.       |

### JalaliDate, HijriDate and GregorianDate Classes
| Method                                                                  | Description                                                             |
|-------------------------------------------------------------------------|-------------------------------------------------------------------------|
| `isLeapYear()`                                                          | Checks if the year is a leap year.                                      |
| `getDaysRemainingInYear()`                                              | Calculates the number of remaining days in the year.                    |
| `getDayInYear()`                                                        | Returns the day number in the current year.                             |
| `getNumberOfDaysInYear()`                                               | Returns the number of days in the year.                                 |
| `getMonthName(language: Language = Language.PERSIAN)`                   | Returns the month name based on the selected language.                  |
| `getMonthNumber()`                                                      | Returns the month number (1-12).                                        |
| `getQuarter(language: Language = Language.PERSIAN)`                     | Returns the name of the current quarter based on the selected language. |
| `getQuarterNumber()`                                                    | Returns the quarter number (1-4).                                       |
| `getWeekNumberOfYear()`                                                 | Returns the week number in the year.                                    |
| `getDaysInMonth()`                                                      | Returns the number of days in the current month.                        |
| `getWeekdayName(language: Language = Language.PERSIAN)`                 | Returns the name of the weekday based on the selected language.         |
| `getWeekdayNumber()`                                                    | Returns the weekday number (1-7, where 1 is Saturday).                  |
| `format(dateFormat: DateFormat, language: Language = Language.PERSIAN)` | Formats the current date based on the format and language.              |
| `getDailyEvent()`                                                       | Returns the list of daily events for the current date.                  |
| `getMonthlyEvent()`                                                     | Returns the list of monthly events for the current month.               |
| `getMonthlyHolidays()`                                                  | Returns the list of monthly holidays for the current month.             |
| `isHoliday()`                                                           | Checks if the current date is a holiday.                                |

### Clock Class
| Method                                                             | Description                                  |
|--------------------------------------------------------------------|----------------------------------------------|
| `formatHour12`                                                     | Returns the time in 12-hour format.          |
| `getHourType()`                                                    | Returns the type of hour (AM or PM).         |
| `getTotalSecond()`                                                 | Calculates the total number of seconds.      |
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