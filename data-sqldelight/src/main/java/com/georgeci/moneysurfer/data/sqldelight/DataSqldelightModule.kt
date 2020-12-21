package com.georgeci.moneysurfer.data.sqldelight

import com.georgeci.moneysurfer.data.AccountQueries
import com.georgeci.moneysurfer.data.Database
import com.georgeci.moneysurfer.data.ReceiptQueries
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSqldelightModule {
    @Provides
    fun provideDatabase(driver: SqlDriver): Database {
        return Database(driver)
    }

    @Provides
    fun provideAcDatabase(db: Database): AccountQueries {
        return db.accountQueries
    }

    @Provides
    fun provideRecDatabase(db: Database): ReceiptQueries {
        return db.receiptQueries
    }
}