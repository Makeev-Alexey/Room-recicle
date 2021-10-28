package com.example.myapplication.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.API.Anecdote
import com.example.myapplication.API.Controller
import com.example.myapplication.DAO.AnecdoteDao
import com.example.myapplication.DAO.AnecdoteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AnecdoteRepository(application: Application) {
    lateinit var anecdoteDao: AnecdoteDao

    init {
        val database = AnecdoteDatabase.getDatabase(application)
            anecdoteDao = database!!.getAnecdoteDao()
    }
    private suspend fun addData(anecdoteList: List<Anecdote>) {
        anecdoteDao.addAnecdotes(anecdoteList)
    }
    private suspend fun deleteAllData() {
        anecdoteDao.deleteAllAnecdotes()
    }
    suspend fun getAllAnecdotes(): List<Anecdote> {
        val list = Controller.getApiArguments().getAnecdotesCourutine("new anekdot", 10)
        return list

    }
    suspend fun getAllAnecdotesDB(): List<Anecdote> {
        return anecdoteDao.getAllAnecdotes()
    }

    suspend fun loadAndPutInDatabase() {

//!!!!!!!!!!!!!
        val list = Controller.getApiArguments().getAnecdotesCourutine("new anekdot", 10)

        deleteAllData()
        addData(list)
    }
}