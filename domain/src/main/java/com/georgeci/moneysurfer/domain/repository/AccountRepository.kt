package com.georgeci.moneysurfer.domain.repository

import com.georgeci.moneysurfer.domain.entity.Account
import com.georgeci.moneysurfer.domain.entity.insert.AccountInsert
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getAccounts(): Flow<List<Account>>
    suspend fun getAccountById(id: Long): Flow<Account?>
    suspend fun insertAccount(account: AccountInsert)
}