package com.maksimisu.asms.di

import android.content.Context
import androidx.room.Room
import com.maksimisu.asms.data.local.ASMSDatabase
import com.maksimisu.asms.domain.repository.ASMSRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideAppData(@ApplicationContext applicationContext: Context): ASMSDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ASMSDatabase::class.java,
            "asms_database"
        ).build()
    }

    @Provides
    fun provideASMSDao(asmsDatabase: ASMSDatabase): ASMSRepository {
        return asmsDatabase.asmsDao()
    }
}