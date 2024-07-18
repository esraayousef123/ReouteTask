package com.example.routetask

import com.example.routetask.ProductRepository.ProductFromApiRepository
import com.example.routetask.ProductRepository.ProductFromApiRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Provides
        fun provideProductRepositoryImpObj(): ProductFromApiRepository {
            return ProductFromApiRepositoryImp()
        }
    }
