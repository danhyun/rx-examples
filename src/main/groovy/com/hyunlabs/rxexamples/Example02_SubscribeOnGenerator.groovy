package com.hyunlabs.rxexamples

import rx.schedulers.Schedulers

@groovy.transform.BaseScript() Setup setup

def shiftedUp = generator().subscribeOn(Schedulers.io()).map(shiftUp).debug("Shifted Up")
def shiftedDown = shiftedUp.map(shiftDown).debug("Shifted Down")

shiftedDown.subscribe { debug("Received", it) }

// The built in Schedulers use daemon threads, so you need to make the main thread stick around or you won't see anything
Thread.sleep(1000)