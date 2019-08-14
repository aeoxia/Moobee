package com.ausom.moobee

import com.ausom.moobee.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {
//    private val channel: BroadcastChannel<ScreenMode> = ConflatedBroadcastChannel()
//    internal var landscape = false
//        set(value) {
//            field = value
//            if (field && screenMode != ScreenMode.FULLSCREEN) {
//                screenMode = ScreenMode.LANDSCAPE
//            } else if (!field && screenMode != ScreenMode.UNDEFINED) {
//                screenMode = ScreenMode.UNDEFINED
//            }
//        }
//
//    var screenMode: ScreenMode = ScreenMode.UNDEFINED
//        private set(value) {
//            field = value
//            runBlocking { channel.send(value) }
//        }
//
//    fun enterFullscreen() {
//        screenMode = ScreenMode.FULLSCREEN
//    }
//
//    fun exitFullscreen() {
//        screenMode = if (landscape) ScreenMode.LANDSCAPE else ScreenMode.UNDEFINED
//    }
//
//    fun observeScreenMode() = channel.openSubscription()
}