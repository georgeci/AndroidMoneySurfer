package com.georgeci.moneysurfer.domain.sqldelight.mapper

import com.georgeci.moneysurfer.domain.entity.Receipt

internal fun receiptMapper(
    id: Long,
    summ: Double,
    accountId: Long
) = Receipt(
    id = id,
    summ = summ,
    accountId = accountId
)