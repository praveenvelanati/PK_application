package com.sravan.covidapplication.Utils;

public class MySingleton {

    private static MySingleton singleton;

    //private constructor
    private MySingleton(){
        // Initializations go here
    }

    public static synchronized MySingleton getInstance(){

        if (singleton == null){

            singleton = new MySingleton();
        }

        return singleton;
    }
}
