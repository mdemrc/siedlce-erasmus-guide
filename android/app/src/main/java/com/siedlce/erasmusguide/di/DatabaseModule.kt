package com.siedlce.erasmusguide.di

import android.content.Context
import androidx.room.Room
import com.siedlce.erasmusguide.data.local.AppDatabase
import com.siedlce.erasmusguide.data.local.dao.CheckedItemDao
import com.siedlce.erasmusguide.data.local.dao.PoiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun providePoiDao(db: AppDatabase): PoiDao = db.poiDao()

    @Provides
    fun provideCheckedItemDao(db: AppDatabase): CheckedItemDao = db.checkedItemDao()
}
