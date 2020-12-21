package com.georgeci.moneysurfer.domain.sqldelight

import com.georgeci.moneysurfer.data.AccountQueries
import com.georgeci.moneysurfer.domain.entity.Account
import com.georgeci.moneysurfer.domain.entity.insert.AccountInsert
import com.georgeci.moneysurfer.domain.sqldelight.mapper.accountMapper
import com.georgeci.moneysurfer.domain.repository.AccountRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import dagger.Binds
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountQueries: AccountQueries
) : AccountRepository {
    override suspend fun getAccounts(): Flow<List<Account>> {
        return accountQueries.selectAll(::accountMapper).asFlow()
            .mapToList()
    }

    override suspend fun getAccountById(id: Long): Flow<Account?> {
        return accountQueries.selectById(id, ::accountMapper).asFlow()
            .mapToOneOrNull()
    }

    override suspend fun insertAccount(account: AccountInsert) {
        accountQueries.insert(account.name, account.number)
    }
}