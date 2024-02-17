package com.sravan.covidapplication.Utils

import java.math.BigInteger

fun main() {

//    largestNumber()
//    smallestNumber()
//    palindrome()
//    reverseString()
//    factorial()
//    swapNumbers()
//    charOccurrence()

//    val myLambdaFunc: ((String, String) -> String) = {a, b -> a + b}
//
//    calculateSum("Sravan", "velanati", myLambdaFunc)

    //anonymous object
//    val aObject = object {
//
//        val name = "Sravan"
//        val age = 27
//    }
//
//    println("Name is: ${aObject.name}")
//    println("Age is: ${aObject.age}")


//    stringReverse()
//    findPalindrome()
//    numberSwap()
//    findFactorial()

//    secondLargest()
//    secondSmallest()
//    charOccur()
//    countVowels()
//    checkAnagrams()
//    countWords()
    findLongestWord()
}

fun findLongestWord(){

    val subject = "This is a sample"
    var longestWord = ""
    var currentWord = ""

    for (i in subject){

        if (i.isWhitespace()){
            if (currentWord.length > longestWord.length){
                longestWord = currentWord
            }
            currentWord = ""
        } else{
            currentWord += i
        }
    }

    if (currentWord.length > longestWord.length){
        longestWord = currentWord
    }

    println("Longest word is: $longestWord")

}

fun countWords(){

    val subject = "This is a Sample"
    var count = 0
    var isWord = false

    for (i in subject){
        if (i.isWhitespace()){
            isWord = false
        } else if (!isWord){
            count++
            isWord = true
        }
    }
    println("Count is $count")
}

fun checkAnagrams(){

    var a = "silent"
    var b = "listen"

    var resultA = a.toCharArray().apply { sort() }
    var resultB = b.toCharArray().apply { sort() }

    if (resultA.contentEquals(resultB)){

        println("True")
    }
}

fun countVowels(){

    val vowels = "aeiouAEIOU"
    val subject = "hello"
    var count = 0

    for (i in subject){

        if (i in vowels){
            count++
        }
    }

    println("Count is $count")
}

fun charOccur(){

    val subject = "hzhzhz"
    val occur = 'z'
    var count = 0

  for (i in subject){

      if (i == occur){
          count++
      }
  }
    println("result is : $count")
}

fun secondSmallest(){

    val numbers = intArrayOf(23,5,67,1,12,98,3)
    var smallest = Int.MAX_VALUE
    var secondSmallest = Int.MAX_VALUE

    for (i in numbers){

        if (i < smallest){
            secondSmallest = smallest
            smallest = i
        } else if (i < secondSmallest){
            secondSmallest = i
        }
    }

    println("Smallest number is: $smallest")
    println("Second smallest is: $secondSmallest")
}

fun secondLargest(){

    val numbers = intArrayOf(2,100,45,7,0,87,12)
    var largest = Int.MIN_VALUE
    var secondLargest = Int.MIN_VALUE
    var thirdLargest = Int.MIN_VALUE

    for (i in numbers){

        if (i > largest){
            thirdLargest = secondLargest
            secondLargest = largest
            largest = i

        }else if (i > secondLargest){
            thirdLargest = secondLargest
            secondLargest = i
        } else if ( i > thirdLargest){
            thirdLargest = i
        }
    }

    println("Third largest number is: $thirdLargest")

}

fun findFactorial(){

    var number = 5
    var result = 1

    while (number > 1){

        result *= number
        number--
    }

    println("factorial is: $result")

}

fun numberSwap(){

    var a = 10
    var b = 5

    b = a + b
    a = b - a
    b = b - a

    println("Values are: $a $b")
}

fun findPalindrome(){

    val subject = "Malayalam".lowercase()
    var start = 0
    var end = subject.length - 1

    while (start < end){

        if (subject[start] == subject[end]){

            start++
            end--

        } else {

            println("Given string is not a palindrome!")
            return
        }
    }
    println("Given string is a palindrome!")
}

fun <T> calculateSum(i: T, i1: T, myLambdaFunc: (T, T) -> T) {

    val result = myLambdaFunc(i, i1)
    println("Result is $result")
}

fun stringReverse(){

    val word = "1234512345"
    var len = word.length - 1
    var result = ""

    while (len >= 0){

        result += word[len]
        len--
    }

    println("String is: $result")
}

fun charOccurrence() {

    val statement = "Hello Srava?n, How are you?"
    val word = '?'
    var appearanceCount = 0

    for (i in statement){

        if (i == word){

            appearanceCount++
        }
    }

    println("Word appeared $appearanceCount times")

}

fun swapNumbers() {

    var a = 10
    var b = 5

    println("Numbers before swap : $a $b")

    a = a + b
    b = a - b
    a = a - b

    println("Numbers after swap : $a $b")
}

fun factorial() {

    val number = 300
    var result = BigInteger.ONE

    for (i in 1..number) {

        result *= i.toBigInteger()
    }

    println("Factorial is : $result")

}

fun largestNumber() {

    var largestNumber = Int.MIN_VALUE
    var secondLargest = Int.MIN_VALUE
    var thirdLargest = Int.MIN_VALUE

    val numbers = listOf<Int>(2, 100, 5, 8, 90, 110, 34, 99, 87, 112)

    for (num in numbers) {

        if (num > largestNumber) {
            thirdLargest = secondLargest
            secondLargest = largestNumber
            largestNumber = num
        } else if (num in (secondLargest + 1) until largestNumber) {
            thirdLargest = secondLargest
            secondLargest = num
        } else if (num in (thirdLargest + 1) until secondLargest) {

            thirdLargest = num
        }
    }

    println("Largest number is : $largestNumber")
    println("Second Largest number is : $secondLargest")
    println("Third Largest number is : $thirdLargest")

}

fun smallestNumber() {

    val numbers = intArrayOf(200, 10, 6, 120, 20, 96, 9, 89, 99, 21)

    var smallestNumber = Int.MAX_VALUE
    var secondSmallest = Int.MAX_VALUE

    for (num in numbers) {

        if (num < smallestNumber) {
            secondSmallest = smallestNumber
            smallestNumber = num
        } else if (num < secondSmallest && num != smallestNumber) {
            secondSmallest = num
        }
    }

    println("Smallest number is : $smallestNumber")
    println("Second smallest number is : $secondSmallest")
}

fun palindrome() {

    val word = "Malayalam"

    var start = 0
    var end = word.length - 1

    while (start < end) {

        if (word[start] == word[end]) {

            start++
            end--

        } else {

            println("Word is not a palindrome")
            return
        }
    }

    println("Word is palindrome")
}

fun reverseString() {

    val word = "king"
    var reverseWord = ""
    val start = 0
    var end = word.length - 1

    while (end >= start) {

        reverseWord += word[end]
        end--

    }

    println("$reverseWord")
}