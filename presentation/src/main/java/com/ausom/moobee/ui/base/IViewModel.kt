package com.ausom.moobee.ui.base

import androidx.databinding.Observable
import androidx.lifecycle.LifecycleObserver
import com.ausom.moobee.navigation.NavigationRequest
import com.ausom.moobee.navigation.NavigationRequestListener
import kotlinx.coroutines.CoroutineScope

interface IViewModel : LifecycleObserver, Observable, CoroutineScope {
    fun registerNavigationRequestListener(listener: NavigationRequestListener)
    fun unregisterNavigationRequestListener(listener: NavigationRequestListener)
    fun requestNavigation(navigationRequest: NavigationRequest)
}