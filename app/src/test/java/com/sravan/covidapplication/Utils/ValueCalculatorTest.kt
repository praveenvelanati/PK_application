package com.sravan.covidapplication.Utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.math.BigInteger

class ValueCalculatorTest {

    private lateinit var valueCalculator: ValueCalculator

    @Before
    fun setUp() {

        // Create and initialize a class instance before each test
        valueCalculator = ValueCalculator()

    }

    // final price test cases
    @Test
    fun testFinalPriceWithPositiveBasePrice() {

        val basePrice = 5.0
        val result = valueCalculator.finalPrice(basePrice)
        Assert.assertEquals(7.5, result, 0.1)

    }

    @Test
    fun testFinalPriceWithZeroBasePrice() {

        val basePrice = 0.0
        val result = valueCalculator.finalPrice(basePrice)
        Assert.assertEquals(0.0, result, 0.0)
    }

    @Test
    fun testFinalPriceWithNegativeBasePrice() {

        val basePrice = -5.0
        val result = valueCalculator.finalPrice(basePrice)
        Assert.assertEquals(-7.5, result, 0.1)
    }

    // factorial test cases

    @Test
    fun testFactorialWithPositiveNumber() {

        val number = 5
        val result = valueCalculator.factorial(number)
        Assert.assertTrue(BigInteger("120") == result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testFactorialWithNegativeNumber() {

        val number = -9
        valueCalculator.factorial(number)
    }

    @Test
    fun testFactorialWithLargeNumber() {

        val number = 21
        val result = valueCalculator.factorial(number)
        Assert.assertTrue(BigInteger("51090942171709440000") == result)
    }

    @Test
    fun testFactorialWithZero() {

        val number = 0
        val result = valueCalculator.factorial(number)
        Assert.assertTrue(BigInteger("1") == result)
    }

    // string reverse function

    @Test
    fun testReverseWithNormalString() {

        val string = "Hey"
        val result = valueCalculator.reverseString(string)
        Assert.assertEquals("yeh", result)
    }

    @Test()
    fun testReverseWithEmptyString() {

        val string = ""
        val result = valueCalculator.reverseString(string)
        Assert.assertEquals("", result)

    }

    @Test
    fun testReverseWithSpacesString() {

        val string = "Hello World"
        val result = valueCalculator.reverseString(string)
        Assert.assertEquals("dlrow olleh", result)
    }

    @Test
    fun testReverseWithSpecialCharactersString() {

        val string = "@$#%!"
        val result = valueCalculator.reverseString(string)
        Assert.assertEquals("!%#$@", result)
    }

    @Test(expected = NullPointerException::class)
    fun testReverseWithNull() {

        val string = null
        valueCalculator.reverseString(string!!)

    }

    @Test
    fun testReverseWithLongString(){

        val string = "A" + "B" + "C".repeat(1000000)
        val result = valueCalculator.reverseString(string)
        val expectedResult = "a" + "b" + "c".repeat(1000000)
        Assert.assertEquals(expectedResult.reversed(), result)
    }

    // palindrome test cases

    @Test
    fun testPalindromeWithCorrectString(){

        val string = "race car"
        val result = valueCalculator.palindrome(string)
        Assert.assertTrue(result)
    }

    @Test
    fun testPalindromeWithEmptyString(){

        val string = ""
        val result = valueCalculator.palindrome(string)
        Assert.assertTrue(result)
    }

    @Test
    fun testPalindromeWithSingleCharacter(){

        val string = "s"
        Assert.assertTrue(valueCalculator.palindrome(string))
    }

    @Test
    fun testPalindromeWithLongCorrectString(){

        val string = "A" + "B".repeat(100000) + "A"
        val result = valueCalculator.palindrome(string)
        Assert.assertTrue(result)
    }

    @Test
    fun testPalindromeWithNonPalindrome(){

        val string = "Hello"
        Assert.assertFalse(valueCalculator.palindrome(string))
    }

}