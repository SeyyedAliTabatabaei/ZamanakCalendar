package seyyed.ali.tabatabaei.zamanakCore.utilsimport org.junit.Testimport org.junit.Assert.assertEqualsimport seyyed.ali.tabatabaei.zamanakCore.model.GregorianDateimport seyyed.ali.tabatabaei.zamanakCore.model.JalaliDateclass DateConverterTest{    @Test    fun `convert gregorian to jalali`() {        assertEquals(JalaliDate(1404, 1, 16), DateConverter.gregorianToJalali(2025, 4, 5))        assertEquals(JalaliDate(1402, 10, 11), DateConverter.gregorianToJalali(2024, 1, 1))        assertEquals(JalaliDate(1402, 10, 10), DateConverter.gregorianToJalali(2023, 12, 31))        assertEquals(JalaliDate(1403, 1, 1), DateConverter.gregorianToJalali(2024, 3, 20))        assertEquals(JalaliDate(1399, 12, 30), DateConverter.gregorianToJalali(2021, 3, 20))        assertEquals(JalaliDate(1400, 1, 1), DateConverter.gregorianToJalali(2021, 3, 21))        assertEquals(JalaliDate(1400, 12, 29), DateConverter.gregorianToJalali(2022, 3, 20))        assertEquals(JalaliDate(1398, 12, 10), DateConverter.gregorianToJalali(2020, 2, 29))        assertEquals(JalaliDate(1402, 7, 1), DateConverter.gregorianToJalali(2023, 9, 23))        assertEquals(JalaliDate(1402, 3, 31), DateConverter.gregorianToJalali(2023, 6, 21))        assertEquals(JalaliDate(1402, 10, 1), DateConverter.gregorianToJalali(2023, 12, 22))        assertEquals(JalaliDate(1402, 6, 31), DateConverter.gregorianToJalali(2023, 9, 22))        assertEquals(JalaliDate(1278, 1, 1), DateConverter.gregorianToJalali(1899, 3, 21))        assertEquals(JalaliDate(1600, 1, 1), DateConverter.gregorianToJalali(2221, 3, 21))        assertEquals(JalaliDate(1403, 12, 30), DateConverter.gregorianToJalali(2025, 3, 20))    }    @Test    fun `convert jalali to gregorian`() {        assertEquals(GregorianDate(2025, 4, 5), DateConverter.jalaliToGregorian(1404 , 1 , 16))        assertEquals(GregorianDate(2024, 1, 1), DateConverter.jalaliToGregorian(1402 , 10 , 11))        assertEquals(GregorianDate(2023, 12, 31), DateConverter.jalaliToGregorian(1402 , 10 , 10))        assertEquals(GregorianDate(2024, 3, 20), DateConverter.jalaliToGregorian(1403 , 1 , 1))        assertEquals(GregorianDate(2021, 3, 20), DateConverter.jalaliToGregorian(1399 , 12 , 30))        assertEquals(GregorianDate(2021, 3, 21), DateConverter.jalaliToGregorian(1400 , 1 , 1))        assertEquals(GregorianDate(2022, 3, 20), DateConverter.jalaliToGregorian(1400 , 12 , 29))        assertEquals(GregorianDate(2020, 2, 29), DateConverter.jalaliToGregorian(1398 , 12 , 10))        assertEquals(GregorianDate(2023, 9, 23), DateConverter.jalaliToGregorian(1402 , 7 , 1))        assertEquals(GregorianDate(2023, 6, 21), DateConverter.jalaliToGregorian(1402 , 3 , 31))        assertEquals(GregorianDate(2023, 12, 22), DateConverter.jalaliToGregorian(1402 , 10 , 1))        assertEquals(GregorianDate(2023, 9, 22), DateConverter.jalaliToGregorian(1402 , 6 , 31))        assertEquals(GregorianDate(1899, 3, 21), DateConverter.jalaliToGregorian(1278 , 1 , 1))        assertEquals(GregorianDate(2221, 3, 21), DateConverter.jalaliToGregorian(1600 , 1 , 1))        assertEquals(GregorianDate(2025, 3, 20), DateConverter.jalaliToGregorian(1403 , 12 , 30))    }}