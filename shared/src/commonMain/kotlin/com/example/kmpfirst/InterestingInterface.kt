package com.example.kmpfirst

interface InterestingInterface {
    val name: String
}

expect fun getTestResult(): String
expect fun intResult(): InterestingInterface?