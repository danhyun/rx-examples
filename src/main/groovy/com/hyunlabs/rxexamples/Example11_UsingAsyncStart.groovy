package com.hyunlabs.rxexamples

import rx.Observable
import rx.util.async.Async

@groovy.transform.BaseScript() Setup setup

def sleepThenGenerate = { n ->
    debug("Generating", n)
    Thread.sleep(800)
    debug("Sending", n)
    n
}

Observable<Integer> asyncGenerator1 = Async.start({ sleepThenGenerate(1) })
Observable<Integer> asyncGenerator2 = Async.start({ sleepThenGenerate(2) })

def shiftedUp1 = asyncGenerator1.debug("Generated").map(shiftUp).debug("Shifted Up #1")
def shiftedUp2 = asyncGenerator2.debug("Generated").map(shiftUp).debug("Shifted Up #2")

def zipped = shiftedUp1.zip(shiftedUp2, { gen1, gen2 -> gen1 }).debug("Zipped")

def shiftedDown = zipped.map(shiftDown).debug("Shifted Down")

Thread.sleep(100)
println("Subscribing...")
shiftedDown.subscribe({
    debug("Received", it)
}, {
    println("Error - ${it.message}")
}, {
    println("Completed")
})

// The built in Schedulers use daemon threads, so you need to make the main thread stick around or you won't see anything
Thread.sleep(3000)