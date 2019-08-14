package com.ausom.domain.common

fun<E, V, V2> ResultWrapper<E, V>.map(function: (V) -> V2): ResultWrapper<E, V2> =
    when(this){
        is ResultWrapper.Success -> ResultWrapper.Success(
            function(this.value)
        )
        is ResultWrapper.Error -> this
    }

inline infix fun <E, E2, V> ResultWrapper<E, V>.mapError(function: (E)-> E2): ResultWrapper<E2, V> =
    when(this){
        is ResultWrapper.Success -> this
        is ResultWrapper.Error -> ResultWrapper.Error(
            function(this.error)
        )
    }

inline infix fun <E,V,V2> ResultWrapper<E, V>.flatMap(function: (V)-> ResultWrapper<E, V2>): ResultWrapper<E, V2> =
    when(this){
        is ResultWrapper.Success ->  function(this.value)
        is ResultWrapper.Error -> this
    }

inline fun <E, V, A> ResultWrapper<E, V>.fold(errorFunction: (E) -> A, successFunction: (V)-> A): A =
    when(this){
        is ResultWrapper.Success -> successFunction(this.value)
        is ResultWrapper.Error -> errorFunction(this.error)
    }

infix fun<E, V, V2> ResultWrapper<E, (V) -> V2>.apply(result: ResultWrapper<E, V>): ResultWrapper<E, V2> =
    when(this){
        is ResultWrapper.Success -> result.map(this.value)
        is ResultWrapper.Error -> this
    }