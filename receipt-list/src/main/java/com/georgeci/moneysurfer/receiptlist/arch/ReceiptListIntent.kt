package com.georgeci.moneysurfer.receiptlist.arch

import com.georgeci.moneysurfer.domain.entity.Receipt

sealed class ReceiptListIntent {
    data class ReceiptClick(val receipt: Receipt) : ReceiptListIntent()
    object Create : ReceiptListIntent()
}
