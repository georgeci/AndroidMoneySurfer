package com.georgeci.moneysurfer.domain.sqldelight

import com.georgeci.moneysurfer.data.ReceiptQueries
import com.georgeci.moneysurfer.domain.entity.Receipt
import com.georgeci.moneysurfer.domain.entity.insert.ReceiptInsert
import com.georgeci.moneysurfer.domain.sqldelight.mapper.receiptMapper
import com.georgeci.moneysurfer.domain.repository.ReceiptRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptQueries: ReceiptQueries
) : ReceiptRepository {
    override suspend fun getAllReceipt(): Flow<List<Receipt>> {
        return receiptQueries.selectAll(::receiptMapper).asFlow()
            .mapToList()
    }

    override suspend fun getReceiptById(id: Long): Flow<Receipt?> {
        return receiptQueries.selectById(id, ::receiptMapper).asFlow()
            .mapToOneOrNull()
    }

    override suspend fun getReceiptByAccountId(accountId: Long): Flow<List<Receipt>> {
        return receiptQueries.selectByAccountId(accountId, ::receiptMapper).asFlow()
            .mapToList()
    }

    override suspend fun insertReceipt(receipt: ReceiptInsert) {
        receiptQueries.insert(receipt.summ, receipt.accountId)
    }
}