package com.kuxu.rxk

class Observable<T> {
    private val observerList = mutableListOf<Observer<T>>()

    fun subscribe(
        onNext: ((T) -> Unit) = {},
        onError: ((e: Throwable) -> Unit) = {},
        onCompleted: ((Unit) -> Unit) = {}
    ): Disposable<T> {
        val observer = Observer(onNext, onError, onCompleted)
        observerList.add(observer)
        return Disposable<T>(
            this,
            observer.hashCode()
        )
    }

    fun onNext(element: T) {
        observerList.toList().forEach { it.onNext(element) }
    }

    fun onError(throwable: Throwable) {
        observerList.toList().forEach { it.onError(throwable) }
    }

    fun onCompleted() {
        observerList.toList().forEach { it.onCompleted(Unit) }
    }

    fun dispose(disposable: Disposable<T>) {
        disposable.observable.observerList.removeAll {
            it.hashCode() == disposable.code
        }
    }


}

internal data class Observer<T>(
    val onNext: (T) -> Unit,
    val onError: (e: Throwable) -> Unit,
    val onCompleted: (Unit) -> Unit
)

class Disposable<T>(
    val observable: Observable<T>,
    val code: Int = -1
) {
    fun dispose() {
        observable.dispose(this)
    }
}