package com.georgeci.moneysurfer.domain.repository

import com.georgeci.moneysurfer.domain.entity.Receipt
import com.georgeci.moneysurfer.domain.entity.insert.ReceiptInsert
import kotlinx.coroutines.flow.Flow

interface ReceiptRepository {
    suspend fun getAllReceipt(): Flow<List<Receipt>>
    suspend fun getReceiptById(id: Long): Flow<Receipt?>
    suspend fun getReceiptByAccountId(accountId: Long): Flow<List<Receipt>>
    suspend fun insertReceipt(receipt: ReceiptInsert)
}