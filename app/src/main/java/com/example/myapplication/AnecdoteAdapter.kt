package com.example.myapplication

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.API.Anecdote
import com.example.myapplication.databinding.LayoutItemBinding

class AnecdoteAdapter(var anecdotes: MutableList<Anecdote>) :
    RecyclerView.Adapter<AnecdoteAdapter.AnecdoteViewHolder>() {

//    fun setAnecdoteList(newList: List<Anecdote>) {
//        val diffUtilCallback = AnecdoteDiffUtilCallback(this.anecdotes, newList)
//        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
//
//        this.anecdotes = newList
//        println("!!!!ADAPTER GET LIST $newList")
//        diffResult.dispatchUpdatesTo(this)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnecdoteViewHolder {
//        return AnecdoteViewHolder(
//            LayoutInflater
//                .from(parent.context)
//                .inflate(
//                    R.layout.layout_item,
//                    parent,
//                    false
//                )
//        )
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnecdoteViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: AnecdoteViewHolder, position: Int) {
//        holder.bind(anecdotes[position])
        var anecdote : Anecdote = anecdotes[position]
        holder.binding.postText.text = Html.fromHtml(anecdote.elementPureHtml, Html.FROM_HTML_MODE_LEGACY)
        holder.binding.siteText.text = anecdote.site
    }

    override fun getItemCount(): Int = anecdotes.size

    inner class AnecdoteViewHolder(val binding : LayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {

//        @RequiresApi(Build.VERSION_CODES.N)
//        fun bind(anecdote: Anecdote) {
//            itemView.post_text.text =
//                Html.fromHtml(anecdote.elementPureHtml, Html.FROM_HTML_MODE_LEGACY)
//            itemView.site_text.text = anecdote.site
//        }
    }
    fun setAnecdote(anecdotes: List<Anecdote>) {
        this.anecdotes.clear()
        this.anecdotes.addAll(anecdotes)
        println(anecdotes + ".....")
        notifyDataSetChanged()
    }
}