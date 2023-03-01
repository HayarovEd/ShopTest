package com.edurda77.shoptest.di

import android.app.Application
import androidx.room.Room
import com.edurda77.data.local.UsersDatabase
import com.edurda77.data.remote.ShopApi
import com.edurda77.data.repository.ShopRepositoryImpl
import com.edurda77.domain.repository.ShopRepository
import com.edurda77.domain.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UsersDatabase {
        return Room.databaseBuilder(
            app,
            UsersDatabase::class.java,
            UsersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UsersDatabase, shopApi: ShopApi): ShopRepository {
        return ShopRepositoryImpl(db.userDao, shopApi)
    }

    @Provides
    @Singleton
    fun provideShopApi(): ShopApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ShopApi::class.java)
    }



    /*@Provides
    @Singleton
    fun provideUserUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getUsers = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }*/
}