package com.hyunlabs.rxexamples

@groovy.transform.BaseScript() Setup setup

def shiftedUpAndDownInParallele = generator().parallel({ f ->
    def shiftedUp = f.map(shiftUp).debug("Shifted Up")
    def shiftedDown = shiftedUp.map(shiftDown).debug("Shifted Down")
    shiftedDown
})

shiftedUpAndDownInParallele.subscribe { debug("Received", it) }

// The built in Schedulers use daemon threads, so you need to make the main thread stick around or you won't see anything
Thread.sleep(1000)