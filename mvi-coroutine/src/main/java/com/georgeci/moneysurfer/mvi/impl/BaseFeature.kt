package com.georgeci.moneysurfer.mvi.impl

import com.georgeci.moneysurfer.mvi.Store
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
open class BaseFeature<STATE : Any, INTENT : Any, EFFECT : Any, INTERNAL_ACTION : Any, NEWS : Any>(
    private val initialState: STATE,
    private val reducer: Reducer<STATE, EFFECT>,
    private val actor: Actor<STATE, INTERNAL_ACTION, EFFECT>,
    private val intentToAction: IntentToEffect<INTENT, INTERNAL_ACTION>
) : Store<STATE, INTENT, NEWS> {

    private var job: Job? = null
    private val stateFlow = MutableStateFlow(initialState)
    private val intentChannel: Channel<INTENT> = Channel(Channel.UNLIMITED)
    private val newsChannel: Channel<NEWS> = Channel(Channel.UNLIMITED)

    override fun dispatch(intent: INTENT) {
        intentChannel.offer(intent)
    }

    override fun state(): StateFlow<STATE> {
        return stateFlow
    }

    override fun news(): Flow<NEWS> {
        return newsChannel.receiveAsFlow()
    }

    override fun dispose() {
        job?.cancel(message = "Store disposed")
    }

    override fun launch(scope: CoroutineScope) {
        // TODO WARN about using of parent(outer) scope in nested vm
        job = scope.launch {
            stateFlow.flatMapLatest { oldState ->
                intentChannel.receiveAsFlow()
                    .map { intentToAction(it) }
                    .flatMapMerge { action ->
                        actor(oldState, action)
                    }
                    .map { reducer(stateFlow.value, it) }
            }
                .collect {
                    stateFlow.emit(it)
                }
        }
    }
}