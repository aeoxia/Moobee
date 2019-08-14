package com.ausom.domain.interactor

abstract class BaseInteractor<out R>{
    abstract suspend fun<P> execute(vararg params: P):R
}