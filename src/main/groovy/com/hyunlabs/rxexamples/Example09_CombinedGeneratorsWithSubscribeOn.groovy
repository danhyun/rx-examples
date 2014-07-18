package com.hyunlabs.rxexamples

import rx.schedulers.Schedulers

@groovy.transform.BaseScript() Setup setup

def generator1 = generator().map(shiftUp).debug("Shifted Up #1")
def generator2 = generator().map(shiftUp).debug("Shifted Up #2")

def zipped = generator1.zip(generator2, { gen1, gen2 -> gen1 }).debug("Zipped")

def shiftedDown = zipped.map(shiftDown).debug("Shifted Down")

shiftedDown.subscribeOn(Schedulers.io()).subscribe { debug("Received", it) }

// The built in Schedulers use daemon threads, so you need to make the main thread stick around or you won't see anything
Thread.sleep(1000)