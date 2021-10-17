package com.example.myapplication.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Controller {
    companion object {
        fun getApiArguments(): AnecdoteAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://umorili.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(AnecdoteAPI::class.java)
        }
    }
}