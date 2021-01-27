package com.georgeci.moneysurfer.mvi.lab

import kotlinx.coroutines.flow.Flow

@DslMarker
annotation class StatorDSL

class Stator<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {
    companion object {
        @StatorDSL
        fun <STATE : Any, EVENT : Any, SIDE_EFFECT : Any> create(
            init: StatorGraphBuilder<STATE, EVENT, SIDE_EFFECT>.() -> Unit
        ) {

        }
    }
}

class StatorGraphBuilder<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {
    private var initialState: (() -> STATE)? = null

    @StatorDSL
    fun initialState(initialState: () -> STATE) {
        this.initialState = initialState
    }

    @StatorDSL
    inline fun <reified S : STATE> state(noinline init: StateDefinitionBuilder<S>.() -> Unit) {
        // add routes
    }

    inner class StateDefinitionBuilder<S : STATE> {
        fun <E : EVENT> on(
            f: StatorActionBuilder<STATE, E, SIDE_EFFECT>.() -> Unit
        ) {
            // create action
        }
    }
}

class StatorActionBuilder<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {
    var buildAction: StatorAction<STATE, EVENT, SIDE_EFFECT>? = null
    @StatorDSL
    fun newState(action: STATE.(EVENT) -> STATE) {
        buildAction = StatorAction.SyncTransition(action)
    }
    @StatorDSL
    fun newAsyncState(action: STATE.(EVENT) -> Flow<STATE>) {
        buildAction = StatorAction.AsyncFlowTransition(action)
    }
    @StatorDSL
    fun newEvent(action: STATE.(EVENT) -> Flow<EVENT>) {
        buildAction = StatorAction.AsyncJumpTransition(action)
    }
    @StatorDSL
    fun justSideEffect(action: STATE.(EVENT) -> SIDE_EFFECT) {
        buildAction = StatorAction.JustNotification(action)
    }

}

class StateEventMatcher
sealed class StatorAction<STATE : Any, EVENT : Any, SIDE_EFFECT : Any> {
    class SyncTransition<STATE : Any, EVENT : Any, SIDE_EFFECT : Any>(action: (STATE, EVENT) -> STATE) :
        StatorAction<STATE, EVENT, SIDE_EFFECT>()

    class JustNotification<STATE : Any, EVENT : Any, SIDE_EFFECT : Any>(action: (STATE, EVENT) -> SIDE_EFFECT) :
        StatorAction<STATE, EVENT, SIDE_EFFECT>()

    class AsyncFlowTransition<STATE : Any, EVENT : Any, SIDE_EFFECT : Any>(action: (STATE, EVENT) -> Flow<STATE>) :
        StatorAction<STATE, EVENT, SIDE_EFFECT>()

    class AsyncJumpTransition<STATE : Any, EVENT : Any, SIDE_EFFECT : Any>(action: (STATE, EVENT) -> Flow<EVENT>) :
        StatorAction<STATE, EVENT, SIDE_EFFECT>()



}