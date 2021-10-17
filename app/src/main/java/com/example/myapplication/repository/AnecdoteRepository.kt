package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.API.Anecdote
import com.example.myapplication.API.Controller
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AnecdoteRepository {
    var list = MutableLiveData<List<Anecdote>?>()
    fun getList(){
        Controller.getApiArguments().getAnecdotes("new anekdot", 10).enqueue(object :
            Callback<List<Anecdote>>{
            override fun onResponse(
                call: Call<List<Anecdote>>,
                response: Response<List<Anecdote>>
            ) {
                if (response.isSuccessful)
                    list.postValue(response.body())
                println(response.body()?.plus("!"))
            }

            override fun onFailure(call: Call<List<Anecdote>>, t: Throwable) {
                list.postValue(null)
                println("...." + t.message)
            }
        })
    }
}