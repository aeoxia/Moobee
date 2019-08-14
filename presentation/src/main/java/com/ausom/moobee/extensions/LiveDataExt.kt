package com.ausom.moobee.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> mutableLiveDataOf(initialData: T? = null) = MutableLiveData<T>().apply { value = initialData }

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline onChange: (T) -> Unit) {
    this.observe(owner, Observer<T> { data -> onChange(data) })
}