package com.georgeci.moneysurfer.receiptlist.arch

import com.georgeci.moneysurfer.domain.entity.Receipt

data class ReceiptListState(
    val items: List<Receipt>
)