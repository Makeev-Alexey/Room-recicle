package com.example.myapplication.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.API.Anecdote
import com.example.myapplication.API.Controller
import com.example.myapplication.DAO.AnecdoteDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AnecdoteRepository {
    var list = MutableLiveData<List<Anecdote>?>()
    fun getListNetwork(context : Context){
        Controller.getApiArguments().getAnecdotes("new anekdot", 10).enqueue(object :
            Callback<List<Anecdote>>{
            override fun onResponse(
                call: Call<List<Anecdote>>,
                response: Response<List<Anecdote>>
            ) {
                if (response.isSuccessful){
                    list.postValue(response.body())
                    saveList(response.body()!!, context)
                }

                println(response.body()?.plus("!"))
            }

            override fun onFailure(call: Call<List<Anecdote>>, t: Throwable) {
                list.postValue(null)
                println("...." + t.message)
            }
        })
    }
    suspend fun getListDB(context : Context): List<Anecdote>? {
        return AnecdoteDatabase.getDatabase(context)?.getAnecdoteDao()?.getAllAnecdotes()
    }
    fun saveList(list : List<Anecdote>, context: Context){
        AnecdoteDatabase.getDatabase(context)?.getAnecdoteDao()?.addAnecdotes(list)
    }
}