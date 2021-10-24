package com.example.myapplication

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.API.Anecdote
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.repository.AnecdoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnectodeViewModel : ViewModel() {
    var repository = AnecdoteRepository()
    var list = MutableLiveData<List<Anecdote>?>()
    fun getLive(context: MainActivity) : MutableLiveData<List<Anecdote>?> {
        var r = mutableListOf<Anecdote>()
        CoroutineScope(Dispatchers.IO).launch {

            repository.getListNetwork(context)
//            repository.list.observe(context, {
//                if (it != null) {
//                    repository.saveList(it, context)
//                }
//            })

            repository.getListDB(context)?.let { r.addAll(it) }
        }
        list.value = r
        return list
    }
}