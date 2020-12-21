package com.georgeci.moneysurfer.domain.sqldelight.mapper

import com.georgeci.moneysurfer.domain.entity.Account

internal fun accountMapper(
    id: Long,
    name: String,
    number: Long
) = Account(
    id = id,
    name = name
)