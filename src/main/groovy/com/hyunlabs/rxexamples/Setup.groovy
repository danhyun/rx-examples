package com.hyunlabs.rxexamples

import rx.Observable

abstract class Setup extends Script {

    def maxNumber = 5

    def static debug(String message, Integer value, Integer indent = null) {
        if (indent == null) indent = value
        def threadName = "[${Thread.currentThread().name}]"
        println "${threadName.padLeft(30)} - ${message?.padRight(13)} ${(" " * (indent * 4))}${value?.toString()?.padLeft(2)}"
    }

    def shiftUp = { int i -> Thread.sleep(25); i + (maxNumber * 2) }
    def shiftDown = { int i -> Thread.sleep(25); i - (maxNumber * 2) }

    Closure<Observable<Integer>> generator = {
        Observable.from(1..maxNumber).debug("Generated")
    }

    Setup() {
        super()
        Observable.metaClass.debug = { String message ->
            delegate.doOnNext({ int i ->
                debug(message, i, (i > maxNumber ? shiftDown(i) : i))
            })
        }
    }
}