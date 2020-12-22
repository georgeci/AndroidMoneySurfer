package com.georgeci.moneysurfer.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow

interface MviComposeViewModel<STATE : Any, INTENT : Any, SIDE_EFFECT> {
    fun dispatchIntent(intent: INTENT)

    @Composable
    fun state(): State<STATE>

    fun effects(): Flow<SIDE_EFFECT>

//    fun stop()
//    fun start()
}