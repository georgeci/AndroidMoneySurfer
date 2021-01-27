package com.georgeci.moneysurfer.receiptedit.arch

import androidx.lifecycle.SavedStateHandle
import com.georgeci.moneysurfer.mvi.android.FeatureViewModel
import com.georgeci.moneysurfer.mvi.impl.SyncSimpleFeature
import javax.inject.Inject

class ReceiptEditViewModel @Inject constructor() :
    FeatureViewModel<Unit, Unit, Nothing>(
        feature = SyncSimpleFeature(
            initialState = Unit,
            syncReducer = { _, _ -> }
        )
    ) {

    override fun onCleared() {
        super.onCleared()
    }
    companion object {
        private fun SavedStateHandle.getInitialState(): Unit {
            return Unit
        }
    }
}
