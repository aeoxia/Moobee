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

package com.ausom.moobee.ui.base

import androidx.annotation.CallSuper
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.ausom.moobee.navigation.NavigationRequest
import com.ausom.moobee.navigation.NavigationRequestListener
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), IViewModel, CoroutineScope {
    @Inject
    lateinit var bgDispatcher: CoroutineDispatcher
    private var isInitialized = false
    private val navigationRequestListeners = mutableListOf<NavigationRequestListener>()
    private val job = Job()
    private lateinit var propertyChangedCallbacks: PropertyChangeRegistry

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected open fun onInitialize() {
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected open fun onCreate() {
        if (!isInitialized) {
            isInitialized = true
            onInitialize()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected open fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected open fun onDestroy() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected open fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected open fun onPause() {
    }

    override fun requestNavigation(navigationRequest: NavigationRequest) {
        navigationRequestListeners.forEach { listener ->
            listener.onNavigationRequest(navigationRequest) }
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    @CallSuper
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (!::propertyChangedCallbacks.isInitialized) {
                propertyChangedCallbacks = PropertyChangeRegistry()
            }
        }
        propertyChangedCallbacks.add(callback)
    }

    @CallSuper
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        if (::propertyChangedCallbacks.isInitialized) {
            propertyChangedCallbacks.remove(callback)
        }
    }

    override fun registerNavigationRequestListener(listener: NavigationRequestListener) {
        navigationRequestListeners.add(listener)
    }

    override fun unregisterNavigationRequestListener(listener: NavigationRequestListener) {
        navigationRequestListeners.remove(listener)
    }

    fun notifyChange() {
        if (::propertyChangedCallbacks.isInitialized) {
            propertyChangedCallbacks.notifyCallbacks(this, 0, null)
        }
    }

    fun notifyPropertyChanged(fieldId: Int) {
        if (::propertyChangedCallbacks.isInitialized) {
            propertyChangedCallbacks.notifyCallbacks(this, fieldId, null)
        }
    }
}
