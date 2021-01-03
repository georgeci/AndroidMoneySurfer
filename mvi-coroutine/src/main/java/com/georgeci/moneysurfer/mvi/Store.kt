package com.georgeci.moneysurfer.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface Store<STATE : Any, INTENT : Any, NEWS : Any> {
    fun dispatch(intent: INTENT)

    fun state(): StateFlow<STATE>

    fun news(): Flow<NEWS>

    fun dispose()

    fun launch(scope: CoroutineScope)
}