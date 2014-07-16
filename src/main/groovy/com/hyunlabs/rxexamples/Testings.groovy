package com.hyunlabs.rxexamples

import rx.Observable;

def static debug(String message, Integer value, int indent=0) {
    println "[${Thread.currentThread().name}] - ${message} ${value}"
}

Observable.metaClass.debug = { String message ->
    delegate.doOnNext({
        debug(message, it)
    })
}

def maxNumber = 5

Closure<Observable> generator = {
    Observable.from(1..maxNumber).debug("Generated")
}


def generated = generator()
generated.subscribe { debug("Received", it) }
