package com.example.chunknorrisjokegallery.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chunknorrisjokegallery.databinding.JokeCardBinding
import com.example.chunknorrisjokegallery.model.Joke

class JokeAdapter (
    private val jokeList: MutableList<JokeViewHolder> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(joke_list: List<Joke>) {
        notifyDataSetChanged()
    }

    inner class JokeViewHolder(
        private val binding: JokeCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}