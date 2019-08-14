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