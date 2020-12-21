package com.georgeci.moneysurfer.domain.entity

data class Receipt(
    val id: Long,
    val summ: Double,
    val accountId: Long
)