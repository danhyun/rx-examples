package com.hyunlabs.rxexamples

import rx.schedulers.Schedulers

@groovy.transform.BaseScript() Setup setup

def shiftedUp = generator().map(shiftUp).debug("Shifted Up")
def shiftedDown = shiftedUp.observeOn(Schedulers.io()).map(shiftDown).debug("Shifted Down")

shiftedDown.subscribeOn(Schedulers.computation()).subscribe { debug("Received", it) }

// The built in Schedulers use daemon threads, so you need to make the main thread stick around or you won't see anything
Thread.sleep(1000)