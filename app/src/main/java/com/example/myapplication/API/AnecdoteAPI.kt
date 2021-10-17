package com.example.myapplication.API

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface AnecdoteAPI {
    @GET("/api/get")
    fun getAnecdotes(@Query("name") name: String, @Query("num") num: Int): Call<List<Anecdote>>
    @GET("/api/get")
    suspend fun getAnecdotesCourutine(@Query("name") name: String, @Query("num") num: Int): List<Anecdote>
}