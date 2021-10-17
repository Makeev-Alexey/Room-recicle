package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.API.Anecdote
import com.example.myapplication.repository.AnecdoteRepository

class AnectodeViewModel : ViewModel() {
    var repository = AnecdoteRepository()
    fun getLive() : MutableLiveData<List<Anecdote>?> {
        repository.getList()
        return repository.list
    }
}