package com.kuxu.rxk

import org.junit.Assert
import org.junit.Test
import java.util.concurrent.CountDownLatch

class ObservableTest {
    @Test
    fun Subscribeable() {
        val observable = Observable<String>()

        var onNextResult = ""
        val message = "Hello Rxk, that is porting Reactive Extensions for Kotlin"

        val disposable = observable.subscribe(
            onNext = { onNextResult = it },
            onError = {},
            onCompleted = { }
        )

        observable.onNext(message)

        Assert.assertEquals(message, onNextResult)

        disposable.dispose()
    }

    @Test
    fun CanObserverbleOnError() {
        val observable = Observable<String>()

        var onErrorResult: Throwable? = null
        val targetException = NullPointerException()

        val disposable = observable.subscribe(
            onNext = { },
            onError = { onErrorResult = it },
            onCompleted = { }
        )

        observable.onError(targetException)

        Assert.assertEquals(onErrorResult, targetException)

        disposable.dispose()
    }

    @Test
    fun CanOnCompletedable() {
        val observable = Observable<String>()

        val latch = CountDownLatch(1)

        val disposable = observable.subscribe(
            onNext = { },
            onError = { },
            onCompleted = { latch.countDown() }
        )

        observable.onCompleted()

        latch.await()

        disposable.dispose()
    }
}