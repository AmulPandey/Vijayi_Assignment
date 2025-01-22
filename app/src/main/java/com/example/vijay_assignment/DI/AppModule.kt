package com.example.vijay_assignment.DI

import com.example.vijay_assignment.Networking.WatchModeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWatchModeApi(): WatchModeApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.watchmode.com/v1/releases/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WatchModeApiService::class.java)
    }
}
