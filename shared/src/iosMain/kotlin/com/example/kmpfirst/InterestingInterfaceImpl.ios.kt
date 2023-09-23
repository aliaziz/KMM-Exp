package com.example.kmpfirst

class IOSInterestingInterfaceImpl: InterestingInterface {
    override val name: String
        get() = "Name iOS"
}

actual fun getTestResult() = "IOSInterestingInterfaceImpl()"
actual fun intResult() = IOSInterestingInterfaceImpl() as? InterestingInterface