package com.edurda77.shoptest.di

import android.app.Application
import androidx.room.Room
import com.edurda77.data.local.UsersDatabase
import com.edurda77.data.repository.ShopRepositoryImpl
import com.edurda77.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
    fun provideUserRepository(db: UsersDatabase): ShopRepository {
        return ShopRepositoryImpl(db.userDao)
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