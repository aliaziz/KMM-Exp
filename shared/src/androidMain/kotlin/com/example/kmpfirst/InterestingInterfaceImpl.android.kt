package com.example.kmpfirst

class AndroidInterestingInterfaceImpl : InterestingInterface {
    override val name: String
        get() = "Name android"
}

actual fun getTestResult() = "AndroidInterestingInterfaceImpl"
actual fun intResult() = AndroidInterestingInterfaceImpl() as? InterestingInterface