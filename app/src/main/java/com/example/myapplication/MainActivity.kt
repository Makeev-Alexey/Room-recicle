package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.myapplication.API.Anecdote
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.repository.AnecdoteRepository

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AnectodeViewModel
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var repository = AnecdoteRepository(application)
        viewModel = ViewModelProvider(this, AnecdoteViewModelFactory(repository)).get(AnectodeViewModel::class.java)
        var adapter = AnecdoteAdapter(mutableListOf())
        checkConnection(viewModel)
        binding.recicle.adapter = adapter
        viewModel.liveData.observe(this, {adapter.setAnecdote(it)})
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun checkConnection(viewModel : AnectodeViewModel){
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .build()
        var callback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
//                viewModel.isConnected.set(true)
                viewModel.loadAndPutInDatabase()
                viewModel.list()
                Toast.makeText(baseContext,"Network is available", Toast.LENGTH_LONG).show()
            }

            override fun onLost(network: Network) {
//                viewModel.isConnected.set(false)
                viewModel.listDB()
                Toast.makeText(baseContext,"Network was lost", Toast.LENGTH_LONG).show()
            }
        }
//!!!!!!!
        try {
            manager.unregisterNetworkCallback(callback)
        }catch (e: Exception){
        }

        manager.registerNetworkCallback(networkRequest,callback)
    }
}