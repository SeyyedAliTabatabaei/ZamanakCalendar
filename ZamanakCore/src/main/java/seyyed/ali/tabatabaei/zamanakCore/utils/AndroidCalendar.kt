package seyyed.ali.tabatabaei.zamanakCore.utils

import seyyed.ali.tabatabaei.zamanakCore.model.Clock
import seyyed.ali.tabatabaei.zamanakCore.model.GregorianDate
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

internal object AndroidCalendar {
    private val calendar = Calendar.getInstance()

    fun setTimeZone(timeZone: TimeZone){
        calendar.timeZone = timeZone
    }

    fun getTimeZone() : TimeZone{
        return calendar.timeZone
    }

    private fun setToCurrentTime() {
        calendar.time = Date()
    }

    fun getCurrentTimeInMillis() : Long {
        setToCurrentTime()
        return calendar.timeInMillis
    }

    fun convertTimeMillisToGregorianDate(time : Long) : Pair<GregorianDate , Clock> {
        calendar.timeInMillis = time
        return Pair(
            GregorianDate(year = calendar.get(Calendar.YEAR) , month = calendar.get(Calendar.MONTH)+1 , dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)) ,
            Clock(hour = calendar.get(Calendar.HOUR_OF_DAY) , minute = calendar.get(Calendar.MINUTE) , second = calendar.get(Calendar.SECOND))
        )
    }

    fun getTimeInMillis(gregorianDate: GregorianDate , clock: Clock) : Long {
        calendar.set(gregorianDate.year , gregorianDate.month-1 , gregorianDate.dayOfMonth , clock.hour , clock.minute , clock.second)
        return calendar.timeInMillis
    }

    fun getCurrentClock() : Clock {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val sec = calendar.get(Calendar.SECOND)
        return Clock(hour, min, sec)
    }

    fun getCurrentYear() : Int {
        setToCurrentTime()
        return calendar.get(Calendar.YEAR)
    }

    fun getCurrentMonth() : Int {
        setToCurrentTime()
        return calendar.get(Calendar.MONTH) + 1
    }

    fun getCurrentDay() : Int {
        setToCurrentTime()
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    fun getDayOfWeek(gregorianDate: GregorianDate): Int {
        calendar.set(gregorianDate.year, gregorianDate.month-1, gregorianDate.dayOfMonth)
        return when(calendar.get(Calendar.DAY_OF_WEEK)){
            Calendar.SATURDAY -> 1
            Calendar.SUNDAY -> 2
            Calendar.MONDAY -> 3
            Calendar.TUESDAY -> 4
            Calendar.WEDNESDAY -> 5
            Calendar.THURSDAY -> 6
            Calendar.FRIDAY -> 7
            else -> 1
        }
    }

    fun addTime(gregorianDate: GregorianDate , clock: Clock , type : Int , number : Int) : Pair<GregorianDate , Clock> {
        calendar.set(gregorianDate.year , gregorianDate.month-1 , gregorianDate.dayOfMonth , clock.hour , clock.minute , clock.second)
        calendar.add(type , number)
        return Pair(
            GregorianDate(year = calendar.get(Calendar.YEAR) , month = calendar.get(Calendar.MONTH)+1 , dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)) ,
            Clock(hour = calendar.get(Calendar.HOUR_OF_DAY) , minute = calendar.get(Calendar.MINUTE) , second = calendar.get(Calendar.SECOND))
        )
    }
}