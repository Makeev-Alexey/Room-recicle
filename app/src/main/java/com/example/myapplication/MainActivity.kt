package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.myapplication.API.Anecdote
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var viewModel = ViewModelProvider(this).get(AnectodeViewModel::class.java)
        var adapter = AnecdoteAdapter(mutableListOf())
        binding.recicle.adapter = adapter
        viewModel.getLive(this).observe(this, Observer{adapter.setAnecdote(it as MutableList<Anecdote>)})
    }
}