package com.sravan.covidapplication.Utils

import java.math.BigInteger

class ValueCalculator {

    /**
     * @return the final price of the product to show in the UI.
     * @param basePrice the base price of the [Product].
     */
    fun finalPrice(basePrice: Double): Double{

        return basePrice * 1.5
    }

    fun factorial(n: Int): BigInteger{

       if (n < 0) {
           throw IllegalArgumentException("Factorial is not defined for negative numbers.")
       }

       var result = BigInteger.ONE

       for (i in 2..n){
           result = result.multiply(BigInteger.valueOf(i.toLong()))
       }

       return result

//       return if(n == 0) 1 else n * factorial(n-1)
   }

    fun reverseString(string: String): String{

        return string.lowercase().reversed()
    }

    fun palindrome(string: String): Boolean{

        val cleanedString = string.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        var start = 0
        var end = cleanedString.length - 1

        while (start < end){

            if (cleanedString[start] != cleanedString[end]){
                return false
            }

            start++
            end--
        }
        return true
    }
}