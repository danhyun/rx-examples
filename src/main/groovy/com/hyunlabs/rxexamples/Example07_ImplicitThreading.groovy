package com.hyunlabs.rxexamples

import java.util.concurrent.TimeUnit

@groovy.transform.BaseScript() Setup setup

def shiftedUp = generator().map(shiftUp).debug("Shifted Up")
def delayed = shiftedUp.delay(10, TimeUnit.MILLISECONDS).debug("Delayed")
def shiftedDown = delayed.map(shiftDown).debug("Shifted Down")

shiftedDown.subscribe { debug("Received", it) }

// The built in Schedulers use daemon threads, so you need to make the main thread stick around or you won't see anything
Thread.sleep(1000)