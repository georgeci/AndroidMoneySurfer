package com.georgeci.moneysurfer.mvi.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgeci.moneysurfer.mvi.Store

open class FeatureViewModel<STATE : Any, INTENT : Any, NEWS : Any>(
    private val feature: Store<STATE, INTENT, NEWS>
) : ViewModel(), Store<STATE, INTENT, NEWS> by feature {

    init {
        feature.launch(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        feature.dispose()
    }
}
