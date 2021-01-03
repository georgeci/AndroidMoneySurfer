package com.georgeci.moneysurfer.mvi.impl

import kotlinx.coroutines.flow.Flow

typealias Actor<STATE, INTENT, EFFECT> = (state: STATE, intent: INTENT) -> Flow<EFFECT>