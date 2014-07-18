package com.hyunlabs.rxexamples

import rx.schedulers.Schedulers

@groovy.transform.BaseScript() Setup setup

def aGenerator = generator()
def neverUsed = aGenerator.subscribeOn(Schedulers.io())
def shiftedUp = aGenerator.map(shiftUp).debug("Shifted Up")
def shiftedDown = shiftedUp.map(shiftDown).debug("Shifted Down")

shiftedDown.subscribe { debug("Received", it) }