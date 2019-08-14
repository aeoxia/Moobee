/*
 * MIT License
 *
 * Copyright (c) 2019 Jan Kennu Paz
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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