package com.georgeci.moneysurfer.receiptlist.arch

import com.georgeci.moneysurfer.mvi.CoroutineViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import kotlin.random.Random

data class ReceiptListState(
    val items: List<ReceiptItemViewModel>,
    val i: Int
)

sealed class ReceiptListIntent {
    object Plus : ReceiptListIntent()
    object Minus : ReceiptListIntent()
}

class ReceiptListViewModel @Inject constructor() :
    CoroutineViewModel<ReceiptListState, ReceiptListIntent, Nothing>(
        ReceiptListState(
            emptyList(),
            0
        )
    ) {

    override fun reduceState(
        oldState: ReceiptListState,
        intent: ReceiptListIntent
    ): Flow<ReceiptListState> {
        return flowOf(
            oldState.copy(
                i = oldState.i + 1,
                items = oldState.items.plus(ReceiptItemViewModel(Random.nextInt(-10, 10)))
            )
        )
    }
}

class ReceiptItemViewModel(initial: Int) :
    CoroutineViewModel<Int, Unit, Nothing>(initial) {

    override fun reduceState(oldState: Int, intent: Unit): Flow<Int> {
        return flowOf(oldState + 1)
    }
}