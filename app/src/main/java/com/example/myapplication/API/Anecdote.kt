package com.example.myapplication.API

data class Anecdote (
    val site: String,
    val name: String,
    val desc: String,
    val link: String?,
    val elementPureHtml: String,
    var id: Long = 0
)