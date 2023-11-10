package com.sravan.covidapplication.Utils

abstract class TestClass(name: String) {

    var name: String
    abstract var age: Int

    // Initializer Block
    init {
        this.name = name
    }

    abstract fun setPersonAge(_age: Int)
    abstract fun getPersonAge(): Int

    fun getPersonName() {
        println("Name = $name")
    }
}

abstract class Employee(_name: String) : TestClass(_name) {

    fun getLife(): Int {

        return 1
    }
}

class Student(abc: String) : Employee(abc) {

    override var age: Int = 0


    override fun getPersonAge(): Int {
        return age
    }

    override fun setPersonAge(_age: Int) {
        TODO("Not yet implemented")
    }
}