package com.example.chunknorrisjokegallery.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.chunknorrisjokegallery.databinding.FragmentEditBinding
import com.example.chunknorrisjokegallery.viewmodel.MainViewModel

class EditFragment : Fragment() {

    private val binding by lazy { FragmentEditBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.jokeConfig.first_name?.let {
            binding.editFirstNameInput.setText(it)
        }
        viewModel.jokeConfig.last_name?.let {
            binding.editLastNameInput.setText(it)
        }
        binding.editFilterExplicit.isChecked = viewModel.jokeConfig.no_explicit
        binding.editFilterNerdy.isChecked = viewModel.jokeConfig.no_nerdy
    }

    override fun onPause() {
        viewModel.jokeConfig.first_name =
            if (binding.editFirstNameInput.text.isNotBlank())
                binding.editFirstNameInput.text.toString()
            else
                null

        viewModel.jokeConfig.last_name =
            if (binding.editLastNameInput.text.isNotBlank())
                binding.editLastNameInput.text.toString()
            else
                null

        viewModel.jokeConfig.no_explicit = binding.editFilterExplicit.isChecked

        viewModel.jokeConfig.no_nerdy = binding.editFilterNerdy.isChecked

        super.onPause()
    }
}