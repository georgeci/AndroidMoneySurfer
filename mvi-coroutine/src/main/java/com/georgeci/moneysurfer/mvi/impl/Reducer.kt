package com.georgeci.moneysurfer.mvi.impl

typealias Reducer<STATE, INTENT> = (state: STATE, intent: INTENT) -> STATE

