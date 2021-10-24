package com.example.myapplication.DAO

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.API.Anecdote

@Database(entities = [Anecdote::class], version = 1)
abstract class AnecdoteDatabase : RoomDatabase() {

    abstract fun getAnecdoteDao(): AnecdoteDao

    companion object {
        var database: AnecdoteDatabase? = null

        fun getDatabase(context: Context): AnecdoteDatabase? {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    AnecdoteDatabase::class.java,
                    "anecdote_database"
                ).build()
            }
            return database
        }
    }
}