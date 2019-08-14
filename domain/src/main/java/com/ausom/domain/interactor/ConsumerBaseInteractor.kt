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

package com.ausom.domain.interactor

import com.ausom.domain.common.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


abstract class ConsumerBaseInteractor<R>:
    BaseInteractor<ReceiveChannel<ResultWrapper<Exception, R>>>(){

    abstract fun dispatcher(): CoroutineDispatcher
    abstract suspend fun<P> source(params: Array<P>): ReceiveChannel<ResultWrapper<Exception, R>>
    abstract suspend fun build(data: ResultWrapper<Exception, R>): ResultWrapper<Exception, R>

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    suspend fun execute(): ReceiveChannel<ResultWrapper<Exception, R>> = execute<Nothing>()

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    override suspend fun<P> execute(vararg params: P):  ReceiveChannel<ResultWrapper<Exception, R>> =
        GlobalScope.produce(dispatcher()){
            source(params).consumeEach {
                send(build(it))
            }
        }
}