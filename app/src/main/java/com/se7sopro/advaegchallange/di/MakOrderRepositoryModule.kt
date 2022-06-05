package com.se7sopro.advaegchallange.di


import com.se7sopro.advaegchallange.data.repo.photoData.PhotoDataRepository
import com.se7sopro.advaegchallange.data.repo.photoData.PhotoDataRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MakOrderRepositoryModule {
    @Binds
    abstract fun providesCurrencyDataRepo(currencyDataRepositoryImp: PhotoDataRepositoryImp): PhotoDataRepository

}