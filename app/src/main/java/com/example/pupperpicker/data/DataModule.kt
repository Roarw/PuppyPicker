package com.example.pupperpicker.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

// TODO: Delete this class?

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun getRandomDog(repo: DataRepositoryImpl): DataRepository

    /*@Provides
    fun getRandomDogImage(response: Response): Bitmap  {
        val url = URL(response.message)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }*/
}