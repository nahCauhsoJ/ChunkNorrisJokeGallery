package com.example.chunknorrisjokegallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chunknorrisjokegallery.databinding.JokeCardBinding
import com.example.chunknorrisjokegallery.model.Joke
import com.example.chunknorrisjokegallery.utils.fixSpecialChars

class JokeAdapter (
    private val jokeList: MutableList<Joke> = mutableListOf()
) : RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    fun updateList(joke_list: List<Joke>) {
        jokeList.clear()
        jokeList.addAll(joke_list)
        notifyDataSetChanged()
    }

    fun addToList(joke_list: List<Joke>) {
        val posStart: Int = jokeList.size
        jokeList.addAll(joke_list)
        notifyItemRangeInserted(posStart, joke_list.size)
    }

    inner class JokeViewHolder(
        private val binding: JokeCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            binding.jokeCardText.text = joke.joke.fixSpecialChars()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder =
        JokeViewHolder(
            JokeCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokeList[position])
    }

    override fun getItemCount(): Int = jokeList.size

}