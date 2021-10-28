package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.API.Anecdote
import com.example.myapplication.API.Controller
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.repository.AnecdoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnectodeViewModel(var repository : AnecdoteRepository) : ViewModel() {

    var liveData = MutableLiveData<List<Anecdote>>()
    fun loadAndPutInDatabase() {
        viewModelScope.launch(Dispatchers.IO) { repository.loadAndPutInDatabase() }
    }
    fun list(){
        viewModelScope.launch(Dispatchers.IO) {

            liveData.postValue(repository.getAllAnecdotesDB())
        }
    }
    fun listDB(){
        viewModelScope.launch(Dispatchers.IO) {

            liveData.postValue(repository.getAllAnecdotesDB())
        }
    }
}