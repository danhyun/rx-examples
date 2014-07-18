package com.hyunlabs.rxexamples

@groovy.transform.BaseScript() Setup setup

def shiftedUp = generator().map(shiftUp).debug("Shifted Up")
def shiftedDown = shiftedUp.map(shiftDown).debug("Shifted Down")

shiftedDown.subscribe { debug("Received", it) }