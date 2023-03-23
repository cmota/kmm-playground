package com.cmota.playground

import platform.WatchKit.WKInterfaceDevice

class WhatchOSPlatform: Platform {
    override val name: String = WKInterfaceDevice.currentDevice().systemName() + " " + WKInterfaceDevice.currentDevice().systemVersion
}

actual fun getPlatform(): Platform = WhatchOSPlatform()