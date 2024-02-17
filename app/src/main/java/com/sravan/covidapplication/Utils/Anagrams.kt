package com.sravan.covidapplication.Utils

fun main() {

    val result = checkAnagram("paris", "pairs")
    println("String are anagrams? : $result")
}

fun checkAnagram(subjectOne: String, subjectTwo: String): Boolean {

    val itemOne = subjectOne.toCharArray().apply { sort() }
    val itemTwo = subjectTwo.toCharArray().apply { sort() }

    return itemOne.contentEquals(itemTwo)
}

