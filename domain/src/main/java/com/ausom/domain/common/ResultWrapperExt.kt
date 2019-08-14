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