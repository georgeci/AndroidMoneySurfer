package com.georgeci.moneysurfer.mvi.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AsyncSimpleFeature<STATE : Any, INTENT : Any>(
    initialState: STATE,
    stateActor: Actor<STATE, INTENT, STATE>
) : BaseFeature<STATE, INTENT, STATE, INTENT, Nothing>(
    initialState = initialState,
    actor = stateActor,
    reducer = { _, effect -> effect },
    intentToAction = { it }
)

open class SyncSimpleFeature<STATE : Any, INTENT : Any>(
    initialState: STATE,
    syncReducer: Reducer<STATE, INTENT>
) : BaseFeature<STATE, INTENT, INTENT, INTENT, Nothing>(
    initialState = initialState,
    actor = StreamedActor(),
    reducer = syncReducer,
    intentToAction = { it }
) {
    private class StreamedActor<STATE : Any, INTENT : Any> : Actor<STATE, INTENT, INTENT> {
        override fun invoke(state: STATE, intent: INTENT): Flow<INTENT> {
            return flowOf(intent)
        }
    }
}

open class SimpleFeature<STATE : Any, INTENT : Any, EFFECT : Any, NEWS : Any>(
    initialState: STATE,
    actor: Actor<STATE, INTENT, EFFECT>,
    reducer: Reducer<STATE, EFFECT>
) : BaseFeature<STATE, INTENT, EFFECT, INTENT, NEWS>(
    initialState = initialState,
    actor = actor,
    reducer = reducer,
    intentToAction = { it }
)