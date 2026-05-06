package com.siedlce.erasmusguide.di

import com.siedlce.erasmusguide.data.remote.NoopRemotePoiSource
import com.siedlce.erasmusguide.data.remote.RemotePoiSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Binds the default offline (no-op) remote source. Swap to a Firebase-backed
 * implementation by changing this single binding once Firebase is configured.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    @Singleton
    abstract fun bindRemotePoiSource(impl: NoopRemotePoiSource): RemotePoiSource
}
