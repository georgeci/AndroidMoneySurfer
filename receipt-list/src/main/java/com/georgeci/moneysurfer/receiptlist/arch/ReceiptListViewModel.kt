package com.georgeci.moneysurfer.receiptlist.arch

import com.georgeci.moneysurfer.domain.entity.Receipt
import com.georgeci.moneysurfer.mvi.android.FeatureViewModel
import com.georgeci.moneysurfer.mvi.impl.Reducer
import com.georgeci.moneysurfer.mvi.impl.SyncSimpleFeature
import javax.inject.Inject

class ReceiptListViewModel @Inject constructor() :
    FeatureViewModel<ReceiptListState, ReceiptListIntent, Nothing>(
        feature = SyncSimpleFeature(
            initialState = ReceiptListState(emptyList()),
            syncReducer = ReceiptListReducer(),
        )
    ) {


    private class ReceiptListReducer() :
        Reducer<ReceiptListState, ReceiptListIntent> {

        override fun invoke(state: ReceiptListState, intent: ReceiptListIntent): ReceiptListState {
            return when (intent) {
                is ReceiptListIntent.ReceiptClick -> TODO()
                ReceiptListIntent.Create -> state.copy(
                    items = state.items.plus(
                        Receipt(
                            0,
                            100.0,
                            100
                        )
                    )
                )
            }
        }
    }
}