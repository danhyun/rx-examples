package com.hyunlabs.rxexamples

import rx.schedulers.Schedulers

@groovy.transform.BaseScript() Setup setup

def generatorSubscribed = generator().subscribeOn(Schedulers.io())
def shiftedUp1 = generatorSubscribed.map(shiftUp).debug("Shifted Up #1")
def shiftedUp2 = generatorSubscribed.map(shiftUp).debug("Shifted Up #2")

def zipped = shiftedUp1.zip(shiftedUp2, { gen1, gen2 -> gen1 }).debug("Zipped")

def shiftedDown = zipped.map(shiftDown).debug("Shifted Down")

shiftedDown.subscribe({ debug("Received", it) })

// The built in Schedulers use daemon threads, so you need to make the main thread stick around or you won't see anything
Thread.sleep(2000)