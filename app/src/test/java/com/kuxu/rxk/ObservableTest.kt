package com.kuxu.rxk

import org.junit.Assert
import org.junit.Test

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
}