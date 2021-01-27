package com.georgeci.moneysurfer.mvi.lab;

import io.jumpco.open.kfsm.async.asyncStateMachine
import kotlinx.coroutines.flow.flowOf

class ReceiptListViewModelF(

) {
    fun reduce() {
        asyncStateMachine(
            TurnstileStates.values().toSet(),
            TurnstileEvents.values().toSet(),
            Turnstile::class
        ) {
            whenState(TurnstileStates.LOCKED) {
                onEvent(TurnstileEvents.COIN) {
                    this.lock()
                }
            }
        }

        StateMachine.create<Int, Int, Int> {
            state<Int>(0) {
                on<Int> {
                    transitionTo(this + it)
                }
            }
        }

        Stator.create<Int, Int, String> {
            initialState { 0 }
            state<Int> {
                on<Int> {
                    // this:State it:Event
                    //action
                    newState {
                        9000
                    }
                    newAsyncState {
                        val oldState = this
                        val event = it
                        flowOf(8000)
                    }
                    newEvent { flowOf(400) }
                    justSideEffect { "800" }
                }
                on<Int> {

                }
            }
        }
//        Reduce(onLeave = fail|ignore|state{value}|new{News object}|compose{news{}, state{}}){
//            OldState on trigger NewState{ oldState.copy()}
//            OldState on trigger NewState{ State.newState()}
//            OldState on trigger NewState{ State.newState()}
//            OldState on trigger News{}
//            OldState on trigger Actor{ -> new trigger()}
//            OldState on trigger AsyncState(not safe method){ -> new trigger()}
//        }

    }
}

enum class TurnstileStates {
    LOCKED,
    UNLOCKED
}

enum class TurnstileEvents {
    COIN,
    PASS
}

class Turnstile(var locked: Boolean = true) {
    fun unlock() {
        assert(locked) { "Cannot unlock when not locked" }
        println("Unlock")
        locked = false
    }

    fun lock() {
        assert(!locked) { "Cannot lock when locked" }
        println("Lock")
        locked = true
    }

    fun alarm() {
        println("Alarm")
    }

    fun returnCoin() {
        println("Return coin")
    }

    override fun toString(): String {
        return "Turnstile(locked=$locked)"
    }
}