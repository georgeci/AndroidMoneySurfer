package com.georgeci.moneysurfer.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
abstract class CoroutineViewModel<STATE : Any, INTENT : Any, SIDE_EFFECT>(
    initialState: STATE
) : ViewModel(), MviComposeViewModel<STATE, INTENT, SIDE_EFFECT> {
    private val stateFlow = MutableStateFlow(initialState)
    private val intentChannel: Channel<INTENT> = Channel(Channel.BUFFERED)
    private val effectChannel: Channel<SIDE_EFFECT> = Channel(Channel.UNLIMITED)

    override fun dispatchIntent(intent: INTENT) {
        intentChannel.offer(intent)
    }

    override fun effects(): Flow<SIDE_EFFECT> {
        return effectChannel.receiveAsFlow()
    }

    @Composable
    override fun state(): State<STATE> {
        return stateFlow.collectAsState()
    }

    private val job = GlobalScope.launch {
        handleIntents()
    }

    private suspend fun handleIntents() {
        //TODO switchMap like behaviour
        stateFlow.flatMapLatest { oldState ->
            intentChannel.receiveAsFlow()
                .flatMapLatest { intent ->
                    reduceState(oldState, intent)
                }
        }
            .collect {
                stateFlow.emit(it)
            }
    }

    abstract fun reduceState(oldState: STATE, intent: INTENT): Flow<STATE>

    override fun onCleared() {
        super.onCleared()
        job.cancel(message = "ViewModel is stopped")
    }
}