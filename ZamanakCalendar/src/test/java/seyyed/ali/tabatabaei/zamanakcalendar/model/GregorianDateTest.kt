package seyyed.ali.tabatabaei.zamanakcalendar.modelimport org.junit.Assert.*import org.junit.Testimport seyyed.ali.tabatabaei.zamanakcalendar.model.enums.Languageimport seyyed.ali.tabatabaei.zamanakcalendar.utils.Constanseclass GregorianDateTest {    @Test    fun `test isLeapYear - leap year`() {        val date = GregorianDate(2020, 2, 29)        assertTrue(date.isLeapYear())    }    @Test    fun `test isLeapYear - non-leap year`() {        val date = GregorianDate(2021, 2, 28)        assertFalse(date.isLeapYear())    }    @Test    fun `test getDaysRemainingInYear - normal year`() {        val date = GregorianDate(2021, 1, 1)        assertEquals(364, date.getDaysRemainingInYear())    }    @Test    fun `test getDaysRemainingInYear - leap year`() {        val date = GregorianDate(2020, 1, 1)        assertEquals(365, date.getDaysRemainingInYear())    }    @Test    fun `test getNumberOfDaysInYear - normal year`() {        val date = GregorianDate(2021, 1, 1)        assertEquals(365, date.getNumberOfDaysInYear())    }    @Test    fun `test getNumberOfDaysInYear - leap year`() {        val date = GregorianDate(2020, 1, 1)        assertEquals(366, date.getNumberOfDaysInYear())    }    @Test    fun `test getDayInYear`() {        val date = GregorianDate(2023, 2, 1)        assertEquals(32, date.getDayInYear())    }    @Test    fun `test getMonthName - persian`() {        val date = GregorianDate(2023, 1, 1)        assertEquals(Constanse.gregorianPersianMonths[0], date.getMonthName(Language.PERSIAN))    }    @Test    fun `test getMonthName - english`() {        val date = GregorianDate(2023, 1, 1)        assertEquals(Constanse.gregorianEnglishMonths[0], date.getMonthName(Language.ENGLISH))    }    @Test    fun `test getQuarterNumber`() {        val date = GregorianDate(2023, 6, 1) // ماه ژوئن در ربع دوم        assertEquals(2, date.getQuarterNumber())    }    @Test    fun `test getQuarter - persian`() {        val date = GregorianDate(2023, 6, 1)        assertEquals(Constanse.seasonsPersian[1], date.getQuarter(Language.PERSIAN))    }    @Test    fun `test getQuarter - english`() {        val date = GregorianDate(2023, 6, 1)        assertEquals(Constanse.seasonsEnglish[1], date.getQuarter(Language.ENGLISH))    }    @Test    fun `test getWeekNumberOfYear`() {        val date = GregorianDate(2023, 1, 15) // روز 15 از سال، یعنی هفته 3        assertEquals(3, date.getWeekNumberOfYear())    }    @Test    fun `test getDaysInMonth - ordinary`() {        val date = GregorianDate(2023, 1, 1)        assertEquals(31, date.getDaysInMonth())    }    @Test    fun `test getDaysInMonth - leap year february`() {        val date = GregorianDate(2020, 2, 1)        assertEquals(29, date.getDaysInMonth())    }    @Test    fun `test getDaysInMonth - non-leap year february`() {        val date = GregorianDate(2021, 2, 1)        assertEquals(28, date.getDaysInMonth())    }    @Test    fun `test getWeekdayName - persian`() {        val date = GregorianDate(2023, 1, 1)        val result = date.getWeekdayName(Language.PERSIAN)        assertTrue(Constanse.persianDayOfTheWeek.contains(result))    }    @Test    fun `test getWeekdayName - english`() {        val date = GregorianDate(2023, 1, 1)        val result = date.getWeekdayName(Language.ENGLISH)        assertTrue(Constanse.englishDayOfTheWeek.contains(result))    }    @Test    fun `test getWeekday`() {        val date = GregorianDate(2023, 1, 1)        val result = date.getWeekdayNumber()        assertTrue(result in 1..7)    }}