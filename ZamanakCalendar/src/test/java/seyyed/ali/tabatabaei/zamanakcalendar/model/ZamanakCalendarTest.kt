package seyyed.ali.tabatabaei.zamanakcalendar.modelimport org.junit.Beforeimport org.junit.Testimport org.junit.Assert.*import seyyed.ali.tabatabaei.zamanakcalendar.ZamanakCalendarimport seyyed.ali.tabatabaei.zamanakcalendar.model.enums.CalendarTypeimport seyyed.ali.tabatabaei.zamanakcalendar.model.enums.TimeUnitTypeimport java.util.TimeZoneclass ZamanakCalendarTest{    private lateinit var zamanakCalendar : ZamanakCalendar    private val jDate = JalaliDate(1404 , 1 , 18)    private val gDate = GregorianDate(2025 , 4 , 7)    private val clock = Clock(13 , 15 , 30)    private val timeMillis : Long = 1744019130925 / 1000    @Before    fun setup() {        zamanakCalendar = ZamanakCalendar().setDateFromGregorian(gDate).setClock(clock)    }    @Test    fun `Set new clock`() {        val newClock = Clock(14 , 35 , 15)        zamanakCalendar.setClock(newClock)        assertEquals(14 , zamanakCalendar.clock.hour)        assertEquals(35 , zamanakCalendar.clock.minute)        assertEquals(15 , zamanakCalendar.clock.second)    }    @Test    fun `Set date from jalali date`() {        val newJalali = JalaliDate(1403, 12, 30)        val newGregorian = GregorianDate(2025 , 3 , 20)        zamanakCalendar.setDateFromJalali(newJalali)        assertEquals(newJalali.year, zamanakCalendar.jalaliDate.year)        assertEquals(newJalali.month, zamanakCalendar.jalaliDate.month)        assertEquals(newJalali.dayOfMonth, zamanakCalendar.jalaliDate.dayOfMonth)        assertEquals(newGregorian.year, zamanakCalendar.gregorianDate.year)        assertEquals(newGregorian.month, zamanakCalendar.gregorianDate.month)        assertEquals(newGregorian.dayOfMonth, zamanakCalendar.gregorianDate.dayOfMonth)    }    @Test    fun `Set date from jalali gregorian`() {        val newGregorian = GregorianDate(2023, 4, 10)        val newJalali = JalaliDate(1402 , 1 , 21)        zamanakCalendar.setDateFromGregorian(newGregorian)        assertEquals(newJalali.year, zamanakCalendar.jalaliDate.year)        assertEquals(newJalali.month, zamanakCalendar.jalaliDate.month)        assertEquals(newJalali.dayOfMonth, zamanakCalendar.jalaliDate.dayOfMonth)        assertEquals(newGregorian.year, zamanakCalendar.gregorianDate.year)        assertEquals(newGregorian.month, zamanakCalendar.gregorianDate.month)        assertEquals(newGregorian.dayOfMonth, zamanakCalendar.gregorianDate.dayOfMonth)    }    @Test    fun `Set date from time in millis`() {        val newGregorian = GregorianDate(2023, 4, 10)        val newJalali = JalaliDate(1402 , 1 , 21)        zamanakCalendar.setDateFromTimeInMillis(1681116417000)        assertEquals(newJalali.year, zamanakCalendar.jalaliDate.year)        assertEquals(newJalali.month, zamanakCalendar.jalaliDate.month)        assertEquals(newJalali.dayOfMonth, zamanakCalendar.jalaliDate.dayOfMonth)        assertEquals(newGregorian.year, zamanakCalendar.gregorianDate.year)        assertEquals(newGregorian.month, zamanakCalendar.gregorianDate.month)        assertEquals(newGregorian.dayOfMonth, zamanakCalendar.gregorianDate.dayOfMonth)    }    @Test    fun `Get time in millis`() {        val currentTime = zamanakCalendar.toMillis() / 1000        assertEquals(currentTime, timeMillis)    }    @Test    fun `Get start of day`() {        val startOfDay = zamanakCalendar.getStartOfDay()        assertEquals(jDate.year, startOfDay.jalaliDate.year)        assertEquals(jDate.month, startOfDay.jalaliDate.month)        assertEquals(jDate.dayOfMonth, startOfDay.jalaliDate.dayOfMonth)        assertEquals(gDate.year, startOfDay.gregorianDate.year)        assertEquals(gDate.month, startOfDay.gregorianDate.month)        assertEquals(gDate.dayOfMonth, startOfDay.gregorianDate.dayOfMonth)        assertEquals(0, startOfDay.clock.hour)        assertEquals(0, startOfDay.clock.minute)        assertEquals(0, startOfDay.clock.second)    }    @Test    fun `Get end of day`() {        val endOfDay = zamanakCalendar.getEndOfDay()        assertEquals(jDate.year, endOfDay.jalaliDate.year)        assertEquals(jDate.month, endOfDay.jalaliDate.month)        assertEquals(jDate.dayOfMonth, endOfDay.jalaliDate.dayOfMonth)        assertEquals(gDate.year, endOfDay.gregorianDate.year)        assertEquals(gDate.month, endOfDay.gregorianDate.month)        assertEquals(gDate.dayOfMonth, endOfDay.gregorianDate.dayOfMonth)        assertEquals(23, endOfDay.clock.hour)        assertEquals(59, endOfDay.clock.minute)        assertEquals(59, endOfDay.clock.second)    }    @Test    fun `Check is today`() {        assertTrue(!zamanakCalendar.isToday())    }    @Test    fun `Set time zone`() {        val newDate = zamanakCalendar.setTimeZone(TimeZone.getTimeZone("UTC"))        assertEquals(jDate.year, newDate.jalaliDate.year)        assertEquals(jDate.month, newDate.jalaliDate.month)        assertEquals(jDate.dayOfMonth, newDate.jalaliDate.dayOfMonth)        assertEquals(gDate.year, newDate.gregorianDate.year)        assertEquals(gDate.month, newDate.gregorianDate.month)        assertEquals(gDate.dayOfMonth, newDate.gregorianDate.dayOfMonth)        assertEquals(9, newDate.clock.hour)        assertEquals(45, newDate.clock.minute)        assertEquals(30, newDate.clock.second)    }    @Test    fun `Check date is before`() {        val newZamanakCalendar1 = ZamanakCalendar().setDateFromGregorian(gDate).setClock(clock).addDate(            CalendarType.Gregorian , TimeUnitType.SECOND , 5)        assertTrue(zamanakCalendar.isBefore(newZamanakCalendar1))        val newZamanakCalendar2 = ZamanakCalendar().setDateFromGregorian(gDate).setClock(clock).subDate(            CalendarType.Gregorian , TimeUnitType.SECOND , 5)        assertTrue(!zamanakCalendar.isBefore(newZamanakCalendar2))    }    @Test    fun `Check date is after`() {        val newZamanakCalendar1 = ZamanakCalendar().setDateFromGregorian(gDate).setClock(clock).addDate(            CalendarType.Gregorian , TimeUnitType.SECOND , 5)        assertTrue(!zamanakCalendar.isAfter(newZamanakCalendar1))        val newZamanakCalendar2 = ZamanakCalendar().setDateFromGregorian(gDate).setClock(clock).subDate(            CalendarType.Gregorian , TimeUnitType.SECOND , 5)        assertTrue(zamanakCalendar.isAfter(newZamanakCalendar2))    }    @Test    fun `Check date is equals`() {        val newZamanakCalendar1 = ZamanakCalendar().setDateFromGregorian(gDate).setClock(clock)        assertTrue(zamanakCalendar.equalsDate(newZamanakCalendar1))    }}