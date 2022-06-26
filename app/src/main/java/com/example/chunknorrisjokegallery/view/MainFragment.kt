package com.example.chunknorrisjokegallery.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chunknorrisjokegallery.R
import com.example.chunknorrisjokegallery.adapter.JokeAdapter
import com.example.chunknorrisjokegallery.databinding.FragmentMainBinding
import com.example.chunknorrisjokegallery.network.Results
import com.example.chunknorrisjokegallery.utils.JokeBox
import com.example.chunknorrisjokegallery.utils.fixSpecialChars
import com.example.chunknorrisjokegallery.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val jokeAdapter by lazy { JokeAdapter() }
    private val viewModel: MainViewModel by activityViewModels()

    private var isJokeListFilled: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.jokeListRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = jokeAdapter
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1))
                        getMoreJokes()
                }
            })
        }

        binding.randomJokeBtn.setOnClickListener {
            getOneJoke()
        }

        binding.jokeListEnable.setOnClickListener {
            binding.jokeListEnable.visibility = View.INVISIBLE
            binding.jokeListLayout.apply {
                this.visibility = View.VISIBLE
                this.scaleY = 0f
                this.translationY = (this.parent as View).height / 2.0f
                animate()
                    .translationY(0f)
                    .scaleY(1f)
                    .setDuration(300L)
                    .setListener(null)
            }
            binding.jokeBtnLayout.apply {
                this.scaleY = 1f
                this.translationY = 0f
                animate()
                    .translationY(-this.height / 2.0f)
                    .scaleY(0f)
                    .setDuration(300L)
                    .setListener(object: AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            this@apply.visibility = View.GONE
                            binding.jokeListDisable.visibility = View.VISIBLE
                        }
                    })
            }

            // This includes the initial 20. The observer will handle the difference.
            getMoreJokes()
        }

        binding.jokeListDisable.setOnClickListener {
            binding.jokeListDisable.visibility = View.INVISIBLE
            binding.jokeListLayout.apply {
                this.scaleY = 1f
                this.translationY = 0f
                animate()
                    .translationY(this.height / 2.0f)
                    .scaleY(0f)
                    .setDuration(300L)
                    .setListener(object: AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            this@apply.visibility = View.GONE
                            binding.jokeListEnable.visibility = View.VISIBLE
                        }
                    })
            }
            binding.jokeBtnLayout.apply {
                this.visibility = View.VISIBLE
                this.scaleY = 0f
                this.translationY = -(this.parent as View).height / 2.0f
                animate()
                    .translationY(0f)
                    .scaleY(1f)
                    .setDuration(300L)
                    .setListener(null)
            }

            isJokeListFilled = false
        }

        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is Results.LOADING -> {}
                is Results.SUCCESS -> {
                    // Just a cheeky way to distinguish between
                    //      getRandomOne() and getRandomMultiple()
                    if (it.response.count() == 1) {
                        JokeBox.speak(requireContext(),
                            it.response[0].joke.fixSpecialChars())
                        doneGettingOneJoke()
                    } else {
                        if (!isJokeListFilled) {
                            jokeAdapter.updateList(it.response)
                            isJokeListFilled = true
                        } else jokeAdapter.addToList(it.response)
                        doneGettingJokes()
                    }

                }
                is Results.ERROR -> {
                    doneGettingOneJoke()
                    doneGettingJokes()
                    Toast.makeText(requireContext(), it.error.localizedMessage, 7000).show()
                }
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    // These are for he never-ending joke list.
    private fun getMoreJokes() {
        viewModel.getRandomMultiple()
        binding.jokeListLoading.visibility = View.VISIBLE
    }
    private fun doneGettingJokes() {
        binding.jokeListLoading.visibility = View.GONE
    }

    // These are for the joke button.
    private fun getOneJoke() {
        binding.randomJokeBtn.text = getString(R.string.joke_btn_loading_label)
        viewModel.getRandomOne()
        binding.randomJokeBtn.isEnabled = false
    }
    private fun doneGettingOneJoke() {
        binding.randomJokeBtn.text = getString(R.string.joke_btn_label)
        binding.randomJokeBtn.isEnabled = true
    }
}