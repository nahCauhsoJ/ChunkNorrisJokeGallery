package com.example.chunknorrisjokegallery.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.chunknorrisjokegallery.adapter.JokeAdapter
import com.example.chunknorrisjokegallery.databinding.FragmentMainBinding
import com.example.chunknorrisjokegallery.network.Results
import com.example.chunknorrisjokegallery.utils.JokeBox
import com.example.chunknorrisjokegallery.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { JokeAdapter() }
    private val viewModel: MainViewModel by activityViewModels()
    private val transition1 by lazy {
        ChangeBounds().also { it.duration = 100L }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.randomJokeBtn.setOnClickListener {
            viewModel.getRandomOne()
            it.isEnabled = false
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
        }

        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is Results.LOADING -> {}
                is Results.SUCCESS -> {
                    // Just a cheeky way to distinguish between
                    //      getRandomOne() and getRandomMultiple()
                    if (it.response.count() == 1) {
                        JokeBox.Speak(requireContext(),
                            it.response[0].joke)
                        binding.randomJokeBtn.isEnabled = true
                    } else {
                        adapter.updateList(it.response) }

                }
                is Results.ERROR -> {
                    binding.randomJokeBtn.isEnabled = true
                }
            }
        }

        return binding.root
    }

}